# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.5.27
# Server OS:                    Win64
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2013-05-19 22:59:51
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for schedule
CREATE DATABASE IF NOT EXISTS `schedule` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `schedule`;


# Dumping structure for table schedule.classroom
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE IF NOT EXISTS `classroom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NOT NULL,
  `number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.classroom: ~13 rows (approximately)
DELETE FROM `classroom`;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` (`id`, `capacity`, `number`) VALUES
	(1, 90, '606'),
	(2, 30, '410'),
	(3, 120, '433'),
	(4, 30, '351'),
	(5, 30, '348'),
	(6, 30, '324'),
	(7, 30, '419'),
	(8, 30, '409'),
	(9, 30, '346'),
	(10, 30, '350'),
	(11, 30, '407'),
	(12, 30, '404'),
	(13, 30, '120');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;


# Dumping structure for table schedule.curriculum
DROP TABLE IF EXISTS `curriculum`;
CREATE TABLE IF NOT EXISTS `curriculum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam` bit(1) DEFAULT NULL,
  `hours` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `setoff` bit(1) DEFAULT NULL,
  `IdDiscipline` bigint(20) DEFAULT NULL,
  `IdSpecialty` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC5509C3B9029E4` (`IdSpecialty`),
  KEY `FKC5509C3B4A4AF91E` (`IdDiscipline`),
  CONSTRAINT `FKC5509C3B4A4AF91E` FOREIGN KEY (`IdDiscipline`) REFERENCES `discipline` (`id`),
  CONSTRAINT `FKC5509C3B9029E4` FOREIGN KEY (`IdSpecialty`) REFERENCES `specialty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.curriculum: ~14 rows (approximately)
DELETE FROM `curriculum`;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` (`id`, `exam`, `hours`, `semester`, `setoff`, `IdDiscipline`, `IdSpecialty`) VALUES
	(1, '', 30, 4, '', 1, 1),
	(2, '', 30, 4, '', 4, 1),
	(3, '', 30, 4, '', 5, 1),
	(4, '', 30, 4, '', 11, 1),
	(5, '', 30, 4, '', 9, 1),
	(6, '', 30, 4, '', 8, 1),
	(7, '', 30, 4, '', 10, 1),
	(8, '', 30, 4, '', 6, 5),
	(9, '', 30, 4, '', 14, 5),
	(10, '', 30, 4, '', 12, 5),
	(11, '', 30, 4, '', 2, 5),
	(12, '', 30, 4, '', 3, 5),
	(13, '', 30, 4, '', 13, 5),
	(14, '', 30, 4, '', 7, 5);
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;


# Dumping structure for table schedule.department
DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.department: ~12 rows (approximately)
DELETE FROM `department`;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `description`, `name`) VALUES
	(1, 'Описание кафедры читайте на сайте БГУ', 'Кафедра высшей алгебры и защиты информации '),
	(2, 'Описание кафедры читайте на сайте БГУ', 'Кафедра дифференциальных уравнений и системного анализа'),
	(3, 'Описание кафедры читайте на сайте БГУ', 'Кафедра функционального анализа'),
	(4, 'Описание кафедры читайте на сайте БГУ', 'Кафедра Веб-технологий и компьютерного моделирования '),
	(5, 'Описание кафедры читайте на сайте БГУ', 'Кафедра теории функций'),
	(6, 'Описание кафедры читайте на сайте БГУ', 'Кафедра математической кибернетики'),
	(7, 'Описание кафедры читайте на сайте БГУ', 'Кафедра геометрии, топологии и методики преподавания математики'),
	(8, 'Описание кафедры читайте на сайте БГУ', 'Кафедра общей математики и информатики'),
	(9, 'Описание кафедры читайте на сайте БГУ', 'Кафедра теоретической и прикладной механики'),
	(10, 'Описание кафедры читайте на сайте БГУ', 'Кафедра нелинейного анализа и аналитической экономики'),
	(11, 'Описание кафедры читайте на сайте БГУ', 'Учебный центр информационных технологий'),
	(12, 'Описание кафедры читайте на сайте БГУ', 'Кафедра био- и наномеханики');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


# Dumping structure for table schedule.discipline
DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `IdDisciplineType` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK157B9B70B78C1AB2` (`IdDisciplineType`),
  CONSTRAINT `FK157B9B70B78C1AB2` FOREIGN KEY (`IdDisciplineType`) REFERENCES `disciplinetype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.discipline: ~14 rows (approximately)
