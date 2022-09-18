--  计算男生人数（#1） 以及 平均GPA（#2）
select
    count(*) as male_num, -- #1
    avg(gpa) as avg_gpa -- #2
from user_profile user
where gender = 'male'
group by gender -- #0 先使用性别进行分组