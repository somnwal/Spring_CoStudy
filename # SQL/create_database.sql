------------------------------------
-- 데이터베이스 생성
------------------------------------
DROP DATABASE IF EXISTS `costudy`;
CREATE DATABASE IF NOT EXISTS `costudy`;
USE `costudy`;

------------------------------------
-- 서비스용 costudy 유저 생성
------------------------------------
CREATE USER IF NOT EXISTS 'costudy'@'localhost' IDENTIFIED BY 'costudy';
GRANT SELECT, INSERT, UPDATE, DELETE ON costudy.* TO 'costudy'@'localhost';

------------------------------------
-- 유저 테이블
------------------------------------
CREATE TABLE `user` (
	`user_id` VARCHAR(30),
    `user_password` VARCHAR(200) NOT NULL,
    `user_nick` VARCHAR(30) NOT NULL,
    `user_email` VARCHAR(40) NOT NULL,
    `user_born` DATETIME NOT NULL,
    PRIMARY KEY(`user_id`)
) default character set utf8mb4 collate utf8mb4_bin;

------------------------------------
-- 스터디그룹 테이블
------------------------------------
CREATE TABLE `study_group` (
	`group_id` INT AUTO_INCREMENT,
    `group_master` VARCHAR(30) NOT NULL,
    `study_name` VARCHAR(30),
    `study_start` VARCHAR(5),
    `study_finish` VARCHAR(5),
    `study_location` VARCHAR(50),
    `group_mem_count` INT,
    PRIMARY KEY(`group_id`),
    FOREIGN KEY(`group_master`) REFERENCES `user`(`user_id`) ON DELETE CASCADE
) default character set utf8mb4 collate utf8mb4_bin;

------------------------------------
-- 가입상태 테이블
------------------------------------
CREATE TABLE `group_reg` (
	`reg_id` INT AUTO_INCREMENT,
    `user_id` VARCHAR(30) NOT NULL,
    `group_id` INT NOT NULL,
    `reg_state` INT NOT NULL,
    PRIMARY KEY(`reg_id`),
    FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    FOREIGN KEY(`group_id`) REFERENCES `study_group`(`group_id`) ON DELETE CASCADE
) default character set utf8mb4 collate utf8mb4_bin;
