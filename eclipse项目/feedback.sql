/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : management
Target Host     : localhost:3306
Target Database : management
Date: 2017-12-11 16:06:00
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `Id` int(10) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `Subject` varchar(200) DEFAULT NULL,
  `Datails` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
