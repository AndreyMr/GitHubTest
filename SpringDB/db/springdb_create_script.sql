CREATE SCHEMA `springdb` ;
CREATE TABLE `springdb`.`mp3` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`));
INSERT INTO `springdb`.`mp3` (`name`, `author`) VALUES ('In the End', 'Linkin Park');
INSERT INTO `springdb`.`mp3` (`name`, `author`) VALUES ('Custle of Glass', 'Linkin Park');
INSERT INTO `springdb`.`mp3` (`name`, `author`) VALUES ('Whiski in the Jar', 'Metallica');