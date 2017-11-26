/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : management
Target Host     : localhost:3306
Target Database : management
Date: 2017-11-12 13:01:10
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for arrange
-- ----------------------------
DROP TABLE IF EXISTS `arrange`;
CREATE TABLE `arrange` (
  `TeacherName` varchar(255) DEFAULT NULL,
  `ArrangeId` int(100) NOT NULL AUTO_INCREMENT,
  `TeacherAccount` varchar(255) NOT NULL,
  `StudentAccount` varchar(255) NOT NULL,
  `ArrangeTime` varchar(255) DEFAULT NULL,
  `ArrangeReason` varchar(255) DEFAULT NULL,
  `commitornot` varchar(255) DEFAULT NULL,
  `SuccessFail` varchar(255) DEFAULT NULL,
  `StudentName` varchar(255) DEFAULT NULL,
  `StudentMajor` varchar(255) DEFAULT NULL,
  `StudentPhone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ArrangeId`),
  KEY `StudentAccount` (`StudentAccount`),
  KEY `TeacherAccount_arrange` (`TeacherAccount`),
  CONSTRAINT `StudentAccount` FOREIGN KEY (`StudentAccount`) REFERENCES `student` (`StudentAccount`),
  CONSTRAINT `TeacherAccount_arrange` FOREIGN KEY (`TeacherAccount`) REFERENCES `teacher` (`TeacherAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of arrange
-- ----------------------------
INSERT INTO `arrange` VALUES ('阿杜', '40', '1', '1', 'TuePm', 'hj', 'true', 'Success', '张永顺', '计算机科学与技术', 'jh');
INSERT INTO `arrange` VALUES ('战德臣', '41', '2', '1', 'MonAm', '项目咨询', 'true', 'Success', '张永顺', '计算机科学与技术', '17745135600');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MonAm` varchar(255) DEFAULT 'none',
  `MonPm` varchar(255) DEFAULT 'none',
  `TueAm` varchar(255) DEFAULT 'none',
  `TuePm` varchar(255) DEFAULT 'none',
  `WedAm` varchar(255) DEFAULT 'none',
  `WedPm` varchar(255) DEFAULT 'none',
  `ThuAm` varchar(255) DEFAULT 'none',
  `ThuPm` varchar(255) DEFAULT 'none',
  `FriAm` varchar(255) DEFAULT 'none',
  `FriPm` varchar(255) DEFAULT 'none',
  `TeacherAccount` varchar(255) NOT NULL DEFAULT 'none',
  PRIMARY KEY (`id`),
  KEY `TeacherAccount_schedule` (`TeacherAccount`),
  CONSTRAINT `TeacherAccount_schedule` FOREIGN KEY (`TeacherAccount`) REFERENCES `teacher` (`TeacherAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('1', 'hj', '1', 'hj', '张永顺预约\r\n:预约理由:hj', '12', 'none', 'none', 'none', 'none', 'none', '1');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Picture` varchar(255) DEFAULT NULL,
  `StudentAccount` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `StudentName` varchar(255) NOT NULL,
  `Major` varchar(255) DEFAULT NULL,
  `College` varchar(255) DEFAULT NULL,
  `Grade` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`StudentAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('images/girl.jpg', '1', '1', '张永顺', '计算机科学与技术', '计算机学院', '2015', '18846419912', null);
INSERT INTO `student` VALUES (null, '1150310108', '000000', '罗伯特', '计算机科学与技术', '哈尔滨工业大学', '大三', '18800428839', '2224127900@qq.com');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Baike` varchar(255) DEFAULT NULL,
  `Money` varchar(255) DEFAULT NULL,
  `TeacherAccount` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `TeacherName` varchar(255) NOT NULL,
  `Picture` varchar(255) DEFAULT NULL,
  `College` varchar(255) DEFAULT NULL,
  `Position` varchar(255) DEFAULT NULL,
  `ResearchDirection` varchar(255) DEFAULT NULL,
  `Record` varchar(255) DEFAULT NULL,
  `FSRA` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TeacherAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/%E5%88%98%E5%AE%8F%E4%BC%9F/1942746?fr=aladdin', '10000000', '1', '1', '刘宏伟', 'images/girl.jpg', '计算机学院', '教授', '计算思维', '2002----目前：哈尔滨工业大学计算机科学与技术学院教授、博士生导', '国家一等奖', '18846419912', '10000001@qq.com');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/%E6%88%98%E5%BE%B7%E8%87%A3/1937013?fr=aladdin', '100', '2', '2', '战德臣', 'images/me.jpg', '哈尔滨工业大学', '哈尔滨工业大学计算机科学与技术学院教授、博士生导师', '计算思维', '1986----1989：毕业于哈尔滨工业大学计算机应用专业获硕士学位\r\n1990----1993：毕业于哈尔滨工业大学计算机应用专业获博士学位\r\n1989----1992：哈尔滨工业大学计算机科学与工程系助教\r\n1992----1995：哈尔滨工业大学计算机科学与工程系讲师\r\n1995----1999：哈尔滨工业大学计算机科学与工程系副教授\r\n1999----2002：哈尔滨工业大学计算机科学与技术学院教授\r\n2002----目前：哈尔滨工业大学计算机科学与技术学院教授、博士生导师', '国家一等奖', '10000001', '10000001@qq.com');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/%E6%88%98%E5%BE%B7%E8%87%A3/1937013?fr=aladdin', '100', '3', '3', '徐汉川', 'images/me.jpg', '哈尔滨工业大学', '哈尔滨工业大学计算机科学与技术学院教授、博士生导师', '计算思维', '2002----目前：哈尔滨工业大学计算机科学与技术学院教授、博士生导', '国家一等奖', '18846419912', '10000001@qq.com');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/%E5%88%98%E5%AE%8F%E4%BC%9F/1942746?fr=aladdin', '100', '4', '4', '姜守旭', 'images/me.jpg', 'a', 'a', 'a', 'a', 'a', 'a', 'a');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/%E5%88%98%E5%AE%8F%E4%BC%9F/1942746?fr=aladdin', '100', '5', '5', '王义和', 'images/me.jpg', 'b', 'b', 'b', 'b', 'b', 'b', 'b');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/%E5%88%98%E5%AE%8F%E4%BC%9F/1942746?fr=aladdin', '100', '6', '6', '唐朔飞', 'images/me.jpg', 'c', 'c', 'c', 'c', 'c', 'c', 'c');
