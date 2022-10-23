-- 查找后降序排列
select
device_id, gpa, age
from user_profile
order by gpa desc, age desc -- 注：这里每个字段都需要添加desc