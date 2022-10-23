-- 查看不同大学(#1)的用户 平均发帖情况(#2)，并期望 结果按照平均发帖情况进行升序排列(#3)
select
    university,
    avg(question_cnt) avg_question_cnt -- #2 平均发帖情况
from user_profile
group by university -- #1 先使用 university字段分组
order by avg_question_cnt asc -- #3 再使用 平均答题数量 排序(having中可以直接使用聚合字段)