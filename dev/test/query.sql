


create table t_pay_distribute_summary(
    reg_user_id varchar2(255) not null,
    room_id varchar2(255) not null,
    token varchar2(3) not null,
    total_amount varchar2(30) not null,
    total_target_num varchar2(30) not null,
    disable varchar2(1) not null,
    reg_date date,

    constraint PK_t_pay_distribute_summary primary key(reg_user_id, room_id, token, disable)

)/

create table t_pay_distribute_detail(
     reg_user_id varchar(255) not null,
     room_id varchar2(255) not null,
     token varchar2(3) not null,
     amount varchar2(30) not null,
     reg_date date
)/

create table t_pay_distribute_history(
     reg_user_id varchar(255) not null,
     room_id varchar2(255) not null,
     token varchar2(3) not null,
     amount varchar2(30) not null,
     recv_user_id varchar2(255),
     reg_date date
)/