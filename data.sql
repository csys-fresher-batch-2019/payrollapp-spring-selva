insert into employee(emp_id,emp_name,designation,email,performance_grade,leaves_taken,basepay,total_leaves,food_subscription,cab_subscription,pan_number)
values(emp_id_seq.nextval,'Selva','Admin','selvamanikandan.ks@gmail.com',2,0,20000,12,'N','Y','ABC14345');

insert into deductions(emp_id,food_deduction,cab_deduction,loss_of_pay,provident_fund)values(emp_id_seq.currval,0,2000,0,0);

insert into credits(emp_id,allowance,salary_increment)values(emp_id_seq.currval,0,0);

insert into biometrices(emp_id,swipe_count)values(emp_id_seq.currval,0);

insert into user_login(emp_id,uname,passwd,designation,active)values(emp_id_seq.currval,'pass123','Admin',0);

insert into final_salary(emp_id,salary)values(emp_id_seq.currval,0);
