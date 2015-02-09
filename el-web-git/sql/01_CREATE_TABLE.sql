USE DB_EL ;

-- 学生表
DROP TABLE IF EXISTS T_INFO_STUDENT ;

CREATE TABLE T_INFO_STUDENT (
  N_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  N_SEX INT,
  C_NAME VARCHAR (50),
  C_FIRSTLANGUAGE VARCHAR (50),
  C_ADDRESS VARCHAR (300),
  N_TIMEZONE INT,
  N_CONTACT_ID BIGINT (20),
  C_AGENCY_ID BIGINT,
  C_ENGLISHLEVEL VARCHAR (300),
  C_AIM VARCHAR (1000),
  C_COMMENT VARCHAR (500),
  N_VALID INT,
  DT_CREATETIME DATETIME,
  B_ROWVERSION TIMESTAMP
) ;

-- 老师表
DROP TABLE IF EXISTS T_INFO_TEACHER ;

CREATE TABLE T_INFO_TEACHER (
  N_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  C_NAME VARCHAR (50),
  N_SEX INT,
  N_FIRSTLANGUAGE INT ,
  C_ADDRESS VARCHAR (300),
  N_TIMEZONE INT,
  N_VALID INT,
  DT_CREATETIME DATETIME,
  B_ROWVERSION TIMESTAMP
) ;

-- 学生课程信息表
DROP TABLE IF EXISTS T_INFO_COURSE ;

CREATE TABLE T_INFO_COURSE (
  N_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  C_TOPIC VARCHAR (200),
  DT_STARTTIME DATETIME,
  DT_ENDTIME DATETIME,
  N_PROGRESS INT,-- 进行状态
  N_PAYMENT_STATUS INT,
  F_CHARGE FLOAT,-- 课的价格
  N_TEACHERID BIGINT,
  N_STUDENTID BIGINT,
  N_BOOK_STATUS INT,-- 支付状态，单值代码，代表这节课的是否支付
  N_VALID INT,
  DT_CREATETIME DATETIME,
  B_ROWVERSION TIMESTAMP
) ;

-- 用户表
DROP TABLE IF EXISTS T_SYS_USER ;

CREATE TABLE T_SYS_USER (
  N_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  C_LOGINID VARCHAR (100) NOT NULL,-- 登录名
  C_PASSOWRD VARCHAR (100),-- 登录密码
  C_EMAIL VARCHAR (100),-- email 注册用，唯一有效
  N_VALID INT,-- 有效性
  N_TYPE INT,-- 类型 教师或者学生
  N_INFOID BIGINT,-- 教师或者学生外键
  B_ROWVERSION TIMESTAMP
) ;

-- 权限表
DROP TABLE IF EXISTS T_SYS_RIGHT ;

CREATE TABLE T_SYS_RIGHT (
  C_KEY VARCHAR (20) PRIMARY KEY,
  C_NAME VARCHAR (20) NOT NULL,
  C_DESCRIPTION VARCHAR (50),
  N_ORDER INT
) ;

-- 角色表
DROP TABLE IF EXISTS T_SYS_ROLE ;

CREATE TABLE T_SYS_ROLE (
  N_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  C_NAME VARCHAR (20),
  C_DESCRIPTION VARCHAR (50),
  N_ORDER INT
) ;

-- 角色权限映射
DROP TABLE IF EXISTS T_SYS_ROLE_RIGHT ;

CREATE TABLE T_SYS_ROLE_RIGHT (
  N_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  N_ROLEID BIGINT NOT NULL,
  C_RIGHTKEY VARCHAR (20) NOT NULL
) ;

-- 用户权限表
DROP TABLE IF EXISTS T_SYS_USER_RIGHT ;

CREATE TABLE T_SYS_USER_RIGHT (
  N_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  N_TPYE INT,
  -- 权限类型，角色还是权限关键字
  N_ROLEID BIGINT,
  -- 角色id
  C_RIGHTKEY VARCHAR (20) -- 权限关键字
) ;

-- 单值代码类型表
DROP TABLE IF EXISTS T_SYS_CODETYPE ;

CREATE TABLE T_SYS_CODETYPE (
  N_ID INT AUTO_INCREMENT PRIMARY KEY,
  C_NAME VARCHAR (50),
  C_DESCRIPTION VARCHAR (100)
) ;

-- 单值代码表
DROP TABLE IF EXISTS T_SYS_CODE ;

CREATE TABLE T_SYS_CODE (
  N_PID INT,
  N_CODE INT,
  C_VALUE VARCHAR (50),
  N_ORDER INT,
  C_DESCRIPTION VARCHAR (100),
  C_INPUTCODE VARCHAR (50),
  PRIMARY KEY (N_PID, N_CODE)
) ;

-- 联系方式表，存储人员联系方式
DROP TABLE IF EXISTS T_INFO_CONTACT ;

CREATE TABLE T_INFO_CONTACT (
  N_ID BIGINT PRIMARY KEY,
  N_TYPE INT, -- 类型 教师或者学生
  N_INFOID BIGINT,
  N_DEFAULT_FLAG INT,-- 是否默认值
  C_CONTACT_NAME VARCHAR (20),
  C_CONTACT_VALUE VARCHAR (20),
  C_DESCRIPTION VARCHAR(100)
)

-- 国家表
DROP TABLE IF EXISTS T_SYS_COUNTRY ;

CREATE TABLE T_SYS_COUNTRY(
 N_ID INT PRIMARY KEY,
 C_NAME VARCHAR(30) NOT NULL,
 C_SHORTNAME VARCHAR(10) NULL,
 C_DESCRIPTION VARCHAR(100),
 C_PHONENUM  VARCHAR(20),
 N_STATES INT,
 N_ORDER INT 
);