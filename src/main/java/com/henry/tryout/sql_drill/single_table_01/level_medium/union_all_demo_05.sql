--  查找山东大学(#1) 或者 性别为男生(#2)的信息
select
  device_id,
  gender,
  age,
  gpa
from
  user_profile
where
  university = '山东大学' -- #01 筛选条件：山东大学
union all -- 特性：结果不会去除重复记录
select
  device_id,
  gender,
  age,
  gpa
from
  user_profile
where
  gender = 'male' -- #02 筛选条件：male