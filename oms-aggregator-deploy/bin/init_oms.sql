/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50633
 Source Host           : localhost
 Source Database       : oms

 Target Server Type    : MySQL
 Target Server Version : 50633
 File Encoding         : utf-8

 Date: 06/24/2017 23:09:24 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `gen_scheme`
-- ----------------------------
CREATE TABLE `gen_scheme` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `category` varchar(2000) DEFAULT NULL COMMENT '分类',
  `project_path` varchar(200) NOT NULL COMMENT '生成文件工程路径',
  `package_name` varchar(500) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `sub_module_name` varchar(30) DEFAULT NULL COMMENT '生成子模块名',
  `function_name` varchar(500) DEFAULT NULL COMMENT '生成功能名',
  `function_name_simple` varchar(100) DEFAULT NULL COMMENT '生成功能名（简写）',
  `function_author` varchar(100) DEFAULT NULL COMMENT '生成功能作者',
  `gen_table_id` varchar(200) DEFAULT NULL COMMENT '生成表编号',
  `create_by` bigint(10) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(10) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `gen_scheme_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-生成方案';

-- ----------------------------
--  Table structure for `gen_table`
-- ----------------------------
CREATE TABLE `gen_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `comments` varchar(500) DEFAULT NULL COMMENT '描述',
  `class_name` varchar(100) DEFAULT NULL COMMENT '实体类名称',
  `parent_table` varchar(200) DEFAULT NULL COMMENT '关联父表',
  `parent_table_fk` varchar(100) DEFAULT NULL COMMENT '关联父表外键',
  `create_by` bigint(10) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(10) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `gen_table_name` (`name`(191)),
  KEY `gen_table_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-业务表';

-- ----------------------------
--  Table structure for `gen_table_column`
-- ----------------------------
CREATE TABLE `gen_table_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `gen_table_id` int(11) DEFAULT NULL COMMENT '归属表编号',
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `comments` varchar(500) DEFAULT NULL COMMENT '描述',
  `jdbc_type` varchar(100) DEFAULT NULL COMMENT '列的数据类型的字节长度',
  `java_type` varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) DEFAULT NULL COMMENT '是否主键',
  `is_null` char(1) DEFAULT NULL COMMENT '是否可为空',
  `is_insert` char(1) DEFAULT NULL COMMENT '是否为插入字段',
  `is_edit` char(1) DEFAULT NULL COMMENT '是否编辑字段',
  `is_list` char(1) DEFAULT NULL COMMENT '是否列表字段',
  `is_query` char(1) DEFAULT NULL COMMENT '是否查询字段',
  `query_type` varchar(200) DEFAULT NULL COMMENT '查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）',
  `show_type` varchar(200) DEFAULT NULL COMMENT '字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）',
  `dict_type` varchar(200) DEFAULT NULL COMMENT '字典类型',
  `settings` varchar(2000) DEFAULT NULL COMMENT '其它设置（扩展字段JSON）',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `create_by` bigint(10) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(10) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记（0：正常；1：删除）',
  PRIMARY KEY (`id`),
  KEY `gen_table_column_table_id` (`gen_table_id`),
  KEY `gen_table_column_name` (`name`(191)),
  KEY `gen_table_column_sort` (`sort`),
  KEY `gen_table_column_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-业务表字段';

-- ----------------------------
--  Table structure for `sys_area`
-- ----------------------------
CREATE TABLE `sys_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-区域表';

-- ----------------------------
--  Records of `sys_area`
-- ----------------------------
BEGIN;
INSERT INTO `sys_area` VALUES
  (1, 0, '0,', '中国', '10', '100000', '1', '1', NOW(), '1', NOW(), null, 0),
  (2, 1, '0,1,', '浙江', '20', '110000', '2', '1', NOW(), '1', NOW(), '', 0),
  (3, 2, '0,1,2,', '杭州', '30', '110101', '3', '1', NOW(), '1', NOW(), '', 0),
  (4, 3, '0,1,2,3,', '上城区', '40', '110102', '4', '1', NOW(), '1', NOW(), '', 0),
  (5, 3, '0,1,2,3,', '下城区', '50', '110104', '4', '1', NOW(), '1', NOW(), '', 0),
  (6, 3, '0,1,2,3,', '西湖区', '60', '110105', '4', '1', NOW(), '1', NOW(), '', 0);
COMMIT;

