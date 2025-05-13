DROP TABLE IF EXISTS t_email;

CREATE TABLE t_email (
    id BIGSERIAL PRIMARY KEY,
    uuid_token VARCHAR(36) NOT NULL,
    account_id BIGINT NOT NULL,
    creation TIMESTAMP NOT NULL,
    expiration TIMESTAMP NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES t_accounts (id) ON DELETE CASCADE
);

SELECT * FROM t_email;