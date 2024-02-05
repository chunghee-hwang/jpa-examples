create table user (
    email varchar(50) not null primary key,
    name varchar(50),
    create_date datetime
) engine innodb character set utf8mb4;

create table hotel_info (
    hotel_id varchar(500) not null primary key,

    addr1 varchar(500),
    addr2 varchar(500),
    zipcode varchar(100),

    nm varchar(100),
    year int,
    grade varchar(6),
    created datetime,
    modified datetime
) engine innodb character set utf8mb4;

create table review (
    id bigint not null AUTO_INCREMENT primary key,
    hotel_id varchar(500) not null,
    mark int,
    writer_name varchar(100),
    comment varchar(1000),
    foreign key(hotel_id) references hotel_info(hotel_id)
) engine innodb character set utf8mb4;

create table access_log (
    id bigint not null primary key,
    path varchar(500) not null,
    created datetime not null
) engine innodb character set utf8mb4;

create table id_seq (
    entity varchar(100) not null primary key,
    nextval bigint
) engine innodb character set utf8mb4;


create table writer (
    id bigint not null AUTO_INCREMENT primary key,
    name varchar(100)
) engine innodb character set utf8mb4;

create table writer_intro (
    writer_id bigint not null primary key,
    content varchar(500),
    content_type varchar(100),
    foreign key(writer_id) references writer(id)
) engine innodb character set utf8mb4;

create table writer_address (
    writer_id bigint not null primary key,
    addr1 varchar(100),
    addr2 varchar(100),
    zipcode varchar(5),
    foreign key(writer_id) references writer(id)
) engine innodb character set utf8mb4;