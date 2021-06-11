insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1000,'amardoddi','amardoddi','Amarnath','Doddi','amarnath@gmail.com',9123456790, sysdate);
insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1002,'ramussss','ramussss','Ramu','Ssss','ramu@gmail.com',9123457770, sysdate);
insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1003,'vinubbbb','vinubbbb','Vinu','Bbbb','vinu@gmail.com',9155556790,  sysdate);
insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1004,'diliptttt','diliptttt','Dilip','Tttt','dilip@gmail.com',9122426790, sysdate);
insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1005,'shamtttt','shamtttt','Sham','Tttt','sham@gmail.com',9122426790, sysdate);
insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1006,'dilipbasani','dilipbasani','Dilip','Basani','basani@gmail.com',9128426790, sysdate);
insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1007,'ruthvidoddi','ruthvidoddi','Ruthvi','Doddi','ruthvi@gmail.com',9122666790, sysdate);
insert into users(id,login_id,password,first_name,last_name,email, phone,last_updated) values(1008,'jhonranjan','jhonranjan','Jhon','Ranjan','john@gmail.com',9122427790, sysdate);

insert into account(id,user_id,account_number,balance) values(2001,1000,676766689,23432.00);
insert into account(id,user_id,account_number,balance) values(2002,1002,876958821,100.00);
insert into account(id,user_id,account_number,balance) values(2003,1003,134525424,190000.00);
insert into account(id,user_id,account_number,balance) values(2004,1004,737376788,13422.00);
insert into account(id,user_id,account_number,balance) values(2005,1005,245115452,1237.00);
insert into account(id,user_id,account_number,balance) values(2006,1006,152353654,1.00);
insert into account(id,user_id,account_number,balance) values(2007,1007,987245873,25.00);
insert into account(id,user_id,account_number,balance) values(2008,1008,985240983,19934.00);

insert into beneficiary(id,account_number, account_id, name, ifsc_code,balance) values(3001, 8989898999,2001,'Peter','ICICI2000',100.00);
insert into beneficiary(id,account_number, account_id, name, ifsc_code,balance) values(3002, 7342465500,2001,'Vinay','HDFC3331',20023.00);
insert into beneficiary(id,account_number, account_id, name, ifsc_code,balance) values(3003, 2989891199,2004,'Jagan','ICICI44532', 4500.00);


