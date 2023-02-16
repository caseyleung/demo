create database wallet;
use wallet;

create table user
(
    id int(10) not null primary key auto_increment,
    username varchar(20) not null,
    money int(10) not null
);

INSERT INTO wallet.user (id, username, money) VALUES (1, 'jack', 1000);

create table oper_log
(
    id int(10) not null primary key auto_increment,
    oper_name varchar(20) not null ,
    oper_type varchar(20) not null ,
    oper_time date not null ,
    result varchar(20)
);

