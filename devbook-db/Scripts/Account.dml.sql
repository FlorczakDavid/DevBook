DELETE FROM t_accounts;

INSERT INTO t_accounts (username, password, flag, id_role)
VALUES (
  'emilie@example.com', '$2a$12$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', '1',
  (SELECT id FROM t_roles WHERE name = 'Member'));

INSERT INTO t_accounts (username, password, flag, id_role)
VALUES (
  'fabien@example.com', '$2a$12$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', '1',
  (SELECT id FROM t_roles WHERE name = 'Developer'));

INSERT INTO t_accounts (username, password, flag, id_role)
VALUES (
  'mohamed@example.com', '$2a$12$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', '1',
  (SELECT id FROM t_roles WHERE name = 'Integrator'));
