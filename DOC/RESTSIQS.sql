/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/9/29 20:13:30                           */
/*==============================================================*/


drop table if exists academy;

drop table if exists course;

drop table if exists notice;

drop table if exists student;

drop table if exists teacher;

drop table if exists technologicalexam;

/*==============================================================*/
/* Table: academy                                               */
/*==============================================================*/
create table academy
(
   academyId            varchar(255) not null,
   academyName          varchar(255) default "��",
   academyAddress       varchar(255) default "��",
   primary key (academyId)
)engine=InnoDB default charset=utf8;

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course
(
   courseId             varchar(255) not null,
   credit               double default 0,
   teacherId varchar(255) default "��",
   studentId varchar(255) default "��",
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
)engine=InnoDB default charset=utf8;

/*==============================================================*/
/* Table: notice                                                */
/*==============================================================*/
create table notice
(
   noticeId             varchar(255) not null,
   noticeTitle          varchar(255) default "��",
   noticeContext        varchar(255) default "��",
   noticeOperator       varchar(255) default "��",
   academyId varchar(255) default "��",
   primary key (noticeId)
)engine=InnoDB default charset=utf8;

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student
(
   studentId            varchar(255) not null,
   studentPassword      varchar(255) default "��",
   sex                  varchar(255) default "��",
   identityCard         varchar(255) default "��",
   bankCard             varchar(255) default "��",
   academyId varchar(255) default "��",
   primary key (studentId)
)engine=InnoDB default charset=utf8;

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   teacherId            varchar(255) not null,
   teacherName          varchar(255) default "��",
   teacherDepartment    varchar(255) default "��",
   teacherStatus        varchar(255) default "��",
   academyId varchar(255) default "��",
   primary key (teacherId)
)engine=InnoDB default charset=utf8;

/*==============================================================*/
/* Table: technologicalexam                                     */
/*==============================================================*/
create table technologicalexam
(
   tId                  varchar(255) not null,
   tName                varchar(255) default "��",
   tDate                varchar(255) default "��",
   tSorce               double default 0,
   studentId varchar(255) default "��",
   primary key (tId)
)engine=InnoDB default charset=utf8;
