-- 复旦大学（#1）的每个用户（#2）在8月份（#3）练习的 总题目数和回答正确的题目数情况（#4）
select
    user.device_id,
    user.university,
    count(question_id) as question_cnt,
    sum(if(answer.result = 'right', 1, 0)) as right_question_cnt -- #4
from user_profile user
left join question_practice_detail answer -- #3 为了保留没有匹配到结果的user(没有答过题目的user)，这里使用left join
on answer.device_id = user.device_id and month(answer.date) = '08' -- #3 在join子句添加条件(why?) - 先于where子句执行。
where user.university = '复旦大学' --#1 where子句(用于过滤结果集)  note: 把不同的条件 放在 不同的地方，会导致不同的查询结果
group by user.device_id -- #2 每个用户
