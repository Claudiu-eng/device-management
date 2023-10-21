create table user_device
(
    id binary(16) not null
        primary key
);


create table device
(
    maxim_hourly_energy int          null,
    id                  binary(16)   not null
        primary key,
    user_id             binary(16)   null,
    address             varchar(255) null,
    description         varchar(255) null,
    constraint FK4ydpqmtqrwkrymewfcvyx6s3b
        foreign key (user_id) references user_device (id)
);

