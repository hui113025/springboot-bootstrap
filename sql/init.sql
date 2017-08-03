/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50545
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50545
File Encoding         : 65001

Date: 2017-08-03 16:33:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '操作人ID',
  `user_name` varchar(64) NOT NULL COMMENT '操作人姓名',
  `oper_name` varchar(255) NOT NULL COMMENT '操作名称',
  `oper_type` varchar(64) NOT NULL COMMENT '操作类型',
  `oper_describe` varchar(128) NOT NULL COMMENT '操作描述',
  `oper_param` longtext NOT NULL COMMENT '操作参数',
  `oper_level` int(11) NOT NULL COMMENT '操作级别',
  `oper_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of admin_log
-- ----------------------------

-- ----------------------------
-- Table structure for authority_menu
-- ----------------------------
DROP TABLE IF EXISTS `authority_menu`;
CREATE TABLE `authority_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(128) DEFAULT NULL COMMENT '菜单URL',
  `menu_enable` tinyint(1) DEFAULT NULL COMMENT '菜单是否启用(1启用0不启用)',
  `menu_parent_id` int(11) DEFAULT NULL COMMENT '上级菜单ID,0为根菜单',
  `menu_icon` varchar(128) DEFAULT NULL COMMENT '菜单的ICON图标',
  `menu_code` varchar(32) DEFAULT NULL COMMENT '菜单的代码',
  `menu_desc` varchar(128) DEFAULT NULL COMMENT '菜单的描述',
  `last_modify_user_id` int(11) DEFAULT NULL COMMENT 'last_modify_user_id',
  `last_modify_datetime` datetime DEFAULT NULL COMMENT 'last_modify_datetime',
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识1删除0使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='资源菜单表';

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES ('1', '系统管理', null, '1', '0', 'fa fa-2x fa-asterisk', null, '系统管理一级菜单', '1', '2016-04-20 16:43:27', '0');
INSERT INTO `authority_menu` VALUES ('2', '菜单管理', '/menu/manager', '1', '1', null, 'menu', '菜单管理', '1', '2017-05-19 18:43:12', '0');
INSERT INTO `authority_menu` VALUES ('3', '员工管理', '/user/manager', '1', '1', null, 'user', '员工的管理', '1', '2016-07-01 17:06:48', '0');
INSERT INTO `authority_menu` VALUES ('4', '员工授权', '/role/manager', '1', '1', null, 'role', '员工授予角色', '1', '2016-06-03 11:19:08', '0');
INSERT INTO `authority_menu` VALUES ('5', '角色管理', '/per/manager', '1', '1', null, 'per', '进行角色的权限授权', '1', '2016-04-11 14:21:37', '0');
INSERT INTO `authority_menu` VALUES ('7', '操作日志', '/adminLog/list', '1', '1', null, 'adminLog', '跳转日志管理页面', '4', '2016-04-27 15:55:55', '0');

