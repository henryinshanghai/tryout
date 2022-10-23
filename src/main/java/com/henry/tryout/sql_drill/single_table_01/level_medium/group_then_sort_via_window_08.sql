--  找出每个学校（#1） GPA最低（#2）的同学（#3）
-- 手段2：窗口函数 - 作用：在每组内排名。
select
    device_id,
    university,
    gpa
from (
    select *, -- #3 *表示原始表中的所有字段 - 包含同学的信息
    row_number() over (partition by university order by gpa) as rn -- 使用窗口函数，得到"记录在子组（#1）中的排名（#2-1）"。并记录为新的字段rn
    from user_profile
) as with_ranking -- 把 包含"记录在子组中的名词字段"的结果集 作为from子句的数据源
where rn=1 -- #2-2 找到子查询结果集中排名字段=1的记录 aka gpa最小的记录
order by university

-- https://zhuanlan.zhihu.com/p/92654574
-- 因为窗口函数是对where或者group by子句处理后的结果进行操作，所以窗口函数原则上只能写在select子句中。
-- row_number函数：这个例子中是5位，6位，7位，8位，也就是不考虑并列名次的情况。比如前3名是并列的名次，排名是正常的1，2，3，4。