--  [#35] 浙江大学的用户（#1）在不同难度题目下（#2）答题的正确率情况（#3），请取出相应数据，并按照准确率升序输出（#4）
select
  difficult_level,
  sum(if(qpd.result = 'right', 1, 0)) / count(qpd.question_id) as correct_rate -- #3 手段1 - 正确率 = 累计得分 / 回答问题的总数量
  -- avg(if(qpd.result = 'right', 1, 0)) as correct_rate -- 手段2 - 对于 0和1组成的序列，序列的平均值 = 计算1的个数在整个序列中的占比
  -- count(if(qpd.result = 'right', 1, null)) / count(qpd.question_id) as correct_rate -- 手段3 - 累计正确题目的数量
from
  user_profile as up
  inner join question_practice_detail as qpd on up.device_id = qpd.device_id -- question_practice_detail：user的答题详情
  inner join question_detail as qd on qd.question_id = qpd.question_id -- question_detail：题目的具体难度
where
  up.university = '浙江大学' -- #1 join/on 先执行，where子句后执行
group by
  qd.difficult_level -- #2
order by
  correct_rate asc; -- #4