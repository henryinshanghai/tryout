-- 取出平均发贴数低于5的学校 或 平均回帖数小于20的学校
select
    university,
    avg(question_cnt) as avg_question_cnt,
    avg(answer_cnt) as avg_answer_cnt
from user_profile
group by university
having avg_question_cnt < 5 or avg_answer_cnt < 20; -- 分组后的过滤使用 having关键字