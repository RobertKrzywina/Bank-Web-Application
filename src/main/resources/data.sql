INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO users (login, password, email, phone_number)
VALUES ('a', '$2a$10$nTtRs2ckjtjWuY/hP.SnnOkud3pLv0kyawerUaN.FdYzSvzg.H4bu', 'coffeeguy@email.com', '+48 509154295');

INSERT INTO users_roles (users_id, roles_id) VALUES (1, 1), (1, 2);
