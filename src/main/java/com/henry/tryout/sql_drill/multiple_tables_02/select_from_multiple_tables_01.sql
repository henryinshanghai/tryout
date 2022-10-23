-- [#21] 查看所有来自浙江大学（#1）的用户题目回答明细情况（#2）
-- 手段1：直接从多张表中查询（类似于交叉连接）
select
    user.device_id,
    questions.question_id,
    questions.result -- #2 答题明细的信息只能从 question表中获取
from
    user_profile user,
    question_practice_detail questions
where
    user.device_id = questions.device_id -- 两张表之间的关联关系：user's device_id = questions' device_id
and
    user.university = '浙江大学' -- #1