-- ----------------------------
--  Table structure for `sys_dict`
-- ----------------------------
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` bigint(10) DEFAULT '0' COMMENT '父级编号',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-字典表';

-- ----------------------------
--  Records of `sys_dict`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES
  (1, '0', '正常', 'del_flag', '删除标记', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (2, '1', '删除', 'del_flag', '删除标记', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (3, '1', '显示', 'show_hide', '显示/隐藏', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (4, '0', '隐藏', 'show_hide', '显示/隐藏', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (5, '1', '是', 'yes_no', '是/否', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (6, '0', '否', 'yes_no', '是/否', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (7, 'red', '红色', 'color', '颜色值', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (8, 'green', '绿色', 'color', '颜色值', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (9, 'blue', '蓝色', 'color', '颜色值', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (10, 'yellow', '黄色', 'color', '颜色值', '40', '0', '1', NOW(), '1', NOW(), null, 0),
  (11, 'orange', '橙色', 'color', '颜色值', '50', '0', '1', NOW(), '1', NOW(), null, 0),
  (12, 'default', '默认主题', 'theme', '主题方案', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (13, 'cerulean', '天蓝主题', 'theme', '主题方案', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (14, 'readable', '橙色主题', 'theme', '主题方案', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (15, 'united', '红色主题', 'theme', '主题方案', '40', '0', '1', NOW(), '1', NOW(), null, 0),
  (16, 'flat', 'Flat主题', 'theme', '主题方案', '60', '0', '1', NOW(), '1', NOW(), null, 0),
  (17, '1', '国家', 'sys_area_type', '区域类型', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (18, '2', '省份、直辖市', 'sys_area_type', '区域类型', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (19, '3', '地市', 'sys_area_type', '区域类型', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (20, '4', '区县', 'sys_area_type', '区域类型', '40', '0', '1', NOW(), '1', NOW(), null, 0),
  (21, '1', '公司', 'sys_office_type', '机构类型', '60', '0', '1', NOW(), '1', NOW(), null, 0),
  (22, '2', '部门', 'sys_office_type', '机构类型', '70', '0', '1', NOW(), '1', NOW(), null, 0),
  (23, '3', '小组', 'sys_office_type', '机构类型', '80', '0', '1', NOW(), '1', NOW(), null, 0),
  (24, '4', '其它', 'sys_office_type', '机构类型', '90', '0', '1', NOW(), '1', NOW(), null, 0),
  (25, '1', '综合部', 'sys_office_common', '快捷通用部门', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (26, '2', '开发部', 'sys_office_common', '快捷通用部门', '40', '0', '1', NOW(), '1', NOW(), null, 0),
  (27, '3', '人力部', 'sys_office_common', '快捷通用部门', '50', '0', '1', NOW(), '1', NOW(), null, 0),
  (28, '1', '一级', 'sys_office_grade', '机构等级', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (29, '2', '二级', 'sys_office_grade', '机构等级', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (30, '3', '三级', 'sys_office_grade', '机构等级', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (31, '4', '四级', 'sys_office_grade', '机构等级', '40', '0', '1', NOW(), '1', NOW(), null, 0),
  (32, '1', '所有数据', 'sys_data_scope', '数据范围', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (33, '2', '所在公司及以下数据', 'sys_data_scope', '数据范围', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (34, '3', '所在公司数据', 'sys_data_scope', '数据范围', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (35, '4', '所在部门及以下数据', 'sys_data_scope', '数据范围', '40', '0', '1', NOW(), '1', NOW(), null, 0),
  (36, '5', '所在部门数据', 'sys_data_scope', '数据范围', '50', '0', '1', NOW(), '1', NOW(), null, 0),
  (37, '8', '仅本人数据', 'sys_data_scope', '数据范围', '90', '0', '1', NOW(), '1', NOW(), null, 0),
  (38, '9', '按明细设置', 'sys_data_scope', '数据范围', '100', '0', '1', NOW(), '1', NOW(), null, 0),
  (39, '1', '系统管理', 'sys_user_type', '用户类型', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (40, '2', '部门经理', 'sys_user_type', '用户类型', '20', '0', '1', NOW(), '1', NOW(), null, 0),
  (41, '3', '普通用户', 'sys_user_type', '用户类型', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (42, '1', '接入日志', 'sys_log_type', '日志类型', '30', '0', '1', NOW(), '1', NOW(), null, 0),
  (43, '2', '异常日志', 'sys_log_type', '日志类型', '40', '0', '1', NOW(), '1', NOW(), null, 0),
  (44, '1', '男', 'sex', '性别', '10', '0', '1', NOW(), '1', NOW(), null, 0),
  (45, '2', '女', 'sex', '性别', '20', '0', '1', NOW(), '1', NOW(), null, 0);
COMMIT;

-- ----------------------------
--  Table structure for `sys_log`
-- ----------------------------
CREATE TABLE `sys_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `create_by` bigint(10) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`(191)),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-日志表';

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-菜单表';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES
  (1, 0, '0,', '功能菜单', '0', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (2, 1, '0,1,', '系统设置', '900', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (3, 2, '0,1,2,', '系统设置', '980', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (4, 3, '0,1,2,3,', '菜单管理', '30', '/sys/menu/', null, 'list-alt', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (5, 4, '0,1,2,3,4,', '查看', '30', null, null, null, '0', 'sys:menu:view', '1', NOW(), '1', NOW(), null, 0),
  (6, 4, '0,1,2,3,4,', '修改', '40', null, null, null, '0', 'sys:menu:edit', '1', NOW(), '1', NOW(), null, 0),
  (7, 3, '0,1,2,3,', '角色管理', '50', '/sys/role/', null, 'lock', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (8, 7, '0,1,2,3,7,', '查看', '30', null, null, null, '0', 'sys:role:view', '1', NOW(), '1', NOW(), null, 0),
  (9, 7, '0,1,2,3,7,', '修改', '40', null, null, null, '0', 'sys:role:edit', '1', NOW(), '1', NOW(), null, 0),
  (10, 3, '0,1,2,3,', '字典管理', '60', '/sys/dict/', null, 'th-list', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (11, 10, '0,1,2,3,10,', '查看', '30', null, null, null, '0', 'sys:dict:view', '1', NOW(), '1', NOW(), null, 0),
  (12, 10, '0,1,2,3,10,', '修改', '40', null, null, null, '0', 'sys:dict:edit', '1', NOW(), '1', NOW(), null, 0),
  (13, 2, '0,1,2,', '机构用户', '970', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (14, 13, '0,1,2,13,', '区域管理', '50', '/sys/area/', null, 'th', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (15, 14, '0,1,2,13,14,', '查看', '30', null, null, null, '0', 'sys:area:view', '1', NOW(), '1', NOW(), null, 0),
  (16, 14, '0,1,2,13,14,', '修改', '40', null, null, null, '0', 'sys:area:edit', '1', NOW(), '1', NOW(), null, 0),
  (17, 13, '0,1,2,13,', '机构管理', '40', '/sys/office/', null, 'th-large', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (18, 17, '0,1,2,13,17,', '查看', '30', null, null, null, '0', 'sys:office:view', '1', NOW(), '1', NOW(), null, 0),
  (19, 17, '0,1,2,13,17,', '修改', '40', null, null, null, '0', 'sys:office:edit', '1', NOW(), '1', NOW(), null, 0),
  (20, 13, '0,1,2,13,', '用户管理', '30', '/sys/user/index', null, 'user', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (21, 20, '0,1,2,13,20,', '查看', '30', null, null, null, '0', 'sys:user:view', '1', NOW(), '1', NOW(), null, 0),
  (22, 20, '0,1,2,13,20,', '修改', '40', null, null, null, '0', 'sys:user:edit', '1', NOW(), '1', NOW(), null, 0),
  (27, 1, '0,1,', '我的面板', '100', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (28, 27, '0,1,27,', '个人信息', '30', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (29, 28, '0,1,27,28,', '个人信息', '30', '/sys/user/info', null, 'user', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (30, 28, '0,1,27,28,', '修改密码', '40', '/sys/user/modifyPwd', null, 'lock', '1', null, '1', NOW(), '1', NOW(), null, 0),
  (31, 1, '0,1,', '用户管理', '200', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (32, 31, '0,1,31,', '用户管理', '500', '', '', '', '1', '', '1', NOW(), '1', NOW(), '', 0),
  (33, 2, '0,1,2,', '日志查询', '985', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (34, 33, '0,1,2,67,', '日志查询', '30', '/sys/log', null, 'pencil', '1', 'sys:log:view', '1', NOW(), '1', NOW(), null, 0),
  (35, 1, '0,1,', '代码生成', '5000', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (36, 35, '0,1,35,', '代码生成', '50', null, null, null, '1', null, '1', NOW(), '1', NOW(), null, 0),
  (37, 36, '0,1,79,36,', '生成方案配置', '30', '/gen/genScheme', null, null, '1', 'gen:genScheme:view,gen:genScheme:edit', '1', NOW(), '1', NOW(), null, 0),
  (38, 36, '0,1,79,36,', '业务表配置', '20', '/gen/genTable', null, null, '1', 'gen:genTable:view,gen:genTable:edit,gen:genTableColumn:view,gen:genTableColumn:edit', '1', NOW(), '1', NOW(), null, 0),
  (39, 33, '0,1,2,33,', '连接池监视', '40', '/../druid', null, null, '1', null, '1', NOW(), '1', NOW(), null, 0);
COMMIT;

-- ----------------------------
--  Table structure for `sys_office`
-- ----------------------------
CREATE TABLE `sys_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `area_id` int(11) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `type` char(1) NOT NULL COMMENT '机构类型',
  `grade` char(1) NOT NULL COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `USEABLE` varchar(64) DEFAULT NULL COMMENT '是否启用',
  `PRIMARY_PERSON` varchar(64) DEFAULT NULL COMMENT '主负责人',
  `DEPUTY_PERSON` varchar(64) DEFAULT NULL COMMENT '副负责人',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_del_flag` (`del_flag`),
  KEY `sys_office_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-机构表';

