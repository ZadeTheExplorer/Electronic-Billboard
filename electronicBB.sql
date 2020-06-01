CREATE DATABASE IF NOT EXISTS electronicBB; $$

USE electronicBB; $$

DROP TABLE IF EXISTS `electronicBB`.`users`; $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`users`(
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(45) NOT NULL default '',
  `username` varchar(30) NOT NULL default 'username',
  `password` varchar(50) NOT NULL default 'password',
  `privilege` VARCHAR(50) default 'edit user, edit schedule, edit billboard'

) ENGINE=MyISAM DEFAULT CHARSET=latin1; $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUsers`; $$

CREATE PROCEDURE `electronicBB`.`displayUsers` ()
BEGIN
  SELECT * FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUserColumnId`; $$

CREATE PROCEDURE `electronicBB`.`displayUserColumnId` ()
BEGIN
  SELECT id FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUserColumnName`; $$

CREATE PROCEDURE `electronicBB`.`displayUserColumnName` ()
BEGIN
    SELECT name FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUserColumnUserName`; $$

CREATE PROCEDURE `electronicBB`.`displayUserColumnUserName` ()
BEGIN
    SELECT username FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUserColumnUserPassword`; $$

CREATE PROCEDURE `electronicBB`.`displayUserColumnUserPassword` ()
BEGIN
    SELECT password FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUserColumnUserPrivilege`; $$

CREATE PROCEDURE `electronicBB`.`displayUserColumnUserPrivilege` ()
BEGIN
    SELECT privilege FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getUserInfo`; $$

CREATE PROCEDURE `electronicBB`.`getUserInfo` (IN id int(3))
BEGIN
    SELECT * FROM users WHERE users.id = id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addUser`; $$

CREATE PROCEDURE `electronicBB`.`addUser` (IN name varchar(45),
                                            IN username varchar(45), IN password varchar(45),IN privilege VARCHAR(50))
BEGIN
  INSERT INTO users(name, username, password, privilege) VALUES(name, username, password, privilege);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteUser`; $$

CREATE PROCEDURE `electronicBB`.`deleteUser` (IN id int(3))
BEGIN
  DELETE FROM users WHERE users.id=id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updatePassword`; $$

CREATE PROCEDURE `electronicBB`.`updatePassword` (IN id INT, IN newPsw VARCHAR(45))
BEGIN
  UPDATE users SET users.password=newPsw WHERE users.id=id;
END $$

DROP TABLE IF EXISTS `electronicBB`.`billboards`; $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`billboards` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(45) NOT NULL,
  `user_id` int(3) NOT NULL,
  `background_color` varchar(7) default '#FFFFFF',
  `message_color` varchar(7) default '#ff0000',
  `information_color` varchar(7) default '#000000',
  `url` varchar(100),
  `message` varchar(100) default '',
  `information` varchar(100) default '',

  FOREIGN KEY (`user_id`) REFERENCES users.id (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1; $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayAllBillboards`; $$

CREATE PROCEDURE `electronicBB`.`displayAllBillboards` ()
BEGIN
  SELECT * FROM billboards;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayBillBoardColumnId`; $$

CREATE PROCEDURE `electronicBB`.`displayBillBoardColumnId` ()
BEGIN
    SELECT id FROM billboards;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getBillboardInfo`; $$

CREATE PROCEDURE `electronicBB`.`getBillboardInfo` (IN id int(3))
BEGIN
    SELECT * FROM users WHERE billboards.id = id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getBillboardIdByUserId`; $$

CREATE PROCEDURE `electronicBB`.`getBillboardIdByUserId` (IN id int(3))
BEGIN
    SELECT id FROM billboards WHERE billboards.user_id = id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addBillboard`; $$

CREATE PROCEDURE `electronicBB`.`addBillboard` (IN name varchar(45),
                                            IN user_id int(3), IN background_color varchar(7),IN message_color VARCHAR(7),
                                            IN information_color varchar(7), IN url varchar(100), IN message varchar(100), IN information varchar(100))
BEGIN
  INSERT INTO billboards(name, user_id, background_color, message_color, information_color, url, message, information) VALUES(name, user_id, background_color, message_color, information_color, url, message, information);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteBillboard`; $$

CREATE PROCEDURE `electronicBB`.`deleteBillboard` (IN id int(3))
BEGIN
  DELETE FROM billboards WHERE billboards.id=id;
END $$

DROP TABLE IF EXISTS `electronicBB`.`schedules`; $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`schedules` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `billboard_id` int(3) NOT NULL,
  `user_id` int(45) NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  `weekdays` VARCHAR(10) NOT NULL,
  FOREIGN KEY (billboard_id) REFERENCES billboards.id (billboard_id),
  FOREIGN KEY (user_id) REFERENCES users.id (user_id)

) ENGINE=MyISAM DEFAULT CHARSET=latin1; $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayAllSchedules`; $$

CREATE PROCEDURE `electronicBB`.`displayAllSchedules` ()
BEGIN
  SELECT * FROM schedules;
END $$
DROP PROCEDURE IF EXISTS `electronicBB`.`getScheduleInfo`; $$

CREATE PROCEDURE `electronicBB`.`getScheduleInfo` (IN id int(3))
BEGIN
    SELECT * FROM schedules WHERE schedules.id = id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getScheduleIdByBillboardId`; $$

CREATE PROCEDURE `electronicBB`.`getScheduleIdByBillboardId` (IN id int(3))
BEGIN
    SELECT id FROM schedules WHERE schedules.billboard_id = id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getScheduleIdByUserId`; $$

CREATE PROCEDURE `electronicBB`.`getScheduleIdByUserId` (IN id int(3))
BEGIN
    SELECT id FROM schedules WHERE schedules.user_id = id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addSchedule`; $$

CREATE PROCEDURE `electronicBB`.`addSchedule` (IN billboard_id int(3), IN user_id int(3),
                                            IN start_time TIME, IN end_time TIME,IN weekdays VARCHAR(10))
BEGIN
  INSERT INTO schedules(billboard_id, user_id, start_time, end_time, weekdays) VALUES(billboard_id, user_id, start_time, end_time, weekdays);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteSchedule`; $$

CREATE PROCEDURE `electronicBB`.`deleteSchedule` (IN id int(3))

BEGIN
  DELETE FROM schedules WHERE schedules.id=id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updateStart`; $$

CREATE PROCEDURE `electronicBB`.`updateStart` (IN id INT, IN newStart_time TIME)
BEGIN
  UPDATE schedules SET schedules.start_time=newStart_time WHERE schedules.id=id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updateEnd`; $$

CREATE PROCEDURE `electronicBB`.`updateEnd` (IN id INT, IN newEnd_time TIME)
BEGIN
  UPDATE schedules SET schedules.end_time=newEnd_time WHERE schedules.id=id;
END $$

call addSchedule(1, 1, '08:00:00','10:00:00', 'Monday'); $$
call addSchedule(1,1, '10:00:11', '22:10:00', 'Thusday'); $$
call addSchedule(1, 1, '08:00:00','10:00:00', 'Monday'); $$
call addSchedule(1,1, '10:00:11', '22:10:00', 'Thusday'); $$
call addSchedule(1, 1, '08:00:00','10:00:00', 'Monday'); $$
call addSchedule(1,1, '10:00:11', '22:10:00', 'Thusday'); $$