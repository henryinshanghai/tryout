-- 参加了答题的不同学校(#1)、不同难度的用户(#2) 平均答题量(#3)
select
  user.university,
  question.difficult_level,
  count(*) / count(distinct answer.device_id) -- 平均答题量(#3)
from
  question_practice_detail answer
join user_profile user on user.device_id = answer.device_id -- 关联user表，来查询出university字段
join question_detail question on answer.question_id = question.question_id -- 关联question表，来查询出difficult_level字段
group by
  university, -- 参加了答题的不同学校(#1)
  difficult_level -- 不同难度(#2)