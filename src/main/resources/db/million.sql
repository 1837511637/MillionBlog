/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : million

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-22 19:39:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for million_ad
-- ----------------------------
DROP TABLE IF EXISTS `million_ad`;
CREATE TABLE `million_ad` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `POSITION` varchar(255) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `WEBLINK` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `STARTTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL COMMENT '0下线 1上线',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for million_blog
-- ----------------------------
DROP TABLE IF EXISTS `million_blog`;
CREATE TABLE `million_blog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) DEFAULT NULL,
  `TYPEID` int(11) DEFAULT NULL COMMENT '分类id',
  `TITLE` varchar(255) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `EVALNUM` int(11) DEFAULT '0',
  `VIEWNUM` int(11) DEFAULT '0',
  `CONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `CROPCONTENT` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '0下线 1上线 -1草稿',
  `ISEVAL` char(1) DEFAULT '1' COMMENT '0不可评价 1可以评价',
  `LABELIDS` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `TYPENAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for million_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `million_evaluation`;
CREATE TABLE `million_evaluation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BLOGID` int(11) DEFAULT NULL,
  `HEADIMG` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  `WEBLINK` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `TYPE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '1.博客评价 2.回复 3.留言 4.留言回复 5.轻语评论',
  `CONTENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `FIRSTEVALID` int(11) DEFAULT NULL,
  `ISUSER` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '0不是作者  1是作者',
  `REPLYID` int(11) DEFAULT NULL,
  `REPLYNAME` varchar(255) DEFAULT NULL,
  `REPLYWEBLINK` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for million_label
-- ----------------------------
DROP TABLE IF EXISTS `million_label`;
CREATE TABLE `million_label` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for million_type
-- ----------------------------
DROP TABLE IF EXISTS `million_type`;
CREATE TABLE `million_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for million_user
-- ----------------------------
DROP TABLE IF EXISTS `million_user`;
CREATE TABLE `million_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `HEADIMG` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `WEBLINK` varchar(255) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for million_whisper
-- ----------------------------
DROP TABLE IF EXISTS `million_whisper`;
CREATE TABLE `million_whisper` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `HEADIMG` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `STATUS` char(1) DEFAULT '1' COMMENT '0下线 1上线',
  `IP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
