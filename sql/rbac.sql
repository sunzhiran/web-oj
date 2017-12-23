/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-12-23 20:39:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `class_id` varchar(255) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `class_grade` int(255) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('20130101', '2013软件工程', '2013');
INSERT INTO `class` VALUES ('20130102', '2013计算机', '2013');
INSERT INTO `class` VALUES ('20140101', '2014软件工程', '2014');
INSERT INTO `class` VALUES ('20140102', '2014计算机', '2014');
INSERT INTO `class` VALUES ('20150101', '2015软件工程', '2015');
INSERT INTO `class` VALUES ('20150102', '2015计算机', '2015');

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `homework_id` int(11) NOT NULL AUTO_INCREMENT,
  `homework_title` varchar(255) DEFAULT NULL,
  `homework_time` datetime DEFAULT NULL,
  `homework_deadline` datetime DEFAULT NULL,
  `homework_content` varchar(255) DEFAULT NULL,
  `homework_writer` varchar(255) DEFAULT NULL,
  `homework_is_delete` int(255) DEFAULT '0',
  `homework_attachment` varchar(255) DEFAULT NULL,
  `homework_class` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`homework_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('1', '第一课', '2017-03-01 15:28:08', null, '课后练习', '0101', '1', null, null);
INSERT INTO `homework` VALUES ('9', null, '2017-05-05 11:15:06', null, null, '0101', '1', null, null);
INSERT INTO `homework` VALUES ('13', 'å¾åä¹ä¹å', '2017-05-05 11:37:57', null, 'å¡è²ç»¯è°è°é®é®ååååå', '0101', '1', null, null);
INSERT INTO `homework` VALUES ('19', '123', '2017-05-09 14:10:33', '2017-05-18 00:00:00', null, '0101', '0', 'H:/bishe/upload-files/homework/19/实验2.doc', '20130101');
INSERT INTO `homework` VALUES ('21', 'jsp', '2017-05-09 14:40:13', '2017-04-30 00:00:00', null, '0101', '0', 'H:/bishe/upload-files/homework/21/实验2.doc', '20140101');
INSERT INTO `homework` VALUES ('22', 'jsp', '2017-05-10 15:23:02', '2017-05-19 00:00:00', null, '0101', '0', null, '20130101');
INSERT INTO `homework` VALUES ('23', 'java', '2017-05-12 11:43:32', '2017-05-19 20:00:00', null, '0101', '0', 'H:/bishe/upload-files/homework/23/实验1.doc', '20130101');
INSERT INTO `homework` VALUES ('24', 'java', '2017-05-20 10:56:11', '2017-04-30 00:00:00', null, '0101', '0', 'H:/bishe/upload-files/homework/24/实验5.doc', '20130101');
INSERT INTO `homework` VALUES ('25', 'jsp课后作业', '2017-05-21 11:21:33', '2017-05-01 00:00:00', null, '0101', '0', 'H:/bishe/upload-files/homework/25/实验1.doc', '20130101');
INSERT INTO `homework` VALUES ('26', '标题', '2017-05-21 18:00:30', '2017-05-16 11:00:00', null, '0101', '0', 'H:/bishe/upload-files/homework/26/实验7.doc', '20130101');

-- ----------------------------
-- Table structure for hw_stu
-- ----------------------------
DROP TABLE IF EXISTS `hw_stu`;
CREATE TABLE `hw_stu` (
  `hw_stu_id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` varchar(255) NOT NULL,
  `hw_id` int(255) NOT NULL,
  `paper_url` varchar(255) DEFAULT NULL,
  `code_url` varchar(255) DEFAULT NULL,
  `project_url` varchar(255) DEFAULT NULL,
  `sql_url` varchar(255) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `hw_stu_url` varchar(255) DEFAULT NULL,
  `hw_stu_sim` double(255,2) DEFAULT NULL,
  `hw_stu_sim_Id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hw_stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hw_stu
