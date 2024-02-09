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

create table role (
    id varchar(10) not null primary key,
    name varchar(20)
) engine innodb character set utf8mb4;


create table role_perm (
    role_id varchar(10) not null,
    perm varchar(20),
    grantor varchar(10),
    foreign key(role_id) references role(id)
) engine innodb character set utf8mb4;


create table question (
    id varchar(10) not null primary key,
    text varchar(20)
) engine innodb character set utf8mb4;


create table question_choice (
    question_id varchar(10),
    idx integer(10),
    text varchar(20),
    input tinyint(1),
    foreign key(question_id) references question(id)
) engine innodb character set utf8mb4;


create table doc (
    id varchar(10) not null primary key,
    title varchar(100),
    content varchar(4000)
) engine innodb character set utf8mb4;


create table doc_prop (
    doc_id varchar(10),
    name varchar(100),
    value varchar(100),
    enabled tinyint(1),
    foreign key(doc_id) references doc(id)
) engine innodb character set utf8mb4;

create table membership_card (
    card_no varchar(50) not null primary key,
    user_email varchar(50),
    expiry_date date,
    enabled tinyint(1),
    foreign key(user_email) references user(email)
) engine innodb character set utf8mb4;


create table best_pick (
    user_email varchar(50) not null primary key,
    title varchar(100),
    foreign key(user_email) references user(email)
) engine innodb character set utf8mb4;

create table sight (
    id varchar(50) not null primary key,
    name varchar(50)
) engine innodb character set utf8mb4;

create table sight_review (
    id bigint not null primary key AUTO_INCREMENT,
    sight_id varchar(50) not null,
    grade varchar(2) not null,
    comment varchar(200) not null,
    foreign key(sight_id) references sight(id)
) engine innodb character set utf8mb4;

create table team (
    id varchar(10) not null primary key,
    name varchar(50)
) engine innodb character set utf8mb4;

create table player (
    id varchar(10) not null primary key,
    name varchar(50) not null,
    team_id varchar(10),
    foreign key(team_id) references team(id)
) engine innodb character set utf8mb4;

create table survey (
    id varchar(10) not null primary key,
    name varchar(50)
) engine innodb character set utf8mb4;

create table survey_question (
    id varchar(10) not null primary key,
    survey_id varchar(10),
    order_no integer(10),
    title varchar(50),
    foreign key(survey_id) references survey(id)
) engine innodb character set utf8mb4;


create table game (
    id varchar(10) not null primary key,
    name varchar(50)
) engine innodb character set utf8mb4;

create table game_member (
    id varchar(10) not null primary key,
    name varchar(50),
    game_id varchar(10),
    role_name varchar(10),
    foreign key(game_id) references game(id)
) engine innodb character set utf8mb4;

