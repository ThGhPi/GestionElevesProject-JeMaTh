-- CREATE SCHEMA IF NOT EXISTS studentgestion
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
CREATE TYPE role AS ENUM('ADMIN', 'TEACHER', 'LEGAL_GUARDIAN');

CREATE TABLE IF NOT EXISTS person(
   person_id SERIAL PRIMARY KEY,
   firstname VARCHAR(50) NOT NULL,
   lastname VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS app_user(
   app_user_id INT,
   password VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   username VARCHAR(50) NOT NULL,
   phonenumber VARCHAR(20) NOT NULL,
   postaladdress VARCHAR(100) NOT NULL,
   role ROLE NOT NULL,
   PRIMARY KEY(app_user_id),
   UNIQUE(email),
   UNIQUE(username),
   UNIQUE(phonenumber),
   FOREIGN KEY(app_user_id) REFERENCES person(person_id)
);

CREATE TABLE IF NOT EXISTS student(
   student_id INT,
   birthday DATE NOT NULL,
   photo BYTEA,
   PRIMARY KEY(student_id),
   UNIQUE(photo),
   FOREIGN KEY(student_id) REFERENCES person(person_id)
);

CREATE TABLE IF NOT EXISTS class_group(
   class_group_id SERIAL PRIMARY KEY,
   name VARCHAR(25) NOT NULL,
   head_teacher_id INT NOT NULL,
   UNIQUE(head_teacher_id),
   UNIQUE(name),
   FOREIGN KEY(head_teacher_id) REFERENCES app_user(app_user_id)
);

CREATE TABLE IF NOT EXISTS teaching(
   teaching_id SERIAL PRIMARY KEY,
   subject_name VARCHAR(50) NOT NULL,
   class_group_id INT NOT NULL,
   teacher_id INT NOT NULL,
   UNIQUE(subject_name),
   FOREIGN KEY(class_group_id) REFERENCES class_group(class_group_id),
   FOREIGN KEY(teacher_id) REFERENCES app_user(app_user_id)
);

CREATE TABLE IF NOT EXISTS school_report(
   school_report_id SERIAL PRIMARY KEY,
   period_start DATE NOT NULL,
   period_end DATE NOT NULL,
   mention VARCHAR(20),
   overall_average VARCHAR(50),
   student_id INT NOT NULL,
   FOREIGN KEY(student_id) REFERENCES student(student_id)
);

CREATE TABLE IF NOT EXISTS evaluation(
   evaluation_id SERIAL PRIMARY KEY,
   weight DECIMAL(15,2) NOT NULL,
   date_and_time TIMESTAMP NOT NULL,
   note INT NOT NULL,
   student_id INT NOT NULL,
   teaching_id INT NOT NULL,
   FOREIGN KEY(student_id) REFERENCES student(student_id),
   FOREIGN KEY(teaching_id) REFERENCES teaching(teaching_id)
);

CREATE TABLE IF NOT EXISTS student_guardian_link(
   guardian_id INT,
   child_id INT,
   PRIMARY KEY(guardian_id, child_id),
   FOREIGN KEY(guardian_id) REFERENCES app_user(app_user_id),
   FOREIGN KEY(child_id) REFERENCES student(student_id)
);

CREATE TABLE IF NOT EXISTS registration(
   student_id INT,
   class_group_id INT,
   registration_date DATE NOT NULL,
   school_year DATE NOT NULL,
   PRIMARY KEY(student_id, class_group_id),
   FOREIGN KEY(student_id) REFERENCES student(student_id),
   FOREIGN KEY(class_group_id) REFERENCES class_group(class_group_id)
);

CREATE TABLE IF NOT EXISTS school_report_line(
   teaching_id INT,
   school_report_id INT,
   comment VARCHAR(256),
   teaching_average DECIMAL(15,2),
   PRIMARY KEY(teaching_id, school_report_id),
   FOREIGN KEY(teaching_id) REFERENCES teaching(teaching_id),
   FOREIGN KEY(school_report_id) REFERENCES school_report(school_report_id)
);
