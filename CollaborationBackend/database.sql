create table User_Detail(
userId varchar2(20),
email varchar2(250),
name varchar2(50),
password varchar2(250),
gender varchar2(10),
status char(1),
CONSTRAINT pk_userId_userDetail primary key (userId)
);

create table user_check(
userId varchar2(20),
email varchar2(250),
password varchar2(250),
enabled number(1),
CONSTRAINT fk_userId_User FOREIGN KEY (userId) REFERENCES User_Detail(userId)
);

create table User_Authorities(
userId varchar2(20),
email varchar2(250),
authority varchar2(50),
CONSTRAINT fk_userId_UserAuthorities FOREIGN key (userId) REFERENCES User_Detail(userId)
);

CREATE table Blog(
blogId varchar2(20),
userId varchar2(20),
blogName varchar(100),
blogDescription varchar2(1000),
noOfComments long,
status char(1),
createdAt TIMESTAMP,
modifiedAt TIMESTAMP,
CONSTRAINT pk_blogId_Blog PRIMARY KEY (blogId),
constraint fk_userId_Blog FOREIGN key (userId) REFERENCES User_Detail(userId)
);

create  table test(
testId varchar2(20),
testName varchar2(20),
testDate TIMESTAMP[0],
CONSTRAINT pk_test_testId PRIMARY KEY (testId)
);

