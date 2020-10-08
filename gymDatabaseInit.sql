DROP DATABASE gym;
Create database Gym;
Use Gym;

Create table employee(
	emp_id int primary key,
    fname varchar(15),
    lname varchar(15),
    ssn varchar(9),
    pnum varchar(10),
    start_time	time,
    end_time time
);

Create table supervisor(
	emp_id int,
    primary key(emp_id),
    foreign key(emp_id) references employee(emp_id)
);

Create table trainer(
	emp_id int,
    primary key(emp_id),
    foreign key(emp_id) references employee(emp_id)
);

Create table skills(
	emp_id int,
    skill varchar(256),
    primary key(emp_id, skill),
    foreign key(emp_id) references trainer(emp_id)
);

create table room(
	room_name varchar(15) primary key    
);

Create table class(
	class_id int,
    name varchar(15),
    time_slot time,
    held_in varchar(15),
    primary key(class_id),
    foreign key(held_in) references room(room_name),
    unique(time_slot, held_in)
);

Create table conducts(
	emp_id int,
    class_id int,
    primary key(emp_id, class_id),
    foreign key(emp_id) references trainer(emp_id),
    foreign key(class_id) references class(class_id)
);

create table equipment(
	equip_id int primary key,
    equip_type varchar(15),
	room varchar(15),
    date date,
    equip_status varchar(256),
    foreign key(room) references room(room_name)
);

INSERT INTO employee VALUES
(001, 'John', 'Smith', '123456789', '7502658496', '08:00:00', '16:00:00'),
(002, 'Kimberly', 'Snyder', '234567891', '8596421578', '08:00:00', '16:00:00'),
(007, 'Joseph', 'Rodgers', '345678912', '4526912584', '08:00:00', '16:00:00'),
(008, 'Sabrina', 'Johnson', '456789123', '6851892255', '08:00:00', '16:00:00'),
(003, 'Richard', 'McDonald', '567891234', '6185596248', '16:00:00', '00:00:00'),
(004, 'Timothy', 'Richards', '678912345', '5481592364', '16:00:00', '00:00:00'),
(009, 'Bailey', 'Sanchez', '789123456', '8455561287', '16:00:00', '00:00:00'),
(010, 'Tracy', 'Mendoza', '891234567', '6198456699', '16:00:00', '00:00:00'),
(005, 'Jessica', 'Bradley', '912345678', '9155567474', '00:00:00', '08:00:00'),
(006, 'Marco', 'Gonzalez', '987654321', '6198951234', '00:00:00', '08:00:00'),
(011, 'Michelle', 'Chu', '876543219', '9158489632', '00:00:00', '08:00:00'),
(012, 'Allen', 'Wang', '765432198', '8585947532', '00:00:00', '08:00:00');

INSERT INTO supervisor VALUES
(001),
(004),
(012);

INSERT INTO trainer VALUES
(001),
(002),
(003),
(004),
(005),
(006),
(007),
(008),
(009),
(010),
(011),
(012);

INSERT INTO skills VALUES
(001, 'cardio'),
(001, 'free weights'),
(002, 'free weights'),
(002, 'machines'),
(003, 'cardio'),
(003, 'free weights'),
(004, 'free weights'),
(004, 'machines'),
(005, 'cardio'),
(005, 'free weights'),
(006, 'free weights'),
(006, 'machines'),
(007, 'cardio'),
(007, 'machines'),
(008, 'cardio'),
(008, 'free weights'),
(009, 'cardio'),
(009, 'machines'),
(010, 'cardio'),
(010, 'free weights'),
(011, 'cardio'),
(011, 'machines'),
(012, 'cardio'),
(012, 'free weights');

INSERT INTO room VALUES
('Jupiter'),
('Pluto'),
('Venus'),
('Storage');

