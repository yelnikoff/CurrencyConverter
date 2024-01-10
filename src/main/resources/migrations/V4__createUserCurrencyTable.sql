create table userCurrency (
    id bigint auto_increment primary key,
    userId bigint not null,
    currencyCode varchar(3) not null,
    indexNumber int null,
    constraint UK12uoxbkwvapg4pvo53yqibwbb unique (userId, currencyCode),
    constraint FK36ac9ow94o1lgarkrfnww4oo9 foreign key (userId) references user (id) on update cascade on delete cascade,
    constraint FKg7sootii8fk11t0koccs2wgu4 foreign key (currencyCode) references currency (code) on update cascade on delete cascade
);