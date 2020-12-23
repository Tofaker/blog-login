drop database if exists chong_blog ;
create database chong_blog character set utf8mb4;
use chong_blog;
drop table if exists user;
create table user (
    id int primary key auto_increment,
    username varchar (20) not null ,
    password varchar (20) not null ,
    nickname varchar (20),
    birthday date,
    phone_number varchar (11),
    email varchar (20),
    head varchar (50)
);
drop table if exists article;
create table article(
    id int primary key auto_increment,
    title varchar (50) not null ,
    content mediumtext not null ,
    user_id int references user(id),
    create_time timestamp default now(),
    view_count int default 0
);
,
    foreign key (user_id) references (user id)