-- ----------------------------
-- Table structure for authority_operation
-- ----------------------------
DROP TABLE IF EXISTS `authority_operation`;
CREATE TABLE `authority_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '操作名称',
  `code` varchar(64) DEFAULT NULL COMMENT '操作代码',
  `description` varchar(64) DEFAULT NULL COMMENT '操作描述',
  `menu_id` int(11) DEFAULT NULL COMMENT '操作所属菜单ID',
  `last_modify_user_id` int(11) DEFAULT NULL COMMENT 'last_modify_user_id',
  `last_modify_datetime` datetime DEFAULT NULL COMMENT 'last_modify_datetime',
  `deleted` int(1) DEFAULT '0' COMMENT '删除标识1删除0使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='操作、动作表';

-- ----------------------------
-- Records of authority_operation
-- ----------------------------
INSERT INTO `authority_operation` VALUES ('1', '查询', 'find', '查询菜单', '2', '1', '2017-03-29 10:28:42', '0');
INSERT INTO `authority_operation` VALUES ('2', '修改', 'update', '修改菜单操作', '2', '1', '2016-04-11 10:39:53', '0');
INSERT INTO `authority_operation` VALUES ('3', '操作管理', 'operManage', '对每个菜单进行操作的添加和修改', '2', '1', '2016-04-11 10:41:18', '0');
INSERT INTO `authority_operation` VALUES ('4', '查询', 'find', '对员工进行查询操作', '3', '1', '2016-04-11 10:43:17', '0');
INSERT INTO `authority_operation` VALUES ('5', '锁定', 'lock', '锁定用户,不许登录', '3', '1', '2016-04-11 10:44:09', '0');
INSERT INTO `authority_operation` VALUES ('6', '新增', 'save', '新增员工', '3', '1', '2016-04-11 10:44:39', '0');
INSERT INTO `authority_operation` VALUES ('7', '修改', 'update', '修改员工', '3', '1', '2016-04-11 10:44:55', '0');
INSERT INTO `authority_operation` VALUES ('8', '查询角色', 'findRole', '查询角色', '4', '1', '2017-08-02 13:47:06', '0');
INSERT INTO `authority_operation` VALUES ('9', '用户授权', 'userAuth', '用户授予角色权限', '4', '1', '2016-04-11 10:47:39', '0');
INSERT INTO `authority_operation` VALUES ('10', '查询', 'find', '查询', '5', '1', '2016-05-31 14:22:11', '0');
INSERT INTO `authority_operation` VALUES ('11', '角色授权', 'roleAuth', '', '5', '1', '2016-04-11 10:58:10', '0');
INSERT INTO `authority_operation` VALUES ('12', '保存设置', 'update', '', '7', '1', '2016-05-26 18:24:20', '0');
INSERT INTO `authority_operation` VALUES ('13', '获取详细', 'find', '', '7', '1', '2016-05-26 18:24:25', '0');
INSERT INTO `authority_operation` VALUES ('21', '浏览', 'view', '菜单默认操作', '2', '1', '2016-04-12 11:02:55', '0');
INSERT INTO `authority_operation` VALUES ('22', '浏览', 'view', '菜单默认操作', '3', '1', '2016-04-12 11:04:40', '0');
INSERT INTO `authority_operation` VALUES ('23', '浏览', 'view', '菜单默认操作', '4', '1', '2016-04-12 11:05:30', '0');
INSERT INTO `authority_operation` VALUES ('24', '浏览', 'view', '菜单默认操作', '5', '1', '2016-04-12 11:06:02', '0');
INSERT INTO `authority_operation` VALUES ('25', '浏览', 'view', '菜单默认操作', '7', '1', '2016-04-12 11:06:21', '0');
INSERT INTO `authority_operation` VALUES ('79', '删除', 'delRoleUser', '删除角色', '4', '1', '2016-06-03 11:19:06', '0');

-- ----------------------------
-- Table structure for authority_permission
-- ----------------------------
DROP TABLE IF EXISTS `authority_permission`;
CREATE TABLE `authority_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `resources_id` int(11) DEFAULT NULL COMMENT '资源ID',
  `resources_code` varchar(32) DEFAULT NULL COMMENT '资源代码',
  `operation_id` int(11) DEFAULT NULL COMMENT '操作ID',
  `operation_code` varchar(32) DEFAULT NULL COMMENT '操作代码',
  `category_id` varchar(64) DEFAULT NULL COMMENT '分类id',
  `permission_code` varchar(64) DEFAULT NULL COMMENT '权限代码{菜单code:操作code:categoryId}',
  `last_modify_user_id` int(11) DEFAULT NULL COMMENT 'last_modify_user_id',
  `last_modify_datetime` datetime DEFAULT NULL COMMENT 'last_modify_datetime',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of authority_permission
