# 查询book_parts
select b.id,bi.yz_book_id,bp.title from books b left join books_info bi on b.id=bi.id left join book_parts  bp on b.id = bp.book_id where b.id=396330 order by bp.id desc limit 1;

# 查询book_parts_api
select b.id,b.title,bpa.id,bpa.title,bpa.create_date,bi.yz_book_id,bpa.part_id from books b left join books_info bi on b.id=bi.id left join book_parts_api bpa on b.id = bpa.book_id where b.copyright_owner='上海阅文' and b.is_serial=1 and b.status='published' and b.id=425788  order by bpa.id desc limit 1;

# 连载+上线
select id,title from books b where b.copyright_owner='上海阅文' and b.is_serial=1 and b.`status`='published';

# 跳板机
mysql -utadu -pjw5iW*asB -h172.16.1.40 --default-character-set=utf8 -A tadu -N </home/liyan/20171102/query_book_info.sql  > /home/liyan/20171102/query_book_info.txt
mysql -utadu -pjw5iW*asB -h172.16.1.40 --default-character-set=utf8 -A tadu </home/liyan/20171102/query_book_info.sql  > /home/liyan/20171102/query_book_info1.txt