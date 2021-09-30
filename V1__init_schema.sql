create table school
(
	school_id serial,
	school_name varchar not null,
	address varchar(256) not null
);

create unique index school_id_uindex
	on school (school_id);

alter table school
	add constraint school_pk
		primary key (school_id);

create table student
(
	student_id serial,
	first_name varchar not null,
	last_name varchar not null,
	patronymic varchar,
	sex int not null,
	age int not null,
	school_id int not null
		constraint school_id
			references school
);

create unique index student_id_uindex
	on student (student_id);

alter table student
	add constraint student_pk
		primary key (student_id);

create table teacher
(
	teacher_id serial,
	firstname varchar not null,
	lastname varchar not null,
	patronymic varchar,
	age int not null,
	sex int not null,
	school_id int not null
		constraint school_id
			references school
);

create unique index teacher_id_uindex
	on teacher (teacher_id);

alter table teacher
	add constraint teacher_pk
		primary key (teacher_id);

create table subject
(
	subject_id serial,
	subject_name varchar not null
);

create unique index subject_id_uindex
	on subject (subject_id);

alter table subject
	add constraint subject_pk
		primary key (subject_id);

create table teacher_subject
(
	teacher_id int not null
		constraint teacher_id
			references teacher,
	subject_id int not null
		constraint subject_id
			references subject
);

alter table teacher_subject
	add constraint teacher_subject_id_pk
		primary key (teacher_id, subject_id);

create table student_subject
(
	student_id int not null
		constraint student_id
			references student,
	subject_id int not null
		constraint subject_id
			references subject
);

alter table student_subject
	add constraint student_subject_id_pk
		primary key (student_id, subject_id);


