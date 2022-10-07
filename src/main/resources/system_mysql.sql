/*
Navicat MySQL Data Transfer

Source Server         : 192.168.10.222
Source Server Version : 50649
Source Host           : 192.168.10.222:3306
Source Database       : system_mysql

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2022-08-25 17:10:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_button
-- ----------------------------
DROP TABLE IF EXISTS `system_button`;
CREATE TABLE `system_button` (
  `button_id` varchar(32) NOT NULL COMMENT '按钮编号',
  `button_name` varchar(100) DEFAULT NULL COMMENT '按钮名称',
  `button_code` varchar(100) DEFAULT NULL COMMENT '按钮代码',
  `button_url` varchar(100) DEFAULT NULL COMMENT '按钮地址URL',
  `button_status` varchar(2) DEFAULT '0' COMMENT '按钮状态0：正常1：禁用',
  `user_id` varchar(32) DEFAULT NULL COMMENT '按钮创建人',
  `button_ico` varchar(100) DEFAULT NULL COMMENT '按钮图标',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `button_order` double DEFAULT NULL COMMENT '按钮顺序',
  `button_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '按钮创建时间',
  `button_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '按钮修改时间',
  `button_father` varchar(100) DEFAULT NULL COMMENT '按钮节点',
  `button_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `button_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `button_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`button_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单中按钮信息的存储';

-- ----------------------------
-- Table structure for system_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary` (
  `dictionary_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据字典编号',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `dictionary_type` varchar(50) DEFAULT NULL COMMENT '节点类型',
  `dictionary_code` varchar(100) DEFAULT NULL COMMENT '节点编码',
  `dictionary_name` varchar(100) DEFAULT NULL COMMENT '节点名称',
  `dictionary_desc` varchar(100) DEFAULT NULL COMMENT '节点描述',
  `dictionary_parent_code` varchar(100) DEFAULT NULL COMMENT '父级节点',
  `dictionary_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `dictionary_create_user_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `dictionary_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `dictionary_update_user_id` varchar(32) DEFAULT NULL COMMENT '修改人',
  `dictionary_status` varchar(10) DEFAULT '0' COMMENT '字典状态：0-正常1-禁用',
  `dictionary_order` double DEFAULT '1' COMMENT '顺序 默认从1开始',
  `dictionary_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `dictionary_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `dictionary_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`dictionary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='数据字典表存储一些公用的类型信息';

-- ----------------------------
-- Table structure for system_logger
-- ----------------------------
DROP TABLE IF EXISTS `system_logger`;
CREATE TABLE `system_logger` (
  `logger_id` varchar(32) NOT NULL COMMENT '日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `logger_type` varchar(100) DEFAULT NULL COMMENT '日志类型',
  `logger_type_name` varchar(10) DEFAULT NULL COMMENT '日志类型名称',
  `logger_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '日志创建时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `logger_ip` varchar(100) DEFAULT NULL COMMENT '日志IP',
  `logger_module` varchar(100) DEFAULT NULL COMMENT '操作的业务模块',
  `logger_module_method` varchar(100) DEFAULT NULL COMMENT '操作的业务模块的方法',
  `logger_response_time` varchar(100) DEFAULT NULL COMMENT '访问响应时间 毫秒',
  `logger_operating_system` varchar(100) DEFAULT NULL COMMENT '浏览器使用的系统',
  `logger_browser_type` varchar(100) DEFAULT NULL COMMENT '浏览器类型',
  `logger_browser_version` varchar(100) DEFAULT NULL COMMENT '浏览器版本',
  `logger_description` varchar(4000) DEFAULT NULL COMMENT '日志描述',
  `logger_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `logger_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `logger_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`logger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储系统用户操作，如新增用户，菜单、按钮、机构等基础信息模块';

-- ----------------------------
-- Table structure for system_logger_exception
-- ----------------------------
DROP TABLE IF EXISTS `system_logger_exception`;
CREATE TABLE `system_logger_exception` (
  `logger_exception_id` varchar(32) NOT NULL COMMENT '日志编号:类型+年+月+日+时+分+秒+毫秒+6位序列',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `logger_exception_type` varchar(100) DEFAULT NULL COMMENT '日志类型',
  `logger_exception_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '日志创建时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `logger_exception_ip` varchar(100) DEFAULT NULL COMMENT '日志IP',
  `logger_exception_module` varchar(100) DEFAULT NULL COMMENT '日志调用模块',
  `logger_exception_method` varchar(10) DEFAULT NULL COMMENT '日志调用模块方法',
  `logger_exception_description` varchar(1024) DEFAULT NULL COMMENT '日志描述',
  `logger_response_time` varchar(100) DEFAULT NULL COMMENT '访问响应时间 毫秒',
  `logger_exception_context` text COMMENT '日志异常信息',
  `logger_operating_system` varchar(100) DEFAULT NULL COMMENT '浏览器使用的系统',
  `logger_browser_type` varchar(100) DEFAULT NULL COMMENT '浏览器类型',
  `logger_browser_version` varchar(100) DEFAULT NULL COMMENT '浏览器版本',
  `logger_exception_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `logger_exception_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `logger_exception_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`logger_exception_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储系统用户操作，如新增用户，菜单、按钮、机构等基础信息模块';

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `menu_id` varchar(32) NOT NULL COMMENT '菜单编号',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(100) DEFAULT NULL COMMENT '菜单代码',
  `menu_father_id` varchar(100) DEFAULT NULL COMMENT '菜单父节点',
  `menu_order` double DEFAULT NULL COMMENT '菜单顺序',
  `menu_status` varchar(10) DEFAULT '0' COMMENT '菜单状态：0正常1禁用',
  `menu_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '菜单修改时间',
  `menu_url` varchar(1024) DEFAULT NULL COMMENT '菜单地址URL',
  `menu_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '菜单创建时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '菜单创建人',
  `menu_ico` varchar(1024) DEFAULT NULL COMMENT '菜单图标',
  `menu_level` varchar(10) DEFAULT NULL COMMENT '是否有子节点',
  `menu_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `menu_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `menu_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统展示菜单信息的存储';

-- ----------------------------
-- Table structure for system_org
-- ----------------------------
DROP TABLE IF EXISTS `system_org`;
CREATE TABLE `system_org` (
  `org_id` varchar(32) NOT NULL COMMENT '组织机构编码',
  `org_full_name` varchar(100) NOT NULL COMMENT '组织机构名称全称',
  `org_name` varchar(100) DEFAULT NULL COMMENT '组织机构名称简称',
  `org_parent_id` varchar(32) NOT NULL COMMENT '组织机构父级节点编码',
  `org_parent_name` varchar(100) DEFAULT NULL COMMENT '组织机构父级节点名称',
  `org_leaf` varchar(2) NOT NULL COMMENT '是否有节点 0：是1：否',
  `org_address` varchar(1000) DEFAULT NULL COMMENT '组织机构地址',
  `org_level` varchar(2) DEFAULT NULL COMMENT '组织机构等级',
  `org_type` varchar(10) DEFAULT NULL COMMENT '组织机构类型',
  `org_area` varchar(100) DEFAULT NULL COMMENT '组织机构行政区划名称',
  `org_area_code` varchar(30) DEFAULT NULL COMMENT '组织机构行政区划编码',
  `org_create_date` date DEFAULT NULL COMMENT '成立日期',
  `org_status` varchar(10) NOT NULL DEFAULT '0' COMMENT '组织机构状态：0：正常1：禁用',
  `org_phone` varchar(1000) DEFAULT NULL COMMENT '机构电话',
  `org_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '组织机构创建时间',
  `org_create_user_id` varchar(32) DEFAULT NULL COMMENT '组织机构创建人',
  `org_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '组织机构修改时间',
  `org_update_user_id` varchar(32) DEFAULT NULL COMMENT '组织机构修改人',
  `org_desc` varchar(4000) DEFAULT NULL COMMENT '机构备注',
  `org_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `org_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `org_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统组织机构信息的存储';

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `role_type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  `role_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '角色创建时间',
  `role_create_user_id` varchar(32) DEFAULT NULL COMMENT '角色创建人',
  `role_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '角色修改时间',
  `role_update_user_id` varchar(32) DEFAULT NULL COMMENT '角色修改人',
  `role_status` varchar(10) DEFAULT '0' COMMENT '角色状态：0：正常1：禁用',
  `role_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `role_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `role_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='系统角色定义信息';

-- ----------------------------
-- Table structure for system_role_button_mapped
-- ----------------------------
DROP TABLE IF EXISTS `system_role_button_mapped`;
CREATE TABLE `system_role_button_mapped` (
  `mapped_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色菜单关系编号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色编号',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `button_id` varchar(32) DEFAULT NULL COMMENT '按钮编号',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `mapped_desc` varchar(1024) DEFAULT NULL COMMENT '角色菜单关系描述',
  `mapped_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '角色菜单关系创建时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '角色菜单关系创建人',
  `mapped_update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '角色菜单关系修改时间',
  `mapped_status` varchar(10) DEFAULT NULL COMMENT '角色菜单关系状态:0正常1禁用',
  `mapped_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `mapped_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `mapped_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`mapped_id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8 COMMENT='系统菜单角色与按钮的关系存储';

-- ----------------------------
-- Table structure for system_role_menu_mapped
-- ----------------------------
DROP TABLE IF EXISTS `system_role_menu_mapped`;
CREATE TABLE `system_role_menu_mapped` (
  `mapped_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色菜单关系编号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色编号',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `mapped_menu_array` varchar(4000) DEFAULT NULL COMMENT '菜单按钮数组以，区分',
  `mapped_desc` varchar(1024) DEFAULT NULL COMMENT '角色菜单关系描述',
  `mapped_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '角色菜单关系创建时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '角色菜单关系创建人',
  `mapped_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '角色菜单关系修改时间',
  `mapped_status` varchar(10) DEFAULT NULL COMMENT '角色菜单关系状态:0正常1禁用',
  `mapped_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `mapped_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `mapped_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`mapped_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2806 DEFAULT CHARSET=utf8 COMMENT='系统菜单角色与菜单和按钮的关系存储';

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户登录名',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `user_password` varchar(100) NOT NULL COMMENT '用户登录密码',
  `user_name` varchar(30) NOT NULL COMMENT '用户姓名',
  `user_age` varchar(3) DEFAULT NULL COMMENT '用户年龄：1-999',
  `user_sex` varchar(4) DEFAULT '0' COMMENT '用户性别：0：男 1：女2：未知',
  `user_identity` varchar(18) NOT NULL COMMENT '用户身份证号码',
  `user_address` varchar(1024) DEFAULT NULL COMMENT '用户地址',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '用户电话',
  `user_mail` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `user_desc` varchar(1024) DEFAULT NULL COMMENT '用户描述',
  `user_image` varchar(1024) DEFAULT NULL COMMENT '用户图像url',
  `user_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
  `user_create_user_id` varchar(32) DEFAULT NULL COMMENT '用户创建人',
  `user_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户修改时间',
  `user_update_user_id` varchar(32) DEFAULT NULL COMMENT '用户修改人',
  `user_update_password_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户密码修改时间',
  `user_status` varchar(10) DEFAULT '0' COMMENT '用户状态：0：正常1：停用',
  `user_skin` varchar(100) DEFAULT NULL COMMENT '用户皮肤',
  `user_level` varchar(4) DEFAULT NULL COMMENT '用户等级',
  `user_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `user_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `user_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统使用者用户的信息存储';

-- ----------------------------
-- Table structure for system_user_role_mapped
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role_mapped`;
CREATE TABLE `system_user_role_mapped` (
  `mapped_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色关系编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色编号',
  `org_id` varchar(32) DEFAULT NULL COMMENT '组织机构编码',
  `mapped_status` varchar(10) DEFAULT NULL COMMENT '用户角色关系状态：0正常1：停用禁用',
  `mapped_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用户角色关系创建时间',
  `mapped_create_user_id` varchar(10) DEFAULT NULL COMMENT '用户角色关系创建人',
  `mapped_reserve_a` varchar(1024) DEFAULT NULL COMMENT '备用字段1',
  `mapped_reserve_b` varchar(1024) DEFAULT NULL COMMENT '备用字段2',
  `mapped_reserve_c` varchar(1024) DEFAULT NULL COMMENT '备用字段3',
  PRIMARY KEY (`mapped_id`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8 COMMENT='用户与角色的关系信息表';

-- ----------------------------
-- Function structure for fun_seq
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_seq`;
DELIMITER ;;
CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `fun_seq`(department varchar(32)) RETURNS varchar(32) CHARSET utf8
BEGIN
    declare  datetimes varchar(14);
	declare seq varchar(6);
    declare returnSEQ varchar(32);
    select date_format(now(),'%Y%m%d%H%i%s') INTO datetimes;
	select lpad(FLOOR(RAND()*1000000),6,'0') into seq;
 if (trim(department) is null or department = '') then
  SET returnSEQ = CONCAT(datetimes,seq);
  else
  SET returnSEQ = CONCAT(department,datetimes,seq) ;
  end if;
RETURN returnSEQ;
END
;;
DELIMITER ;
