CREATE TABLE Subject(
   subject_id INT,
   name VARCHAR(50) NOT NULL,
   PRIMARY KEY(subject_id),
   UNIQUE(name)
);

CREATE TABLE Person(
   person_id INT AUTO_INCREMENT,
   firstname VARCHAR(50) NOT NULL,
   lastname VARCHAR(50) NOT NULL,
   PRIMARY KEY(person_id)
);

CREATE TABLE AppUser(
   person_id INT,
   user_password VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   user_connection_name VARCHAR(50) NOT NULL,
   PRIMARY KEY(person_id),
   UNIQUE(email),
   UNIQUE(user_connection_name),
   FOREIGN KEY(person_id) REFERENCES Person(person_id)
);

CREATE TABLE Student(
   person_id INT,
   birthday DATE NOT NULL,
   photo .png,
   PRIMARY KEY(person_id),
   UNIQUE(photo),
   FOREIGN KEY(person_id) REFERENCES Person(person_id)
);

CREATE TABLE legal_guardian(
   person_id INT,
   phone_number VARCHAR(20) NOT NULL,
   postal_adress VARCHAR(100) NOT NULL,
   PRIMARY KEY(person_id),
   UNIQUE(phone_number),
   FOREIGN KEY(person_id) REFERENCES AppUser(person_id)
);

CREATE TABLE Teacher(
   person_id INT,
   work_duration TIME,
   PRIMARY KEY(person_id),
   FOREIGN KEY(person_id) REFERENCES AppUser(person_id)
);

CREATE TABLE ClassGroup(
   classgroup_id INT,
   name VARCHAR(25) NOT NULL,
   person_id INT NOT NULL,
   PRIMARY KEY(classgroup_id),
   UNIQUE(person_id),
   UNIQUE(name),
   FOREIGN KEY(person_id) REFERENCES Teacher(person_id)
);

CREATE TABLE Teaching(
   teaching_id VARCHAR(50),
   subject_id INT NOT NULL,
   classgroup_id INT NOT NULL,
   person_id INT NOT NULL,
   PRIMARY KEY(teaching_id),
   FOREIGN KEY(subject_id) REFERENCES Subject(subject_id),
   FOREIGN KEY(classgroup_id) REFERENCES ClassGroup(classgroup_id),
   FOREIGN KEY(person_id) REFERENCES Teacher(person_id)
);

CREATE TABLE SchoolReport(
   schoolreport_id INT,
   schoolperiod VARCHAR(50) NOT NULL,
   person_id INT NOT NULL,
   PRIMARY KEY(schoolreport_id),
   FOREIGN KEY(person_id) REFERENCES Student(person_id)
);

CREATE TABLE Evaluation(
   evaluation_id INT,
   weight DECIMAL(15,2) NOT NULL,
   date_and_time DATETIME NOT NULL,
   note INT NOT NULL,
   subject_id INT NOT NULL,
   schoolreport_id INT NOT NULL,
   PRIMARY KEY(evaluation_id),
   FOREIGN KEY(subject_id) REFERENCES Subject(subject_id),
   FOREIGN KEY(schoolreport_id) REFERENCES SchoolReport(schoolreport_id)
);

CREATE TABLE Admin(
   person_id INT,
   PRIMARY KEY(person_id),
   FOREIGN KEY(person_id) REFERENCES AppUser(person_id)
);

CREATE TABLE take care(
   person_id INT,
   person_id_1 INT,
   PRIMARY KEY(person_id, person_id_1),
   FOREIGN KEY(person_id) REFERENCES Student(person_id),
   FOREIGN KEY(person_id_1) REFERENCES legal_guardian(person_id)
);

CREATE TABLE registration(
   person_id INT,
   classgroup_id INT,
   registration_date DATE NOT NULL,
   school_year DATE NOT NULL,
   PRIMARY KEY(person_id, classgroup_id),
   FOREIGN KEY(person_id) REFERENCES Student(person_id),
   FOREIGN KEY(classgroup_id) REFERENCES ClassGroup(classgroup_id)
);

CREATE TABLE comments(
   teaching_id VARCHAR(50),
   schoolreport_id INT,
   comment VARCHAR(256),
   PRIMARY KEY(teaching_id, schoolreport_id),
   FOREIGN KEY(teaching_id) REFERENCES Teaching(teaching_id),
   FOREIGN KEY(schoolreport_id) REFERENCES SchoolReport(schoolreport_id)
);
