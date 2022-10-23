--  找出每个学校GPA最低的同学
select
    device_id,
    university,
    gpa
from (
    select *, -- #3 因为是用在from子句中，所以子查询中必须使用 * 查询出所有的字段
    row_number() over (partition by university order by gpa) as rn -- #1 使用窗口函数，得到记录在子组中的排名
    from user_profile
) as ranking
where rn=1 -- #2 排定第一位的 就是gpa最小的记录
order by university

-- https://zhuanlan.zhihu.com/p/92654574
-- 因为窗口函数是对where或者group by子句处理后的结果进行操作，所以窗口函数原则上只能写在select子句中。
-- row_number函数：这个例子中是5位，6位，7位，8位，也就是不考虑并列名次的情况。比如前3名是并列的名次，排名是正常的1，2，3，4。