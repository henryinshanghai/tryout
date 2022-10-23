-- 取出平均发贴数(#1)低于5的学校 或 平均回帖数(#2)小于20的学校(#3)
select
    university,
    avg(question_cnt) as avg_question_cnt,
    avg(answer_cnt) as avg_answer_cnt
from user_profile
group by university -- 按照university分组后,其他的字段也能查询出来 #3
having avg_question_cnt < 5 or avg_answer_cnt < 20; -- 分组后的过滤使用 having关键字(可以直接使用聚合字段) #1 + #2