USE electronicBB;

--DROP TABLE IF EXISTS `electronicBB`.`users`;

CREATE TABLE  IF NOT EXISTS `electronicBB`.`schedule` (
  `id` int(3) unsigned NOT NULL default '0',
  `billboard_id` int(3) NOT NULL,
  `user_id` int(45) NOT NULL,
  -- SELECT TIMEDIFF("13:10:11", "13:10:10"); CHECK THE FUKDISOUT
   --Timestamp and date
  --`background_color` varchar(7) default '#FFFFFF',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DELIMITER $$

DROP PROCEDURE IF EXISTS `electronicBB`.`displayAllBillboards` $$
CREATE PROCEDURE `electronicBB`.`displayAllBillboards` ()
BEGIN
  SELECT * FROM billboards;
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`addBillboard` $$
CREATE PROCEDURE `electronicBB`.`addBillboard` (IN id int(3), IN name varchar(45),
                                            IN username varchar(45), IN password varchar(45),IN privilege VARCHAR(50))
BEGIN
  INSERT INTO users VALUES(id, name, username, password, privilege);
END $$

DROP PROCEDURE IF EXISTS `electronicBB`.`deleteBillboard` $$
CREATE PROCEDURE `electronicBB`.`deleteBillboard` (IN id int(10))

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
call addUser(1, 'Patrick', 'user', 'password', 'edit user');