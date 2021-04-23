CREATE TABLE employee
(
    employee_id           SERIAL             NOT NULL PRIMARY KEY,
    first_name            VARCHAR(255)       NOT NULL,
    last_name             VARCHAR(255)       NOT NULL,
   	department_id         INTEGER            NOT NULL,
    job_title             VARCHAR(255)       NOT NULL,
    gender                VARCHAR(255)       NOT NULL,
    date_of_birth   	  DATE
);