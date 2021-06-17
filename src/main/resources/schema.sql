--create schema fund_management;
CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1 NOCYCLE;
--create table users(id number primary key, login_id varchar(15),password varchar(30),first_name varchar(50), last_name varchar(50), email varchar(30),phone number, last_updated date);
create table account(id number primary key, user_id number,account_number number,balance number(10,2));
create table beneficiary(id number primary key,account_number number,account_id number,name varchar(50),ifsc_code varchar(10),balance number(10,2), foreign key(account_id) references account(id));

