-- 每个学校(#1 ) 答过题的用户(#2) 平均答题数量情况(#3)
select
  university,
  count(question_id) / count(distinct(answer.device_id)) as avg_answer_cnt -- 平均答题数量情况(#3)
from
  question_practice_detail answer -- 答过题的用户(#2)
join user_profile user on answer.device_id = user.device_id
group by
  university -- 每个学校(#1)