DELETE FROM t_accounts;
DELETE FROM t_roles;

INSERT INTO t_roles (name, role_default) VALUES 
('MEMBER', true),
('INTEGRATOR', false);

INSERT INTO t_accounts (username, password, status_email, id_role)
VALUES (
  'emilie@example.com', '$2a$12$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', '1',
  (SELECT id FROM t_roles WHERE name = 'MEMBER')
 );

INSERT INTO t_accounts (username, password, status_email, id_role)
VALUES (
  'fabien@example.com', '$2a$12$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', '1',
  (SELECT id FROM t_roles WHERE name = 'MEMBER')
 );

INSERT INTO t_accounts (username, password, status_email, id_role)
VALUES (
  'mohamed@example.com', '$2a$12$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', '1',
  (SELECT id FROM t_roles WHERE name = 'INTEGRATOR')
 );
