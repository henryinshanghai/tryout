--  2021年8月份(#1) 所有练习过题目的总用户数(#2) 和 练习过题目的总次数(#3)
select
  count(distinct device_id) as did_cnt, -- #2 统计人数时，由于一人可能做多题 所以使用distinct关键字来去重
  count(question_id) as question_cnt -- #3
from
  question_practice_detail
where
  year(date) = 2021
  and month(date) = 8 -- #1 没有group by时，也可以使用 count()对整个组的记录进行计数

