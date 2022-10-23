-- 统计每种性别(#1)的人数(#2)
SELECT
    SUBSTRING_INDEX(profile,",",-1) gender, -- -1表示： 从右边数第一个分隔符 往右所有的内容
    COUNT(gender) number -- #2
FROM user_submit
GROUP BY gender; -- #1

device_id	profile	blog_url
2138	180cm,75kg,27,male	http:/url/bigboy777
3214	165cm,45kg,26,female	http:/url/kittycc
6543	178cm,65kg,25,male	http:/url/tiger
4321	171cm,55kg,23,female	http:/url/uhksd
2131	168cm,45kg,22,female	http:/urlsydney