-- ----------------------------
INSERT INTO `hw_stu` VALUES ('50', '2013010101', '23', 'H:/bishe/upload-files/student/23/8a7f3169d408b8b4fa8b4fc0554e406e/毕设.docx', 'H:/bishe/upload-files/student/23/8a7f3169d408b8b4fa8b4fc0554e406e/simpleweb.zip', 'http://47.93.232.241:8080/simpleweb/', 'H:/bishe/upload-files/student/23/8a7f3169d408b8b4fa8b4fc0554e406e/simpleweb.sql', '2017-05-22 22:40:23', 'H:/bishe/upload-files/student/23/8a7f3169d408b8b4fa8b4fc0554e406e', null, null);
INSERT INTO `hw_stu` VALUES ('51', '2013010102', '23', 'H:/bishe/upload-files/student/23/9deb6e490bfdc630e04352b0b7f96b60/毕设.docx', 'H:/bishe/upload-files/student/23/9deb6e490bfdc630e04352b0b7f96b60/simpleweb001.zip', 'http://47.93.232.241:8080/simpleweb001/', 'H:/bishe/upload-files/student/23/9deb6e490bfdc630e04352b0b7f96b60/simpleweb001.sql', '2017-05-22 22:44:29', 'H:/bishe/upload-files/student/23/9deb6e490bfdc630e04352b0b7f96b60', '95.80', '2013010101');
INSERT INTO `hw_stu` VALUES ('52', '2013010103', '23', 'H:/bishe/upload-files/student/23/61863f7261a132481ea67b9fbc4bd3d9/毕设.docx', 'H:/bishe/upload-files/student/23/61863f7261a132481ea67b9fbc4bd3d9/simpleweb002.zip', 'http://47.93.232.241:8080/simpleweb002/', 'H:/bishe/upload-files/student/23/61863f7261a132481ea67b9fbc4bd3d9/simpleweb002.sql', '2017-05-23 09:54:28', 'H:/bishe/upload-files/student/23/61863f7261a132481ea67b9fbc4bd3d9', '100.00', '2013010102');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` varchar(255) NOT NULL,
  `manager_name` varchar(255) DEFAULT NULL,
  `manager_password` varchar(255) DEFAULT NULL,
  `manager_sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('001', '李宏亮', '1234', '男');
INSERT INTO `manager` VALUES ('002', '刘玉梅', '1234', '女');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_content` varchar(255) DEFAULT NULL,
  `notice_writer` varchar(255) DEFAULT NULL,
  `notice_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '网站于3.1日进行维护，停止使用', '', '2017-05-08 14:27:50');
INSERT INTO `notice` VALUES ('2', '网站于3.2日用于考试，停止使用正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行正常运行', '', '2017-05-08 14:29:02');
INSERT INTO `notice` VALUES ('3', '3.3日正常运行', '', null);
INSERT INTO `notice` VALUES ('4', '作业1的截至时间有变动，截止到5.3日 ，请注意', '', '2017-05-09 19:40:56');
INSERT INTO `notice` VALUES ('5', '5月20日系统维护，暂停使用', '0101', '2017-05-20 09:16:54');
INSERT INTO `notice` VALUES ('6', 'QWERETRTR', '2013010101', '2017-12-07 11:59:27');
INSERT INTO `notice` VALUES ('7', 'QWER', '2013010101', '2017-12-07 12:00:57');

