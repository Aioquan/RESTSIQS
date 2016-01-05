/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/9/29 20:13:30                           */
/*==============================================================*/


drop table if exists Academy;

drop table if exists Course;

drop table if exists Notice;

drop table if exists Student;

drop table if exists Teacher;

drop table if exists TechnologicalExam;

/*==============================================================*/
/* Table: academy                                               */
/*==============================================================*/
create table Academy
(
   academyId            varchar(255) not null,
   academyName          varchar(255) default "��",
   academyAddress       varchar(255) default "��",
   primary key (academyId)
)engine=InnoDB default charset=gbk;

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table Course
(
   courseId             varchar(255) not null,
   credit               double default 0,
   teacherId		varchar(255) default "��",
   studentId		varchar(255) default "��",
   courseName           varchar(255) default "��",
   courseTime           varchar(255) default "��",
   courseDate           varchar(255) default "��",
   test1                double default 0,
   test2                double default 0,
   test3                double default 0,
   exercises1           double default 0,
   exercises2           double default 0,
   exercises3           double default 0,
   exercises4           double default 0,
   exercises5           double default 0,
   finalTest            double default 0,
   dailyMark            double default 0,
   sum                  double default 0,
   primary key (courseId)
)engine=InnoDB default charset=gbk;

/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table Notice
(
   noticeId             varchar(255) not null,
   noticeTitle          varchar(255) default "��",
   noticeContext        varchar(255) default "��",
   noticeOperator       varchar(255) default "��",
   academyId		varchar(255) default "��",
   primary key (noticeId)
)engine=InnoDB default charset=gbk;

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table Student
(
   studentId            varchar(255) not null,
   studentPassword      varchar(255) default "��",
   sex                  varchar(255) default "��",
   identityCard         varchar(255) default "��",
   bankCard             varchar(255) default "��",
   academyId		varchar(255) default "��",
   primary key (studentId)
)engine=InnoDB default charset=gbk;

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table Teacher
(
   teacherId            varchar(255) not null,
   teacherName          varchar(255) default "��",
   teacherDepartment    varchar(255) default "��",
   teacherStatus        varchar(255) default "��",
   academyId		varchar(255) default "��",
   primary key (teacherId)
)engine=InnoDB default charset=gbk;

/*==============================================================*/
/* Table: technologicalExam                                     */
/*==============================================================*/
create table TechnologicalExam
(
   tId                  varchar(255) not null,
   tName                varchar(255) default "��",
   tDate                varchar(255) default "��",
   tSorce               double default 0,
   studentId		varchar(255) default "��",
   primary key (tId)
)engine=InnoDB default charset=gbk;
