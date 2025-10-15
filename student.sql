CREATE TABLE subject (
    subject_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE person (
    person_id SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL
);

CREATE TABLE app_user (
    person_id INT PRIMARY KEY,
    user_password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    user_connection_name VARCHAR(50) NOT NULL UNIQUE,
    FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE TABLE student (
    person_id INT PRIMARY KEY,
    birthday DATE NOT NULL,
    photo BYTEA UNIQUE,
    FOREIGN KEY (person_id) REFERENCES person(person_id)
);

CREATE TABLE legal_guardian (
    person_id INT PRIMARY KEY,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
    postal_address VARCHAR(100) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES app_user(person_id)
);

CREATE TABLE teacher (
    person_id INT PRIMARY KEY,
    work_duration TIME,
    FOREIGN KEY (person_id) REFERENCES app_user(person_id)
);

CREATE TABLE class_group (
    class_group_id SERIAL PRIMARY KEY,
    name VARCHAR(25) NOT NULL UNIQUE,
    person_id INT NOT NULL UNIQUE,
    FOREIGN KEY (person_id) REFERENCES teacher(person_id)
);

CREATE TABLE teaching (
    teaching_id VARCHAR(50) PRIMARY KEY,
    subject_id INT NOT NULL,
    class_group_id INT NOT NULL,
    person_id INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (class_group_id) REFERENCES class_group(classgroup_id),
    FOREIGN KEY (person_id) REFERENCES teacher(person_id)
);

CREATE TABLE school_report (
    school_report_id SERIAL PRIMARY KEY,
    school_period VARCHAR(50) NOT NULL,
    person_id INT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES student(person_id)
);

CREATE TABLE evaluation (
    evaluation_id BIGSERIAL PRIMARY KEY,
    weight DECIMAL(15,2) NOT NULL,
    date_and_time TIMESTAMP NOT NULL,
    note INT NOT NULL,
    subject_id INT NOT NULL,
    school_report_id INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(subject_id),
    FOREIGN KEY (school_report_id) REFERENCES school_report(school_report_id)
);

CREATE TABLE admin (
    person_id INT PRIMARY KEY,
    FOREIGN KEY (person_id) REFERENCES app_user(person_id)
);

CREATE TABLE student_guardian_link (
    person_id INT,
    person_id_1 INT,
    PRIMARY KEY (person_id, person_id_1),
    FOREIGN KEY (person_id) REFERENCES student(person_id),
    FOREIGN KEY (person_id_1) REFERENCES legal_guardian(person_id)
);

CREATE TABLE registration (
    person_id INT,
    class_group_id INT,
    registration_date DATE NOT NULL,
    school_year DATE NOT NULL,
    PRIMARY KEY (person_id, class_group_id),
    FOREIGN KEY (person_id) REFERENCES student(person_id),
    FOREIGN KEY (class_group_id) REFERENCES class_group(class_group_id)
);

CREATE TABLE comments (
    teaching_id VARCHAR(50),
    school_report_id INT,
    comment VARCHAR(256),
    PRIMARY KEY (teaching_id, schoolreport_id),
    FOREIGN KEY (teaching_id) REFERENCES teaching(teaching_id),
    FOREIGN KEY (school_report_id) REFERENCES school_report(school_report_id)
);
