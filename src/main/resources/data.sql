INSERT INTO roles (id, role) VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO bank_accounts (balance, number) VALUES (4040, 'PL91 3018 4529 8669 0250 7367'),
                                                   (5000, 'PL80 0295 1501 5310 1109 7251'),
                                                   (1000, 'PL37 2194 1715 1934 9051 6937'),
                                                   (1001, 'PL49 4249 2703 1880 1960 6768'),
                                                   (1002, 'PL65 7348 2413 3521 2843 7960'),
                                                   (1003, 'PL07 1908 4517 7149 2879 9276'),
                                                   (1004, 'PL50 5062 0085 1227 5015 3729'),
                                                   (1005, 'PL62 2026 5721 5750 6891 6466'),
                                                   (1006, 'PL83 0721 2492 9992 7740 1693');

INSERT INTO users (login, password, decoded_b_crypt_password, email, phone_number, bank_account_id, is_enabled)
VALUES ('a', '$2a$10$nTtRs2ckjtjWuY/hP.SnnOkud3pLv0kyawerUaN.FdYzSvzg.H4bu', 'a', 'coffeeguy@email.com', '+48 509-154-295', 1, true),
       ('b', '$2a$10$XHERx/vbTW1i5DEpIgw01uY5bSNB084sYPVmc8ohdgB9KKxjhNG/e', 'b', 'sampleeee@email.com', '+48 523-653-325', 2, true),
       ('c', '$2a$10$Kv7SackFzZVDNJUbRE.y/ejbZkDMHwADeElIkTKIVEkIMuBKf27xC', 'c', 'emailemai@email.com', '+48 509-236-390', 3, true),
       ('d', '$2a$10$P/jr.n8LtxDwvGMs327oIeJvo5emgfeYG1aksJoNO40m9kr69FdYO', 'd', 'imaeliame@email.com', '+48 512-652-431', 4, true),
       ('e', '$2a$10$qQuI.t2TLxwkXTORakhwUemJZFFE.a2wMoAkLMxwd6u.zbPYKt2qq', 'e', 'mailmaile@email.com', '+48 513-236-541', 5, true),
       ('f', '$2a$10$4V1UW6iAipENbb3Gv.oEoutPSWK8hXUO1IPDxNGfo8sROhKsDLvHe', 'f', 'eeeelpmas@email.com', '+48 554-236-124', 6, true),
       ('g', '$2a$10$mKb/F4cNb4YIZMFFkBh5YuOBeYcSdIkYcAYR0pWehGMy1dgcFfXPu', 'g', 'uygeeffoc@email.com', '+48 453-534-322', 7, true),
       ('h', '$2a$10$dYGQ68zutjcH3kboolV7jeEl8bNfAOp7MYZpARhtgqBXpesUArFcu', 'h', 'elimaelim@email.com', '+48 534-453-555', 8, true),
       ('i', '$2a$10$kRRvoiFr1w2PTWusk5Cgj.b6uadBnN5x0maWvDnlIzsYXCkS02Ihq', 'i', 'ogarniam100@wp.pl',   '+48 657-236-463', 9, true);

INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1), (1, 2),
                                                   (2, 1), (2, 2),
                                                   (3, 1),
                                                   (4, 1),
                                                   (5, 1),
                                                   (6, 1),
                                                   (7, 1),
                                                   (8, 1),
                                                   (9, 1);

INSERT INTO transactions (title, description, sender_account_number, receiver_account_number, date, amount, bank_account_id)
VALUES ('a', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-01-04 07:03:24', 200, 1),
       ('b', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-02-04 07:03:24', 201, 1),
       ('c', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-03-04 07:03:24', 202, 1),
       ('d', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-04-04 07:03:24', 203, 1),
       ('e', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-05-04 07:03:24', 204, 1),
       ('f', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-06-04 07:03:24', 205, 1),
       ('g', 'Enjoy your money :)', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-07-04 07:03:24', 206, 1),

       ('h', 'Enjoy your money :)', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-01-05 07:03:24', 207, 2),
       ('i', 'Enjoy your money :)', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-02-05 07:03:24', 208, 2),
       ('j', 'Enjoy your money :)', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-03-05 07:03:24', 209, 2),
       ('k', 'Enjoy your money :)', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-04-05 07:03:24', 210, 2),
       ('l', 'Enjoy your money :)', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-05-05 07:03:24', 211, 2),
       ('m', 'Enjoy your money :)', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-06-05 07:03:24', 212, 2),
       ('n', 'Enjoy your money :)', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-07-05 07:03:24', 213, 2),

       ('o', 'Enjoy your money :)', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-01-06 07:03:24', 214, 3),
       ('p', 'Enjoy your money :)', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-02-06 07:03:24', 215, 3),
       ('r', 'Enjoy your money :)', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-03-06 07:03:24', 216, 3),
       ('s', 'Enjoy your money :)', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-04-06 07:03:24', 217, 3),
       ('t', 'Enjoy your money :)', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-05-06 07:03:24', 218, 3),
       ('u', 'Enjoy your money :)', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-06-06 07:03:24', 219, 3),
       ('v', 'Enjoy your money :)', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-07-06 07:03:24', 220, 3),

       ('w', 'Enjoy your money :)', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-01-07 07:03:24', 221, 4),
       ('x', 'Enjoy your money :)', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-02-07 07:03:24', 222, 4),
       ('y', 'Enjoy your money :)', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-03-07 07:03:24', 223, 4),
       ('z', 'Enjoy your money :)', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-04-07 07:03:24', 224, 4),
       ('a', 'Enjoy your money :)', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-05-07 07:03:24', 225, 4),
       ('b', 'Enjoy your money :)', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-06-07 07:03:24', 226, 4),
       ('c', 'Enjoy your money :)', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-07-07 07:03:24', 227, 4);
