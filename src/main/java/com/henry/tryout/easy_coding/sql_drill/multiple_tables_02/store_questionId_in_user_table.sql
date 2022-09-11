-- 查看所有来自浙江大学的用户题目回答明细情况
select
    user.device_id,
    questions.question_id,
    questions.result -- 答题明细的信息只能从 question表中获取
from
    user_profile user,
    question_practice_detail questions
where
    user.device_id = questions.device_id -- user表中的 device_id 与 questions表中的 device_id列值是一样的
and
    user.university = '浙江大学'