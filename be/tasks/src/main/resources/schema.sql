-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE TASKS
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date DATE,

    PRIMARY KEY (name)
);

insert into TASKS (name, date)
values ('Learn React', '2021-01-03'),
       ('Profit', '2021-01-05');