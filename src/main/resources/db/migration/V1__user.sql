create table users(
user_id bigint auto_increment,
name varchar(255),
email varchar(250),
password int(250),
constraint pk_users primary key(user_id));