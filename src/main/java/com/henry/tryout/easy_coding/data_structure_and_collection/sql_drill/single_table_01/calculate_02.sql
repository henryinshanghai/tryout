--  计算男生人数以及平均GPA
select count(*), avg(gpa)
from user_profile
where gender = 'male'