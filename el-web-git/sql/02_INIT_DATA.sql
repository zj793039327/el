USE DB_EL ;

/*
-- code type limited
**********************
1~100  		system code
101~200 	englishlive-business code 
201~300 
*/
-- -code_type
INSERT INTO t_sys_codetype VALUES(1,'yes or no','是否');
INSERT INTO t_sys_codetype VALUES(2,'sex','男女');
INSERT INTO t_sys_codetype VALUES(3,'continent','七大洲');
INSERT INTO t_sys_codetype VALUES(4,'first language','母语');
INSERT INTO t_sys_codetype VALUES(5,'time zone','时区');


INSERT INTO t_sys_codetype VALUES(101,'course progress','课程进行状态');
INSERT INTO t_sys_codetype VALUES(102,'pay status','交易状态');
INSERT INTO t_sys_codetype VALUES(103,'user type','用户状态');

-- -code
INSERT INTO t_sys_code VALUES(1, 1, 'yes', 0, '是', 'Y');
INSERT INTO t_sys_code VALUES(1, 2, 'no', 0, '否', 'N');

INSERT INTO t_sys_code VALUES(2, 1, 'male', 0, '男', 'M');
INSERT INTO t_sys_code VALUES(2, 2, 'famale', 0, '女', 'F');

INSERT INTO t_sys_code VALUES(3, 1, 'Asia', 0, '亚洲', '');
INSERT INTO t_sys_code VALUES(3, 2, 'Europe', 1, '欧洲', '');
INSERT INTO t_sys_code VALUES(3, 3, 'North America', 2, '北美洲', '');
INSERT INTO t_sys_code VALUES(3, 4, 'South America', 3, '南美洲', '');
INSERT INTO t_sys_code VALUES(3, 5, 'Oceania', 4, '大洋洲', '');
INSERT INTO t_sys_code VALUES(3, 6, 'Africa', 5, '非洲', '');
INSERT INTO t_sys_code VALUES(3, 7, 'Antarctica', 6, '南极洲', '');

INSERT INTO t_sys_code VALUES(5, 1, 'GMT+1', 1, '', '');
INSERT INTO t_sys_code VALUES(5, 2, 'GMT+2', 2, '', '');
INSERT INTO t_sys_code VALUES(5, 3, 'GMT+3', 3, '', '');
INSERT INTO t_sys_code VALUES(5, 4, 'GMT+4', 4, '', '');
INSERT INTO t_sys_code VALUES(5, 5, 'GMT+5', 5, '', '');
INSERT INTO t_sys_code VALUES(5, 6, 'GMT+6', 6, '', '');
INSERT INTO t_sys_code VALUES(5, 7, 'GMT+7', 7, '', '');
INSERT INTO t_sys_code VALUES(5, 8, 'GMT+8', 8, '', '');
INSERT INTO t_sys_code VALUES(5, 9, 'GMT+9', 9, '', '');
INSERT INTO t_sys_code VALUES(5, 10, 'GMT+10', 10, '', '');
INSERT INTO t_sys_code VALUES(5, 11, 'GMT+11', 11, '', '');
INSERT INTO t_sys_code VALUES(5, 12, 'GMT+12', 12, '', '');
INSERT INTO t_sys_code VALUES(5, -1, 'GMT-1', 13, '', '');
INSERT INTO t_sys_code VALUES(5, -2, 'GMT-2', 14, '', '');
INSERT INTO t_sys_code VALUES(5, -3, 'GMT-3', 15, '', '');
INSERT INTO t_sys_code VALUES(5, -4, 'GMT-4', 16, '', '');
INSERT INTO t_sys_code VALUES(5, -5, 'GMT-5', 17, '', '');
INSERT INTO t_sys_code VALUES(5, -6, 'GMT-6', 18, '', '');
INSERT INTO t_sys_code VALUES(5, -7, 'GMT-7', 19, '', '');
INSERT INTO t_sys_code VALUES(5, -8, 'GMT-8', 20, '', '');
INSERT INTO t_sys_code VALUES(5, -9, 'GMT-9', 21, '', '');
INSERT INTO t_sys_code VALUES(5, -10, 'GMT-10', 22, '', '');
INSERT INTO t_sys_code VALUES(5, -11, 'GMT-11', 23, '', '');
INSERT INTO t_sys_code VALUES(5, -12, 'GMT-12', 24, '', '');

--
INSERT INTO t_sys_code VALUES(101, 1, 'in appointment', 1, '预约中', '');
INSERT INTO t_sys_code VALUES(101, 2, 'book success', 2, '预约成功', '');
INSERT INTO t_sys_code VALUES(101, 3, 'in process', 3, '进行中', '');
INSERT INTO t_sys_code VALUES(101, 4, 'finish', 4, '结束', '');

--
INSERT INTO t_sys_code VALUES(102, 1, 'wait for pay', 1, '等待付款', '');
INSERT INTO t_sys_code VALUES(102, 2, 'pay success', 2, '付款成功', '');
INSERT INTO t_sys_code VALUES(102, 3, 'use cridets', 3, '赊账', '');
INSERT INTO t_sys_code VALUES(102, 4, 'repayment of credit', 4, '', '已还赊账');

--
INSERT INTO t_sys_code VALUES(103, 1, 'teacher', 1, '教师', '');
INSERT INTO t_sys_code VALUES(103, 2, 'student', 2, '学生', '');

