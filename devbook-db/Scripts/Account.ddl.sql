DROP TABLE IF EXISTS t_accounts;

CREATE TABLE t_accounts (
    id INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255),
    password VARCHAR (72),
    status_email BOOLEAN,
    notif_article BOOLEAN,
    notif_rss BOOLEAN,
    id_role INT NOT NULL,
    CONSTRAINT t_account_pkey PRIMARY KEY (id),
    CONSTRAINT t_account_ukey UNIQUE (username),
    CONSTRAINT fkey_role_name FOREIGN KEY(id_role) REFERENCES t_roles(id)
);
