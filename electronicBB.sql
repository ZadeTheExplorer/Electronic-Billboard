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
  UPDATE users SET names.password=newPsw WHERE names.id=id;
END $$

DELIMITER ;

call addUser(2, 'Edward','user2','password','edit user');