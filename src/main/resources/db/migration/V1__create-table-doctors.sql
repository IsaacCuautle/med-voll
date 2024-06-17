CREATE TABLE doctors
(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(10) not null,
    document varchar(6) not null unique,
    speciality varchar(100) not null,
    street varchar(100) not null,
    district varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,
    primary key(id)
);