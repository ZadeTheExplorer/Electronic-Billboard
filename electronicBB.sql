CREATE DATABASE IF NOT EXISTS electronicBB; $$

USE electronicBB; $$

DROP TABLE IF EXISTS `electronicBB`.`users`; $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`users`(
  `user_name` varchar(30) NOT NULL PRIMARY KEY,
  `salt` varchar(300) NOT NULL,
  `saltedPassword` varchar(300) NOT NULL,
  `privilege` VARCHAR(100) NOT NULL

) ENGINE=MyISAM DEFAULT CHARSET=latin1; $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUsers`; $$

CREATE PROCEDURE `electronicBB`.`displayUsers` ()
BEGIN
  SELECT user_name, privilege FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUserColumnUserName`; $$

CREATE PROCEDURE `electronicBB`.`displayUserColumnUserName` ()
BEGIN
    SELECT user_name FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUserColumnUserPrivilege`; $$

CREATE PROCEDURE `electronicBB`.`displayUserColumnUserPrivilege` ()
BEGIN
    SELECT privilege FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getUserInfo`; $$

CREATE PROCEDURE `electronicBB`.`getUserInfo` (IN user_name varchar(45))
BEGIN
    SELECT * FROM users WHERE users.user_name = user_name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addUser`; $$

CREATE PROCEDURE `electronicBB`.`addUser` (IN user_name varchar(45), IN salt varchar(300),
                                        IN saltedPassword varchar(300), IN privilege varchar(200))
BEGIN
  INSERT INTO users VALUES(user_name, salt, saltedPassword, privilege);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteUser`; $$

CREATE PROCEDURE `electronicBB`.`deleteUser` (IN user_name varchar(45))
BEGIN
  DELETE FROM users WHERE users.user_name=user_name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updatePassword`; $$

CREATE PROCEDURE `electronicBB`.`updatePassword` (IN user_name varchar(45), IN salt varchar(300), IN saltedPassword varchar(300))
BEGIN
  UPDATE users SET users.salt=salt, users.saltedPassword=saltedPassword  WHERE users.user_name=user_name;
END $$

DROP TABLE IF EXISTS `electronicBB`.`billboards`; $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`billboards` (
  `name` varchar(45) NOT NULL PRIMARY KEY,
  `user_name` int(3) NOT NULL,
  `background_color` varchar(7) default '#FFFFFF',
  `message_color` varchar(7) default '#ff0000',
  `information_color` varchar(7) default '#000000',
  `url` varchar(200),
  `message` varchar(100) default '',
  `information` varchar(100) default ''

) ENGINE=MyISAM DEFAULT CHARSET=latin1; $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayAllBillboards`; $$

CREATE PROCEDURE `electronicBB`.`displayAllBillboards` ()
BEGIN
  SELECT * FROM billboards;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayBillBoardColumnName`; $$

CREATE PROCEDURE `electronicBB`.`displayBillBoardColumnName` ()
BEGIN
    SELECT billboards.name FROM billboards;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getBillboardInfo`; $$

CREATE PROCEDURE `electronicBB`.`getBillboardInfo` (IN name varchar(45))
BEGIN
    SELECT * FROM users WHERE billboards.name = name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getBillboardIdByUserName`; $$

CREATE PROCEDURE `electronicBB`.`getBillboardIdByUserName` (IN user_name varchar(45))
BEGIN
    SELECT billboards.name FROM billboards WHERE billboards.user_name = user_name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addBillboard`; $$

CREATE PROCEDURE `electronicBB`.`addBillboard` (IN name varchar(45), IN user_name varchar(45), IN background_color varchar(7),IN message_color VARCHAR(7),
                                            IN information_color varchar(7), IN url varchar(200), IN message varchar(100), IN information varchar(100))
BEGIN
  INSERT INTO billboards VALUES(name, user_name, background_color, message_color, information_color, url, message, information);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteBillboard`; $$

CREATE PROCEDURE `electronicBB`.`deleteBillboard` (IN bbName varchar(45))
BEGIN
  DELETE FROM billboards WHERE billboards.name=bbName;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`editBillboard`; $$

CREATE PROCEDURE `electronicBB`.`editBillboard` (IN name varchar(45), IN user_name varchar(45), IN background_color varchar(7),IN message_color VARCHAR(7),
                                            IN information_color varchar(7), IN url varchar(200), IN message varchar(100), IN information varchar(100))
BEGIN
    UPDATE billboards
    SET billboard.user_name=user_name, billboard.background_color=background_color, billboard.message_color=message_color,
     billboard.information_color=information_color, billboard.url=url, billboard.message=message, billboard.information=information
    WHERE billboard.name=name;
END $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`schedules` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `billboard_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  `weekdays` VARCHAR(10) NOT NULL,
  FOREIGN KEY (billboard_name) REFERENCES billboards.name (billboard_name),
  FOREIGN KEY (user_name) REFERENCES users.user_name (user_name)

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

DROP PROCEDURE IF EXISTS `electronicBB`.`getScheduleIdByBillboardName`; $$

CREATE PROCEDURE `electronicBB`.`getScheduleIdByBillboardName` (IN id int(3))
BEGIN
    SELECT id FROM schedules WHERE schedules.billboard_name = name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getScheduleIdByUserName`; $$

CREATE PROCEDURE `electronicBB`.`getScheduleIdByUserName` (IN id int(3))
BEGIN
    SELECT id FROM schedules WHERE schedules.user_name = name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addSchedule`; $$

CREATE PROCEDURE `electronicBB`.`addSchedule` (IN billboard_name varchar(45), IN user_name varchar(45),
                                            IN start_time TIME, IN end_time TIME,IN weekdays VARCHAR(10))
BEGIN
  INSERT INTO schedules(billboard_name, user_nname, start_time, end_time, weekdays) VALUES(billboard_id, user_id, start_time, end_time, weekdays);
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
END
