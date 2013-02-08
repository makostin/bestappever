# --------------------------------------------------------
# Host:                         sdc
# Server version:               5.1.48-community
# Server OS:                    Win64
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2013-02-07 13:51:23
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
# Dumping data for table rez.classroom: ~13 rows (approximately)
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` (`ID`, `Number`) VALUES
  (13, '120'),
	(6, '324'),
	(9, '346'),
	(5, '348'),
	(10, '350'),
	(4, '351'),
	(12, '404'),
	(11, '407'),
	(8, '409'),
	(2, '410'),
	(7, '419'),
	(3, '433'),
	(1, '606');
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;

# Dumping data for table rez.department: ~12 rows (approximately)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`ID`, `Name`) VALUES
	(1, 'Кафедра алгебры и защиты информации '),
	(12, 'Кафедра био- и наномеханики'),
	(4, 'Кафедра Веб-технологий и компьютерного моделирования '),
	(7, 'Кафедра геометрии, топологии и методики преподавания математики'),
	(2, 'Кафедра дифференциальных уравнений и системного анализа'),
	(6, 'Кафедра математической кибернетики'),
	(10, 'Кафедра нелинейного анализа и аналитической экономики'),
	(8, 'Кафедра общей математики и информатики'),
	(9, 'Кафедра теоретической и прикладной механики'),
	(5, 'Кафедра теории функций'),
	(3, 'Кафедра функционального анализа'),
	(11, 'Учебный центр информационных технологий');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

# Dumping data for table rez.grouppair: ~42 rows (approximately)
/*!40000 ALTER TABLE `grouppair` DISABLE KEYS */;
INSERT INTO `grouppair` (`ID_Lesson`, `ID_SubGroup`) VALUES
	(4, 1),
	(4, 2),
	(5, 2),
	(6, 1),
	(6, 2),
	(7, 1),
	(7, 2),
	(9, 1),
	(9, 2),
	(10, 1),
	(10, 2),
	(11, 1),
	(11, 2),
	(12, 1),
	(12, 2),
	(13, 1),
	(13, 2),
	(14, 1),
	(14, 2),
	(15, 1),
	(15, 2),
	(16, 1),
	(16, 2),
	(17, 1),
	(17, 2),
	(18, 1),
	(18, 2),
	(19, 1),
	(19, 2),
	(20, 1),
	(20, 2),
	(21, 2),
	(22, 1),
	(22, 2),
	(23, 1),
	(23, 2),
	(24, 1),
	(24, 2),
	(25, 1),
	(26, 1),
	(27, 1),
	(27, 2);
/*!40000 ALTER TABLE `grouppair` ENABLE KEYS */;

# Dumping data for table rez.lesson: ~23 rows (approximately)
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` (`ID`, `ID_Subject`, `ID_Classroom`, `Time`, `Day`, `ID_User`, `StartWeek`, `EndWeek`, `Period`) VALUES
	(4, 1, 1, '14:30', 2, 5, 6, 21, 1),
	(5, 1, 2, '17:30', 5, 15, 6, 21, 1),
	(6, 2, 3, '13:00', 3, 7, 6, 21, 1),
	(7, 2, 4, '14:30', 4, 7, 6, 21, 1),
	(9, 4, 2, '14:30', 3, 8, 6, 21, 2),
	(10, 3, 5, '16:00', 3, 9, 6, 21, 1),
	(11, 3, 5, '16:00', 4, 9, 6, 21, 1),
	(12, 4, 6, '16:00', 5, 8, 7, 21, 2),
	(13, 11, 7, '16:00', 2, 11, 6, 21, 1),
	(14, 11, 8, '17:20', 2, 11, 6, 21, 1),
	(15, 9, 9, '17:30', 3, 12, 6, 21, 1),
	(16, 8, 5, '11:15', 7, 14, 6, 21, 1),
	(17, 9, 1, '17:30', 4, 10, 6, 21, 1),
	(18, 6, 10, '19:00', 4, 17, 7, 21, 1),
	(19, 10, 8, '16:00', 5, 5, 6, 21, 2),
	(20, 7, 8, '14:30', 6, 6, 7, 21, 2),
	(21, 5, 11, '14:30', 6, 13, 6, 21, 2),
	(22, 6, 1, '16:00', 6, 16, 7, 21, 1),
	(23, 5, 3, '17:30', 6, 13, 6, 21, 2),
	(24, 8, 5, '13:00', 7, 14, 6, 21, 1),
	(25, 1, 12, '17:30', 5, 19, 6, 21, 1),
	(26, 5, 13, '14:30', 3, 18, 7, 21, 2),
	(27, 7, 7, '13:00', 6, 6, 6, 21, 2);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;

# Dumping data for table rez.subgroup: ~4 rows (approximately)
/*!40000 ALTER TABLE `subgroup` DISABLE KEYS */;
INSERT INTO `subgroup` (`ID`, `Number`, `Year`) VALUES
	(1, '9a', 2008),
	(2, '9b', 2008),
	(3, '2b', 2008),
	(4, '2a', 2008);