-- ----------------------------
INSERT INTO `authority_permission` VALUES ('1', '2', '2', 'menu', '1', 'find', null, 'menu:find', '422', '2017-06-06 11:42:08');
INSERT INTO `authority_permission` VALUES ('2', '2', '2', 'menu', '2', 'update', null, 'menu:update', '422', '2017-06-06 11:42:08');
INSERT INTO `authority_permission` VALUES ('3', '2', '2', 'menu', '3', 'operManage', null, 'menu:operManage', '422', '2017-06-06 11:42:08');
INSERT INTO `authority_permission` VALUES ('4', '2', '2', 'menu', '21', 'view', null, 'menu:view', '422', '2017-06-06 11:42:08');
INSERT INTO `authority_permission` VALUES ('5', '2', '3', 'user', '4', 'find', null, 'user:find', '422', '2017-06-06 11:42:12');
INSERT INTO `authority_permission` VALUES ('6', '2', '3', 'user', '5', 'lock', null, 'user:lock', '422', '2017-06-06 11:42:12');
INSERT INTO `authority_permission` VALUES ('7', '2', '3', 'user', '6', 'save', null, 'user:save', '422', '2017-06-06 11:42:12');
INSERT INTO `authority_permission` VALUES ('8', '2', '3', 'user', '7', 'update', null, 'user:update', '422', '2017-06-06 11:42:12');
INSERT INTO `authority_permission` VALUES ('9', '2', '3', 'user', '22', 'view', null, 'user:view', '422', '2017-06-06 11:42:12');
INSERT INTO `authority_permission` VALUES ('10', '2', '4', 'role', '8', 'findRole', null, 'role:findRole', '422', '2017-06-06 11:42:14');
INSERT INTO `authority_permission` VALUES ('11', '2', '4', 'role', '9', 'userAuth', null, 'role:userAuth', '422', '2017-06-06 11:42:14');
INSERT INTO `authority_permission` VALUES ('12', '2', '4', 'role', '23', 'view', null, 'role:view', '422', '2017-06-06 11:42:14');
INSERT INTO `authority_permission` VALUES ('13', '2', '4', 'role', '79', 'delRoleUser', null, 'role:delRoleUser', '422', '2017-06-06 11:42:14');
INSERT INTO `authority_permission` VALUES ('14', '2', '7', 'adminLog', '12', 'update', null, 'adminLog:update', '422', '2017-06-06 11:42:21');
INSERT INTO `authority_permission` VALUES ('15', '2', '7', 'adminLog', '13', 'find', null, 'adminLog:find', '422', '2017-06-06 11:42:21');
INSERT INTO `authority_permission` VALUES ('16', '2', '7', 'adminLog', '25', 'view', null, 'adminLog:view', '422', '2017-06-06 11:42:21');
INSERT INTO `authority_permission` VALUES ('17', '2', '5', 'per', '10', 'find', null, 'per:find', '1', '2017-06-06 11:26:23');
INSERT INTO `authority_permission` VALUES ('18', '2', '5', 'per', '11', 'roleAuth', null, 'per:roleAuth', '1', '2017-06-06 11:26:23');
INSERT INTO `authority_permission` VALUES ('19', '2', '5', 'per', '24', 'view', null, 'per:view', '1', '2017-06-06 11:26:23');
INSERT INTO `authority_permission` VALUES ('20', '2', '5', 'per', '10', 'find', null, 'per:find', '1', '2017-06-08 14:18:37');
INSERT INTO `authority_permission` VALUES ('21', '2', '5', 'per', '11', 'roleAuth', null, 'per:roleAuth', '1', '2017-06-08 14:18:37');
INSERT INTO `authority_permission` VALUES ('22', '2', '5', 'per', '24', 'view', null, 'per:view', '1', '2017-06-08 14:18:37');
INSERT INTO `authority_permission` VALUES ('23', '2', '5', 'per', '10', 'find', null, 'per:find', '1', '2017-06-08 14:47:40');
INSERT INTO `authority_permission` VALUES ('24', '2', '5', 'per', '11', 'roleAuth', null, 'per:roleAuth', '1', '2017-06-08 14:47:40');
INSERT INTO `authority_permission` VALUES ('25', '2', '5', 'per', '24', 'view', null, 'per:view', '1', '2017-06-08 14:47:40');
INSERT INTO `authority_permission` VALUES ('26', '3', '5', 'per', '10', 'find', null, 'per:find', '1', '2017-08-02 16:14:43');
INSERT INTO `authority_permission` VALUES ('27', '3', '5', 'per', '11', 'roleAuth', null, 'per:roleAuth', '1', '2017-08-02 16:14:43');
INSERT INTO `authority_permission` VALUES ('28', '3', '5', 'per', '24', 'view', null, 'per:view', '1', '2017-08-02 16:14:43');
INSERT INTO `authority_permission` VALUES ('29', '3', '4', 'role', '8', 'findRole', null, 'role:findRole', '1', '2017-08-02 16:14:58');
INSERT INTO `authority_permission` VALUES ('30', '3', '4', 'role', '9', 'userAuth', null, 'role:userAuth', '1', '2017-08-02 16:14:58');
INSERT INTO `authority_permission` VALUES ('31', '3', '4', 'role', '23', 'view', null, 'role:view', '1', '2017-08-02 16:14:58');
INSERT INTO `authority_permission` VALUES ('32', '3', '4', 'role', '79', 'delRoleUser', null, 'role:delRoleUser', '1', '2017-08-02 16:14:58');
INSERT INTO `authority_permission` VALUES ('33', '5', '4', 'role', '8', 'findRole', null, 'role:findRole', '3', '2017-08-02 20:45:19');
INSERT INTO `authority_permission` VALUES ('34', '5', '4', 'role', '9', 'userAuth', null, 'role:userAuth', '3', '2017-08-02 20:45:19');
INSERT INTO `authority_permission` VALUES ('35', '5', '4', 'role', '23', 'view', null, 'role:view', '3', '2017-08-02 20:45:19');
INSERT INTO `authority_permission` VALUES ('36', '5', '4', 'role', '79', 'delRoleUser', null, 'role:delRoleUser', '3', '2017-08-02 20:45:19');
INSERT INTO `authority_permission` VALUES ('37', '4', '2', 'menu', '1', 'find', null, 'menu:find', '1', '2017-08-02 20:47:44');
INSERT INTO `authority_permission` VALUES ('38', '4', '2', 'menu', '2', 'update', null, 'menu:update', '1', '2017-08-02 20:47:44');
INSERT INTO `authority_permission` VALUES ('39', '4', '2', 'menu', '3', 'operManage', null, 'menu:operManage', '1', '2017-08-02 20:47:44');
INSERT INTO `authority_permission` VALUES ('40', '4', '2', 'menu', '21', 'view', null, 'menu:view', '1', '2017-08-02 20:47:44');
INSERT INTO `authority_permission` VALUES ('41', '4', '7', 'adminLog', '12', 'update', null, 'adminLog:update', '1', '2017-08-02 20:47:49');
INSERT INTO `authority_permission` VALUES ('42', '4', '7', 'adminLog', '13', 'find', null, 'adminLog:find', '1', '2017-08-02 20:47:49');
INSERT INTO `authority_permission` VALUES ('43', '4', '7', 'adminLog', '25', 'view', null, 'adminLog:view', '1', '2017-08-02 20:47:49');
INSERT INTO `authority_permission` VALUES ('44', '4', '4', 'role', '8', 'findRole', null, 'role:findRole', '1', '2017-08-02 20:48:19');
INSERT INTO `authority_permission` VALUES ('45', '4', '4', 'role', '9', 'userAuth', null, 'role:userAuth', '1', '2017-08-02 20:48:19');
INSERT INTO `authority_permission` VALUES ('46', '4', '4', 'role', '23', 'view', null, 'role:view', '1', '2017-08-02 20:48:19');
INSERT INTO `authority_permission` VALUES ('47', '4', '4', 'role', '79', 'delRoleUser', null, 'role:delRoleUser', '1', '2017-08-02 20:48:19');
INSERT INTO `authority_permission` VALUES ('49', '4', '3', 'user', '7', 'update', null, 'user:update', '1', '2017-08-02 20:49:13');
INSERT INTO `authority_permission` VALUES ('51', '4', '3', 'user', '7', 'update', null, 'user:update', '1', '2017-08-02 20:55:24');
INSERT INTO `authority_permission` VALUES ('52', '4', '3', 'user', '22', 'view', null, 'user:view', '1', '2017-08-02 20:55:24');
INSERT INTO `authority_permission` VALUES ('53', '4', '3', 'user', '4', 'find', null, 'user:find', '1', '2017-08-02 20:55:39');
INSERT INTO `authority_permission` VALUES ('55', '4', '3', 'user', '7', 'update', null, 'user:update', '1', '2017-08-02 20:55:39');
INSERT INTO `authority_permission` VALUES ('56', '4', '3', 'user', '22', 'view', null, 'user:view', '1', '2017-08-02 20:55:39');
INSERT INTO `authority_permission` VALUES ('57', '4', '3', 'user', '4', 'find', null, 'user:find', '1', '2017-08-02 20:56:14');
INSERT INTO `authority_permission` VALUES ('58', '4', '3', 'user', '7', 'update', null, 'user:update', '1', '2017-08-02 20:56:14');
INSERT INTO `authority_permission` VALUES ('59', '4', '3', 'user', '22', 'view', null, 'user:view', '1', '2017-08-02 20:56:14');

