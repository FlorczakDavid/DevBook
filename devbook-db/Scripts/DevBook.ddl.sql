DROP TABLE IF EXISTS t_roles, t_accounts, t_2fa, t_articles;

CREATE TABLE t_roles (
	id int GENERATED ALWAYS AS IDENTITY,
	name varchar(10),
	role_default boolean,
	CONSTRAINT pk_t_role PRIMARY KEY (id),
	CONSTRAINT uq_t_roles_name UNIQUE (name)
);

CREATE TABLE t_accounts (
    id int GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255),
    password VARCHAR (72),
    status_email BOOLEAN,
    notif_article BOOLEAN,
    notif_rss BOOLEAN,
    role_id INT NOT NULL,
    CONSTRAINT pk_t_account PRIMARY KEY (id),
    CONSTRAINT uq_t_account_username UNIQUE (username),
    CONSTRAINT fk_t_accounts_role_id FOREIGN KEY(role_id) REFERENCES t_roles(id)
);

CREATE TABLE t_2fa (
    id int GENERATED ALWAYS AS IDENTITY,
    pin_code CHAR(4) NOT NULL,
    uuid_token VARCHAR(36) NOT NULL,
    expiration TIMESTAMP,
    accounts_id INT NOT NULL,
    CONSTRAINT pk_t_2fa PRIMARY KEY (id),
    CONSTRAINT fk_t_2fa_accounts_id FOREIGN KEY(accounts_id) REFERENCES t_accounts(id)
);

CREATE TABLE t_articles (
	id int GENERATED ALWAYS AS IDENTITY,
	url varchar(255) NOT NULL ,
	title varchar(100) NOT NULL ,
	image_path varchar(255),
	description varchar(255),
	authors varchar(100),
    published_date timestamptz(6) NULL,
    provider_id int NULL,
	CONSTRAINT pk_t_articles PRIMARY KEY (id),
	CONSTRAINT uq_t_articles_title UNIQUE (title),
    CONSTRAINT fk_t_articles_provider_id FOREIGN KEY (provider_id) REFERENCES t_rss_providers(id);
);

CREATE TABLE t_rss_providers (
    id int GENERATED ALWAYS AS IDENTITY,
    description varchar(255) NULL,
    image_url varchar(255) NULL,
    last_update timestamptz(6) NULL,
    link varchar(255) NULL,
    title varchar(255) NULL,
    url varchar(255) NULL,
    CONSTRAINT pk_t_rss_providers PRIMARY KEY (id)
);

CREATE TABLE t_share_articles(
    id int GENERATED ALWAYS AS IDENTITY,
    account_id int NOT NULL,
    article_id int NOT NULL,
    published_date timestamp WITH time ZONE NOT NULL,
    CONSTRAINT pk_t_share_articles PRIMARY KEY (id),
    CONSTRAINT uk_t_share_articles_account_id_article_id UNIQUE (account_id, article_id)
);
