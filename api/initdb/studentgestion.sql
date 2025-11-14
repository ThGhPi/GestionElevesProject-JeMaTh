-- =============================
--  SCHEMA DE LA BASE studentgestion
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
    UNIQUE (photo),
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

-- Persons: Admin + Teachers + Legal Guardians
INSERT INTO person (firstname, lastname) VALUES
('Jean', 'Dupont'),        -- Admin + Teacher
('Sophie', 'Martin'),      -- Teacher
('Luc', 'Moreau'),         -- Teacher
('Isabelle', 'Durand'),    -- Teacher
('Thomas', 'Petit'),       -- Teacher
('Julie', 'Robert'),       -- Teacher
('Nicolas', 'Bernard'),    -- Teacher
('Hélène', 'Lefevre'),     -- Teacher
('Pierre', 'Garcia'),      -- Teacher
('Laura', 'Faure'),        -- Teacher
('Émilie', 'Blanc'),       -- Teacher
('Olivier', 'Rousseau'),   -- Teacher
('Anne', 'Germain'),       -- Teacher
('Marc', 'Renard'),        -- Legal Guardian
('Fatima', 'Boulanger'),   -- Legal Guardian
('Vincent', 'Carpentier'), -- Legal Guardian
('Claire', 'Legrand'),     -- Legal Guardian
('Ahmed', 'Benali'),       -- Legal Guardian
('Nadia', 'Benali'),       -- Legal Guardian
('Camille', 'Girard'),     -- Legal Guardian
('Paul', 'Girard'),        -- Legal Guardian
('Lucie', 'Lemoine');      -- Legal Guardian

