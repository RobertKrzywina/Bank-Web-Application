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

INSERT INTO users (login, password, email, phone_number, bank_account_id, is_enabled)
VALUES ('a',            '$2a$10$nTtRs2ckjtjWuY/hP.SnnOkud3pLv0kyawerUaN.FdYzSvzg.H4bu', 'coffeeguy@email.com',            '+48 509-154-295', 1, true),
       ('b',            '$2a$10$XHERx/vbTW1i5DEpIgw01uY5bSNB084sYPVmc8ohdgB9KKxjhNG/e', 'sampleeee@email.com',            '+48 523-653-325', 2, true),
       ('JamesGosling', '$2a$10$Kv7SackFzZVDNJUbRE.y/ejbZkDMHwADeElIkTKIVEkIMuBKf27xC', 'iLikeToDrinkCoffee@email.com',   '+48 509-236-390', 3, true),
       ('Steve Jobs',   '$2a$10$P/jr.n8LtxDwvGMs327oIeJvo5emgfeYG1aksJoNO40m9kr69FdYO', 'appleIsTheBest@mail.com',        '+48 512-652-431', 4, true),
       ('Bill Gates',   '$2a$10$qQuI.t2TLxwkXTORakhwUemJZFFE.a2wMoAkLMxwd6u.zbPYKt2qq', 'microsoft@outletDotcom',         '+48 513-236-541', 5, true),
       ('Mr Penguin',   '$2a$10$4V1UW6iAipENbb3Gv.oEoutPSWK8hXUO1IPDxNGfo8sROhKsDLvHe', 'linus.torvalds@linux.com',       '+48 554-236-124', 6, true),
       ('John Doe',     '$2a$10$mKb/F4cNb4YIZMFFkBh5YuOBeYcSdIkYcAYR0pWehGMy1dgcFfXPu', 'imTrueJohnDoe@yahoo.com',        '+48 453-534-322', 7, true),
       ('Jan Kowlaski', '$2a$10$dYGQ68zutjcH3kboolV7jeEl8bNfAOp7MYZpARhtgqBXpesUArFcu', 'pocztaPolska@email.com',         '+48 534-453-555', 8, true),
       ('fakeJohnDoe',  '$2a$10$kRRvoiFr1w2PTWusk5Cgj.b6uadBnN5x0maWvDnlIzsYXCkS02Ihq', 'helloimcopyoftruejohndoe@wp.pl', '+48 657-236-463', 9, true);

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
VALUES ('a', 'd', 'PL91 3018 4529 8669 0250 7367', 'PL80 0295 1501 5310 1109 7251', '2019-02-11 07:10:56', 2606.24, 1),
       ('b', 'c', 'PL91 3018 4529 8669 0250 7367', 'PL37 2194 1715 1934 9051 6937', '2019-02-13 00:56:56', 2872.25, 1),
       ('c', 'b', 'PL91 3018 4529 8669 0250 7367', 'PL37 2194 1715 1934 9051 6937', '2019-02-18 16:00:46', 1779.49, 1),
       ('d', 'a', 'PL91 3018 4529 8669 0250 7367', 'PL49 4249 2703 1880 1960 6768', '2019-02-19 17:57:10', 2151.26, 1),
       ('e', 'z', 'PL91 3018 4529 8669 0250 7367', 'PL65 7348 2413 3521 2843 7960', '2019-02-20 01:22:57', 1152.82, 1),
       ('f', 'y', 'PL91 3018 4529 8669 0250 7367', 'PL07 1908 4517 7149 2879 9276', '2019-02-27 08:07:08', 256.30,  1),
       ('g', 'x', 'PL91 3018 4529 8669 0250 7367', 'PL50 5062 0085 1227 5015 3729', '2019-03-05 21:17:46', 2352.34, 1),
       ('h', 'w', 'PL91 3018 4529 8669 0250 7367', 'PL62 2026 5721 5750 6891 6466', '2019-03-06 21:53:01', 1584.49, 1),
       ('i', 'v', 'PL91 3018 4529 8669 0250 7367', 'PL83 0721 2492 9992 7740 1693', '2019-03-08 12:46:01', 772.66,  1),

       ('j', 'u', 'PL80 0295 1501 5310 1109 7251', 'PL91 3018 4529 8669 0250 7367', '2019-02-14 14:54:37', 558.45,  2),
       ('k', 't', 'PL80 0295 1501 5310 1109 7251', 'PL37 2194 1715 1934 9051 6937', '2019-02-15 21:53:25', 540.46,  2),
       ('l', 's', 'PL80 0295 1501 5310 1109 7251', 'PL37 2194 1715 1934 9051 6937', '2019-02-16 21:37:23', 1454.66, 2),
       ('m', 'r', 'PL80 0295 1501 5310 1109 7251', 'PL49 4249 2703 1880 1960 6768', '2019-02-17 21:50:51', 520.97,  2),
       ('n', 'q', 'PL80 0295 1501 5310 1109 7251', 'PL65 7348 2413 3521 2843 7960', '2019-02-26 17:20:55', 2736.20, 2),
       ('o', 'p', 'PL80 0295 1501 5310 1109 7251', 'PL07 1908 4517 7149 2879 9276', '2019-03-01 23:25:03', 2026.93, 2),
       ('p', 'o', 'PL80 0295 1501 5310 1109 7251', 'PL50 5062 0085 1227 5015 3729', '2019-03-02 23:26:02', 1338.73, 2),
       ('q', 'n', 'PL80 0295 1501 5310 1109 7251', 'PL62 2026 5721 5750 6891 6466', '2019-03-03 07:49:01', 2834.41, 2),
       ('r', 'm', 'PL80 0295 1501 5310 1109 7251', 'PL83 0721 2492 9992 7740 1693', '2019-03-04 17:05:09', 1000.00, 2),

       ('s', 'l', 'PL37 2194 1715 1934 9051 6937', 'PL91 3018 4529 8669 0250 7367', '2019-02-15 02:03:42', 2951.90, 3),
       ('t', 'k', 'PL37 2194 1715 1934 9051 6937', 'PL80 0295 1501 5310 1109 7251', '2019-02-17 11:11:13', 2008.81, 3),
       ('u', 'j', 'PL37 2194 1715 1934 9051 6937', 'PL49 4249 2703 1880 1960 6768', '2019-02-18 02:00:21', 2718.60, 3),
       ('v', 'i', 'PL37 2194 1715 1934 9051 6937', 'PL65 7348 2413 3521 2843 7960', '2019-02-18 17:26:14', 494.39,  3),
       ('w', 'h', 'PL37 2194 1715 1934 9051 6937', 'PL07 1908 4517 7149 2879 9276', '2019-02-20 12:19:40', 210.79,  3),
       ('x', 'g', 'PL37 2194 1715 1934 9051 6937', 'PL07 1908 4517 7149 2879 9276', '2019-02-20 20:19:19', 874.37,  3),
       ('y', 'f', 'PL37 2194 1715 1934 9051 6937', 'PL50 5062 0085 1227 5015 3729', '2019-02-21 19:41:15', 842.98,  3),
       ('z', 'e', 'PL37 2194 1715 1934 9051 6937', 'PL62 2026 5721 5750 6891 6466', '2019-03-10 11:16:49', 1306.08, 3),
       ('a', 'd', 'PL37 2194 1715 1934 9051 6937', 'PL83 0721 2492 9992 7740 1693', '2019-03-12 22:57:28', 381.40,  3),

       ('b', 'c', 'PL49 4249 2703 1880 1960 6768', 'PL91 3018 4529 8669 0250 7367', '2019-02-16 09:58:19', 746.37,  4),
       ('c', 'b', 'PL49 4249 2703 1880 1960 6768', 'PL80 0295 1501 5310 1109 7251', '2019-02-17 02:27:39', 774.95,  4),
       ('d', 'a', 'PL49 4249 2703 1880 1960 6768', 'PL37 2194 1715 1934 9051 6937', '2019-02-27 00:22:39', 2795.39, 4),
       ('e', 'z', 'PL49 4249 2703 1880 1960 6768', 'PL65 7348 2413 3521 2843 7960', '2019-03-02 19:08:51', 1962.07, 4),
       ('f', 'y', 'PL49 4249 2703 1880 1960 6768', 'PL65 7348 2413 3521 2843 7960', '2019-03-05 09:48:55', 2480.59, 4),
       ('g', 'x', 'PL49 4249 2703 1880 1960 6768', 'PL07 1908 4517 7149 2879 9276', '2019-03-05 15:07:45', 331.16,  4),
       ('h', 'w', 'PL49 4249 2703 1880 1960 6768', 'PL50 5062 0085 1227 5015 3729', '2019-03-06 11:28:16', 2665.42, 4),
       ('i', 'v', 'PL49 4249 2703 1880 1960 6768', 'PL62 2026 5721 5750 6891 6466', '2019-03-09 14:30:38', 2130.66, 4),
       ('j', 'u', 'PL49 4249 2703 1880 1960 6768', 'PL83 0721 2492 9992 7740 1693', '2019-03-11 17:48:13', 2690.65, 4),

       ('k', 't', 'PL65 7348 2413 3521 2843 7960', 'PL91 3018 4529 8669 0250 7367', '2019-02-13 14:00:51', 1836.36, 5),
       ('l', 's', 'PL65 7348 2413 3521 2843 7960', 'PL80 0295 1501 5310 1109 7251', '2019-02-15 04:17:57', 2255.51, 5),
       ('m', 'r', 'PL65 7348 2413 3521 2843 7960', 'PL37 2194 1715 1934 9051 6937', '2019-02-19 22:42:26', 220.76,  5),
       ('n', 'q', 'PL65 7348 2413 3521 2843 7960', 'PL49 4249 2703 1880 1960 6768', '2019-02-20 19:54:11', 111.28,  5),
       ('o', 'p', 'PL65 7348 2413 3521 2843 7960', 'PL07 1908 4517 7149 2879 9276', '2019-02-21 13:38:09', 1498.03, 5),
       ('p', 'o', 'PL65 7348 2413 3521 2843 7960', 'PL07 1908 4517 7149 2879 9276', '2019-02-24 13:45:57', 2743.63, 5),
       ('q', 'n', 'PL65 7348 2413 3521 2843 7960', 'PL50 5062 0085 1227 5015 3729', '2019-03-02 12:54:45', 2862.13, 5),
       ('r', 'm', 'PL65 7348 2413 3521 2843 7960', 'PL62 2026 5721 5750 6891 6466', '2019-03-10 11:57:31', 635.24,  5),
       ('s', 'l', 'PL65 7348 2413 3521 2843 7960', 'PL83 0721 2492 9992 7740 1693', '2019-03-12 03:12:31', 1787.07, 5),

       ('t', 'k', 'PL07 1908 4517 7149 2879 9276', 'PL91 3018 4529 8669 0250 7367', '2019-02-14 16:51:11', 1040.74, 6),
       ('u', 'j', 'PL07 1908 4517 7149 2879 9276', 'PL80 0295 1501 5310 1109 7251', '2019-02-18 17:30:19', 504.79,  6),
       ('v', 'i', 'PL07 1908 4517 7149 2879 9276', 'PL37 2194 1715 1934 9051 6937', '2019-02-18 23:31:11', 38.81,   6),
       ('w', 'h', 'PL07 1908 4517 7149 2879 9276', 'PL49 4249 2703 1880 1960 6768', '2019-02-25 12:15:31', 732.44,  6),
       ('x', 'g', 'PL07 1908 4517 7149 2879 9276', 'PL65 7348 2413 3521 2843 7960', '2019-02-26 18:55:38', 2293.69, 6),
       ('y', 'f', 'PL07 1908 4517 7149 2879 9276', 'PL50 5062 0085 1227 5015 3729', '2019-02-26 22:12:23', 2012.32, 6),
       ('z', 'e', 'PL07 1908 4517 7149 2879 9276', 'PL50 5062 0085 1227 5015 3729', '2019-03-06 20:31:17', 2731.81, 6),
       ('a', 'd', 'PL07 1908 4517 7149 2879 9276', 'PL62 2026 5721 5750 6891 6466', '2019-03-08 05:47:41', 505.88,  6),
       ('b', 'c', 'PL07 1908 4517 7149 2879 9276', 'PL83 0721 2492 9992 7740 1693', '2019-03-11 03:38:26', 243.31,  6),

       ('c', 'b', 'PL50 5062 0085 1227 5015 3729', 'PL91 3018 4529 8669 0250 7367', '2019-02-11 15:56:47', 806.32,  7),
       ('d', 'a', 'PL50 5062 0085 1227 5015 3729', 'PL37 2194 1715 1934 9051 6937', '2019-02-24 05:30:31', 1371.53, 7),
       ('e', 'z', 'PL50 5062 0085 1227 5015 3729', 'PL37 2194 1715 1934 9051 6937', '2019-03-01 13:04:58', 1308.83, 7),
       ('f', 'y', 'PL50 5062 0085 1227 5015 3729', 'PL49 4249 2703 1880 1960 6768', '2019-03-03 08:58:57', 2095.35, 7),
       ('g', 'x', 'PL50 5062 0085 1227 5015 3729', 'PL65 7348 2413 3521 2843 7960', '2019-03-04 11:17:30', 475.52,  7),
       ('h', 'w', 'PL50 5062 0085 1227 5015 3729', 'PL07 1908 4517 7149 2879 9276', '2019-03-11 12:38:33', 2007.01, 7),
       ('i', 'v', 'PL50 5062 0085 1227 5015 3729', 'PL50 5062 0085 1227 5015 3729', '2019-03-11 23:43:41', 945.05,  7),
       ('j', 'u', 'PL50 5062 0085 1227 5015 3729', 'PL62 2026 5721 5750 6891 6466', '2019-03-12 07:28:44', 253.77,  7),
       ('k', 't', 'PL50 5062 0085 1227 5015 3729', 'PL83 0721 2492 9992 7740 1693', '2019-03-12 08:33:29', 478.74,  7),

       ('l', 's', 'PL62 2026 5721 5750 6891 6466', 'PL80 0295 1501 5310 1109 7251', '2019-02-18 13:14:56', 528.35,  8),
       ('m', 'r', 'PL62 2026 5721 5750 6891 6466', 'PL91 3018 4529 8669 0250 7367', '2019-02-18 14:05:59', 890.45,  8),
       ('n', 'q', 'PL62 2026 5721 5750 6891 6466', 'PL37 2194 1715 1934 9051 6937', '2019-02-19 02:40:32', 228.95,  8),
       ('o', 'q', 'PL62 2026 5721 5750 6891 6466', 'PL49 4249 2703 1880 1960 6768', '2019-02-19 03:11:16', 1436.60, 8),
       ('p', 'p', 'PL62 2026 5721 5750 6891 6466', 'PL65 7348 2413 3521 2843 7960', '2019-02-28 07:42:59', 1807.37, 8),
       ('q', 'o', 'PL62 2026 5721 5750 6891 6466', 'PL07 1908 4517 7149 2879 9276', '2019-03-05 12:11:34', 2131.80, 8),
       ('r', 'n', 'PL62 2026 5721 5750 6891 6466', 'PL50 5062 0085 1227 5015 3729', '2019-03-07 09:29:35', 1517.27, 8),
       ('s', 'm', 'PL62 2026 5721 5750 6891 6466', 'PL62 2026 5721 5750 6891 6466', '2019-03-10 08:24:59', 1068.49, 8),
       ('t', 'l', 'PL62 2026 5721 5750 6891 6466', 'PL83 0721 2492 9992 7740 1693', '2019-03-11 21:07:15', 2003.35, 8),

       ('u', 'k', 'PL83 0721 2492 9992 7740 1693', 'PL80 0295 1501 5310 1109 7251', '2019-02-11 14:14:09', 837.90,  9),
       ('v', 'j', 'PL83 0721 2492 9992 7740 1693', 'PL37 2194 1715 1934 9051 6937', '2019-02-12 12:42:33', 2173.37, 9),
       ('w', 'i', 'PL83 0721 2492 9992 7740 1693', 'PL37 2194 1715 1934 9051 6937', '2019-02-15 06:42:15', 1974.81, 9),
       ('x', 'h', 'PL83 0721 2492 9992 7740 1693', 'PL49 4249 2703 1880 1960 6768', '2019-02-24 12:48:05', 1405.00, 9),
       ('y', 'g', 'PL83 0721 2492 9992 7740 1693', 'PL65 7348 2413 3521 2843 7960', '2019-03-08 04:57:50', 350.50,  9),
       ('z', 'f', 'PL83 0721 2492 9992 7740 1693', 'PL07 1908 4517 7149 2879 9276', '2019-03-09 15:08:00', 1854.12, 9),
       ('a', 'e', 'PL83 0721 2492 9992 7740 1693', 'PL50 5062 0085 1227 5015 3729', '2019-03-09 19:15:13', 377.42,  9),
       ('b', 'd', 'PL83 0721 2492 9992 7740 1693', 'PL62 2026 5721 5750 6891 6466', '2019-03-10 00:37:30', 1445.27, 9),
       ('c', 'c', 'PL83 0721 2492 9992 7740 1693', 'PL83 0721 2492 9992 7740 1693', '2019-03-10 19:23:04', 1282.38, 9);
