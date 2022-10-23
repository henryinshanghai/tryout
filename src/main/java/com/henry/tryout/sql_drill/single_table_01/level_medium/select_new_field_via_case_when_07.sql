-- 将用户 划分为 20岁以下，20-24岁，25岁及以上三个年龄段(#1)，分别查看不同年龄段(#2) 用户的明细情况(#3)
-- 根据 age列的值，新添加一列 age_cnt 手段：case...when
select
  device_id,
  gender,
  case
    when age < 20 then '20岁以下'
    when age < 25 and age >= 20 then '20-24岁'
    when age >= 25 then '25岁及以上'
    else '其他'
  end as age_cut -- #1 根据条件 select出自定义的新字段 手段：case...when 特征：新字段的值会根据case...when的执行情况确定
from
  user_profile -- 用户的明细情况(#3)
-- #2 这里题目没有要求进行分组后统计，因此不用group by