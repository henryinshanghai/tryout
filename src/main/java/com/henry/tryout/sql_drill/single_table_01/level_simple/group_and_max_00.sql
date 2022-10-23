-- 复旦大学学生(#1) gpa最高值(#2)是多少
select
    max(gpa) as gpa -- #2-2 对分组后的gap字段进行最值处理
from user_profile user
where university = '复旦大学' -- #1
group by university -- #2-1 使用university进行分组