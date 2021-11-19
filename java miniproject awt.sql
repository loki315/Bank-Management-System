use projectjava;

select * from userdata;
select * from login;
select * from bank;

delete from userdata
where id = 1;
delete from login
where id = 1;
delete from bank
where id = 1;

-- to reset auto_increment variable 
alter table userdata auto_increment=1;

use testjava;

create table testdata(
    id int not null auto_increment,
    fname varchar(20),
    lname varchar(20),
    email varchar(20),
    ac_no varchar(15),
    mobile varchar(10),
    primary key(id)
);

create table testlogin(
	id int,
    username varchar(20),
    pass varchar(20),
    pin int
);

create table testbank(
	id int,
    balance int
);

-- to insert into login table
insert into testlogin(id, username, pass, pin) values(1, 'ruchit', 'password', 1212);
insert into testdata(id, fname, lname, email, ac_no, mobile) 
values(1, 'ruchit', 'bhandari', 'ruchit@gmail.com', 1234554321, 9879879870);
insert into testbank(id, balance) values(1, 1000);

-- to update value in database
update testlogin set pin = 2424 where id = 1;

select * from testdata;
select * from testlogin;
select * from testbank;

delete from testdata
where id = 1;
delete from testbank
where id = 1;
delete from testlogin
where id = 1;

-- to delete table
drop table test;
-- to delete database
drop database test;



