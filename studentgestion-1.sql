CREATE TABLE subject(
   subject_id SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   UNIQUE(name)
);

CREATE TABLE person(
   person_id SERIAL PRIMARY KEY,
   firstname VARCHAR(50) NOT NULL,
   lastname VARCHAR(50) NOT NULL,
);

CREATE TABLE app_user(
   person_id INT,
   user_password VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   user_connection_name VARCHAR(50) NOT NULL,
   UNIQUE(email),
   UNIQUE(user_connection_name),
   FOREIGN KEY(person_id) REFERENCES person(person_id)
);

CREATE TABLE student(
   person_id INT,
   birthday DATE NOT NULL,
   photo .png,
   UNIQUE(photo),
   FOREIGN KEY(person_id) REFERENCES Person(person_id)
);

CREATE TABLE legal_guardian(
   person_id INT,
   phone_number VARCHAR(20) NOT NULL,
   postal_adress VARCHAR(100) NOT NULL,
   UNIQUE(phone_number),
   FOREIGN KEY(person_id) REFERENCES app_user(person_id)
);

CREATE TABLE teacher(
   person_id INT,
   work_duration TIME,
   FOREIGN KEY(person_id) REFERENCES app_user(person_id)
);

CREATE TABLE class_group(
   classgroup_id SERIAL PRIMARY KEY,
   name VARCHAR(25) NOT NULL,
   person_id INT NOT NULL,
   UNIQUE(person_id),
   UNIQUE(name),
   FOREIGN KEY(person_id) REFERENCES teacher(person_id)
);

CREATE TABLE teaching(
   teaching_id SERIAL PRIMARY KEY,
   subject_id INT NOT NULL,
   classgroup_id INT NOT NULL,
   person_id INT NOT NULL,
   FOREIGN KEY(subject_id) REFERENCES subject(subject_id),
   FOREIGN KEY(classgroup_id) REFERENCES class_group(classgroup_id),
   FOREIGN KEY(person_id) REFERENCES teacher(person_id)
);

CREATE TABLE school_report(
   schoolreport_id SERIAL PRIMARY KEY,
   schoolperiod VARCHAR(50) NOT NULL,
   person_id INT NOT NULL,
   FOREIGN KEY(person_id) REFERENCES student(person_id)
);

CREATE TABLE evaluation(
   evaluation_id SERIAL PRIMARY KEY,
   weight DECIMAL(15,2) NOT NULL,
   date_and_time DATETIME NOT NULL,
   note INT NOT NULL,
   person_id INT NOT NULL,
   subject_id INT NOT NULL,
   FOREIGN KEY(person_id) REFERENCES student(person_id),
   FOREIGN KEY(subject_id) REFERENCES subject(subject_id)
);

CREATE TABLE admin(
   person_id INT,
   FOREIGN KEY(person_id) REFERENCES app_user(person_id)
);

CREATE TABLE student_guardian_link(
   student_id INT,
   techer_id INT,
   FOREIGN KEY(student_id) REFERENCES student(person_id),
   FOREIGN KEY(teacher_id) REFERENCES legal_guardian(person_id)
);

CREATE TABLE registration(
   person_id INT,
   classgroup_id INT,
   registration_date DATE NOT NULL,
   school_year DATE NOT NULL,
   FOREIGN KEY(person_id) REFERENCES student(person_id),
   FOREIGN KEY(classgroup_id) REFERENCES class_group(classgroup_id)
);

CREATE TABLE comments(
   teaching_id VARCHAR(50),
   schoolreport_id INT,
   comment VARCHAR(256),
   FOREIGN KEY(teaching_id) REFERENCES teaching(teaching_id),
   FOREIGN KEY(schoolreport_id) REFERENCES school_report(schoolreport_id)
);
