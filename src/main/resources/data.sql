INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO users (login, password, email, phone_number) VALUES ('a', 'a', 'coffeeguy@email.com', '509154295');

INSERT INTO users_roles (users_id, roles_id) VALUES (1, 1), (1, 2);
