-- 每个学校(#1) 答过题的用户(#2) 平均答题数量情况(#3)
select
  user.university,
  count(question_id) / count(distinct(answer.device_id)) as avg_answer_cnt -- #3 平均答题数量情况 = 答题总数量 / 答题总人数
from
  question_practice_detail answer -- 答题明细表
join user_profile user -- #2 join用户表 来 查询到university字段信息
on answer.device_id = user.device_id
group by
  user.university -- #1 按照university分组 来 查询每个学校