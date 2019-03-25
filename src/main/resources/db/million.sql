/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : million

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-03-25 18:10:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for million_association
-- ----------------------------
DROP TABLE IF EXISTS `million_association`;
CREATE TABLE `million_association` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `BLOGID` int(18) DEFAULT NULL,
  `LABELID` int(18) NOT NULL,
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '0下线  1上线',
  `CREATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客标签关联表';

-- ----------------------------
-- Records of million_association
-- ----------------------------

-- ----------------------------
-- Table structure for million_blog
-- ----------------------------
DROP TABLE IF EXISTS `million_blog`;
CREATE TABLE `million_blog` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `USERID` int(18) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `EVALNUM` int(11) DEFAULT '0',
  `VIEWNUM` int(11) DEFAULT '0',
  `CONTENT` varchar(1200) DEFAULT NULL,
  `CROPCONTENT` varchar(800) DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  `ISEVAL` char(1) DEFAULT '1' COMMENT '0不可评价 1可以评价',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of million_blog
-- ----------------------------

-- ----------------------------
-- Table structure for million_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `million_evaluation`;
CREATE TABLE `million_evaluation` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `BLOGID` int(11) DEFAULT NULL,
  `HEADIMG` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  `WEBLINK` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `TYPE` char(1) DEFAULT NULL COMMENT '1.博客评价 2.评价回复 3.留言',
  `EVALUATION` int(18) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of million_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for million_label
-- ----------------------------
DROP TABLE IF EXISTS `million_label`;
CREATE TABLE `million_label` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of million_label
-- ----------------------------

-- ----------------------------
-- Table structure for million_type
-- ----------------------------
DROP TABLE IF EXISTS `million_type`;
CREATE TABLE `million_type` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of million_type
-- ----------------------------

-- ----------------------------
-- Table structure for million_user
-- ----------------------------
DROP TABLE IF EXISTS `million_user`;
CREATE TABLE `million_user` (
  `ID` int(18) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `WEBLINK` varchar(255) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of million_user
-- ----------------------------
