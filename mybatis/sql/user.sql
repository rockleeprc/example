
Create TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `userAge` int(11) DEFAULT NULL,
  `userAddress` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


Insert INTO `user` VALUES ('1', 'summer', '100', 'shanghai,pudong');

# 增加列
alter table user add column sex int; 
alter table t_book modify name varchar(22)