-- ----------------------------
--  Records of `sys_office`
-- ----------------------------
BEGIN;
INSERT INTO `sys_office` VALUES
  (1, 0, '0,', '浙江省总公司', '10', 2, '100000', '1', '1', '', '', '', '', '', '', '1', '', '', '1', NOW(), '1', NOW(), '', 0),
  (2, 1, '0,1,', '公司领导', '10', 2, '100001', '2', '1', null, null, null, null, null, null, '1', null, null, '1', NOW(), '1', NOW(), null, 0),
  (3, 1, '0,1,', '综合部', '20', 2, '100002', '2', '1', null, null, null, null, null, null, '1', null, null, '1', NOW(), '1', NOW(), null, 0),
  (4, 1, '0,1,', '市场部', '30', 2, '100003', '2', '1', null, null, null, null, null, null, '1', null, null, '1', NOW(), '1', NOW(), null, 0),
  (5, 1, '0,1,', '技术部', '40', 2, '100004', '2', '1', null, null, null, null, null, null, '1', null, null, '1', NOW(), '1', NOW(), null, 0);
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `office_id` int(11) DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `enname` varchar(255) DEFAULT NULL COMMENT '英文名称',
  `role_type` varchar(255) DEFAULT NULL COMMENT '角色类型',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `is_sys` varchar(64) DEFAULT NULL COMMENT '是否系统数据',
  `useable` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(64) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`),
  KEY `sys_role_enname` (`enname`(191))
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES
  (1, 2, '系统管理员', 'dept', 'assignment', '1', '1', '1', '1', NOW(), '1', NOW(), '', 0),
  (2, 2, '运营权限', 'market', 'assignment', '1', '1', '1', '1', NOW(), '1', NOW(), '', 0);
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `menu_id` int(11) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-角色-菜单';


-- ----------------------------
--  Table structure for `sys_role_office`
-- ----------------------------
CREATE TABLE `sys_role_office` (
  `role_id` int(11) NOT NULL COMMENT '角色编号',
  `office_id` int(11) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-角色-机构';


-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company_id` int(11) NOT NULL COMMENT '归属公司',
  `office_id` int(11) NOT NULL COMMENT '归属部门',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `photo` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` varchar(64) DEFAULT NULL COMMENT '是否可登录',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_company_id` (`company_id`),
  KEY `sys_user_update_time` (`update_time`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 1, 2, 'admin', 'aa51f382e26bc8a87c0353b63c57e0bd5dd12ce11e345a6560fd8e82', '0001', '系统管理员', '', '8675', '8675', null, '', '10.10.10.1', NOW(), '1', '1', NOW(), '1', NOW(), '最高管理员', 0);
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
CREATE TABLE `sys_user_role` (
  `user_id` bigint(10) NOT NULL COMMENT '用户编号',
  `role_id` bigint(10) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-用户-角色';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1'), ('1', '2');
