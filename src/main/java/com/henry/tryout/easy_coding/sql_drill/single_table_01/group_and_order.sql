-- 查看不同大学的用户平均发帖情况，并期望 结果按照平均发帖情况进行升序排列
select
    university,
    avg(question_cnt) avg_question_cnt
from user_profile
group by university -- 先使用 university字段分组
order by avg_question_cnt asc -- 再使用 平均答题数量 排序