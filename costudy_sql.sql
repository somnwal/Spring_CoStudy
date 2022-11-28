create database if not exists `costudy`;
use `costudy`;



-- -----------------------------
-- user table
-- -----------------------------

drop table if exists `costudy_user`;

create table `costudy_user` (
	`id`				integer auto_increment not null,
	`user_id`			varchar(20) unique not null,
    `user_password`		varchar(30) not null,
    `user_nick`			varchar(20) unique not null,
    `user_email`		varchar(40) unique not null,
    `user_profile`		varchar(100),
    `user_born`			date not null,
    `user_gender`		varchar(3) not null,
    `is_cert`			integer not null,
    `user_level`		integer not null,
    primary key(id)
);

-- -----------------------------
-- board table
-- -----------------------------

drop table if exists `costudy_board`;