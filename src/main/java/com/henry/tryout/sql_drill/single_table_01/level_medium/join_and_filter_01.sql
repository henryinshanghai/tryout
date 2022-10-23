-- 查看所有来自浙江大学(#1) 的用户 题目回答明细情况(#2)
select
    answer.device_id,
    answer.question_id,
    answer.result
from question_practice_detail answer -- #2 题目回答明细情况
join user_profile user on user.device_id = answer.device_id and user.university = '浙江大学' -- 来自浙江大学(#1) 的用户