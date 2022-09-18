-- 了解浙江大学的用户(#1) 在不同难度题目下(#2) 答题的正确率情况(#3)
select
    difficult_level,
    sum(if(answer.result = 'right', 1, 0)) / count(*) as correct_rate -- #3
from
    user_profile user
join question_practice_detail answer on answer.device_id = user.device_id
join question_detail question on question.question_id = answer.question_id
where user.university = '浙江大学' -- #1
group by question.difficult_level -- #2
order by correct_rate asc