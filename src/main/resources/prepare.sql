DROP TABLE IF EXISTS hiber.employee CASCADE;
CREATE TABLE hiber.employee (id bigserial PRIMARY KEY, name VARCHAR(255), age int);

DROP TABLE IF EXISTS hiber.task CASCADE;
CREATE TABLE hiber.task (id bigserial PRIMARY KEY, name VARCHAR(255), owner bigint REFERENCES hiber.employee (id), assignee bigint REFERENCES hiber.employee (id), description varchar(255), status varchar(255));
