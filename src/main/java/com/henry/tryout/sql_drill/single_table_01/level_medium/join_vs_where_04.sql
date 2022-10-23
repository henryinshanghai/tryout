-- 查看 参加了答题的(#1) 山东大学的用户(#2) 在不同难度下(#3) 的平均答题题目数(#4)
select
  university,
  difficult_level,
  count(*) / count(distinct(answer.device_id)) -- #4 子组中answer的总数 / 子组中所有user的数量
from
question_practice_detail answer -- #1 参加了答题的
join user_profile user on user.device_id = answer.device_id
and user.university = '山东大学' -- #2 山东大学的用户
join question_detail question on question.question_id = answer.question_id
group by
  university,
  difficult_level -- #3 不同难度