-- 查找后按照多列正向排序（#1）
SELECT device_id, gpa, age
FROM user_profile
ORDER BY gpa, age; -- #1