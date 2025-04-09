DROP TABLE IF EXISTS t_accounts;



CREATE TABLE t_accounts (
    id INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255),
    password VARCHAR (72),
    CONSTRAINT t_account_pkey PRIMARY KEY (id),
    CONSTRAINT t_account_ukey unique (username)
);



SELECT * FROM t_accounts;

