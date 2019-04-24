DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`title` varchar(100) DEFAULT NULL,
	`author` varchar(100) DEFAULT NULL,
	`price` varchar(100) DEFAULT NULL,
	`status` int(11) DEFAULT '0',
	PRIMARY KEY (`id`),
	UNIQUE KEY `title` (`title`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLE `books` WRITE;

INSERT INTO `books` (`title`, `author`, `price`) VALUES 
('Hamlet', 'Shakespear', '$100'),
('King Lear', 'Shakespear', '$120'),
('Macbeth', 'Shakespear', '$80'),
('Romeo and Juliet', 'Shakespear', '$50');

UNLOCK TABLES;
