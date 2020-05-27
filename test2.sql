USE electronicBB;

--DROP TABLE IF EXISTS `electronicBB`.`users`;

CREATE TABLE  IF NOT EXISTS `electronicBB`.`schedule` (
  `id` int(3) unsigned NOT NULL default '0',
  `billboard_id` int(3) NOT NULL,
  `user_id` int(45) NOT NULL,
  `start_time` TIME (0) NOT NULL,
  `end_time` TIME (0) NOT NULL,
  `weekdays` int(1) NOT NULL default WEEKDAY(CURRENT())
  FOREIGN KEY (billboard_id) REFERENCES billboards.id (billboard_id),
  FOREIGN KEY (user_id) REFERENCES users.id (user_id)
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