COMMIT;

-- ----------------------------
--  Table structure for `test_data`
-- ----------------------------
CREATE TABLE `test_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) DEFAULT NULL COMMENT '归属用户',
  `office_id` int(11) DEFAULT NULL COMMENT '归属部门',
  `area_id` int(11) DEFAULT NULL COMMENT '归属区域',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `in_date` date DEFAULT NULL COMMENT '加入日期',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `test_data_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-业务数据表';

-- ----------------------------
--  Table structure for `test_data_child`
-- ----------------------------
CREATE TABLE `test_data_child` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `test_data_main_id` int(11) DEFAULT NULL COMMENT '业务主表ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `test_data_child_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=8580 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-业务数据子表';

-- ----------------------------
--  Table structure for `test_data_main`
-- ----------------------------
CREATE TABLE `test_data_main` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) DEFAULT NULL COMMENT '归属用户',
  `office_id` int(11) DEFAULT NULL COMMENT '归属部门',
  `area_id` int(11) DEFAULT NULL COMMENT '归属区域',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `in_date` date DEFAULT NULL COMMENT '加入日期',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `test_data_main_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-业务数据表';

-- ----------------------------
--  Table structure for `test_tree`
-- ----------------------------
CREATE TABLE `test_tree` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `create_by` bigint(10) NOT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(10) NOT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `test_tree_del_flag` (`del_flag`),
  KEY `test_data_parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='管理平台-树结构表';

SET FOREIGN_KEY_CHECKS = 1;
