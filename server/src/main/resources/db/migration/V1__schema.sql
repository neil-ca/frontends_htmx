DROP TABLE IF EXISTS TASK;
CREATE TABLE TASK (
  id    SERIAL PRIMARY KEY,
  title varchar(55) NOT NULL UNIQUE
--  description varchar(255) NOT NULL,
--  status smallint(3) NOT NULL,
--  limit-date date NOT NULL,
--  owner varchar(55),
--  tag varchar(30)
);



