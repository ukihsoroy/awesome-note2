CREATE TABLE `t_job` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '业务自增主键',
	`batch_no` VARCHAR(50) NOT NULL COMMENT '任务批次号',
	`source_database` VARCHAR(50) NOT NULL COMMENT '原数据库',
	`table_name` VARCHAR(50) NOT NULL COMMENT '原数据表',
	`project_id` VARCHAR(50) NOT NULL COMMENT 'mc project id.',
	`task_script` JSON NULL DEFAULT NULL COMMENT '任务脚本',
	`task_report` JSON NULL DEFAULT NULL COMMENT '任务报告',
	`start_time` DATETIME NULL DEFAULT NULL COMMENT '任务开始时间',
	`end_time` DATETIME NULL DEFAULT NULL COMMENT '任务结束时间',
	`status` SMALLINT(6) NOT NULL DEFAULT '0' COMMENT '任务状态：0-待运行，1-运行中，2-成功，3-失败，4-完成',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (`id`)
)
COMMENT='任务执行job'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=7
;