INSERT INTO app_user (id, password, email, username, phone_number, postal_address, role) VALUES
(1, '$2a$10$sAZzPFV/DX7lu2JGVN7db.eoF6xtR7gItaCZ5vm68ixoHcqxZzTI2', 'jean.dupont@example.com', 'jdupont', '+33611223344', '10 Rue Victor Hugo, Paris', 'ADMIN'),
(2, '$2a$10$OARb5zOADe2gLGvf2XntGuam11E35jWhpbdzfpZZSrP6D2aq7QepW', 'sophie.martin@example.com', 'smartin', '+33655667788', '25 Avenue de la Liberté, Lyon', 'TEACHER'),
(3, '$2a$10$M6kxMw5Me1bLgjn7uNpSkeVmzPae/Bw392.ZHn0stCzfsnhiOzUhG', 'luc.moreau@example.com', 'lmoreau', '+33799887766', '7 Rue des Écoles, Marseille', 'TEACHER'),
(4, '$2a$10$yEXJ1EwsqO5eqpryWcE5QOB4JjC6E8kq7bVJ9V/g0DSh1Y4p5IVT6', 'isabelle.durand@example.com', 'idurant', '+33689012345', '5 Rue Lafayette, Lille', 'TEACHER'),
(5, '$2a$10$E6kVCPVObyPnJdHhPjHoUel9KcdbuVAXvB9n2v0qE2NH5C2xib.E2', 'thomas.petit@example.com', 'tpetit', '+33690234567', '3 Rue Jean Moulin, Nice', 'TEACHER'),
(6, '$2a$10$gPCQhslmBhX8ZcU/kIX1i.C3dJ/1zP2A3B8d0M4zFz4I9qMJ14aHa', 'julie.robert@example.com', 'jrobert', '+33760123456', '22 Rue du Rhône, Strasbourg', 'TEACHER'),
(7, '$2a$10$kB.AK4pbnEXeKHv8lmpv4eP3LcpQyGgEpCsnqK5T4h5a0CNv8bcCe', 'nicolas.bernard@example.com', 'nbernard', '+33777788990', '15 Rue Gambetta, Nantes', 'TEACHER'),
(8, '$2a$10$U6eY6pFbQAbWn4bgFmv1xe2.5q3plH2MRM3nIt8oCMZ5c9R1y/Nu2', 'helene.lefevre@example.com', 'hlefevre', '+33611227890', '30 Boulevard Saint-Michel, Paris', 'TEACHER'),
(9, '$2a$10$Y9WjYxKTXxV7U4AqOZV2Fe9u.6y6gr4yF0y5hDR5xxz0iZhv3KPO2', 'pierre.garcia@example.com', 'pgarcia', '+33655669988', '18 Rue du Marché, Toulouse', 'TEACHER'),
(10, '$2a$10$h9oZBObG8e4ApV6UewD0re6n8opbJ9r6A2OZITjO8F5Wb6z5DlqXG', 'laura.faure@example.com', 'lfaure', '+33622446688', '10 Rue des Lilas, Rennes', 'TEACHER'),
(11, '$2a$10$OaO2Z1hXhOq3nK4yN2H4tOQjRZV7vKPGNwP3qjh7blKPNb1pF/yk6', 'emilie.blanc@example.com', 'eblanc', '+33677880011', '2 Rue Pasteur, Dijon', 'TEACHER'),
(12, '$2a$10$xEz4hW7E04Wh.DDdL3wT9O5U9PR9a8SlV36J1mvbI4Lg6sU/jvZhK', 'olivier.rousseau@example.com', 'orousseau', '+33655667788', '8 Rue Victor Hugo, Orléans', 'TEACHER'),
(13, '$2a$10$KpQqP5pK1fK3hRzBnRkzXOo7H0M6L9R1W7k2rZVo1Z2W2z3PjXZ0m', 'anne.germain@example.com', 'agermain', '+33666778899', '4 Rue du Stade, Reims', 'TEACHER'),
(14, '$2a$10$NqL3jLxjO2N8wM5lK7h0sO8Z9yR5y8sGqJ1lZ0yM3kN6tA7uYdWzO', 'marc.renard@example.com', 'mrenard', '+33677889977', '11 Rue des Peupliers, Tours', 'LEGAL_GUARDIAN'),
(15, '$2a$10$zGkY8wV7oT6xC4rZpQnYzO4UuQqM5aJ8wVnD4kE8hB7aC9uVxYyJ2', 'fatima.boulanger@example.com', 'fboulanger', '+33699887711', '6 Rue du Lac, Montpellier', 'LEGAL_GUARDIAN'),
(16, '$2a$10$TnV7uXyL8hQkJ3tN9rZxF5eS6pC1mD4vT5nJ2hQ0rC7bM8pF6dO8G', 'vincent.carpentier@example.com', 'vcarpentier', '+33688990077', '9 Rue du Soleil, Caen', 'LEGAL_GUARDIAN'),
(17, '$2a$10$FjRzJkJ2uCzYkM7iL6h9OeK6xB2dN9qS3pU6xZ9bQ7fG1vL8yJxO8', 'claire.legrand@example.com', 'clegrand', '+33666554433', '1 Rue des Fleurs, Brest', 'LEGAL_GUARDIAN'),
(18, '$2a$10$TgVnP8oZ1kL2mJ5qS7tB9hD4fR3vL8uP9sK6nO3aF8mH1yG2rZxJ1', 'ahmed.benali@example.com', 'abenali', '+33677889922', '8 Rue du Parc, Toulouse', 'LEGAL_GUARDIAN'),
(19, '$2a$10$DnWmE9pZ2rC5vB8jH4sL7fP9qA6dR3zM2nT8xW4yG1cK5bH9jQvO1', 'nadia.benali@example.com', 'nbenali', '+33666778800', '8 Rue du Parc, Toulouse', 'LEGAL_GUARDIAN'),
(20, '$2a$10$KwZxA5rL8pO1sE6qD3hV9nK7bC2tM4fG5rX8jU2mL6vY9zB1wPqT0', 'camille.girard@example.com', 'cgirard', '+33677889944', '5 Rue de Provence, Lyon', 'LEGAL_GUARDIAN'),
(21, '$2a$10$RxL3nM8vF5kS9hC7qD2pB6tR4eG1wN3jK8lO5aY7zP0xU2mW9cVdZ', 'paul.girard@example.com', 'pgirard', '+33688776655', '5 Rue de Provence, Lyon', 'LEGAL_GUARDIAN'),
(22, '$2a$10$VnM4pK2sJ8qD1rL9tB3hF5eN6aC7vO4xP8zR0wY5mT6nU2jL3gH9', 'lucie.lemoine@example.com', 'llemoine', '+33699001122', '4 Rue du Moulin, Nantes', 'LEGAL_GUARDIAN');

-- 3ème A (students 1 → 30)
INSERT INTO person (firstname, lastname) VALUES
('Lucas', 'Girard'), ('Emma', 'Girard'), ('Nathan', 'Benali'), ('Sarah', 'Benali'), ('Louis', 'Renard'), ('Chloé', 'Renard'),
('Hugo', 'Boulanger'), ('Léa', 'Boulanger'), ('Tom', 'Carpentier'), ('Jade', 'Carpentier'), ('Arthur', 'Legrand'), ('Manon', 'Legrand'),
('Gabriel', 'Martin'), ('Inès', 'Martin'), ('Enzo', 'Petit'), ('Clara', 'Petit'), ('Noah', 'Durand'), ('Lina', 'Durand'),
('Mathis', 'Garcia'), ('Mila', 'Garcia'), ('Ethan', 'Lemoine'), ('Romane', 'Lemoine'), ('Paul', 'Rousseau'), ('Anna', 'Rousseau'),
('Sacha', 'Bernard'), ('Camille', 'Bernard'), ('Léo', 'Faure'), ('Zoé', 'Faure'), ('Baptiste', 'Blanc'), ('Eva', 'Blanc');

-- 3ème B (students 31 → 60)
INSERT INTO person (firstname, lastname) VALUES
('Noé', 'Germain'), ('Juliette', 'Germain'), ('Axel', 'Robert'), ('Ambre', 'Robert'), ('Raphaël', 'Moreau'), ('Alice', 'Moreau'),
('Timéo', 'Lemoine'), ('Élisa', 'Lemoine'), ('Adam', 'Benali'), ('Yasmine', 'Benali'), ('Maël', 'Girard'), ('Charlotte', 'Girard'),
('Nolan', 'Legrand'), ('Louna', 'Legrand'), ('Victor', 'Durand'), ('Eléonore', 'Durand'), ('Valentin', 'Garcia'), ('Justine', 'Garcia'),
('Théo', 'Boulanger'), ('Léonie', 'Boulanger'), ('Maxime', 'Rousseau'), ('Clémentine', 'Rousseau'), ('Adrien', 'Renard'), ('Océane', 'Renard'),
('Antoine', 'Faure'), ('Capucine', 'Faure'), ('Alexandre', 'Blanc'), ('Margaux', 'Blanc'), ('Benjamin', 'Martin'), ('Élodie', 'Martin');

-- STUDENT table (using matching IDs in order of insertion)
INSERT INTO student (id, birthday, photo_url) VALUES
(23, '1998-01-15', NULL), (24, '1999-04-02', NULL), (25, '1998-02-18', NULL), (26, '1999-05-30', NULL),
(27, '1998-03-11', NULL), (28, '1998-11-05', NULL), (29, '1999-02-25', NULL), (30, '1998-09-10', NULL),
(31, '1999-06-12', NULL), (32, '1998-07-08', NULL), (33, '1999-01-22', NULL), (34, '1998-03-27', NULL),
(35, '1998-10-03', NULL), (36, '1999-03-18', NULL), (37, '1998-09-12', NULL), (38, '1999-04-28', NULL),
(39, '1998-11-09', NULL), (40, '1999-05-21', NULL), (41, '1998-02-10', NULL), (42, '1999-06-03', NULL),
(43, '1998-04-19', NULL), (44, '1998-12-29', NULL), (45, '1998-07-14', NULL), (46, '1999-01-03', NULL),
(47, '1999-02-07', NULL), (48, '1999-06-25', NULL), (49, '1998-05-15', NULL), (50, '1999-04-11', NULL),
(51, '1998-09-01', NULL), (52, '1999-03-20', NULL),

(53, '1998-01-30', NULL), (54, '1999-04-06', NULL),
(55, '1998-02-17', NULL), (56, '1999-05-14', NULL), (57, '1998-03-22', NULL), (58, '1999-06-05', NULL),
(59, '1998-07-19', NULL), (60, '1998-12-02', NULL), (61, '1999-03-11', NULL), (62, '1998-08-29', NULL),
(63, '1999-01-19', NULL), (64, '1998-05-08', NULL), (65, '1998-06-26', NULL), (66, '1999-02-24', NULL),
(67, '1998-11-04', NULL), (68, '1999-04-19', NULL), (69, '1998-01-10', NULL), (70, '1999-05-22', NULL),
(71, '1998-09-03', NULL), (72, '1998-10-30', NULL), (73, '1999-02-12', NULL), (74, '1998-06-17', NULL),
(75, '1998-07-25', NULL), (76, '1998-03-29', NULL), (77, '1999-05-01', NULL), (78, '1998-04-15', NULL),
(79, '1999-02-06', NULL), (80, '1998-10-12', NULL), (81, '1998-05-24', NULL), (82, '1999-04-03', NULL);

-- REGISTRATION (one per student)
INSERT INTO registration (student_id, class_group_id, registration_date, school_year) VALUES
(23, 1, '2011-09-01', '2011'), (24, 1, '2011-09-01', '2011'), (25, 1, '2011-09-01', '2011'), (26, 1, '2011-09-01', '2011'),
(27, 1, '2011-09-01', '2011'), (28, 1, '2011-09-01', '2011'), (29, 1, '2011-09-01', '2011'), (30, 1, '2011-09-01', '2011'),
(31, 1, '2011-09-01', '2011'), (32, 1, '2011-09-01', '2011'), (33, 1, '2011-09-01', '2011'), (34, 1, '2011-09-01', '2011'),
(35, 1, '2011-09-01', '2011'), (36, 1, '2011-09-01', '2011'), (37, 1, '2011-09-01', '2011'), (38, 1, '2011-09-01', '2011'),
(39, 1, '2011-09-01', '2011'), (40, 1, '2011-09-01', '2011'), (41, 1, '2011-09-01', '2011'), (42, 1, '2011-09-01', '2011'),
(43, 1, '2011-09-01', '2011'), (44, 1, '2011-09-01', '2011'), (45, 1, '2011-09-01', '2011'), (46, 1, '2011-09-01', '2011'),
(47, 1, '2011-09-01', '2011'), (48, 1, '2011-09-01', '2011'), (49, 1, '2011-09-01', '2011'), (50, 1, '2011-09-01', '2011'),
(51, 1, '2011-09-01', '2011'), (52, 1, '2011-09-01', '2011'),

(53, 2, '2011-09-01', '2011'), (54, 2, '2011-09-01', '2011'), (55, 2, '2011-09-01', '2011'), (56, 2, '2011-09-01', '2011'),
(57, 2, '2011-09-01', '2011'), (58, 2, '2011-09-01', '2011'), (59, 2, '2011-09-01', '2011'), (60, 2, '2011-09-01', '2011'),
(61, 2, '2011-09-01', '2011'), (62, 2, '2011-09-01', '2011'), (63, 2, '2011-09-01', '2011'), (64, 2, '2011-09-01', '2011'),
(65, 2, '2011-09-01', '2011'), (66, 2, '2011-09-01', '2011'), (67, 2, '2011-09-01', '2011'), (68, 2, '2011-09-01', '2011'),
(69, 2, '2011-09-01', '2011'), (70, 2, '2011-09-01', '2011'), (71, 2, '2011-09-01', '2011'), (72, 2, '2011-09-01', '2011'),
(73, 2, '2011-09-01', '2011'), (74, 2, '2011-09-01', '2011'), (75, 2, '2011-09-01', '2011'), (76, 2, '2011-09-01', '2011'),
(77, 2, '2011-09-01', '2011'), (78, 2, '2011-09-01', '2011'), (79, 2, '2011-09-01', '2011'), (80, 2, '2011-09-01', '2011'),
(81, 2, '2011-09-01', '2011'), (82, 2, '2011-09-01', '2011');

-- Girard family (Paul & Camille Girard)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(21, 23), (20, 23), (21, 24), (20, 24),
(21, 63), (20, 63), (21, 64), (20, 64);

-- Benali family (Ahmed & Nadia Benali)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(18, 25), (19, 25), (18, 26), (19, 26),
(18, 61), (19, 61), (18, 62), (19, 62);

-- Renard family (Marc Renard only)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(14, 27), (14, 28), (14, 73), (14, 74);

-- Boulanger family (Fatima Boulanger)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(15, 29), (15, 30), (15, 69), (15, 70);

-- Carpentier family (Vincent Carpentier)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(16, 31), (16, 32), (16, 67), (16, 68);

-- Legrand family (Claire Legrand)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(17, 33), (17, 34), (17, 65), (17, 66);

-- Martin family (Sophie Martin is also a teacher)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(2, 35), (2, 36), (2, 80), (2, 81);

-- Petit family (Thomas Petit)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(5, 37), (5, 38);

-- Durand family (Isabelle Durand)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(4, 39), (4, 40), (4, 65), (4, 66);

-- Garcia family (Pierre Garcia)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(9, 41), (9, 42), (9, 67), (9, 68);

-- Lemoine family (Lucie Lemoine)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(22, 43), (22, 44), (22, 59), (22, 60);

-- Rousseau family (Olivier Rousseau)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(12, 45), (12, 46), (12, 71), (12, 72);

-- Bernard family (Nicolas Bernard)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(7, 47), (7, 48);

-- Faure family (Laura Faure)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(10, 49), (10, 50), (10, 77), (10, 78);

-- Blanc family (Émilie Blanc)
INSERT INTO student_guardian_link (guardian_id, child_id) VALUES
(11, 51), (11, 52), (11, 79), (11, 80);

-- 3ème A teachings
INSERT INTO teaching (subject_name, class_group_id, teacher_id)
VALUES
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
('Éducation Musicale', 1, 1);

-- 3ème B teachings
INSERT INTO teaching (subject_name, class_group_id, teacher_id)
VALUES
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
('Éducation Musicale', 2, 1);

-- Lucas Girard - 3ème A - 2011–2012
INSERT INTO school_report (period_start, period_end, mention, overall_average, general_comment, student_id)
VALUES
('2011-09-01', '2011-12-15', 'Bien', NULL, 'Bon trimestre. Élève appliqué et curieux.', 23),
('2012-01-03', '2012-03-30', 'Très bien', NULL, 'Excellent travail et bonne participation.', 23),
('2012-04-16', '2012-06-30', 'Très bien', NULL, 'Très bons résultats, sérieuse motivation.', 23),
('2011-09-01', '2012-06-30', 'Très bien', NULL, 'Excellente année scolaire. Félicitations du conseil de classe.', 23);

-- Mathis Garcia - 3ème A
INSERT INTO school_report (period_start, period_end, mention, overall_average, general_comment, student_id)
VALUES
('2011-09-01', '2011-12-15', 'Passable', NULL, "Résultats fragiles. Doit s'impliquer davantage"., 41),
('2012-01-03', '2012-03-30', 'Insuffisant', NULL, 'Manque de sérieux. Attention au comportement en classe.', 41),
('2012-04-16', '2012-06-30', 'Assez bien', NULL, "Progrès notables en fin d'année. Encourageant"., 41),
('2011-09-01', '2012-06-30', 'Assez bien', NULL, 'Année mitigée mais en amélioration. À poursuivre.', 41);

-- Emma Girard - 3ème A - Bulletins scolaires 2011–2012

INSERT INTO school_report (period_start, period_end, mention, overall_average, general_comment, student_id)
VALUES
('2011-09-01', '2011-12-15', 'Bien', NULL,  "Très bon début d'année.", 24),
('2012-01-03', '2012-03-30', 'Très bien', NULL, 'Excellent trimestre. Emma montre une grande régularité et participe activement en classe.', 24),
('2012-04-16', '2012-06-30', 'Très bien', NULL, 'Très belle progression. Élève motivée, attentive.', 24),
('2011-09-01', '2012-06-30', 'Très bien', NULL, 'Année remarquable. Travail sérieux et constant. Félicitations du conseil de classe.', 24);

INSERT INTO school_report_line (teaching_id, school_report_id, comment, teaching_average)
VALUES
(1, 101, "Bon niveau de compréhension et d'expression en français. Travail régulier.", NULL),
(2, 101, "Bonne maîtrise des notions abordées. Peut approfondir ses raisonnements.", NULL),
(3, 101, "Participation pertinente, connaissances historiques solides.", NULL),
(4, 101, "Résultats satisfaisants. Quelques imprécisions à corriger.", NULL),
(5, 101, "Élève sérieuse et curieuse, bonne attitude scientifique.", NULL),
(6, 101, "Travail précis et soigné en technologie.", NULL),
(7, 101, "Bon niveau en anglais oral et écrit.", NULL),
(8, 101, "Quelques difficultés en grammaire espagnole, mais ensemble positif.", NULL),
(9, 101, "Participation active, bonne endurance.", NULL),
(10, 101, "Bonne créativité et investissement sérieux.", NULL),
(11, 101, "Bonne écoute et implication musicale.", NULL);

INSERT INTO school_report_line (teaching_id, school_report_id, comment, teaching_average)
VALUES
(1, 102, 'Excellent trimestre. Rédactions très abouties et riches en vocabulaire.', NULL),
(2, 102, 'Progrès constants, résultats excellents.', NULL),
(3, 102, "Bonne capacité d'analyse, travail approfondi.", NULL),
(4, 102, 'Travail sérieux et rigoureux, bons résultats.', NULL),
(5, 102, 'Très bon investissement en SVT.', NULL),
(6, 102, 'Très bonne compréhension technique. Bonne autonomie.', NULL),
(7, 102, 'Très bon accent et bonne compréhension orale.', NULL),
(8, 102, 'Beaucoup de progrès, travail régulier.', NULL),
(9, 102, 'Toujours volontaire et dynamique.', NULL),
(10, 102, 'Très belle sensibilité artistique.', NULL),
(11, 102, 'Excellente écoute musicale et interprétation soignée.', NULL);

INSERT INTO school_report_line (teaching_id, school_report_id, comment, teaching_average)
VALUES
(1, 103, "Toujours appliquée et curieuse, très bons écrits.", NULL),
(2, 103, "Bonne progression, logique maîtrisée.", NULL),
(3, 103, "Travail régulier et bonne mémoire.", NULL),
(4, 103, "Bonne compréhension des phénomènes étudiés.", NULL),
(5, 103, "Attitude scientifique exemplaire.", NULL),
(6, 103, "Bonne participation et esprit d'initiative.", NULL),
(7, 103, "Excellent accent et bonne aisance à l'oral.", NULL),
(8, 103, "Sérieuse et attentive, résultats constants.", NULL),
(9, 103, "Toujours motivée et respectueuse.", NULL),
(10, 103, "Grande créativité, productions de qualité.", NULL),
(11, 103, "Très bon investissement en chorale.", NULL);
