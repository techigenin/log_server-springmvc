CREATE DATABASE  IF NOT EXISTS `compliance_project`;
USE `compliance_project`;

BEGIN;

-- Drop Foreign Keys

-- ALTER TABLE `calls` 
-- DROP FOREIGN KEY calls_ibfk_1; 

-- ALTER TABLE `calls`
-- DROP FOREIGN KEY calls_ibfk_2; 

-- ALTER TABLE `logs`
-- DROP FOREIGN KEY logs_ibfk_1; 

-- ALTER TABLE `logs`
-- DROP FOREIGN KEY logs_ibfk_2; 

-- ALTER TABLE `comments`
-- DROP FOREIGN KEY comments_ibfk_1; 

DROP TABLE IF EXISTS `calls`;-- 

CREATE TABLE `calls` (
  `call_id` int NOT NULL AUTO_INCREMENT,
  `date` date,
  `duration` varchar(10),
  `sales_person_id` int,
  `client_id` int,
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `log_id` int NOT NULL AUTO_INCREMENT,
  `call_id` int,
  `user_id` int,
  `date` date,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `log_id` int,
  `concern_level` varchar(10),
  `statement` text,
  `comment` text,
  `time` varchar(10),
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50),
  `last_name` varchar(50),
  `phone_number` varchar(15),
  `email` varchar(50),
  `status` varchar(10),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `sales_people`;

CREATE TABLE `sales_people` (
  `sales_person_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50),
  `last_name` varchar(50),
  `phone_number` varchar(15),
  PRIMARY KEY (`sales_person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `clients`;

CREATE TABLE `clients` (
  `client_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50),
  `last_name` varchar(50),
  `phone_number` varchar(15),
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `sales_people` (`first_name`, `last_name`, `phone_number`) 
	VALUES 
		('Alison', 'Anderson', '1234567890'),
		('Billy', 'Bandersnatch', '0987654321'),
        ('Charles', 'Charleton', '5432167890');

INSERT INTO `clients` (`first_name`, `last_name`, `phone_number`)
	VALUES
		('Dori', 'Dottson', '1112223344'),
        ('Edward', 'Erikson', '5556667788'),
        ('Felicia', 'Fox', '9990001234');
        
INSERT INTO `calls` (`date`, `duration`, `sales_person_id`, `client_id`)
	VALUES
		('2020-05-10','00:12:34',1,1),
		('2020-04-15','00:45:01',2,2),
		('2020-06-01','01:16:00',3,3);

INSERT INTO `users` (`first_name`, `last_name`, `phone_number`, `email`, `status`)
	VALUES
		('Greg','Garrick', '(123)456-7890', 'first@thatmail.com', 'guest'),
		('Hattie','Hondo', '(098)765-4321', 'second@thatmail.com', 'admin'),
		('Julie','Jaxson', '(432)109-8765', 'third@thatmail.com', 'user');                

INSERT INTO `comments` (`log_id`, `concern_level`, `statement`, `comment`, `time`)
	VALUES
		(1, 'Good', 'Oh Gee Whiz!', 'So much good!', '00:01:15'),
        (1, 'Excellent', 'So exciting', 'Even more good!', '00:25:25'),
        (1, 'Concerning', 'The sun is green!', 'But why..?', '00:59:12'),
        (2, 'Bad', 'Something bad', 'On No!', '01:03:02'),
        (3, 'Bad', 'Creepy Crawlers!', '...!', '03:02:01'),
        (3, 'Neutral', 'And then we had ice cream', 'Me too..?', '04:04:01');
        
INSERT INTO `logs` (`call_id`, `user_id`, `date`)
	VALUES
		(2, 1, '2019-01-01'),
		(1, 1, '2020-02-03'),
		(3, 3, '2019-12-01'),
		(2, 3, '2019-12-01');
        
-- ALTER TABLE `calls`
-- ADD FOREIGN KEY (`sales_person_id`) REFERENCES `sales_people`(`sales_person_id`); 

-- ALTER TABLE `calls`
-- ADD FOREIGN KEY (`client_id`) REFERENCES `clients`(`client_id`); 

-- ALTER TABLE `logs`
-- ADD FOREIGN KEY (`call_id`) REFERENCES `calls`(`call_id`); 

-- ALTER TABLE `logs`
-- ADD FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`); 

-- ALTER TABLE `comments`
-- ADD FOREIGN KEY (`log_id`) REFERENCES `logs`(`log_id`); 

COMMIT