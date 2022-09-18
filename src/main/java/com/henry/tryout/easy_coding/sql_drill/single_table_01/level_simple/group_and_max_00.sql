-- 复旦大学学生(#1) gpa最高值(#2)是多少
select
    max(gpa) as gpa -- #2-2
from user_profile user
where university = '复旦大学' -- #1
group by university -- #2-1