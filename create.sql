
create sequence emp_id_seq start with 1000 increment by 1;



CREATE TABLE employee(
emp_id  number not null,
emp_name varchar2(50) not null,
designation varchar2(50) not null,
email varchar(50) unique,
performance_grade number check(performance_grade > 0),
leaves_taken number,
basepay number,
total_leaves number default 12,
food_subscription char default 'N',
cab_subscription char default 'N',
Pan_number varchar2(10) unique,

constraint emp_id_pk primary key(emp_id),
constraint food_cq check(food_subscription in('Y','N')),
constraint cab_cq check(cab_subscription in('Y','N'))
);




create table deductions(
emp_id number not null,
food_deduction number,
cab_deduction number,
loss_of_pay number,
provident_fund number,
constraint empl_id_fk foreign key(emp_id) references employee(emp_id)
);



create table credits(
emp_id number not null,
allowance number,
salary_increment number,
constraint emp_id_fk foreign key(emp_id) references employee(emp_id));



create table final_salary(
emp_id number not null,
salary_to_be_credited number,
constraint emp_id_key foreign key(emp_id) references employee(emp_id)
);




create table biometrices(
emp_id number not null,
login_time timestamp,
logout_time timestamp,
swipe_count number default 0,
constraint emp_id_fkey foreign key(emp_id) references employee(emp_id)
);




create table user_login(
emp_id number,
passwd varchar2(20),
designation varchar2(20),
active number default 0
);




create table leave_info(
emp_id number,
from_leave_date date,
to_leave_date date,
reason varchar2(50),
status varchar2(20) default 'PENDING'
);




create or replace procedure entry_gate(employee_id in number)
as
swiping number;
begin
        select swipe_count into swiping from biometrices where emp_id = employee_id;
        if(swiping = 0) then 
            update biometrices set login_time = systimestamp,swipe_count = swipe_count+1 where emp_id = employee_id;
        else
            update biometrices set logout_time = systimestamp where emp_id = employee_id;
        end if;
    commit;
end entry_gate;




create or replace procedure calculate_salary(employee_id in number)
as
v_credits number;
v_deductions number;
v_salary number;
v_total number;

begin
        select allowance + (select salary_increment from credits where emp_id = employee_id) into v_credits from credits where emp_id = employee_id;   
        select food_deduction + (select cab_deduction + (select loss_of_pay + (select provident_fund from deductions where emp_id = employee_id) from deductions where emp_id = employee_id) from deductions where emp_id = employee_id) into v_deductions from deductions where emp_id = employee_id; 
        select basepay into v_salary from employee where emp_id = employee_id;
        v_total := v_salary + v_credits - v_deductions;
        update final_salary set salary_to_be_credited = v_total where emp_id = employee_id;

end calculate_salary;





create or replace procedure attendance_check(employee_id in number )
as
present_hour number;
begin
        select extract(hour from diff) into present_hour from ( select (logout_time - login_time) diff from biometrices where emp_id = employee_id);
        if(present_hour = 0) 
        then 
            update employee set leaves_taken = leaves_taken+1,total_leaves = total_leaves-1 where emp_id = employee_id;
        end if;
end attendance_check;

create or replace procedure calculate_pf(employee_id in number)
as

v_pf number;
v_basepay number;

begin
        select basepay into v_basepay from employee where emp_id = employee_id;
        select round(v_basepay * (0.15),-1) into v_pf from  dual;
        update deductions set provident_fund = v_pf where emp_id = employee_id;

end calculate_pf;

create or replace procedure calculate_increment(employee_id in number)
as

v_salary number;
v_increment number;
v_basepay number;
v_grade number;

begin
        select basepay,performance_grade into v_basepay,v_grade from employee where emp_id = employee_id;
        select round(v_basepay * (0.2),-1) into v_salary from  dual;
        v_increment := v_salary * v_grade;
        update credits set salary_increment = v_increment where emp_id = employee_id;

end calculate_increment;

