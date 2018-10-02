drop table customsetusergrouppermissions;
drop table usergroupuser;
drop table usergroup;

alter table vote drop FOREIGN KEY FK_vote_4;

drop table user;

create table users(
  username char(50) not null primary key,
  password char(60) not null,
  enabled boolean not null);

create table authorities (
  username char(50) not null,
  authority char(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  create unique index ix_auth_username on authorities (username,authority);

create table groups (
  id bigint primary key,
  group_name char(50) not null);

create table group_authorities (
  group_id bigint not null,
  authority varchar(50) not null,
  constraint fk_group_authorities_group foreign key(group_id) references groups(id));

create table group_members (
  id bigint primary key,
  username varchar(50) not null,
  group_id bigint not null,
  constraint fk_group_members_group foreign key(group_id) references groups(id));

create table persistent_logins (
  username varchar(64) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null);

alter table vote add
CONSTRAINT FK_vote_4
FOREIGN KEY (userID)
REFERENCES users(username)
ON DELETE CASCADE;