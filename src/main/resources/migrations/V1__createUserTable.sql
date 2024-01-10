create table user (
    id bigint auto_increment primary key,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    email varchar(255) not null,
    companyName varchar(255) null,
    passwordHash varchar(255) not null,
    lastSignInAt datetime(6) null,
    createdAt datetime(6) not null,
    constraint user_UK_email unique (email)
);