-- 找到每个学校（#1） gpa最低的同学（#2）
-- 手段1：先分组查询出最小gpa，再用原始表连接子查询 - 从而得到原始表中legit的数据行
select
    device_id,
    user.university,
    user.gpa -- select列表时，指定 字段来自于哪张表
from 
user_profile as user
join (
    select 
        university,
        min(gpa) as gpa -- #2 在子查询中查询出 每个university子组中最小的gpa是多少
    from user_profile
    group by university -- #1 先用分组查询，得到 每个大学中gpa最小的记录 - 作为子查询
) as group_result 
on group_result.university = user.university
and group_result.gpa = user.gpa -- #3 从user表中 join子查询 - 用以找到当前表中的legit记录行
order by university
