USE `costudy`;

INSERT INTO `user`
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("yeongha_kim",
"1234",
"김영하",
"kim_yeong_ha@costudy.com",
"1996-02-01");

INSERT INTO `user`
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("cjw1234",
"1234",
"최진우",
"cjw1234@costudy.com",
"2000-08-16");

INSERT INTO `user`
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("leems4669",
"1234",
"이민수",
"leems4669@costudy.com",
"1984-12-27");

INSERT INTO `user`
(`user_id`,
`user_password`,
`user_nick`,
`user_email`,
`user_born`)
VALUES
("jangyy",
"1234",
"장윤영",
"jangyy@costudy.com",
"1995-01-15");

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
"jangyy",
"같이 C언어 공부하실 분!",
"8:00",
"10:00",
"37.5650172,126.8494668",
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
"yeongha_kim",
"자바 강의 같이 들을사람만",
"16:00",
"18:00",
"37.6010077,239.2010199",
3);

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
"leems4669",
"스프링 스터디 모집합니다",
"06:00",
"23:00",
"37.4368585,126.8726502",
1);

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"jangyy",
1,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"yeongha_kim",
2,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"cjw1234",
2,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"leems4669",
2,
"2");

INSERT INTO `costudy`.`group_reg`
(`reg_id`,
`user_id`,
`group_id`,
`reg_state`)
VALUES
(0,
"leems4669",
3,
"2");

COMMIT;