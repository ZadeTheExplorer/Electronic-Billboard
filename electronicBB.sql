CREATE DATABASE IF NOT EXISTS electronicBB; $$

USE electronicBB; $$

DROP TABLE IF EXISTS `electronicBB`.`users`; $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`users`(
  `user_name` varchar(30) NOT NULL PRIMARY KEY,
  `salt` varchar(300) NOT NULL,
  `saltedPassword` varchar(300) NOT NULL,
  `privileges` VARCHAR(100) NOT NULL

) ENGINE=MyISAM DEFAULT CHARSET=latin1; $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayUsers`; $$

CREATE PROCEDURE `electronicBB`.`displayUsers` ()
BEGIN
  SELECT user_name, privileges FROM users;
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
                                        IN saltedPassword varchar(300), IN privileges varchar(200))
BEGIN
  INSERT INTO users VALUES(user_name, salt, saltedPassword, privileges);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteUser`; $$

CREATE PROCEDURE `electronicBB`.`deleteUser` (IN user_name varchar(45))
BEGIN
  DELETE FROM users WHERE users.user_name=user_name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getUserPrivileges`; $$

CREATE PROCEDURE `electronicBB`.`getUserPrivileges` (IN user_name varchar(45))
BEGIN
    SELECT users.privileges FROM users WHERE users.user_name = user_name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`setUserPrivileges`; $$

CREATE PROCEDURE `electronicBB`.`setUserPrivileges` (IN user_name varchar(45), IN privileges varchar(100))
BEGIN
  UPDATE users SET users.privileges=privileges  WHERE users.user_name=user_name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updatePassword`; $$

CREATE PROCEDURE `electronicBB`.`updatePassword` (IN user_name varchar(45), IN salt varchar(300), IN saltedPassword varchar(300))
BEGIN
  UPDATE users SET users.salt=salt, users.saltedPassword=saltedPassword  WHERE users.user_name=user_name;
END $$

DROP TABLE IF EXISTS `electronicBB`.`billboards`; $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`billboards` (
  `name` varchar(150) NOT NULL PRIMARY KEY,
  `user_name` varchar(150) NOT NULL,
  `background_color` varchar(7) default '#FFFFFF',
  `message_color` varchar(7) default '#ff0000',
  `information_color` varchar(7) default '#000000',
  `url` varchar(200),
  `message` varchar(100) default '',
  `information` varchar(300) default ''
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
                                            IN information_color varchar(7), IN url varchar(200), IN message varchar(100), IN information varchar(300))
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
    SET billboards.user_name=user_name, billboards.background_color=background_color, billboards.message_color=message_color,
        billboards.information_color=information_color, billboards.url=url, billboards.message=message, billboards.information=information
    WHERE billboards.name=name;
END $$

CREATE TABLE  IF NOT EXISTS `electronicBB`.`schedules` (
  `billboard_name` varchar(45) NOT NULL,
  `start_time` TIME NOT NULL,
  `duration` TIME NOT NULL,
  `weekday` varchar(10) NOT NULL,
  CONSTRAINT PK_schedules PRIMARY KEY (billboard_name, start_time),
  FOREIGN KEY (billboard_name) REFERENCES billboards.name (billboard_name)
) ENGINE=MyISAM DEFAULT CHARSET=latin1; $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayAllSchedules`; $$

CREATE PROCEDURE `electronicBB`.`displayAllSchedules` ()
BEGIN
  SELECT * FROM schedules;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`getScheduleInfo`; $$

CREATE PROCEDURE `electronicBB`.`getScheduleInfo` (IN billboard_name varchar(50))
BEGIN
    SELECT * FROM schedules WHERE schedules.billboard_name = billboard_name;
END $$



DROP PROCEDURE IF EXISTS `electronicBB`.`addSchedule`; $$

CREATE PROCEDURE `electronicBB`.`addSchedule` (IN billboard_name varchar(45),
                                            IN start_time TIME, IN duration TIME, IN weekday varchar(10))
BEGIN
  INSERT INTO schedules(billboard_name, start_time, duration, weekday) VALUES(billboard_name, start_time, duration, weekday);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteSchedule`; $$

CREATE PROCEDURE `electronicBB`.`deleteSchedule` (IN billboard_name varchar(45),
                                            IN start_time TIME)

BEGIN
  DELETE FROM schedules
  WHERE schedules.billboard_name=billboard_name AND schedules.start_time=start_time;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updateStart`; $$

CREATE PROCEDURE `electronicBB`.`updateStart` (IN billboard_name varchar(50),IN oldStart_time TIME, IN newStart_time TIME)
BEGIN
  UPDATE schedules SET schedules.start_time=newStart_time WHERE schedules.start_time=oldStart_time AND schedules.billboard_name = billboard_name;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updateDuration`; $$

CREATE PROCEDURE `electronicBB`.`updateDuration` (IN billboard_name varchar(50),IN startTime TIME, IN duration TIME)
BEGIN
  UPDATE schedules SET schedules.duration=duration WHERE schedules.start_time=startTime AND schedules.billboard_name = billboard_name;
END