INSERT INTO class VALUES
(001, 'Crossfit', '08:00:00', 'Jupiter'),
(002, 'Crossfit', '16:00:00', 'Jupiter'),
(003, 'Crossfit', '00:00:00', 'Jupiter'),
(004, 'Spin', '09:00:00', 'Venus'),
(005, 'Spin', '17:00:00', 'Venus'),
(006, 'Spin', '01:00:00', 'Venus'),
(007, 'Core Strength', '10:00:00', 'Pluto'),
(008, 'Core Strength', '18:00:00', 'Pluto'),
(009, 'Core Strength', '02:00:00', 'Pluto'),
(010, 'Power Lifting', '11:00:00', 'Jupiter'),
(011, 'Power Lifting', '19:00:00', 'Jupiter'),
(012, 'Power Lifting', '03:00:00', 'Jupiter'),
(013, 'HIIT Cardio', '12:00:00', 'Venus'),
(014, 'HIIT Cardio', '20:00:00', 'Venus'),
(015, 'HIIT Cardio', '04:00:00', 'Venus'),
(016, 'Toning', '13:00:00', 'Pluto'),
(017, 'Toning', '21:00:00', 'Pluto'),
(018, 'Toning', '05:00:00', 'Pluto');

INSERT INTO conducts VALUES
(001, 004),
(002, 007),
(002, 016),
(003, 005),
(004, 008),
(004, 017),
(005, 006),
(006, 009),
(006, 018),
(007, 013),
(008, 001),
(008, 010),
(009, 014),
(010, 002),
(010, 011),
(011, 015),
(012, 003),
(012, 012);

INSERT INTO equipment VALUES
(1, 'Treadmill', 'Venus', '2020-11-15', 'In Use'),
(2, 'Treadmill', 'Venus', '2020-12-15', 'In Use'),
(3, 'Treadmill', 'Venus', '2021-01-15', 'In Use'),
(4, 'Treadmill', 'Venus', '2021-02-15', 'In Use'),
(5, 'Treadmill', 'Venus', '2021-03-15', 'In Use'),
(6, 'Treadmill', 'Storage', '2021-04-15', 'Standby'),
(7, 'Rower', 'Jupiter', '2020-11-21','In Use'),
(8, 'Rower', 'Jupiter', '2020-12-21','In Use'),
(9, 'Rower', 'Storage', '2021-01-21','Standby'),
(10, '1000lb SquatRk', 'Jupiter', '2020-11-28','In Use'),
(11, '1000lb SquatRk', 'Jupiter', '2020-12-28','In Use'),
(12, '1000lb SquatRk', 'Jupiter', '2021-01-28','In Use'),
(13, '1000lb SquatRk', 'Jupiter', '2021-02-28','In Use'),
(14, '1000lb SquatRk', 'Jupiter', '2020-11-28','Awaiting Parts'),
(15, 'Stair Master', 'Venus', '2020-11-05','In Use'),
(16, 'Stair Master', 'Venus', '2020-12-05','In Use'),
(17, 'Stair Master', 'Storage', '2021-01-05','Standby'),
(18, 'Multi Function', 'Pluto', '2020-11-24','In Use'),
(19, 'Multi Function', 'Pluto', '2020-12-24','In Use'),
(20, 'Multi Function', 'Pluto', '2021-01-24','Awaiting Parts'),
(21, 'Spin Bike', 'Venus', '2020-11-01', 'In Use'),
(22, 'Spin Bike', 'Venus', '2020-12-01', 'In Use'),
(23, 'Spin Bike', 'Venus', '2021-01-01', 'In Use'),
(24, 'Spin Bike', 'Venus', '2021-02-01', 'In Use'),
(25, 'Spin Bike', 'Venus', '2020-03-01', 'In Use'),
(26, 'Wgts w/ Bench', 'Pluto', '2020-11-07', 'In Use'),
(27, 'Wgts w/ Bench', 'Pluto', '2020-12-07', 'In Use'),
(28, 'Wgts w/ Bench', 'Pluto', '2021-01-07', 'In Use'),
(29, 'CrossFit Kit', 'Jupiter', '2020-11-18', 'In Use'),
(30, 'CrossFit Kit', 'Jupiter', '2020-12-18', 'In Use');
