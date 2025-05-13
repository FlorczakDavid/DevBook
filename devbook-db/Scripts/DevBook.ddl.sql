DROP TABLE IF EXISTS t_roles, t_accounts, t_2fa, t_articles;

CREATE TABLE t_roles(
	id int GENERATED ALWAYS AS IDENTITY,
	name varchar(10),
	role_default boolean,
	CONSTRAINT t_role_pkey PRIMARY KEY (id),
	CONSTRAINT t_role_name_ukey UNIQUE (name)
);

CREATE TABLE t_accounts (
    id int GENERATED ALWAYS AS IDENTITY,
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

CREATE TABLE t_2fa (
    id int GENERATED ALWAYS AS IDENTITY,
    pin_code CHAR(4) NOT NULL,
    uuid_token VARCHAR(36) NOT NULL,
    expiration TIMESTAMP,
    user_id INT NOT NULL,
    CONSTRAINT t_2fa_fkey PRIMARY KEY (id),
    CONSTRAINT t_2fa_accounts_fkey FOREIGN KEY(user_id) REFERENCES t_accounts(id)
);

CREATE TABLE t_articles(
	id int GENERATED ALWAYS AS IDENTITY,
	url varchar(255) NOT NULL ,
	title varchar(100) NOT NULL ,
	image_path varchar(255),
	description varchar(255),
	author varchar(100),
	CONSTRAINT t_articles_pk PRIMARY KEY (id),
	CONSTRAINT t_articles_title UNIQUE (title)
);
