-- =============================
--  SCHEMA DE LA BASE studentgestion
--  VERSION CORRIGÉE SANS DUPLICATIONS
-- =============================

DROP TABLE IF EXISTS school_report_line;
DROP TABLE IF EXISTS registration;
DROP TABLE IF EXISTS student_guardian_link;
DROP TABLE IF EXISTS evaluation;
DROP TABLE IF EXISTS school_report;
DROP TABLE IF EXISTS teaching;
DROP TABLE IF EXISTS class_group;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS person;
DROP TYPE IF EXISTS role;

CREATE TYPE role AS ENUM ('ADMIN', 'TEACHER', 'LEGAL_GUARDIAN');

CREATE TABLE IF NOT EXISTS person (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT PRIMARY KEY REFERENCES person(id) ON DELETE CASCADE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    phone_number VARCHAR(50) UNIQUE NOT NULL,
    postal_address VARCHAR(255) NOT NULL,
    role ROLE NOT NULL
);

CREATE TABLE IF NOT EXISTS student (
    id BIGINT,
    birthday DATE NOT NULL,
    photo_url VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (photo_url),
    FOREIGN KEY (id) REFERENCES person (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS class_group (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(25) UNIQUE NOT NULL,
    head_teacher_id INT UNIQUE,
    FOREIGN KEY (head_teacher_id) REFERENCES app_user (id)
);

CREATE TABLE IF NOT EXISTS teaching (
    id BIGSERIAL PRIMARY KEY,
    subject_name VARCHAR(50) NOT NULL,
    class_group_id BIGINT NOT NULL,
    teacher_id BIGINT NOT NULL,
    FOREIGN KEY (class_group_id) REFERENCES class_group (id) ON DELETE CASCADE,
    FOREIGN KEY (teacher_id) REFERENCES app_user (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS school_report (
    id BIGSERIAL PRIMARY KEY,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    mention VARCHAR(20),
    overall_average DOUBLE PRECISION,
    general_comment VARCHAR(255),
    student_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS evaluation (
    id BIGSERIAL PRIMARY KEY,
    weight DOUBLE PRECISION NOT NULL,
    date_and_time TIMESTAMP NOT NULL,
    note DOUBLE PRECISION NOT NULL,
    student_id BIGINT NOT NULL,
    teaching_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,
    FOREIGN KEY (teaching_id) REFERENCES teaching (id)
);

CREATE TABLE IF NOT EXISTS student_guardian_link (
    guardian_id BIGINT,
    child_id BIGINT,
    PRIMARY KEY (guardian_id, child_id),
    FOREIGN KEY (guardian_id) REFERENCES app_user (id) ON DELETE CASCADE,
    FOREIGN KEY (child_id) REFERENCES student (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS registration (
    student_id BIGINT,
    class_group_id BIGINT,
    registration_date DATE NOT NULL,
    school_year VARCHAR(4) NOT NULL, 
    PRIMARY KEY (student_id, class_group_id),
    FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,
    FOREIGN KEY (class_group_id) REFERENCES class_group (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS school_report_line (
    teaching_id BIGINT,
    school_report_id BIGINT,
    comment VARCHAR(255),
    teaching_average DOUBLE PRECISION,
    PRIMARY KEY (teaching_id, school_report_id),
    FOREIGN KEY (teaching_id) REFERENCES teaching (id),
    FOREIGN KEY (school_report_id) REFERENCES school_report (id) ON DELETE CASCADE
);

-- =============================
-- INSERT PERSONS (Staff)
-- =============================

INSERT INTO person (firstname, lastname) VALUES
-- Admin
('Jean', 'Dupont'),     
-- Teachers
('Sophie', 'Martin'),
('Luc', 'Moreau'),
('Isabelle', 'Durand'),
('Thomas', 'Petit'),
('Julie', 'Robert'),
('Nicolas', 'Bernard'),
('Hélène', 'Lefevre'),
('Pierre', 'Garcia'),
('Laura', 'Faure'),
('Émilie', 'Blanc'),
('Olivier', 'Rousseau'),
('Anne', 'Germain'),
-- Legal Guardians
('Marc', 'Renard'),
('Fatima', 'Boulanger'),
('Vincent', 'Carpentier'),
('Claire', 'Legrand'),
('Ahmed', 'Benali'),
('Nadia', 'Benali'),
('Camille', 'Girard'),
('Paul', 'Girard'),
('Lucie', 'Lemoine');

-- =============================
-- INSERT APP_USERS
-- =============================

INSERT INTO app_user (id, password, email, username, phone_number, postal_address, role) VALUES
-- Admin
(1, '$2a$10$sAZzPFV/DX7lu2JGVN7db.eoF6xtR7gItaCZ5vm68ixoHcqxZzTI2', 'jean.dupont@example.com', 'jdupont', '+33611223344', '10 Rue Victor Hugo, Paris', 'ADMIN'),
-- Teachers
(2, '$2a$10$OX9DwMNKOvd.cICg98rznOhwLHg2Fn/3IIYqIirxtEIIAxqwH9Idy', 'sophie.martin@example.com', 'smartin', '+33655667789', '25 Avenue de la Liberté, Lyon', 'TEACHER'),
(3, '$2a$10$OFyKekZjBdDGRZ7Eo/wpuOT7nmRYvLJWnz4j5jnzfNpTXL3S0eOAK', 'luc.moreau@example.com', 'lmoreau', '+33799887766', '7 Rue des Écoles, Marseille', 'TEACHER'),
(4, '$2a$10$5MNA5LUyZaLNo3cL/TENfuVNcgu5zO5juVCKvPKFrNVjvW3Qkhir6', 'isabelle.durand@example.com', 'idurant', '+33689012345', '5 Rue Lafayette, Lille', 'TEACHER'),
(5, '$2a$10$WFnHoFAOU2YYeYOqC2TsUu.Y6YjDLDOodJ.WIm509SHH6j7DlWHHG', 'thomas.petit@example.com', 'tpetit', '+33690234567', '3 Rue Jean Moulin, Nice', 'TEACHER'),
(6, '$2a$10$xt2wqLw0/IB4OW0q29PXY.RyhLrefBdC7c7CNibIQ1JbLf1cZJUX6', 'julie.robert@example.com', 'jrobert', '+33760123456', '22 Rue du Rhône, Strasbourg', 'TEACHER'),
(7, '$2a$10$uKugv5x/wzKAhT7pbhBrDOLIRzski18/FKuFbxojslTWftG6m3IJC', 'nicolas.bernard@example.com', 'nbernard', '+33777788990', '15 Rue Gambetta, Nantes', 'TEACHER'),
(8, '$2a$10$.wdzrY146UNOFDwvJmIMcOLT8qC9Tazf7SLsl2b6BsWTk3Sju9zYW', 'helene.lefevre@example.com', 'hlefevre', '+33611227890', '30 Boulevard Saint-Michel, Paris', 'TEACHER'),
(9, '$2a$10$f1WQhlCv3Y6ifMy9SAcdbOCESD3Xrnb3lIkFdxJNhdrVcg5qQs.TO', 'pierre.garcia@example.com', 'pgarcia', '+33655669988', '18 Rue du Marché, Toulouse', 'TEACHER'),
(10, '$2a$10$VfGvInfe9hVvPTSWrBCcAuuKgJ/XKo7nVrY1DcvEtIK.8OM7hgY06', 'laura.faure@example.com', 'lfaure', '+33622446688', '10 Rue des Lilas, Rennes', 'TEACHER'),
(11, '$2a$10$m.YGG3txiKrxBnAFb3d4j.UJnPqO9.iwfrAMLEyA/5ygZXa/pwFKC', 'emilie.blanc@example.com', 'eblanc', '+33677880011', '2 Rue Pasteur, Dijon', 'TEACHER'),
(12, '$2a$10$.Hn0qjN7ZGCd39UC1injiO9DpPg9pjf9GgOtpOSzSTAtprUDDFjhi', 'olivier.rousseau@example.com', 'orousseau', '+33655667788', '8 Rue Victor Hugo, Orléans', 'TEACHER'),
(13, '$2a$10$R/WjBzeUon1uzisk1DBKTOFA00rAj7kYW4XiHR8T6VTA5iTnjyRi2', 'anne.germain@example.com', 'agermain', '+33666778899', '4 Rue du Stade, Reims', 'TEACHER'),
-- Legal Guardians
(14, '$2a$10$TccXv.7nfDkPIKSPqNI2N.ucuaaksUn8WuRThKGpm8qSd.TIfVEJ.', 'marc.renard@example.com', 'mrenard', '+33677889977', '11 Rue des Peupliers, Tours', 'LEGAL_GUARDIAN'),
(15, '$2a$10$heqcoRhqu/ZKoNHi3KBlye9DRIYamZ9.wkHM653POnEq6lIkE8YGq', 'fatima.boulanger@example.com', 'fboulanger', '+33699887711', '6 Rue du Lac, Montpellier', 'LEGAL_GUARDIAN'),
(16, '$2a$10$YKP3zXRz4bcG2PAVxLRQXezAB4L.5n1lyI69T8sJB5O/wHMKnEmbi', 'vincent.carpentier@example.com', 'vcarpentier', '+33688990077', '9 Rue du Soleil, Caen', 'LEGAL_GUARDIAN'),
(17, '$2a$10$b66SsE7rVuDEgeNotN/KyOB53UmR4fLTK3WXj8XoewtenH4xpZGpm', 'claire.legrand@example.com', 'clegrand', '+33666554433', '1 Rue des Fleurs, Brest', 'LEGAL_GUARDIAN'),
(18, '$2a$10$b2rfbTRfS8vXltQhpQMec.hwrldcAIFQ8rMP.6PqLkPYY8ohz5Q1a', 'ahmed.benali@example.com', 'abenali', '+33677889922', '8 Rue du Parc, Toulouse', 'LEGAL_GUARDIAN'),
(19, '$2a$10$HQR8cXqciurvXI4z7SIJ0OaFunIHIrBJBjldDdHd2/c3YlKCzU5b2', 'nadia.benali@example.com', 'nbenali', '+33666778800', '8 Rue du Parc, Toulouse', 'LEGAL_GUARDIAN'),
(20, '$2a$10$AK274H8dR.DNahbmxQ8yfuktkofSUHXVzcw3DPKGM/a4Ykr1zkOK.', 'camille.girard@example.com', 'cgirard', '+33677889944', '5 Rue de Provence, Lyon', 'LEGAL_GUARDIAN'),
(21, '$2a$10$W3S.Pxf5CkNkznWVoPk3eeob472KOFOrqCefPyiI/qMnBQODkjEP2', 'paul.girard@example.com', 'pgirard', '+33688776655', '5 Rue de Provence, Lyon', 'LEGAL_GUARDIAN'),
(22, '$2a$10$ilVP8.w6BhWG2kgPDV60N.NWPxTrRi.O6rishiiB8ocJ.7PbbL8ga', 'lucie.lemoine@example.com', 'llemoine', '+33699001122', '4 Rue du Moulin, Nantes', 'LEGAL_GUARDIAN');

-- =============================
-- STUDENTS PERSON DATA (60 étudiants)
-- =============================

INSERT INTO person (firstname, lastname) VALUES
-- 3ème A (30 étudiants)
('Lucas', 'Girard'), ('Emma', 'Girard'), 
('Nathan', 'Benali'), ('Sarah', 'Benali'),
('Louis', 'Renard'), ('Chloé', 'Renard'),
('Hugo', 'Boulanger'), ('Léa', 'Boulanger'),
('Tom', 'Carpentier'), ('Jade', 'Carpentier'),
('Arthur', 'Legrand'), ('Manon', 'Legrand'),
('Gabriel', 'Martin'), ('Inès', 'Martin'),
('Enzo', 'Petit'), ('Clara', 'Petit'),
('Noah', 'Durand'), ('Lina', 'Durand'),
('Mathis', 'Garcia'), ('Mila', 'Garcia'),
('Ethan', 'Lemoine'), ('Romane', 'Lemoine'),
('Paul', 'Rousseau'), ('Anna', 'Rousseau'),
('Sacha', 'Bernard'), ('Camille', 'Bernard'),
('Léo', 'Faure'), ('Zoé', 'Faure'),
('Baptiste', 'Blanc'), ('Eva', 'Blanc'),

-- 3ème B (30 étudiants)
('Noé', 'Germain'), ('Juliette', 'Germain'),
('Axel', 'Robert'), ('Ambre', 'Robert'),
('Raphaël', 'Moreau'), ('Alice', 'Moreau'),
('Timéo', 'Lemoine'), ('Élisa', 'Lemoine'),
('Adam', 'Benali'), ('Yasmine', 'Benali'),
('Maël', 'Girard'), ('Charlotte', 'Girard'),
('Nolan', 'Legrand'), ('Louna', 'Legrand'),
('Victor', 'Durand'), ('Eléonore', 'Durand'),
('Valentin', 'Garcia'), ('Justine', 'Garcia'),
('Théo', 'Boulanger'), ('Léonie', 'Boulanger'),
('Maxime', 'Rousseau'), ('Clémentine', 'Rousseau'),
('Adrien', 'Renard'), ('Océane', 'Renard'),
('Antoine', 'Faure'), ('Capucine', 'Faure'),
('Alexandre', 'Blanc'), ('Margaux', 'Blanc'),
('Benjamin', 'Martin'), ('Élodie', 'Martin');

-- =============================
-- STUDENT TABLE (60 étudiants)
-- =============================

INSERT INTO student (id, birthday, photo_url) VALUES
-- 3ème A (IDs 23-52)
(23, '1998-01-15', NULL), (24, '1999-04-02', NULL),
(25, '1998-02-18', NULL), (26, '1999-05-30', NULL),
(27, '1998-03-11', NULL), (28, '1998-11-05', NULL),
(29, '1999-02-25', NULL), (30, '1998-09-10', NULL),
(31, '1999-06-12', NULL), (32, '1998-07-08', NULL),
(33, '1999-01-22', NULL), (34, '1998-03-27', NULL),
(35, '1998-10-03', NULL), (36, '1999-03-18', NULL),
(37, '1998-09-12', NULL), (38, '1999-04-28', NULL),
(39, '1998-11-09', NULL), (40, '1999-05-21', NULL),
(41, '1998-02-10', NULL), (42, '1999-06-03', NULL),
(43, '1998-04-19', NULL), (44, '1998-12-29', NULL),
(45, '1998-07-14', NULL), (46, '1999-01-03', NULL),
(47, '1999-02-07', NULL), (48, '1999-06-25', NULL),
(49, '1998-05-15', NULL), (50, '1999-04-11', NULL),
(51, '1998-09-01', NULL), (52, '1999-03-20', NULL),

-- 3ème B (IDs 53-82)
(53, '1998-01-30', NULL), (54, '1999-04-06', NULL),
(55, '1998-02-17', NULL), (56, '1999-05-14', NULL),
(57, '1998-03-22', NULL), (58, '1999-06-05', NULL),
(59, '1998-07-19', NULL), (60, '1998-12-02', NULL),
(61, '1999-03-11', NULL), (62, '1998-08-29', NULL),
(63, '1999-01-19', NULL), (64, '1998-05-08', NULL),
(65, '1998-06-26', NULL), (66, '1999-02-24', NULL),
(67, '1998-11-04', NULL), (68, '1999-04-19', NULL),
(69, '1998-01-10', NULL), (70, '1999-05-22', NULL),
(71, '1998-09-03', NULL), (72, '1998-10-30', NULL),
(73, '1999-02-12', NULL), (74, '1998-06-17', NULL),
(75, '1998-07-25', NULL), (76, '1998-03-29', NULL),
(77, '1999-05-01', NULL), (78, '1998-04-15', NULL),
(79, '1999-02-06', NULL), (80, '1998-10-12', NULL),
(81, '1998-05-24', NULL), (82, '1999-04-03', NULL);

-- =============================
-- CLASS GROUPS
-- =============================

INSERT INTO class_group (name, head_teacher_id) VALUES
('3ème A', 2), -- Sophie Martin
('3ème B', 3); -- Luc Moreau

-- =============================
-- REGISTRATIONS (✅ SANS DUPLICATION)
-- =============================

INSERT INTO registration (student_id, class_group_id, registration_date, school_year) VALUES
-- 3ème A (30 étudiants)
(23, 1, '2011-09-01', '2011'), (24, 1, '2011-09-01', '2011'), (25, 1, '2011-09-01', '2011'),
(26, 1, '2011-09-01', '2011'), (27, 1, '2011-09-01', '2011'), (28, 1, '2011-09-01', '2011'),
(29, 1, '2011-09-01', '2011'), (30, 1, '2011-09-01', '2011'), (31, 1, '2011-09-01', '2011'),
(32, 1, '2011-09-01', '2011'), (33, 1, '2011-09-01', '2011'), (34, 1, '2011-09-01', '2011'),
(35, 1, '2011-09-01', '2011'), (36, 1, '2011-09-01', '2011'), (37, 1, '2011-09-01', '2011'),
(38, 1, '2011-09-01', '2011'), (39, 1, '2011-09-01', '2011'), (40, 1, '2011-09-01', '2011'),
(41, 1, '2011-09-01', '2011'), (42, 1, '2011-09-01', '2011'), (43, 1, '2011-09-01', '2011'),
(44, 1, '2011-09-01', '2011'), (45, 1, '2011-09-01', '2011'), (46, 1, '2011-09-01', '2011'),
(47, 1, '2011-09-01', '2011'), (48, 1, '2011-09-01', '2011'), (49, 1, '2011-09-01', '2011'),
(50, 1, '2011-09-01', '2011'), (51, 1, '2011-09-01', '2011'), (52, 1, '2011-09-01', '2011'),

-- 3ème B (30 étudiants) - ✅ SANS LA DUPLICATION DU 71
(53, 2, '2011-09-01', '2011'), (54, 2, '2011-09-01', '2011'), (55, 2, '2011-09-01', '2011'),
(56, 2, '2011-09-01', '2011'), (57, 2, '2011-09-01', '2011'), (58, 2, '2011-09-01', '2011'),
(59, 2, '2011-09-01', '2011'), (60, 2, '2011-09-01', '2011'), (61, 2, '2011-09-01', '2011'),
(62, 2, '2011-09-01', '2011'), (63, 2, '2011-09-01', '2011'), (64, 2, '2011-09-01', '2011'),
(65, 2, '2011-09-01', '2011'), (66, 2, '2011-09-01', '2011'), (67, 2, '2011-09-01', '2011'),
(68, 2, '2011-09-01', '2011'), (69, 2, '2011-09-01', '2011'), (70, 2, '2011-09-01', '2011'),
(71, 2, '2011-09-01', '2011'), -- ✅ UNE SEULE FOIS
(72, 2, '2011-09-01', '2011'), (73, 2, '2011-09-01', '2011'),
(74, 2, '2011-09-01', '2011'), (75, 2, '2011-09-01', '2011'),
(76, 2, '2011-09-01', '2011'), (77, 2, '2011-09-01', '2011'),
(78, 2, '2011-09-01', '2011'), (79, 2, '2011-09-01', '2011'),
(80, 2, '2011-09-01', '2011'), (81, 2, '2011-09-01', '2011'),
(82, 2, '2011-09-01', '2011');

-- =============================
-- FAMILY LINKS (✅ CORRIGÉ - SANS CONFLITS)
-- =============================

-- Famille Girard (Paul 21 + Camille 20)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(21, 23), (20, 23), -- Lucas Girard
(21, 24), (20, 24), -- Emma Girard
(21, 63), (20, 63), -- Maël Girard
(21, 64), (20, 64); -- Charlotte Girard

-- Famille Benali (Ahmed 18 + Nadia 19)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(18, 25), (19, 25), -- Nathan Benali
(18, 26), (19, 26), -- Sarah Benali
(18, 61), (19, 61), -- Adam Benali
(18, 62), (19, 62); -- Yasmine Benali

-- Famille Renard (Marc 14)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(14, 27), (14, 28),  -- Louis, Chloé Renard
(14, 73), (14, 74);  -- Adrien, Océane Renard

-- Famille Boulanger (Fatima 15)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(15, 29), (15, 30),  -- Hugo, Léa Boulanger
(15, 69), (15, 70);  -- Théo, Léonie Boulanger

-- Famille Carpentier (Vincent 16)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(16, 31), (16, 32); -- Tom, Jade Carpentier

-- Famille Legrand (Claire 17)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(17, 33), (17, 34),  -- Arthur, Manon Legrand
(17, 65), (17, 66);  -- Nolan, Louna Legrand

-- Famille Martin (Sophie 2 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(2, 35), (2, 36),   -- Gabriel, Inès Martin
(2, 81), (2, 82);   -- Benjamin, Élodie Martin

-- Famille Petit (Thomas 5 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(5, 37), (5, 38);   -- Enzo, Clara Petit

-- Famille Durand (Isabelle 4 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(4, 39), (4, 40),   -- Noah, Lina Durand
(4, 67), (4, 68);   -- Victor, Eléonore Durand

-- Famille Garcia (Pierre 9 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(9, 41), (9, 42),   -- Mathis, Mila Garcia
(9, 69), (9, 70);   -- Valentin, Justine Garcia

-- Famille Lemoine (Lucie 22)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(22, 43), (22, 44), -- Ethan, Romane Lemoine
(22, 59), (22, 60); -- Timéo, Élisa Lemoine

-- Famille Rousseau (Olivier 12 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(12, 45), (12, 46), -- Paul, Anna Rousseau
(12, 71), (12, 72); -- Maxime, Clémentine Rousseau

-- Famille Bernard (Nicolas 7 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(7, 47), (7, 48);   -- Sacha, Camille Bernard

-- Famille Faure (Laura 10 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(10, 49), (10, 50), -- Léo, Zoé Faure
(10, 75), (10, 76); -- Antoine, Capucine Faure

-- Famille Blanc (Émilie 11 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(11, 51), (11, 52), -- Baptiste, Eva Blanc
(11, 77), (11, 78); -- Alexandre, Margaux Blanc

-- Famille Germain (Anne 13 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(13, 53), (13, 54); -- Noé, Juliette Germain

-- Famille Robert (Julie 6 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(6, 55), (6, 56);   -- Axel, Ambre Robert

-- Famille Moreau (Luc 3 - Teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(3, 57), (3, 58);   -- Raphaël, Alice Moreau

-- =============================
-- TEACHINGS
-- =============================

INSERT INTO teaching (subject_name, class_group_id, teacher_id) VALUES
-- 3ème A (IDs 1-11)
('Français', 1, 2),
('Mathématiques', 1, 3),
('Histoire-Géographie', 1, 6),
('Physique-Chimie', 1, 8),
('Sciences de la Vie et de la Terre', 1, 9),
('Technologie', 1, 5),
('Anglais', 1, 7),
('Espagnol', 1, 10),
('Éducation Physique et Sportive', 1, 11),
('Arts Plastiques', 1, 12),
('Éducation Musicale', 1, 13),

-- 3ème B (IDs 12-22)
('Français', 2, 2),
('Mathématiques', 2, 3),
('Histoire-Géographie', 2, 6),
('Physique-Chimie', 2, 8),
('Sciences de la Vie et de la Terre', 2, 9),
('Technologie', 2, 5),
('Anglais', 2, 7),
('Espagnol', 2, 10),
('Éducation Physique et Sportive', 2, 11),
('Arts Plastiques', 2, 12),
('Éducation Musicale', 2, 13);

-- =============================
-- EVALUATIONS (Échantillon pour Lucas Girard - ID 23)
-- =============================

-- Trimestre 1
INSERT INTO evaluation (weight, date_and_time, note, student_id, teaching_id) VALUES
-- Français
(1.0, '2011-10-15 10:00:00', 15.5, 23, 1),
(1.0, '2011-11-20 10:00:00', 16.0, 23, 1),
(2.0, '2011-12-10 10:00:00', 17.0, 23, 1),
-- Mathématiques
(1.0, '2011-10-10 09:00:00', 14.0, 23, 2),
(1.0, '2011-11-15 09:00:00', 15.5, 23, 2),
(2.0, '2011-12-05 09:00:00', 16.5, 23, 2),
-- Histoire-Géographie
(1.0, '2011-10-18 14:00:00', 16.5, 23, 3),
(1.0, '2011-11-22 14:00:00', 17.0, 23, 3),
(1.0, '2011-12-12 14:00:00', 16.0, 23, 3),
-- Physique-Chimie
(1.0, '2011-10-25 11:00:00', 15.0, 23, 4),
(1.0, '2011-11-28 11:00:00', 14.5, 23, 4),
(2.0, '2011-12-15 11:00:00', 15.5, 23, 4),
-- SVT
(1.0, '2011-10-20 08:00:00', 16.0, 23, 5),
(1.0, '2011-11-25 08:00:00', 17.5, 23, 5),
(1.0, '2011-12-08 08:00:00', 16.5, 23, 5),
-- Technologie
(1.0, '2011-10-12 13:00:00', 15.5, 23, 6),
(1.0, '2011-11-18 13:00:00', 16.0, 23, 6),
(1.0, '2011-12-13 13:00:00', 15.0, 23, 6),
-- Anglais
(1.0, '2011-10-14 15:00:00', 17.0, 23, 7),
(1.0, '2011-11-19 15:00:00', 17.5, 23, 7),
(2.0, '2011-12-09 15:00:00', 18.0, 23, 7),
-- Espagnol
(1.0, '2011-10-21 16:00:00', 14.5, 23, 8),
(1.0, '2011-11-24 16:00:00', 15.0, 23, 8),
(1.0, '2011-12-14 16:00:00', 15.5, 23, 8),
-- EPS
(1.0, '2011-10-13 10:00:00', 16.0, 23, 9),
(1.0, '2011-11-17 10:00:00', 16.5, 23, 9),
(1.0, '2011-12-11 10:00:00', 17.0, 23, 9),
-- Arts Plastiques
(1.0, '2011-10-19 14:00:00', 16.5, 23, 10),
(1.0, '2011-11-23 14:00:00', 17.0, 23, 10),
(1.0, '2011-12-07 14:00:00', 16.0, 23, 10),
-- Éducation Musicale
(1.0, '2011-10-26 11:00:00', 15.5, 23, 11),
(1.0, '2011-11-29 11:00:00', 16.0, 23, 11),
(1.0, '2011-12-16 11:00:00', 16.5, 23, 11);

-- Trimestre 2
INSERT INTO evaluation (weight, date_and_time, note, student_id, teaching_id) VALUES
-- Français
(1.0, '2012-01-15 10:00:00', 17.0, 23, 1),
(1.0, '2012-02-20 10:00:00', 17.5, 23, 1),
(2.0, '2012-03-25 10:00:00', 18.0, 23, 1),
-- Mathématiques
(1.0, '2012-01-10 09:00:00', 16.0, 23, 2),
(1.0, '2012-02-15 09:00:00', 16.5, 23, 2),
(2.0, '2012-03-20 09:00:00', 17.5, 23, 2),
-- Histoire-Géographie
(1.0, '2012-01-18 14:00:00', 17.5, 23, 3),
(1.0, '2012-02-22 14:00:00', 18.0, 23, 3),
(1.0, '2012-03-28 14:00:00', 17.0, 23, 3),
-- Physique-Chimie
(1.0, '2012-01-25 11:00:00', 16.0, 23, 4),
(1.0, '2012-02-28 11:00:00', 16.5, 23, 4),
(2.0, '2012-03-22 11:00:00', 17.0, 23, 4),
-- SVT
(1.0, '2012-01-20 08:00:00', 17.0, 23, 5),
(1.0, '2012-02-25 08:00:00', 18.0, 23, 5),
(1.0, '2012-03-24 08:00:00', 17.5, 23, 5),
-- Technologie
(1.0, '2012-01-12 13:00:00', 16.0, 23, 6),
(1.0, '2012-02-18 13:00:00', 16.5, 23, 6),
(1.0, '2012-03-26 13:00:00', 17.0, 23, 6),
-- Anglais
(1.0, '2012-01-14 15:00:00', 18.0, 23, 7),
(1.0, '2012-02-19 15:00:00', 18.5, 23, 7),
(2.0, '2012-03-23 15:00:00', 19.0, 23, 7),
-- Espagnol
(1.0, '2012-01-21 16:00:00', 15.5, 23, 8),
(1.0, '2012-02-24 16:00:00', 16.0, 23, 8),
(1.0, '2012-03-29 16:00:00', 16.5, 23, 8),
-- EPS
(1.0, '2012-01-13 10:00:00', 17.0, 23, 9),
(1.0, '2012-02-17 10:00:00', 17.5, 23, 9),
(1.0, '2012-03-21 10:00:00', 18.0, 23, 9),
-- Arts Plastiques
(1.0, '2012-01-19 14:00:00', 17.0, 23, 10),
(1.0, '2012-02-23 14:00:00', 17.5, 23, 10),
(1.0, '2012-03-27 14:00:00', 18.0, 23, 10),
-- Éducation Musicale
(1.0, '2012-01-26 11:00:00', 16.5, 23, 11),
(1.0, '2012-02-29 11:00:00', 17.0, 23, 11),
(1.0, '2012-03-30 11:00:00', 17.5, 23, 11);

-- =============================
-- SCHOOL REPORTS (Bulletins)
-- =============================

-- Lucas Girard (ID 23)
INSERT INTO school_report (period_start, period_end, mention, overall_average, general_comment, student_id) VALUES
('2011-09-01', '2011-12-15', 'Bien', 15.9, 'Bon trimestre. Élève appliqué et curieux.', 23),
('2012-01-03', '2012-03-30', 'Très bien', 17.1, 'Excellent travail et bonne participation.', 23),
('2012-04-16', '2012-06-30', 'Très bien', NULL, 'Très bons résultats, sérieuse motivation.', 23),
('2011-09-01', '2012-06-30', 'Très bien', NULL, 'Excellente année scolaire. Félicitations du conseil de classe.', 23);

-- Emma Girard (ID 24)
INSERT INTO school_report (period_start, period_end, mention, overall_average, general_comment, student_id) VALUES
('2011-09-01', '2011-12-15', 'Bien', NULL, 'Très bon début d''année.', 24),
('2012-01-03', '2012-03-30', 'Très bien', NULL, 'Excellent trimestre. Emma montre une grande régularité.', 24),
('2012-04-16', '2012-06-30', 'Très bien', NULL, 'Très belle progression. Élève motivée, attentive.', 24),
('2011-09-01', '2012-06-30', 'Très bien', NULL, 'Année remarquable. Félicitations du conseil de classe.', 24);

-- Mathis Garcia (ID 41)
INSERT INTO school_report (period_start, period_end, mention, overall_average, general_comment, student_id) VALUES
('2011-09-01', '2011-12-15', 'Passable', NULL, 'Résultats fragiles. Doit s''impliquer davantage.', 41),
('2012-01-03', '2012-03-30', 'Assez bien', NULL, 'Progrès notables. Encouragements.', 41),
('2012-04-16', '2012-06-30', 'Assez bien', NULL, 'Amélioration constante. À poursuivre.', 41),
('2011-09-01', '2012-06-30', 'Assez bien', NULL, 'Année en progression. Encouragements du conseil.', 41);

-- =============================
-- SCHOOL REPORT LINES
-- =============================

-- Trimestre 1 - Lucas Girard (School Report ID 1)
INSERT INTO school_report_line (teaching_id, school_report_id, comment, teaching_average) VALUES
(1, 1, 'Bon niveau de compréhension. Travail régulier.', 16.1),
(2, 1, 'Bonne maîtrise des notions. Peut approfondir.', 15.5),
(3, 1, 'Participation pertinente, connaissances solides.', 16.5),
(4, 1, 'Résultats satisfaisants.', 15.0),
(5, 1, 'Élève sérieuse, bonne attitude scientifique.', 16.7),
(6, 1, 'Travail précis et soigné.', 15.5),
(7, 1, 'Très bon niveau en anglais.', 17.5),
(8, 1, 'Ensemble positif.', 15.0),
(9, 1, 'Participation active, bonne endurance.', 16.5),
(10, 1, 'Bonne créativité.', 16.5),
(11, 1, 'Bonne implication musicale.', 16.0);

-- Trimestre 2 - Lucas Girard (School Report ID 2)
INSERT INTO school_report_line (teaching_id, school_report_id, comment, teaching_average) VALUES
(1, 2, 'Excellent trimestre. Rédactions très abouties.', 17.4),
(2, 2, 'Progrès constants, résultats excellents.', 16.7),
(3, 2, 'Bonne capacité d''analyse, travail approfondi.', 17.5),
(4, 2, 'Travail sérieux et rigoureux.', 16.5),
(5, 2, 'Très bon investissement en SVT.', 17.5),
(6, 2, 'Très bonne compréhension technique.', 16.5),
(7, 2, 'Excellent accent et bonne compréhension.', 18.5),
(8, 2, 'Beaucoup de progrès.', 16.0),
(9, 2, 'Toujours volontaire et dynamique.', 17.5),
(10, 2, 'Très belle sensibilité artistique.', 17.5),
(11, 2, 'Excellente écoute musicale.', 17.0);