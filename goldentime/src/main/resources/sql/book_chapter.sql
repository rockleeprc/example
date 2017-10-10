CREATE TABLE `book_chapter` (
	`id` BIGINT(20) NULL DEFAULT NULL,
	`cbid` BIGINT(20) NULL DEFAULT NULL COMMENT '书号',
	`ccid` BIGINT(20) NULL DEFAULT NULL COMMENT '章号',
	`cvid` BIGINT(20) NULL DEFAULT NULL COMMENT '卷号',
	`sort` INT(11) NULL DEFAULT NULL COMMENT '序号',
	`title` VARCHAR(50) NULL DEFAULT NULL COMMENT '章节名' COLLATE 'utf8_unicode_ci',
	`vip_flag` TINYINT(4) NULL DEFAULT NULL COMMENT 'vip 类型:-1:非 vip 1:vip',
	`amount` INT(11) NULL DEFAULT NULL COMMENT '价格',
	`original_words` INT(11) NULL DEFAULT NULL COMMENT '计费字数',
	`update_time` DATETIME NULL DEFAULT NULL,
	`content_md5` VARCHAR(50) NULL DEFAULT NULL COMMENT '内容 MD5' COLLATE 'utf8_unicode_ci',
	`chapter_url` VARCHAR(50) NULL DEFAULT NULL COMMENT '章节资源' COLLATE 'utf8_unicode_ci',
	`book_id` INT(11) NULL DEFAULT NULL COMMENT '书籍id'
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;