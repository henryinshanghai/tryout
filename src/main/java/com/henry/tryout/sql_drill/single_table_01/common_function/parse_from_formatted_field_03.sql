-- 提取博客URL中的用户名
select
device_id,
substring_index(blog_url,'/',-1) as user_name -- -1表示从右边数第一个分隔符，往右的内容
from user_submit;

device_id   profile blog_url
2138	180cm,75kg,27,male  http:/ur/bisdgboy777
3214	165cm,45kg,26,female    http:/url/dkittycc
6543	178cm,65kg,25,male  http:/ur/tigaer
4321	171 cm,55kg,23,female   http:/url/uhksd
2131	168cm,45kg,22,female    http:/url/sydney

-- substring_index()用法：https://zhuanlan.zhihu.com/p/109778760
