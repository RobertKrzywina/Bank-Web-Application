INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO users (login, password, email, phone_number)
VALUES ('a', '$2a$10$nTtRs2ckjtjWuY/hP.SnnOkud3pLv0kyawerUaN.FdYzSvzg.H4bu', 'coffeeguy@email.com', '+48 509-154-295'),
       ('b', '$2a$10$XHERx/vbTW1i5DEpIgw01uY5bSNB084sYPVmc8ohdgB9KKxjhNG/e', 'sampleeee@email.com', '+48 523-653-325');

INSERT INTO users_roles (users_id, roles_id) VALUES (1, 1), (1, 2),
                                                    (2, 1), (2, 2);

INSERT INTO bank_accounts (balance, number) VALUES (4040, 'PL91 3018 4529 8669 0250 7367'),
                                                   (5000, 'PL80 0295 1501 5310 1109 7251');

INSERT INTO transactions (title, description, sender_account_number, receiver_account_number, date, amount, transaction_id)
VALUES ('Gift', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-01-04 07:03:24.482', 200, 1);