-- ----------------------------
-- Table structure for authority_roles
-- ----------------------------
DROP TABLE IF EXISTS `authority_roles`;
CREATE TABLE `authority_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `parent_id` int(11) NOT NULL COMMENT '上级角色ID',
  `enable` int(11) NOT NULL DEFAULT '1' COMMENT '1:启用角色0:停用角色',
  `last_modify_user_id` int(11) NOT NULL,
  `last_modify_datetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='权限角色表';

-- ----------------------------
-- Records of authority_roles
-- ----------------------------
INSERT INTO `authority_roles` VALUES ('1', '默认', '0', '1', '1', '2017-08-03 10:39:44');
INSERT INTO `authority_roles` VALUES ('2', '系统管理员', '1', '1', '1', '2017-08-02 13:44:44');
INSERT INTO `authority_roles` VALUES ('3', '开发', '1', '1', '1', '2017-08-02 16:07:56');
INSERT INTO `authority_roles` VALUES ('4', '测试', '1', '1', '1', '2017-08-03 14:28:40');
INSERT INTO `authority_roles` VALUES ('5', 'role2-test', '1', '1', '3', '2017-08-02 20:42:35');

-- ----------------------------
-- Table structure for authority_user_role
-- ----------------------------
DROP TABLE IF EXISTS `authority_user_role`;
CREATE TABLE `authority_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `last_modify_user_id` int(11) DEFAULT NULL COMMENT 'last_modify_user_id',
  `last_modify_datetime` datetime DEFAULT NULL COMMENT 'last_modify_datetime',
  `deleted` int(11) DEFAULT '0' COMMENT '删除标识1删除0使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- ----------------------------
-- Records of authority_user_role
-- ----------------------------
INSERT INTO `authority_user_role` VALUES ('1', '1', '2', '1', '2017-06-07 10:57:21', '0');
INSERT INTO `authority_user_role` VALUES ('3', '3', '4', '1', '2017-08-02 16:08:23', '0');
INSERT INTO `authority_user_role` VALUES ('4', '2', '5', '3', '2017-08-02 20:45:08', '0');

-- ----------------------------
-- Table structure for authority_users
-- ----------------------------
DROP TABLE IF EXISTS `authority_users`;
CREATE TABLE `authority_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `flower_name` varchar(64) DEFAULT NULL COMMENT '花名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `job` varchar(64) DEFAULT NULL COMMENT '职位',
  `num` varchar(4) DEFAULT NULL COMMENT '员工工号',
  `user_lock` int(1) DEFAULT NULL COMMENT '账号是否锁定(0:锁定1:正常)',
  `user_status` int(11) DEFAULT NULL COMMENT '用户状态',
  `expand_code` varchar(3) DEFAULT NULL COMMENT '拓展编码',
  `channel_code` varchar(7) DEFAULT NULL COMMENT '渠道编码',
  `channel_type` int(1) DEFAULT '0' COMMENT '0默认1拓展2代理',
  `proxy_type` int(1) DEFAULT '0' COMMENT '0默认1公司2个人',
  `credentials_num` varchar(50) DEFAULT NULL COMMENT '资质证件号（营业执照/身份证）',
  `credentials_img` varchar(128) DEFAULT NULL COMMENT '资质照片',
  `recharge_amount` int(11) NOT NULL DEFAULT '0' COMMENT '充值金额',
  `consumption_amount` int(11) NOT NULL DEFAULT '0' COMMENT '消费金额',
  `surplus_amount` int(11) NOT NULL DEFAULT '0' COMMENT '剩余金额',
  `receivable_amount` int(11) DEFAULT '0' COMMENT '应收账款',
  `administrator` int(1) DEFAULT NULL COMMENT '1:超级管理员0:普通用户',
  `last_modify_user_id` int(11) DEFAULT '-1' COMMENT '最后一次修改该用户信息的用户',
  `last_modify_user_email` varchar(64) DEFAULT NULL COMMENT '最后一次修改该用户信息的用户email',
  `create_user_id` int(11) DEFAULT '-1' COMMENT '创建人',
  `create_datetime` datetime DEFAULT NULL COMMENT '用户创建时间',
  `last_modify_datetime` datetime DEFAULT NULL COMMENT '最后一次修改该用户信息的时间',
  `last_login_datetime` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of authority_users
