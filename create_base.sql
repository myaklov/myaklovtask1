DROP DATABASE IF EXISTS test;

CREATE DATABASE test DEFAULT CHARACTER SET 'utf8';

USE test;

create table user
(
	id int(8) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name varchar(25),
	age int,
	isAdmin bit,
	createdDate timestamp
);


USE test;
DELIMITER $$
CREATE PROCEDURE insert_test_data()
BEGIN
  DECLARE i INT DEFAULT 1;
DECLARE j INT DEFAULT 51;
  WHILE i < 50 DO
    INSERT INTO user (id, name, age, isAdmin, createdDate)
    values(i, 'Alex', i%100,  i%1, now());
    set i = i + 1;
  END WHILE;
  WHILE j < 100 DO
    INSERT INTO user (id, name, age, isAdmin, createdDate)
    values(j, 'Maxim', j%100,  j%1, now());
    set j = j + 1;
  END WHILE;
END$$
DELIMITER ;
CALL insert_test_data();
DROP PROCEDURE insert_test_data;