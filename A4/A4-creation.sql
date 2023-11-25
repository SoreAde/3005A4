
create table students
    (
        student_id Integer SERIAL,
        first_name text not null,
        last_name text not null,
        email text unique not null,
        enrollment_date DATE,
        primary key (student_id)
    );