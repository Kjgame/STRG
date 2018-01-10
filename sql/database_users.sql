create database login;
create table login.users(id int not null PRIMARY KEY, name varchar(100) not null, pass varchar(100) not null);
insert into login.users(id, name, pass) values (0, "guest", "");
insert into login.users(id, name, pass) values (1, "erik", "root");
insert into login.users(id, name, pass) values (2, "samuel", "root");