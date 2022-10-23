--  21年8月份 练题总数
select
  count(distinct device_id) as did_cnt, -- 一人可能做多题 所以使用distinct关键字
  count(question_id) as question_cnt
from
  question_practice_detail
where
  year(date) = 2021
  and month(date) = 8 -- 没有group by时，也可以使用 count()对整个组的记录进行计数

