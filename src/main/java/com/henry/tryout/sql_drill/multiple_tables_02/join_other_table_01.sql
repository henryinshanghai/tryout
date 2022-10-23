-- [#21] 查看所有来自浙江大学（#1）的用户题目回答明细情况（#2）
-- 手段2：连接查询
select
    answer.device_id,
    answer.question_id,
    answer.result -- #2
from question_practice_detail answer
join user_profile user
on user.device_id = answer.device_id -- 两张表之间的关联关系：user's device_id = questions' device_id
and user.university = '浙江大学' -- #1
