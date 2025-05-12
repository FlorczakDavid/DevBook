DELETE FROM t_roles;

INSERT INTO t_roles (name, role_default) VALUES 
('MEMBER', true),
('INTEGRATOR', false);
