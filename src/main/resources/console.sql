select now();

create table tbl_todo (
    tno int auto_increment primary key ,
    title varchar(100) not null ,
    dueDate date not null ,
    writer varchar(50) not null ,
    finished tinyint default 0
);

select *
from tbl_todo;

insert into tbl_todo (title, dueDate, writer)
    (select title, dueDate, writer from tbl_todo);

select count(*) from tbl_todo;

select * from tbl_todo order by tno desc;