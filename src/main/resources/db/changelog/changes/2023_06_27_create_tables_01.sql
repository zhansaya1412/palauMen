CREATE TABLE t_categories
(
    id        bigserial
        primary key,
    name      varchar(255),
    price_for varchar(255),
    url       varchar(255)
);
CREATE TABLE t_foods
(
    id bigserial primary key,
    description text,
    food_name varchar(255),
    food_url varchar(255)
        constraint therthwetrkgj
            unique,
    photo          varchar(255),
    t_categories_id    bigint
        constraint qergldjkgkjerkg
            references t_categories,
    price_per_kg   numeric(38, 2),
    price_per_unit numeric(38, 2),
    quantity       integer,
    weight         numeric(38, 2)
);