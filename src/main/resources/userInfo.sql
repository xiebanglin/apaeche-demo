DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(32) NOT NULL COMMENT '姓名',
  `age` int(11) NOT NULL COMMENT '年龄',
  `address` varchar(32) NOT NULL COMMENT '住址',
  `phoneNum` varchar(32) NOT NULL COMMENT '电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;