-- ----------------------------
INSERT INTO `authority_users` VALUES ('1', 'admin', '管理员', 'e10adc3949ba59abbe56e057f20f883e', '13466313726', 'admin@163.com', '男', '管理员', '0000', '1', '1', null, null, '0', '0', null, null, '0', '50', '350', '500', '0', '1', 'admin@163.com', '-1', '2016-01-18 14:55:02', '2017-08-02 14:42:27', '2017-08-03 16:32:52');
INSERT INTO `authority_users` VALUES ('2', 'role01', 'role01', 'e10adc3949ba59abbe56e057f20f883e', '18500295040', 'role01@163.com', '男', '开发', '0001', '1', '1', null, null, '0', '0', null, null, '0', '0', '0', '0', '0', '1', 'admin@163.com', '-1', '2017-08-02 16:06:35', '2017-08-02 16:06:35', '2017-08-02 20:56:53');
INSERT INTO `authority_users` VALUES ('3', 'role02', 'role02', 'e10adc3949ba59abbe56e057f20f883e', '18500295001', 'role02@163.com', '男', '测试', '0002', '1', '1', null, null, '0', '0', null, null, '0', '0', '0', '0', '0', '1', 'admin@163.com', '-1', '2017-08-02 16:07:13', '2017-08-03 14:02:43', '2017-08-02 20:52:25');
