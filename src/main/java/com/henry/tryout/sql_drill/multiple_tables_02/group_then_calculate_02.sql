-- [#22] 每个学校（#1） 答过题的用户（#2）的 平均答题数量情况（#3）
SELECT 
    university,
    count(*)/count(DISTINCT question.device_id) avg_answer_cnt -- #3 多少人答过题目 = 答题数量 / 学生数量
FROM question_practice_detail question -- 把问题表作为主表
JOIN user_profile user ON user.device_id=question.device_id -- #2 user的device_id出现在了问题表中。
GROUP by university -- #1 以大学作为分组