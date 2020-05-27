CREATE DATABASE IF NOT EXISTS electronicBB;

USE electronicBB;

--DROP TABLE IF EXISTS `electronicBB`.`users`;

CREATE TABLE  IF NOT EXISTS `electronicBB`.`users` (
  `id` int(3) unsigned NOT NULL default '0',
  `name` varchar(45) NOT NULL default '',
  `username` varchar(30) NOT NULL default 'username',
  `password` varchar(50) NOT NULL default 'password',
  `privilege` VARCHAR(50) default 'edit user, edit schedule, edit billboard',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DELIMITER $$

DROP PROCEDURE IF EXISTS `electronicBB`.`display` $$
CREATE PROCEDURE `electronicBB`.`display` ()
BEGIN
  SELECT * FROM users;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addUser` $$
CREATE PROCEDURE `electronicBB`.`addUser` (IN id int(3), IN name varchar(45),
                                            IN username varchar(45), IN password varchar(45),IN privilege VARCHAR(50))
BEGIN
  INSERT INTO users VALUES(id, name, username, password, privilege);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deletePerson` $$
CREATE PROCEDURE `electronicBB`.`deletePerson` (IN id int(10))

BEGIN
  DELETE FROM users WHERE users.id=id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updatePassword` $$
CREATE PROCEDURE `electronicBB`.`updatePassword` (IN id INT, IN newPsw VARCHAR(45))
BEGIN
  UPDATE users SET users.password=newPsw WHERE users.id=id;
END $$

DELIMITER ;

call addUser(1, 'Patrick', 'user', 'password', 'edit user');
call addUser(2, 'Edward','user2','password','edit user');


--DROP TABLE IF EXISTS `electronicBB`.`users`;

CREATE TABLE  IF NOT EXISTS `electronicBB`.`billboards` (
  `id` int(3) unsigned NOT NULL default '0',
  `name` varchar(45) NOT NULL,
  `user_id` int(3) NOT NULL,
  `background_color` varchar(7) default '#FFFFFF',
  `message_color` varchar(7) default '#ff0000',
  `information_color` varchar(7) default '#000000',
  `url` varchar(100),
  `message` varchar(100) default '',
  `information` varchar(100) default '',
  PRIMARY KEY  (`id`),
  FOREIGN KEY (`user_id`) REFERENCES users.id (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DELIMITER $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayAllBillboards` $$
CREATE PROCEDURE `electronicBB`.`displayAllBillboards` ()
BEGIN
  SELECT * FROM billboards;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addBillboard` $$
CREATE PROCEDURE `electronicBB`.`addBillboard` (IN id int(3), IN name varchar(45),
                                            IN user_id int(3), IN background_color varchar(7),IN message_color VARCHAR(7),
                                            IN information_color varchar(7), IN url varchar(100), IN message varchar(100), IN information varchar(100))
BEGIN
  INSERT INTO billboards VALUES(id, name, user_id, background_color, message_color, information_color, url, message, information);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteBillboard` $$
CREATE PROCEDURE `electronicBB`.`deleteBillboard` (IN id int(3))

BEGIN
  DELETE FROM billboards WHERE billboards.id=id;
END $$

DELIMITER ;

call addBillboard(1, 'COVID-19', 1, 'white', 'red', 'black', 'https://d2v9ipibika81v.cloudfront.net/uploads/sites/40/COVID-19.jpg', 'Wash your hand', 'Stay at home!');

--DROP TABLE IF EXISTS `electronicBB`.`schedules`;
CREATE TABLE  IF NOT EXISTS `electronicBB`.`schedules` (
  `id` int(3) unsigned NOT NULL default '0',
  `billboard_id` int(3) NOT NULL,
  `user_id` int(45) NOT NULL,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  `weekdays` VARCHAR(10) NOT NULL,
  FOREIGN KEY (billboard_id) REFERENCES billboards.id (billboard_id),
  FOREIGN KEY (user_id) REFERENCES users.id (user_id),
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DELIMITER $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayAllSchedules` $$
CREATE PROCEDURE `electronicBB`.`displayAllSchedules` ()
BEGIN
  SELECT * FROM schedules;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addSchedule` $$
CREATE PROCEDURE `electronicBB`.`addSchedule` (IN id int(3), IN billboard_id int(3), IN user_id int(3),
                                            IN start_time TIME, IN end_time TIME,IN weekdays VARCHAR(10))
BEGIN
  INSERT INTO schedules VALUES(id, billboard_id, user_id, start_time, end_time, weekdays);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteSchedule` $$
CREATE PROCEDURE `electronicBB`.`deleteSchedule` (IN id int(3))

BEGIN
  DELETE FROM schedule WHERE schedules.id=id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updateStart` $$
CREATE PROCEDURE `electronicBB`.`updateStart` (IN id INT, IN newStart_time TIME)
BEGIN
  UPDATE schedules SET schedules.start_time=newStart_time WHERE schedules.id=id;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`updateEnd` $$
CREATE PROCEDURE `electronicBB`.`updateEnd` (IN id INT, IN newEnd_time TIME)
BEGIN
  UPDATE schedules SET schedules.end_time=newEnd_time WHERE schedules.id=id;
END $$

-- ADDING OR SUBTRACT TIME: USING ADDTIME(originalTime, amout) and SUBTIME()
-- OR USING TIMEDIFF(end, start)

-- TIME_FORMAT (start_at, '%h:%i %p') start at; // Ex: 10:10 AM
DELIMITER ;
-- TIME: HH:MM:SS or HHH:MM:SS if >24hrs
-- OR: HHMMSS
call addSchedule(1, 1, 1, '08:00:00','10:00:00', 'Monday');