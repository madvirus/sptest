create user 'spring4'@'localhost' identified by 'spring4';

create database sample character set=utf8;

grant all privileges on sample.* to 'spring4'@'localhost';

create table sample.MEMBER (
	ID varchar(100) primary key,
	PASSWORD varchar(100),
	NAME varchar(100)
) engine=InnoDB character set = utf8;
