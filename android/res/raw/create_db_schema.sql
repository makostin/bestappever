drop table if exists lesson;
create table if not exists lesson
(
    _id integer primary key autoincrement,
    lessonId LONG,
    subject VARCHAR(100),
    time VARCHAR(10),
    lecturer VARCHAR(150),
    day INTEGER,
    course INTEGER,
    subGroup VARCHAR(5),
    classroom VARCHAR(5)
);

drop table if exists studentOption;
create table if not exists studentOption
(
    _id INTEGER primary key autoincrement,
    course INTEGER,
    is_download INTEGER,
    is_login INTEGER,
    is_current INTEGER,
    week INTEGER,
    name VARCHAR(150),
    subGroup VARCHAR(5)
);

drop table if exists lecturerOption;
create table if not exists lecturerOption
(
    _id INTEGER primary key autoincrement,
    lecturer_id LONG,
    name VARCHAR(150),
    department VARCHAR(250),
    is_current INTEGER,
    is_download INTEGER,
    is_login INTEGER,
    week INTEGER
);

