CREATE TABLE users
(
    id bigint not null auto_increment PRIMARY KEY,
    login varchar(100) not null,
    password varchar(300) not null unique
);