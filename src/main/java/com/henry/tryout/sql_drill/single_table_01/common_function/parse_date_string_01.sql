-- 计算出 2021年8月(#1) 每天(#2) 用户练习题目的数量(#3)
select
  day(date) as day,
  count(question_id) as question_cnt
from
  question_practice_detail -- #3
where
  month(date) = 8
  and year(date) = 2021 -- #1 获取date中的年月日   手段：date(date_str)
group by
  date -- #2 每天, group by date