-- ----------------------------
-- Table structure for rbac_access
-- ----------------------------
DROP TABLE IF EXISTS `rbac_access`;
CREATE TABLE `rbac_access` (
  `rbac_access_id` int(11) NOT NULL AUTO_INCREMENT,
  `rbac_access_title` varchar(255) DEFAULT NULL,
  `rbac_access_urls` varchar(255) DEFAULT NULL,
  `rbac_access_status` int(11) DEFAULT '1',
  PRIMARY KEY (`rbac_access_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_access
-- ----------------------------
INSERT INTO `rbac_access` VALUES ('1', '权限1', 'login', '1');
INSERT INTO `rbac_access` VALUES ('2', '权限2', 'index', '1');

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE `rbac_role` (
  `rbac_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `rbac_role_name` varchar(255) DEFAULT NULL,
  `rbac_role_status` int(255) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`rbac_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_role
-- ----------------------------
INSERT INTO `rbac_role` VALUES ('1', '学生', '1');
INSERT INTO `rbac_role` VALUES ('2', '教师', '1');
INSERT INTO `rbac_role` VALUES ('3', '管理员', '1');
INSERT INTO `rbac_role` VALUES ('4', '销售', '1');
INSERT INTO `rbac_role` VALUES ('5', '销售经理', '1');

-- ----------------------------
-- Table structure for rbac_role_access
-- ----------------------------
DROP TABLE IF EXISTS `rbac_role_access`;
CREATE TABLE `rbac_role_access` (
  `rbac_role_access_id` int(11) NOT NULL AUTO_INCREMENT,
  `rbac_role_access_rid` int(11) DEFAULT NULL,
  `rbac_role_access_aid` int(11) DEFAULT NULL,
  `rbac_role_access_status` int(11) DEFAULT '1',
  PRIMARY KEY (`rbac_role_access_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_role_access
-- ----------------------------
INSERT INTO `rbac_role_access` VALUES ('1', '1', '1', '1');

-- ----------------------------
-- Table structure for rbac_user
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user`;
CREATE TABLE `rbac_user` (
  `rbac_user_id` varchar(255) NOT NULL,
  `rbac_user_name` varchar(255) DEFAULT NULL,
  `rbac_user_password` varchar(255) DEFAULT NULL,
  `rbac_user_class` varchar(255) DEFAULT NULL,
  `rbac_user_is_admin` int(11) DEFAULT '0',
  `rbac_user_status` int(11) DEFAULT '1',
  PRIMARY KEY (`rbac_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user
-- ----------------------------
INSERT INTO `rbac_user` VALUES ('001', '李宏亮', '1234', null, '0', '1');
INSERT INTO `rbac_user` VALUES ('0101', '爱因斯坦', '1234', null, '0', '1');
INSERT INTO `rbac_user` VALUES ('0102', '图灵', '1234', null, '0', '1');
INSERT INTO `rbac_user` VALUES ('0103', '谭浩强', '1234', '', '0', '1');
INSERT INTO `rbac_user` VALUES ('0104', '严蔚敏', '1234', '', '0', '1');
INSERT INTO `rbac_user` VALUES ('2013010101', '安琪', '1234', '20130101', '0', '1');
INSERT INTO `rbac_user` VALUES ('2013010102', '毕涵', '1234', '20130101', '0', '1');
INSERT INTO `rbac_user` VALUES ('2013010103', '小范', '1234', '20130101', '0', '1');
INSERT INTO `rbac_user` VALUES ('2013010201', '小川', '1234', '20130102', '0', '1');
INSERT INTO `rbac_user` VALUES ('2013010202', '小凳子', '1234', '20130102', '0', '1');

-- ----------------------------
-- Table structure for rbac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `rbac_user_role`;
CREATE TABLE `rbac_user_role` (
  `rbac_user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `rbac_user_role_uid` varchar(255) DEFAULT NULL,
  `rbac_user_role_rid` int(11) DEFAULT NULL,
  `rbac_user_role_status` int(11) DEFAULT '1',
  PRIMARY KEY (`rbac_user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rbac_user_role
-- ----------------------------
INSERT INTO `rbac_user_role` VALUES ('1', '2013010101', '1', '1');
INSERT INTO `rbac_user_role` VALUES ('2', '2013010202', '1', '1');
INSERT INTO `rbac_user_role` VALUES ('3', '2013010103', '1', '1');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` varchar(255) NOT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `teacher_password` varchar(255) DEFAULT NULL,
  `teacher_sex` varchar(255) DEFAULT NULL,
  `teacher_note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('0101', '爱因斯坦', '1234', '男', 'null');
INSERT INTO `teacher` VALUES ('0102', '图灵', '1234', '男', '');
INSERT INTO `teacher` VALUES ('0103', '谭浩强', '1234', '男', null);
INSERT INTO `teacher` VALUES ('0104', '严蔚敏', '1234', '女', null);
INSERT INTO `teacher` VALUES ('0105', '刘汝佳', '1234', '男', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_class` varchar(255) DEFAULT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  `user_type` int(255) DEFAULT '1' COMMENT '1 is student 2 is teacher',
  `user_note` varchar(255) DEFAULT NULL,
  `rbac_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2013010101', '安琪', '1234', '20130101', '女', '1', null, '2013010101');
INSERT INTO `user` VALUES ('2013010102', '毕涵', '1234', '20130101', '女', '1', null, null);
INSERT INTO `user` VALUES ('2013010103', '菜菜', '1234', '20130101', '女', '1', null, null);
INSERT INTO `user` VALUES ('2013010104', '啊啊', '1234', '20130101', '男', '1', null, null);
INSERT INTO `user` VALUES ('2013010105', '丁一', '1234', '20140101', '男', '1', '', null);
INSERT INTO `user` VALUES ('2013010107', '强强', '1234', '20130101', '男', '1', null, null);
INSERT INTO `user` VALUES ('2013010108', '威威', '1234', '20130101', '男', '1', null, null);
INSERT INTO `user` VALUES ('2013010109', '娥娥', '1234', '20130101', '女', '1', null, null);
INSERT INTO `user` VALUES ('2013010110', '荣荣', '1234', '20130101', '男', '1', null, null);
INSERT INTO `user` VALUES ('2013010201', '小真', '1234', '20130102', '女', '1', null, null);
INSERT INTO `user` VALUES ('2013010202', '小红', '1234', '20130102', '男', '1', null, null);
INSERT INTO `user` VALUES ('2013010203', '小川', '1234', '20130102', '男', '1', null, null);
INSERT INTO `user` VALUES ('2014010101', '千万', '1234', '20140101', '男', '1', null, null);
INSERT INTO `user` VALUES ('2014010102', '企鹅', '1234', '20140101', '男', '1', null, null);
INSERT INTO `user` VALUES ('2014010103', '李磊', '1234', '20140101', '男', '1', null, null);
INSERT INTO `user` VALUES ('2014010201', '王依', '1234', '20140102', '女', '1', '', null);
INSERT INTO `user` VALUES ('2014010202', '刘尔', '1234', '20140102', '男', '1', null, null);
INSERT INTO `user` VALUES ('2015010101', '豆豆', '1234', '20150101', '男', '1', '', null);
INSERT INTO `user` VALUES ('2015010102', '一一', '1234', '20150101', '女', '1', null, null);
INSERT INTO `user` VALUES ('2015010201', 'qq', '1234', '20150102', '男', '1', null, null);
