CREATE TABLE t_2fa (
    id INT GENERATED ALWAYS AS IDENTITY,
    pin_code CHAR(4) NOT NULL,
    uuid_token VARCHAR(36) NOT NULL,
    expiration TIMESTAMP,
    user_id INT NOT NULL,
    CONSTRAINT t_2fa_fkey PRIMARY KEY (id),
    CONSTRAINT t_2fa_accounts_fkey FOREIGN KEY(user_id) REFERENCES t_accounts(id)
);
