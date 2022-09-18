-- 分别计算出每个学校（#1）每种性别（#2）的用户数、30天内平均活跃天数和平均发帖数量（#3）
select
    gender, university,
    count(*) as user_num,
    avg(active_days_within_30) as avg_active_day,
    avg(question_cnt) as avg_question_cnt -- #3
from user_profile user
group by university, gender -- #1 + #2