CREATE SCHEMA `student_test_db`;

CREATE TABLE `student_test_db`.`students`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `mark` VARCHAR(1)  NOT NULL,
    PRIMARY KEY (`id`)
);