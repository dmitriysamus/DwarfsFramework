--DELETE FROM roles_app;
--DELETE FROM roles_bd;
--
--
--INSERT INTO roles_app (id, ROLE_NAME) VALUES
--(1, 'COMMON'),(2, 'SILVER'),(3, 'GOLD');
--
--INSERT INTO roles_bd (id, ROLE_NAME) VALUES
--(1, 'ROLE_USER'),(2, 'ROLE_ADMIN'),(3, 'ROLE_MODERATOR');

alter table if exists user_roles_app drop constraint if exists FKj8pacxudoxfrtll14o2lrpkcw
alter table if exists user_roles_app drop constraint if exists FKl4x0tu1v9batevlwpx38pvf8w
alter table if exists user_roles_bd drop constraint if exists FK7xtc9lddyh2r32itv18c75d2j
alter table if exists user_roles_bd drop constraint if exists FK5nhsnojghpqdxosk4oqfsle4j
drop table if exists roles_app cascade
drop table if exists roles_bd cascade
drop table if exists user_roles_app cascade
drop table if exists user_roles_bd cascade
drop table if exists users cascade
drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start 1 increment 1
create table roles_app (id int4 not null, role_name varchar(255), primary key (id))
create table roles_bd (id int4 not null, role_name varchar(20), primary key (id))
create table user_roles_app (user_id int8 not null, role_id int4 not null, primary key (user_id, role_id))
create table user_roles_bd (user_id int8 not null, role_id int4 not null, primary key (user_id, role_id))
create table users (id int8 not null, user_creation_date timestamp, user_last_visited_date timestamp, user_password varchar(100), user_email varchar(100) not null, user_name varchar(100) not null, primary key (id))
--alter table roles_bd
alter table if exists user_roles_app add constraint FKj8pacxudoxfrtll14o2lrpkcw foreign key (role_id) references roles_app
alter table if exists user_roles_app add constraint FKl4x0tu1v9batevlwpx38pvf8w foreign key (user_id) references users
alter table if exists user_roles_bd add constraint FK7xtc9lddyh2r32itv18c75d2j foreign key (role_id) references roles_bd
alter table if exists user_roles_bd add constraint FK5nhsnojghpqdxosk4oqfsle4j foreign key (user_id) references users