DELETE FROM `discipline`;
/*!40000 ALTER TABLE `discipline` DISABLE KEYS */;
INSERT INTO `discipline` (`id`, `name`, `IdDisciplineType`) VALUES
	(1, 'Численные методы', 1),
	(2, 'УМФ', 1),
	(3, 'Математическая Статистика', 1),
	(4, 'Системный анализ', 1),
	(5, 'Компьютерная математика', 1),
	(6, 'Java', 2),
	(7, 'Тестирование', 2),
	(8, 'Методы оптимизации', 2),
	(9, 'Теоретическая механика', 1),
	(10, 'МатЛаб', 2),
	(11, 'Методы оптимизации', 1),
	(12, 'УМФ', 2),
	(13, 'Математическая Статистика', 2),
	(14, 'Java', 1),
	(15, 'Численные методы1', 1);
/*!40000 ALTER TABLE `discipline` ENABLE KEYS */;


# Dumping structure for table schedule.disciplinetime
DROP TABLE IF EXISTS `disciplinetime`;
CREATE TABLE IF NOT EXISTS `disciplinetime` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `breakTime` int(11) DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.disciplinetime: ~8 rows (approximately)
DELETE FROM `disciplinetime`;
/*!40000 ALTER TABLE `disciplinetime` DISABLE KEYS */;
INSERT INTO `disciplinetime` (`id`, `breakTime`, `endTime`, `number`, `startTime`) VALUES
	(1, 0, '09:35:00', 1, '08:15:00'),
	(2, 0, '11:05:00', 2, '09:45:00'),
	(3, 0, '12:35:00', 3, '11:15:00'),
	(4, 0, '14:20:00', 4, '13:00:00'),
	(5, 0, '15:50:00', 5, '14:30:00'),
	(6, 0, '17:20:00', 6, '16:00:00'),
	(7, 0, '18:50:00', 7, '17:30:00'),
	(8, 10, '20:20:00', 8, '19:00:00');
/*!40000 ALTER TABLE `disciplinetime` ENABLE KEYS */;


