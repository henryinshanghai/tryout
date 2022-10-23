-- 计算25岁以上 和 以下(#1) 的用户数量(#2)
-- if（x=n,a,b）表示如果x=n,则返回a，否则就是b了
select
    if(age>=25,'25岁及以上','25岁以下') as age_cut, -- #1 select out 新字段age_cut - '25岁及以上','25岁以下'会成为字段的值
    count(device_id) as number -- #2 最后对子组进行统计
from user_profile
group by age_cut -- 再根据新的字段 age_cut进行分组