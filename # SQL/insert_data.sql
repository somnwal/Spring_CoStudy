USE `costudy`;

INSERT INTO USER
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("asdf",
"1234",
"테스트",
"admin@admin.com",
"1996-02-01");

INSERT INTO USER
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("aaaa",
"1234",
"테스트2",
"admin@admin.com",
"1996-02-01");

INSERT INTO USER
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("bbbb",
"1234",
"테스트3",
"admin@admin.com",
"1996-02-01");

INSERT INTO USER
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("cccc",
"1234",
"테스트4",
"admin@admin.com",
"1996-02-01");

INSERT INTO `costudy`.`study_group`
(`group_id`,
`group_master`,
`study_name`,
`study_start`,
`study_finish`,
`study_location`,
`group_mem_count`)
VALUES
(0,
"aaaa",
"test1",
"8:00",
"12:00",
"37.5650172,126.8494668",
2);

INSERT INTO `costudy`.`study_group`
(`group_id`,
`group_master`,
`study_name`,
`study_start`,
`study_finish`,
`study_location`,
`group_mem_count`)
VALUES
(0,
"aaaa",
"test2",
"8:00",
"12:00",
"37.6010077,239.2010199",
1);

INSERT INTO `costudy`.`study_group`
(`group_id`,
`group_master`,
`study_name`,
`study_start`,
`study_finish`,
`study_location`,
`group_mem_count`)
VALUES
(0,
"asdf",
"test3",
"8:00",
"12:00",
"37.4368585,126.8726502",
1);

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"aaaa",
1,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"aaaa",
2,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"asdf",
1,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"asdf",
2,
"1");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"asdf",
3,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"bbbb",
3,
"1");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"cccc",
3,
"1");
