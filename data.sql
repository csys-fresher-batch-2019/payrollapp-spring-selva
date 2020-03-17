insert into employee(emp_id,emp_name,designation,email,food_subscription,cab_subscription,pan_number)
values(emp_id_seq.nextval,'Selva','Admin','selvamanikandan.ks@gmail.com','N','Y','ABCL4345');

insert into deductions(emp_id,food_deduction,cab_deduction)values(emp_id_seq.currval,0,2000);

insert into credits(emp_id)values(emp_id_seq.currval);

insert into biometrices(emp_id)values(emp_id_seq.currval);

insert into user_login(emp_id,designation)values(emp_id_seq.currval,'Admin');

insert into final_salary(emp_id)values(emp_id_seq.currval);

