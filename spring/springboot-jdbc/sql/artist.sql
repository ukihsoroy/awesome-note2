#创建数据库
CREATE DATABASE `artist` /*!40100 COLLATE 'utf8mb4_general_ci' */;

#创建Person表
CREATE TABLE `person` (
	`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	`name` VARCHAR(50) NOT NULL COMMENT '姓名',
	`age` SMALLINT NOT NULL COMMENT '年龄',
	`birthday` DATE NOT NULL COMMENT '生日',
	PRIMARY KEY (`id`)
)
COMMENT='人'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB;