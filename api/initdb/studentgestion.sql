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
    email VARCHAR(100) UNIQUE NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    phone_number VARCHAR(20) UNIQUE NOT NULL,
    postal_address VARCHAR(150) NOT NULL,
    role ROLE NOT NULL
);


CREATE TABLE IF NOT EXISTS student (
    id BIGINT,
    birthday DATE NOT NULL,
    photo BYTEA,
    PRIMARY KEY (id),
    UNIQUE (photo),
    FOREIGN KEY (id) REFERENCES person (id)
);


CREATE TABLE IF NOT EXISTS class_group (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    head_teacher_id INT UNIQUE,
    FOREIGN KEY (head_teacher_id) REFERENCES app_user (id)
);


CREATE TABLE IF NOT EXISTS teaching (
    id BIGSERIAL PRIMARY KEY,
    subject_name VARCHAR(100) UNIQUE NOT NULL,
    class_group_id BIGINT NOT NULL,
    teacher_id BIGINT NOT NULL,
    FOREIGN KEY (class_group_id) REFERENCES class_group (id),
    FOREIGN KEY (teacher_id) REFERENCES app_user (id)
);


CREATE TABLE IF NOT EXISTS school_report (
    id BIGSERIAL PRIMARY KEY,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    mention VARCHAR(20),
    overall_average DECIMAL(5,2),
    student_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id)
);


CREATE TABLE IF NOT EXISTS evaluation (
    id BIGSERIAL PRIMARY KEY,
    weight DECIMAL(5,2) NOT NULL,
    date_and_time TIMESTAMP NOT NULL,
    note DECIMAL(5,2) NOT NULL,
    student_id BIGINT NOT NULL,
    teaching_id BIGINT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (teaching_id) REFERENCES teaching (id)
);


CREATE TABLE IF NOT EXISTS student_guardian_link (
    guardian_id BIGINT,
    child_id BIGINT,
    PRIMARY KEY (guardian_id, child_id),
    FOREIGN KEY (guardian_id) REFERENCES app_user (id),
    FOREIGN KEY (child_id) REFERENCES student (id)
);


CREATE TABLE IF NOT EXISTS registration (
    student_id BIGINT,
    class_group_id BIGINT,
    registration_date DATE NOT NULL,
    school_year VARCHAR(9) NOT NULL, 
    PRIMARY KEY (student_id, class_group_id),
    FOREIGN KEY (student_id) REFERENCES student (id),
    FOREIGN KEY (class_group_id) REFERENCES class_group (id)
);

CREATE TABLE IF NOT EXISTS school_report_line (
    teaching_id BIGINT,
    school_report_id BIGINT,
    comment VARCHAR(256),
    teaching_average DECIMAL(5,2),
    PRIMARY KEY (teaching_id, school_report_id),
    FOREIGN KEY (teaching_id) REFERENCES teaching (id),
    FOREIGN KEY (school_report_id) REFERENCES school_report (id)
);


INSERT INTO person (firstname, lastname) VALUES ( 'Jean',   'Dupont'),('Sophie', 'Martin'),('Luc', 'Moreau'), ('Claire', 'Lemoine'),('Karim',  'Benali');

INSERT INTO app_user (id, password, email, username, phone_number, postal_address, role)
VALUES
  (1,  '$2a$10$sAZzPFV/DX7lu2JGVN7db.eoF6xtR7gItaCZ5vm68ixoHcqxZzTI2', 'jean.dupont@example.com',  'jdupont',  '+33611223344', '10 Rue Victor Hugo, Paris',  'ADMIN'),
  (2,  '$2a$10$W6gXDz/8/bSGrOYe1zCkPe2JHajtA7EFDE8ocwHaMPBtjtaRzaLRS', 'sophie.martin@example.com','smartin',  '+33655667788', '25 Avenue de la Liberté, Lyon','TEACHER'),
  ( 3,  '$2a$10$vJUSY25gE0xRp0YeWwlkyOSot1u05q8tDfre8h5KLZc09IH3t//tq', 'luc.moreau@example.com',   'lmoreau',  '+33799887766', '7 Rue des Écoles, Marseille',  'TEACHER'),
  ( 4, '$2a$10$f4jk2aAszBH0CmyU.pYOpewFOFTDPtUcDyd3/iAjehNLBwCDl/lx.', 'claire.lemoine@example.com','clemoine', '+33622334455', '12 Rue de la Paix, Bordeaux','LEGAL_GUARDIAN'),
  ( 5,  '$2a$10$rDKfdtHvEI1BV5KgL97n7uLbJRFYqhzc2MUUlydoVbvKQZPzVuLFG', 'karim.benali@example.com', 'kbenali',  '+33677889900', '8 Rue du Parc, Toulouse',     'LEGAL_GUARDIAN');

