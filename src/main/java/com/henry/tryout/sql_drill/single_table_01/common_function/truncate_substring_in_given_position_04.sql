-- 统计每个年龄(#1)的用户 分别有多少参赛者(#2)
select
  SUBSTRING_INDEX(SUBSTRING_INDEX(profile, ',', -2), ',', 1) as age,
  count(device_id) as number -- #2
From
  user_submit
Group by
  age -- #1

device_id profile blog_url
2138    180cm,75kg,27,male http:/ur/bigboy777
3214	165cm,45kg,26,female http:/url/kittycc
6543	178cm,65kg,25,male http:/url/tiger
4321	171cm,55kg,23,female http:/url/uhksd
2131	168cm,45kg,22,female http:/url/sydney