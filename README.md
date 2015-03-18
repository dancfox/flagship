# Flagship
Example Java project that includes the basics for REST endpoints.

See Git tags for step-by-step notes.

```
git tag -ln

v0.1            Initial App
v0.2            Introducing Guice and App Environment
v0.3            Introducing Jetty and Jersey
v0.4            Introducing HealthChecks and Jackson
v0.5            Basic persistence with MyBatis and MySQL
```

## Database Example
```
create table projects (
  id int not null auto_increment primary key,
  name varchar(256)
);

insert into projects (name) values ('Project 1');
insert into projects (name) values ('Project 2');
```