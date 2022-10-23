-- 找到每个学校（#1） gpa最低的同学（#2）
select
    device_id,
    user.university,
    user.gpa -- select列表时，指定 字段来自于哪张表
from 
user_profile as user
join (
    select 
        university,
        min(gpa) as gpa -- #2
    from user_profile
    group by university -- #1 先用分组查询，得到 每个大学中gpa最小的记录 - 作为子查询
) as group_result 
on group_result.university = user.university
and group_result.gpa = user.gpa -- #3 从user表中 join子查询 - 用以生成匹配的结果集
order by university
