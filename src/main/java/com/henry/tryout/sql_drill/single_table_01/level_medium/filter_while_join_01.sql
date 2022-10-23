-- 查看所有来自浙江大学(#1) 的用户 题目回答明细情况(#2)
select
    answer.device_id, -- #2-3 题目回答明细情况
    answer.question_id,
    answer.result
from question_practice_detail answer
join user_profile user -- #2-1 连接用户表
on user.device_id = answer.device_id -- #2-2 与用户表的字段关联关系
and user.university = '浙江大学' -- #1 来自浙江大学 的用户 - 在join的过程中进行条件过滤