create table category
(
    id            tinyint auto_increment
        primary key,
    category_name varchar(30) null,
    constraint category_category_name_uindex
        unique (category_name)
);

create table currency
(
    id            int unsigned auto_increment
        primary key,
    currency_code tinyint     null,
    currency_name varchar(10) not null,
    constraint currency_currency_code_uindex
        unique (currency_code),
    constraint currency_currency_name_uindex
        unique (currency_name)
);

create table user
(
    id                 int unsigned auto_increment
        primary key,
    given_name         varchar(30)                       not null,
    first_last_name    varchar(30)                       not null,
    second_last_name   varchar(30)                       not null,
    id_number          int(9)                            not null,
    direction          varchar(255)                      not null,
    telephone          int(8)                            null,
    cellphone          int(8)                            null,
    password           varchar(255)                      not null,
    profile_img        longblob                          null,
    added_by_cellphone tinyint(1)     default 0          not null,
    transaction_limit  decimal(10, 4) default 40000.0000 not null,
    constraint user_cellphone_uindex
        unique (cellphone),
    constraint user_id_number_uindex
        unique (id_number)
);

create table account
(
    id                int unsigned auto_increment
        primary key,
    user_id           int unsigned                  null,
    balance           decimal(10, 4) default 0.0000 not null,
    account_number    int(17)                       not null,
    account_type      tinyint        default 0      not null,
    transaction_limit tinyint        default 0      null,
    initial_amount    decimal(10, 4) default 0.0000 not null,
    currency_id       tinyint        default 0      not null,
    privacy           tinyint        default 0      not null,
    interest          decimal(5, 4)                 null,
    constraint account_user_id_number_fk
        foreign key (user_id) references user (id)
);

create table budget
(
    id           int unsigned auto_increment
        primary key,
    account_id   int unsigned         null,
    category_id  tinyint    default 0 null,
    balance      decimal(10, 4)       null,
    discountable tinyint(1) default 0 null,
    constraint budget_category_id_fk
        foreign key (category_id) references category (id),
    constraint budgets_account_account_number_fk
        foreign key (account_id) references account (id)
);

create table budget_transaction
(
    id        int unsigned auto_increment
        primary key,
    budget_id int unsigned null,
    date      datetime     not null,
    constraint budget_transaction_budget_id_fk
        foreign key (budget_id) references budget (id)
);

create table favourite
(
    account_id int unsigned null,
    constraint favourite_account_account_number_fk
        foreign key (account_id) references account (id)
);

create table saving
(
    account_id      int unsigned      null,
    id              int unsigned auto_increment
        primary key,
    start_date      datetime          not null,
    end_date        datetime          not null,
    interest        decimal(5, 4)     null,
    initial_balance varchar(255)      not null,
    current_balance varchar(255)      not null,
    category_id     tinyint default 1 not null,
    constraint saving_account_account_number_fk
        foreign key (account_id) references account (id),
    constraint saving_category_id_fk
        foreign key (category_id) references category (id)
);

create table saving_day
(
    saving_id int unsigned null,
    id        int unsigned auto_increment
        primary key,
    day       tinyint      not null,
    constraint saving_day_saving_id_fk
        foreign key (saving_id) references saving (id)
);

create table saving_transaction
(
    id        int unsigned auto_increment
        primary key,
    saving_id int unsigned      null,
    date      datetime          null,
    amount    decimal(10, 4)    null,
    state     tinyint default 1 not null,
    constraint saving_transaction_saving_id_fk
        foreign key (saving_id) references saving (id)
);

create table transaction
(
    account_id      int unsigned      null,
    datetime        datetime          not null,
    amount          decimal(10, 4)    not null,
    state           tinyint default 1 not null,
    bank_id         int               not null,
    amount_at_t     decimal(10, 4)    not null,
    category        tinyint default 1 not null,
    id              int unsigned auto_increment
        primary key,
    destiny_account int unsigned      null,
    other_account   int unsigned      null,
    constraint transactions_account_account_number_fk
        foreign key (account_id) references account (id),
    constraint transactions_account_account_number_fk_2
        foreign key (destiny_account) references account (id)
);


