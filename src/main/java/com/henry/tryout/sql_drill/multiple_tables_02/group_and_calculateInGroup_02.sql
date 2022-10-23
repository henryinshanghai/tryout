-- 每个学校 答过题的用户的 平均答题数量情况
SELECT university,
       count(*)/count(DISTINCT q.device_id) avg_answer_cnt -- 答题数量 / 学生数量
FROM question_practice_detail q -- 把问题表作为主表
LEFT JOIN user_profile u ON u.device_id=q.device_id
GROUP by university -- 以大学作为分组