/*
create table user_groups
(
	id int primary key auto_increment,
    name varchar(255)
);

create table users
(
	id int primary key auto_increment,
    user_group_id int,
    username varchar(255),
    email varchar(255),
    password varchar(255),
    constraint unique (email),
    constraint unique (id),
    constraint foreign key (user_group_id) references user_groups (id)
);

create table excercises
(
	id int primary key auto_increment,
    title varchar(255),
    description text
);

create table solutions
(
	id int primary key auto_increment,
    excercise_id int,
    user_id int,
    created datetime,
    updated datetime,
    description text,
    foreign key (excercise_id) references excercises (id),
    foreign key (user_id) references users (id)
);
*/