# Flagship
Example Java project that includes the basics for REST endpoints.

See Git tags for step-by-step notes.

## Database Example
```
create table projects (
  id int not null auto_increment primary key,
  name varchar(256)
);

insert into projects (name) values ('Project 1');
insert into projects (name) values ('Project 2');
```