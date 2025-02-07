create table if not exists dept(
                     id int primary key auto_increment,
                     name varchar(10) not null unique,
                     create_time timestamp default current_timestamp,
                     update_time timestamp default current_timestamp
);

create table if not exists emp(
                     id int primary key auto_increment,
                     username varchar(20) not null unique,
                     password varchar(255) not null default 'e10adc3949ba59abbe56e057f20f883e',
                     name varchar(10) not null,
                     gender int not null,
                     phone char(10) not null unique,
                     job int,
                     salary int,
                     image varchar(255),
                     entry_date date,
                     dept_id int,
                     create_time timestamp default current_timestamp,
                     update_time timestamp default current_timestamp
    );

create table if not exists emp_expr(
                         id int primary key auto_increment,
                         emp_id int,
                         `begin` date,
                         `end` date,
                         company varchar(50),
                         job varchar(50)
);

create table operate_log(
                        id int primary key auto_increment comment 'ID',
                        operate_emp_id int comment '操作人ID',
                        operate_time timestamp comment '操作時間',
                        class_name varchar(100) comment '操作的類別名',
                        method_name varchar(100) comment '操作的方法名',
                        method_params varchar(2000) comment '方法參數',
                        return_value varchar(2000) comment '返回值',
                        cost_time bigint comment '方法執行耗時,單位:ms'
) comment '操作日誌表';