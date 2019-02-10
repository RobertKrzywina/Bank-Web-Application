INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO users (login, password, email, phone_number)
VALUES ('a', '$2a$10$nTtRs2ckjtjWuY/hP.SnnOkud3pLv0kyawerUaN.FdYzSvzg.H4bu', 'coffeeguy@email.com', '+48 509-154-295');

INSERT INTO users_roles (users_id, roles_id) VALUES (1, 1), (1, 2);

INSERT INTO bank_accounts(balance, number) VALUES (4040, 'PL91 3018 4529 8669 0250 7367');
