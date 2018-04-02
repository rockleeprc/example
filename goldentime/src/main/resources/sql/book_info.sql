CREATE TABLE `book_info` (
	`id` BIGINT(20) NOT NULL,
	`cbid` BIGINT(20) NOT NULL COMMENT '书号',
	`title` VARCHAR(50) NULL DEFAULT NULL COMMENT '小说名' COLLATE 'utf8_unicode_ci',
	`intro` VARCHAR(50) NULL DEFAULT NULL COMMENT '简介' COLLATE 'utf8_unicode_ci',
	`all_words` INT(11) NULL DEFAULT NULL COMMENT '总字数',
	`cover_url` VARCHAR(50) NULL DEFAULT NULL COMMENT '封面url' COLLATE 'utf8_unicode_ci',
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '入库时间',
	`update_time` DATETIME NULL DEFAULT NULL COMMENT '最近一个章节的更新时间',
	`vip_time` DATETIME NULL DEFAULT NULL COMMENT '被设置成为收费作品的时间',
	`status` TINYINT(4) NULL DEFAULT NULL COMMENT '进度状态 30:连载中 40:暂停中 45:完结申请 50:已完结 (101: 封 笔 102:undefined 103: 精 彩 纷 呈 104: 接 近 尾 声 105: 新 书 上 传 106: 情 节 展 开 107:出版中)',
	`vip_status` TINYINT(4) NULL DEFAULT NULL COMMENT 'vip 标志 -1: 公众 1:VIP',
	`key_work` VARCHAR(50) NULL DEFAULT NULL COMMENT '关键字' COLLATE 'utf8_unicode_ci',
	`tag` INT(11) NULL DEFAULT NULL COMMENT '标签',
	`site` TINYINT(4) NULL DEFAULT NULL COMMENT '授权来源：5: 起点男生 1：创世 2：云起 3：起点女生 6：阅文集团 9：起点文学网 24：言情小说吧',
	`cpid` INT(11) NULL DEFAULT NULL COMMENT '1：创世 2：云起 3：起点女生 5: 起点男生 9：起点文学网 24：言情小说吧 66960: 红袖添香 56029：小说阅读网 47626：潇湘书院',
	`form` TINYINT(4) NULL DEFAULT NULL COMMENT '-1:原创 1:出版',
	`charge_type` TINYINT(4) NULL DEFAULT NULL COMMENT '计费方式 1:按章 2:按本',
	`unit_price` TINYINT(4) NULL DEFAULT NULL COMMENT '千字价格 单位:分',
	`total_price` TINYINT(4) NULL DEFAULT NULL COMMENT '按本计费价格',
	`monthly_allowed` TINYINT(4) NULL DEFAULT NULL COMMENT '是否允许包月 -1: 否 1:是',
	`monthly_time` DATETIME NULL DEFAULT NULL COMMENT '允许包月时间',
	`cancle_monthly_time` DATETIME NULL DEFAULT NULL COMMENT '取消包月时间',
	`sign_type` INT(11) NULL DEFAULT NULL,
	`category_id` BIGINT(20) NULL DEFAULT NULL COMMENT '阅文二级分类',
	`sub_category_id` BIGINT(20) NULL DEFAULT NULL COMMENT '三级分类',
	`author_id` BIGINT(20) NULL DEFAULT NULL COMMENT '作者id',
	`author_name` VARBINARY(50) NULL DEFAULT NULL COMMENT '作者名'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;
