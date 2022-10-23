-- 计算用户(#1) 的 平均(#2) 次日留存率(#3)
SELECT
  -- #2 平均 = 在第二张表中出现的次数 / 在第一张表中出现的次数
  count(distinct q2.device_id, q2.date) / count(DISTINCT q1.device_id, q1.date) as avg_ret
from -- 自连接两张表 作用：在join时指定连接条件 - 将滞后一天日期的唯一id数量与前一天的进行错位
  question_practice_detail as q1
left join question_practice_detail as q2 on q1.device_id = q2.device_id -- 表的自连接
and DATEDIFF(q2.date, q1.date) = 1 -- #3 次日留存率 定义日期间隔为1天 这个条件必须要放在 join语句中 why? no clue

