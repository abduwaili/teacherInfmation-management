/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : management
Target Host     : localhost:3306
Target Database : management
Date: 2017-12-08 10:25:51
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `Id` int(20) NOT NULL,
  `AdminName` varchar(20) NOT NULL,
  `AdminPassword` varchar(20) NOT NULL,
  `RealName` varchar(20) NOT NULL,
  `Email` varchar(20) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Photo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'abduwaili', 'abduwaili', '阿卜杜外力', '2696272844@qq.com', '17745135600', null);

-- ----------------------------
-- Table structure for arrange
-- ----------------------------
DROP TABLE IF EXISTS `arrange`;
CREATE TABLE `arrange` (
  `TeacherName` varchar(255) DEFAULT NULL,
  `ArrangeId` int(100) NOT NULL AUTO_INCREMENT,
  `TeacherAccount` varchar(255) NOT NULL,
  `StudentAccount` varchar(255) NOT NULL,
  `StartHour` varchar(255) DEFAULT NULL,
  `StartMinutes` varchar(255) DEFAULT NULL,
  `EndHour` varchar(255) DEFAULT NULL,
  `EndMinutes` varchar(255) DEFAULT NULL,
  `AllDay` varchar(255) DEFAULT NULL,
  `ArrangeDay` varchar(255) DEFAULT NULL,
  `ArrangeMonth` varchar(255) DEFAULT NULL,
  `ArrangeYear` varchar(255) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of arrange
-- ----------------------------

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Year` varchar(255) DEFAULT 'none',
  `AllDay` varchar(255) DEFAULT NULL,
  `Month` varchar(255) DEFAULT 'none',
  `Day` varchar(255) DEFAULT 'none',
  `StartHour` varchar(255) DEFAULT 'none',
  `StartMinutes` varchar(255) DEFAULT 'none',
  `EndHour` varchar(255) DEFAULT 'none',
  `EndMinutes` varchar(255) DEFAULT 'none',
  `Arrange` varchar(255) DEFAULT 'none',
  `TeacherAccount` varchar(255) NOT NULL DEFAULT 'none',
  `IsWatch` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TeacherAccount_schedule` (`TeacherAccount`),
  CONSTRAINT `TeacherAccount_schedule` FOREIGN KEY (`TeacherAccount`) REFERENCES `teacher` (`TeacherAccount`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Picture` varchar(255) DEFAULT 'http://tongtong-teacherorder.stor.sinaapp.com/student-img/me.jpg',
  `StudentAccount` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `StudentName` varchar(255) NOT NULL,
  `Major` varchar(255) DEFAULT NULL,
  `College` varchar(255) DEFAULT NULL,
  `Grade` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `IsSiginUp` varchar(10) NOT NULL DEFAULT 'False',
  PRIMARY KEY (`StudentAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('http://tongtong-teacherorder.stor.sinaapp.com/student-img/1.jpg', '1', '1', '张永顺1212', '计算机科学与技术', '计算机学院', '2015', '18846419912', '2696272844@qq.com', 'True');
INSERT INTO `student` VALUES ('http://tongtong-teacherorder.stor.sinaapp.com/student-img/2.jpg', '2', '2', '2', '2', '2', null, '2', null, 'True');

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
  `Picture` varchar(255) DEFAULT 'http://tongtong-teacherorder.stor.sinaapp.com/teacher-img/me.jpg',
  `College` varchar(255) DEFAULT NULL,
  `Position` varchar(255) DEFAULT NULL,
  `ResearchDirection` varchar(255) DEFAULT NULL,
  `Record` varchar(255) DEFAULT NULL,
  `FSRA` varchar(255) DEFAULT NULL,
  `Phone` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `IsSiginUp` varchar(10) NOT NULL DEFAULT 'False',
  PRIMARY KEY (`TeacherAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/刘宏伟/20400972?fr=aladdin', '10000000', '1', '1', '刘宏伟', 'http://tongtong-teacherorder.stor.sinaapp.com/teacher-img/1.jpg', '计算机学院', '哈尔滨工业大学计算机科学与技术学院教授', '计算机', '刘宏伟，博士，教授，博士生导师，哈工大计算机科学与技术学院计算机硬件基础教研室主任，中国计算机学会高级会员，中国计算机学会体系结构专业委员会常委、中国计算机学会容错计算专业委员会专委。', '国家一等奖', '18846419912', '10000001@qq.com', 'True');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/战德臣/1937013?fr=aladdin', '100', '2', '2', '战德臣', 'http://tongtong-teacherorder.stor.sinaapp.com/teacher-img/me.jpg', '计算机学院', '哈尔滨工业大学计算机科学与技术学院教授', '计算思', '战德臣，国家工业自动化系统与集成标准化技术委员会委员 黑龙江省制造业信息化专家组成员 国家863/CIMS主题企业管理与电子商务系统专题工作组成员。', '国家一等奖', '18846419912', '10000001@qq.com', 'True');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/姜守旭/10900364?fr=aladdin', '100', '4', '4', '姜守旭', 'http://tongtong-teacherorder.stor.sinaapp.com/teacher-img/me.jpg', '计算机学院', '哈尔滨工业大学计算机科学与技术学院教授', '计算机', '中国计算机学会高级会员\r\nYOCSEF哈尔滨学术委员会委员\r\n“计算机科学与技术核心课程”国家级教学团队成员\r\n中国民主促进会会员\r\n哈尔滨市南岗区第12届政协委员', 'a', '18846419912', '10000001@qq.com', 'True');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/王义和/46153?fr=aladdin', '100', '5', '5', '王义和', 'http://tongtong-teacherorder.stor.sinaapp.com/teacher-img/me.jpg', '计算机学院', '哈尔滨工业大学计算机科学与技术学院教授', '就三级', '王义和，哈尔滨工业大学计算机教授。主讲集合与图论、形式语言、组合数学等计算机基础理论、专业理论。', 'b', '18846419912', '10000001@qq.com', 'True');
INSERT INTO `teacher` VALUES ('https://baike.baidu.com/item/唐朔飞/5718942?fr=aladdin', '100', '6', '6', '唐朔飞', 'http://tongtong-teacherorder.stor.sinaapp.com/teacher-img/me.jpg', '计算机学院', '哈尔滨工业大学计算机科学与技术学院教授', '计算机', '第八、九届哈尔滨市政治协商委员会委员，现任校、院教学督导组成员。1999年被评为哈工大“十佳三育人”标兵。自1986年获哈工大首届教学一等奖后，又获得各级各类教学奖励18次，2000年获黑龙江省教学成果一等奖。', 'c', '18846419912', '10000001@qq.com', 'True');