# Dumping structure for table schedule.disciplinetype
DROP TABLE IF EXISTS `disciplinetype`;
CREATE TABLE IF NOT EXISTS `disciplinetype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.disciplinetype: ~2 rows (approximately)
DELETE FROM `disciplinetype`;
/*!40000 ALTER TABLE `disciplinetype` DISABLE KEYS */;
INSERT INTO `disciplinetype` (`id`, `type`) VALUES
	(1, 'lecture'),
	(2, 'practice');
/*!40000 ALTER TABLE `disciplinetype` ENABLE KEYS */;


# Dumping structure for table schedule.lecturer
DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE IF NOT EXISTS `lecturer` (
  `id` bigint(20) NOT NULL,
  `IdDepartment` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK615DA2B4328BCB89` (`id`),
  KEY `FK615DA2B4D2D75762` (`IdDepartment`),
  CONSTRAINT `FK615DA2B4328BCB89` FOREIGN KEY (`id`) REFERENCES `usermmf` (`id`),
  CONSTRAINT `FK615DA2B4D2D75762` FOREIGN KEY (`IdDepartment`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table schedule.lecturer: ~16 rows (approximately)
DELETE FROM `lecturer`;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` (`id`, `IdDepartment`) VALUES
	(11, 1),
	(8, 2),
	(13, 2),
	(18, 2),
	(9, 3),
	(4, 4),
	(5, 4),
	(6, 4),
	(15, 4),
	(17, 4),
	(19, 4),
	(7, 6),
	(10, 9),
	(12, 9),
	(14, 10),
	(16, 10);
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;


# Dumping structure for table schedule.note
DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `IdSchedule` bigint(20) DEFAULT NULL,
  `IdUser` bigint(20) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33AFF27EE100EC` (`IdSchedule`),
  KEY `FK33AFF2EB70B1D4` (`IdUser`),
  CONSTRAINT `FK33AFF27EE100EC` FOREIGN KEY (`IdSchedule`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FK33AFF2EB70B1D4` FOREIGN KEY (`IdUser`) REFERENCES `usermmf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table schedule.note: ~0 rows (approximately)
DELETE FROM `note`;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
/*!40000 ALTER TABLE `note` ENABLE KEYS */;


# Dumping structure for table schedule.schedule
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE IF NOT EXISTS `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dayOfWeek` int(11) DEFAULT NULL,
  `everyNWeek` int(11) DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `IdClassroom` bigint(20) DEFAULT NULL,
  `IdDisciplineTime` bigint(20) DEFAULT NULL,
  `IdStudy` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD66692975B7E31BA` (`IdStudy`),
  KEY `FKD66692973BA16798` (`IdDisciplineTime`),
  KEY `FKD6669297455A68CE` (`IdClassroom`),
  CONSTRAINT `FKD66692973BA16798` FOREIGN KEY (`IdDisciplineTime`) REFERENCES `disciplinetime` (`id`),
  CONSTRAINT `FKD6669297455A68CE` FOREIGN KEY (`IdClassroom`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKD66692975B7E31BA` FOREIGN KEY (`IdStudy`) REFERENCES `study` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.schedule: ~14 rows (approximately)
DELETE FROM `schedule`;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` (`id`, `dayOfWeek`, `everyNWeek`, `week`, `IdClassroom`, `IdDisciplineTime`, `IdStudy`) VALUES
	(1, 2, 1, 0, 1, 2, 1),
	(2, 2, 1, 0, 1, 3, 2),
	(3, 3, 1, 0, 6, 3, 3),
	(4, 3, 1, 0, 1, 4, 4),
	(5, 4, 1, 0, 1, 1, 5),
	(6, 4, 1, 0, 1, 3, 6),
	(7, 5, 1, 0, 2, 2, 7),
	(8, 5, 1, 0, 4, 5, 8),
	(9, 6, 1, 0, 10, 3, 9),
	(10, 6, 1, 0, 4, 4, 10),
	(11, 7, 1, 0, 1, 1, 11),
	(12, 7, 1, 0, 1, 2, 12),
	(13, 3, 1, 0, 11, 2, 13),
	(14, 4, 1, 0, 10, 4, 14);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;


# Dumping structure for table schedule.specialty
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE IF NOT EXISTS `specialty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.specialty: ~7 rows (approximately)
DELETE FROM `specialty`;
/*!40000 ALTER TABLE `specialty` DISABLE KEYS */;
INSERT INTO `specialty` (`id`, `description`, `name`) VALUES
	(1, 'Квалификация – Математик', 'Математика. Научно-производственная деятельность'),
	(2, 'Квалификация – Математик. Преподаватель математики и информатики', 'Математика. Научно-педагогическая деятельность'),
	(3, 'Квалификация – Математик. Математик – экономист', 'Математика. Экономическая деятельность'),
	(4, 'Квалификация – Математик. Конструктор программно-аппаратных систем', 'Математика. Научно-конструкторская деятельность'),
	(5, 'Квалификация – Математик. Специалист по информационным технологиям', 'Математика. Информационные технологии'),
	(6, 'Квалификация – Математик. Системный аналитик', 'Математика. Анализ и моделирование информационных систем'),
	(7, 'Квалификация – Механик. Математик – прикладник', 'Механика. Научно-производственная деятельность');
/*!40000 ALTER TABLE `specialty` ENABLE KEYS */;


# Dumping structure for table schedule.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `praepostor` bit(1) DEFAULT NULL,
  `yearOfEntrance` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `IdGroup` bigint(20) DEFAULT NULL,
  KEY `FK8FFE823B328BCB89` (`id`),
  KEY `FK8FFE823B7CA22A6` (`IdGroup`),
  CONSTRAINT `FK8FFE823B328BCB89` FOREIGN KEY (`id`) REFERENCES `usermmf` (`id`),
  CONSTRAINT `FK8FFE823B7CA22A6` FOREIGN KEY (`IdGroup`) REFERENCES `studygroup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Dumping data for table schedule.student: ~3 rows (approximately)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`praepostor`, `yearOfEntrance`, `id`, `IdGroup`) VALUES
	('', 2012, 1, 2),
	(NULL, 2011, 2, 5),
	(NULL, 2011, 3, 26);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


# Dumping structure for table schedule.study
DROP TABLE IF EXISTS `study`;
CREATE TABLE IF NOT EXISTS `study` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `IdCurriculum` bigint(20) DEFAULT NULL,
  `IdGroup` bigint(20) DEFAULT NULL,
  `IdLecturer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK68B0DC9A8C5F674` (`IdCurriculum`),
  KEY `FK68B0DC9A157D766` (`IdLecturer`),
  KEY `FK68B0DC97CA22A6` (`IdGroup`),
  CONSTRAINT `FK68B0DC97CA22A6` FOREIGN KEY (`IdGroup`) REFERENCES `studygroup` (`id`),
  CONSTRAINT `FK68B0DC9A157D766` FOREIGN KEY (`IdLecturer`) REFERENCES `lecturer` (`id`),
  CONSTRAINT `FK68B0DC9A8C5F674` FOREIGN KEY (`IdCurriculum`) REFERENCES `curriculum` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.study: ~14 rows (approximately)
DELETE FROM `study`;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` (`id`, `IdCurriculum`, `IdGroup`, `IdLecturer`) VALUES
	(1, 1, 1, 5),
	(2, 2, 1, 8),
	(3, 3, 1, 13),
	(4, 4, 1, 16),
	(5, 5, 1, 10),
	(6, 6, 1, 14),
	(7, 7, 2, 18),
	(8, 8, 5, 6),
	(9, 9, 5, 4),
	(10, 10, 5, 7),
	(11, 11, 4, 7),
	(12, 12, 4, 9),
	(13, 13, 5, 9),
	(14, 14, 5, 4);
/*!40000 ALTER TABLE `study` ENABLE KEYS */;


# Dumping structure for table schedule.studygroup
DROP TABLE IF EXISTS `studygroup`;
CREATE TABLE IF NOT EXISTS `studygroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  `IdMainGroup` bigint(20) DEFAULT NULL,
  `IdSpecialty` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FKE4341E5614CD5E0D` (`IdMainGroup`),
  KEY `FKE4341E569029E4` (`IdSpecialty`),
  CONSTRAINT `FKE4341E5614CD5E0D` FOREIGN KEY (`IdMainGroup`) REFERENCES `studygroup` (`id`),
  CONSTRAINT `FKE4341E569029E4` FOREIGN KEY (`IdSpecialty`) REFERENCES `specialty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.studygroup: ~30 rows (approximately)
DELETE FROM `studygroup`;
/*!40000 ALTER TABLE `studygroup` DISABLE KEYS */;
INSERT INTO `studygroup` (`id`, `name`, `year`, `IdMainGroup`, `IdSpecialty`) VALUES
	(1, '1', 2011, NULL, 1),
	(2, '1a', 2011, 1, 1),
	(3, '1b', 2011, 1, 1),
	(4, '2', 2011, NULL, 5),
	(5, '2b', 2011, 4, 5),
	(6, '2a', 2011, 4, 5),
	(7, '3', 2008, NULL, 2),
	(8, '3b', 2008, 7, 2),
	(9, '3a', 2008, 7, 2),
	(10, '4', 2008, NULL, 6),
	(11, '4b', 2008, 10, 6),
	(12, '4a', 2008, 10, 6),
	(13, '5', 2008, NULL, 4),
	(14, '5b', 2008, 13, 4),
	(15, '5a', 2008, 13, 4),
	(16, '6', 2008, NULL, 3),
	(17, '6b', 2008, 16, 3),
	(18, '6a', 2008, 16, 3),
	(19, '7', 2008, NULL, 7),
	(20, '7b', 2008, 19, 7),
	(21, '7a', 2008, 19, 7),
	(22, '8', 2008, NULL, 7),
	(23, '8b', 2008, 22, 7),
	(24, '8a', 2008, 22, 7),
	(25, '9', 2008, NULL, 5),
	(26, '9b', 2008, 25, 5),
	(27, '9a', 2008, 25, 5),
	(28, '10', 2008, NULL, 1),
	(29, '10b', 2008, 28, 1),
	(30, '10a', 2008, 28, 1);
/*!40000 ALTER TABLE `studygroup` ENABLE KEYS */;


# Dumping structure for table schedule.usermmf
DROP TABLE IF EXISTS `usermmf`;
CREATE TABLE IF NOT EXISTS `usermmf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin` bit(1) NOT NULL,
  `login` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `passwordFormat` varchar(255) NOT NULL,
  `passwordSalt` varchar(255) NOT NULL,
  `patronymic` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

# Dumping data for table schedule.usermmf: ~19 rows (approximately)
DELETE FROM `usermmf`;
/*!40000 ALTER TABLE `usermmf` DISABLE KEYS */;
INSERT INTO `usermmf` (`id`, `admin`, `login`, `name`, `password`, `passwordFormat`, `passwordSalt`, `patronymic`, `surname`) VALUES
	(1, '', 'yasvedko', 'Светлана', '70d0b1fbf6e5cb5fd4486bc295b99ac4149d23f6', 'SHA-1', 'Y9OAsne8vfk=', 'Геннадьевна', 'Войтех'),
	(2, '', 'verofeev', 'Виктория', 'i7sHiLeOH0WH9nFqP4iA8WdNlTs=', 'SHA-1', 'YVvDzXNCZwo=', NULL, 'Ерофеева'),
	(3, '', 'varvara', 'Варвара', 'lEzs4E0Y7IH6uuAf0dmFRzCIS24=', 'SHA-1', 'XGGTdFsWOPs=', NULL, 'Тимошкина'),
	(4, '', 'suzdal', 'Станислав', '1H8M7ArYttfoBrQipfJDkvXpwrI=', 'SHA-1', '/PdMGJK2rxE=', 'Валерьевич', 'Суздаль'),
	(5, '', 'volkov', 'Василий', 'lbCWkw2ia3XaIjMrkIpSvE6UQC4=', 'SHA-1', 'cdIoqeMp5J0=', 'Михайлович', 'Волков'),
	(6, '', 'iblinov', 'Игорь', 'OR+SRMrdsbMZLmlLLWql6tUsuJk=', 'SHA-1', 'J49u1IoUI5I=', 'Николаевич', 'Блинов'),
	(7, '', 'urchyk', 'Николай', 'x2oLyyDFJtTrn3DqwF+4kexPOow=', 'SHA-1', 'Tftq8EJ8MBY=', 'Иосифович', 'Юрчук'),
	(8, '', 'atrohov', 'Кирилл', 'waF9UJ4g4UtGy1XMDsaSjXMqxfk=', 'SHA-1', 'RgrMTsq6Sz8=', 'Георгиевич', 'Атрохов'),
	(9, '', 'yablonskii', 'Олег', '4XzvGokRVYzvXDFuRni0bDftuQ8=', 'SHA-1', 'AhEUBdr7MCg=', 'Леонилович', 'Яблонский'),
	(10, '', 'savchyk', 'Владимир', 'H+Pi2qDAg94H1f7YVfa7jim6LxY=', 'SHA-1', 'EkeYnRb1cqc=', 'Петрович', 'Савчук'),
	(11, '', 'vasil', 'Денис', 'WxqEsIyybmULxlII8V/IyDYK9Gc=', 'SHA-1', 'IGnqz8S6kFg=', 'Владимирович', 'Васильев'),
	(12, '', 'protop', 'Борис', 'Fh3x2x/dmj1CObTKu8yrMaXqRjI=', 'SHA-1', 'vrquFGaT/Zo=', 'Егорович', 'Протапопов'),
	(13, '', 'lipnic', 'Валерий', 'qxJ308IdXO8tfRecF4xE8d9AJig=', 'SHA-1', 'fTHYD4HZDYM=', 'Антонович', 'Липницкий'),
	(14, '', 'pindrik', 'Ольга', 'yDljptTU9K5+aXtBYsUby/dezSI=', 'SHA-1', 'zIE2g9FjxuU=', 'Исааковна', 'Пиндрик'),
	(15, '', 'yakimen', 'Татьяна', 'oDQWJxHoGC8y3dZUsBwEPczdqpA=', 'SHA-1', 'yKmI2Asv31Y=', 'Семеновна', 'Якименко'),
	(16, '', 'goroh', 'Валентин', 'UuwyfWah4phLjMeP0hM/Eg3a1ic=', 'SHA-1', '2hqWOiTQINE=', 'Викентьевич', 'Гороховик'),
	(17, '', 'stelm', 'Сергей', 'Y4gLr6STvAkozNoLf8k+lG3r7WA=', 'SHA-1', 'Ci39z5sGiG0=', 'Николаевич', 'Стельмах'),
	(18, '', 'grigor', 'Антон', 'sx2M/JQ1ivACDR0GYRoaBLLVpQ4=', 'SHA-1', 'fzxwrtXQJxY=', 'Александрович', 'Григорьев'),
	(19, '', 'radaeva', 'Валентина', 'gk+DxCGOlQmYGlxT+fSipF2+4AA=', 'SHA-1', 'kgmxCyH94Vg=', 'Анатольевна', 'Радаева');
/*!40000 ALTER TABLE `usermmf` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