/*!40000 ALTER TABLE `subgroup` ENABLE KEYS */;

# Dumping data for table rez.subject: ~12 rows (approximately)
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`ID`, `Name`) VALUES
	(5, 'Компьютерная математика'),
	(11, 'Компьютерные сети'),
	(3, 'Математическая Статистика'),
	(10, 'МатЛаб'),
	(8, 'Методы оптимизации'),
	(4, 'Системный анализ'),
	(9, 'Теоретическая механика'),
	(7, 'Тестирование'),
	(2, 'УМФ'),
	(6, 'Философия'),
	(1, 'Численные методы'),
	(15, 'ыыы');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;

# Dumping data for table rez.user: ~19 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`, `Name`, `Login`, `Password`, `ID_SubGroup`, `PasswordSalt`, `PasswordFormat`, `ID_UserRole`, `ID_Department`) VALUES
	(1, 'Sveta', 'yasvedko', '6LYO+ZcuMaDDMASY32JnEG9Wq5k=', 2, '1cWqu6bebBE=', 'SHA-1', 1, NULL),
	(2, 'Max', 'mkostin', '881Tv7Un0fhuwshDaIPWRMgqYjs=', 1, 'igXbZDq3604=', 'SHA-1', 1, NULL),
	(3, 'Varvara', 'varvara', 'u/ocoUzNG4ob7AkoWalTKAQUXJ8=', 1, 'ALdZdlxCxGQ=', 'SHA-1', 1, NULL),
	(4, 'Суздаль С.В.', 'suzdal', '9UxTJRZR4MW0cZjocrvPlowFC94=', NULL, 'R9r3UUzxzsk=', 'SHA-1', 2, 4),
	(5, 'Волков В.М.', 'volkov', 'bWF4sB79UZiAg3+w1r1y41t0l60=', NULL, 'bGkhpyXKauY=', 'SHA-1', 2, 4),
	(6, 'Крылов Е.В.', 'ekrylov', 'vakZ9SWDCATRN4cbA/QP/hZsU+E=', NULL, 'nkFyIP65sTM=', 'SHA-1', 2, 4),
	(7, 'Юрчук Н.И.', 'urchyk', 'GACYDZeLjc4WJrqpXaZxy6Rw8TE=', NULL, '1RF01ttypCQ=', 'SHA-1', 2, 6),
	(8, 'Атрохов Кирилл', 'atrohov', 'Na3cNOLojV0n+NHaIL5aO/Eniqc=', NULL, 'MzfajY9vPR4=', 'SHA-1', 2, 2),
	(9, 'Яблонский О.Л.', 'yablonskii', 'LjoPgANHWr5NZHHz/l3B/Nw/3qI=', NULL, 'aMmqa040Gzo=', 'SHA-1', 2, 3),
	(10, 'Савчук В.П.', 'savchyk', 'HapfU3leLc2o9g8oE7/b1MPyR2k=', NULL, 'qACmuKx4DwU=', 'SHA-1', 2, 9),
	(11, 'Мухаметов В.Н.', 'myham', '3qd+RBg5ljgTjT61pTjvMo8eH3g=', NULL, 'ae+NV8TQwVA=', 'SHA-1', 2, NULL),
	(12, 'Протапопов Б.Е.', 'protop', '0hvfBTt4fbz2392b3D0Ilrp45jI=', NULL, '+RTp65aIuMU=', 'SHA-1', 2, 9),
	(13, 'Липницкий В.А.', 'lipnic', 'jO9EaOa7D0WuekxSLTNZea1jTOM=', NULL, '4t/35devRB8=', 'SHA-1', 2, 2),
	(14, 'Пиндрик О.И.', 'pindrik', 'NbwHfnqxCkIkYABuyt2u2zQrzmQ=', NULL, '4FZgzYnZb1w=', 'SHA-1', 2, 10),
	(15, 'Якименко Т.С.', 'yakimen', 'RngahZPcGeJaoRPrkumJNnwsL8U=', NULL, 'HBX9NiiBu70=', 'SHA-1', 2, 4),
	(16, 'Медведева И.А.', 'medved', 'Gf9AsULYnk3d03RISnADci4IIOc=', NULL, 'dMks2ZSZWh8=', 'SHA-1', 2, NULL),
	(17, 'Сергей Сергеевич', 'sergei', 'ENrbiFNb/lkWUmq0xAbzyRPvB9s=', NULL, '6B9hoVBW4QU=', 'SHA-1', 2, NULL),
	(18, 'Григорьев А.А.', 'grigor', 'q67mnByLN7FKE8uX0XIUglfP4kM=', NULL, 'eJJD/RYxDbQ=', 'SHA-1', 2, 2),
	(19, 'Радаева В.А.', 'radaeva', 'mZyZDCKjYTuu+cn/pJHbqQBy9SY=', NULL, '5ugSUw9uApE=', 'SHA-1', 2, 4);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

# Dumping data for table rez.userrole: ~3 rows (approximately)
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` (`ID`, `Role`) VALUES
	(3, 'admin'),
	(2, 'lecturer'),
	(1, 'student');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
