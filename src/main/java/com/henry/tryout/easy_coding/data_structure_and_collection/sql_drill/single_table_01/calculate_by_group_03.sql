-- 分别计算出每个学校每种性别的用户数、30天内平均活跃天数和平均发帖数量
select
    gender, university,
    count(gender),
    avg(active_days_within_30),
    avg(question_cnt)
from user_profile
group by gender, university
