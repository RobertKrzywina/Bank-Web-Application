INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO bank_accounts (balance, number) VALUES (4040, 'PL91 3018 4529 8669 0250 7367'),
                                                   (5000, 'PL80 0295 1501 5310 1109 7251'),
                                                   (1000, 'PL37 2194 1715 1934 9051 6937'),
                                                   (1001, 'PL49 4249 2703 1880 1960 6768');

INSERT INTO users (login, password, decoded_b_crypt_password, email, phone_number, bank_account_id)
VALUES ('a', '$2a$10$nTtRs2ckjtjWuY/hP.SnnOkud3pLv0kyawerUaN.FdYzSvzg.H4bu', 'a', 'coffeeguy@email.com', '+48 509-154-295', 1),
       ('b', '$2a$10$XHERx/vbTW1i5DEpIgw01uY5bSNB084sYPVmc8ohdgB9KKxjhNG/e', 'b', 'sampleeee@email.com', '+48 523-653-325', 2),
       ('c', '$2a$10$Kv7SackFzZVDNJUbRE.y/ejbZkDMHwADeElIkTKIVEkIMuBKf27xC', 'c', 'emailemai@email.com', '+48 509-236-390', 3),
       ('d', '$2a$10$P/jr.n8LtxDwvGMs327oIeJvo5emgfeYG1aksJoNO40m9kr69FdYO', 'd', 'imaeliame@email.com', '+48 512-652-431', 4);

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1), (1, 2),
                                                   (2, 1), (2, 2),
                                                   (3, 1),
                                                   (4, 1);

INSERT INTO transactions (title, description, sender_account_number, receiver_account_number, date, amount, bank_account_id)
VALUES ('Gift', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-01-04 07:03:24.482', 200, 1),
       ('Gif', 'Enjoy your money too', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-01-05 08:03:24.482', 250, 2);
