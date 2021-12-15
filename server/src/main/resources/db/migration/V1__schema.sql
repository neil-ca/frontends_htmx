DROP TABLE IF EXISTS TASK;
CREATE TABLE TASK (
  id    SERIAL PRIMARY KEY,
  title varchar(55) NOT NULL UNIQUE,
  description varchar(255) NOT NULL,
  status smallint NOT NULL,
  limit_date varchar NOT NULL,
  owner varchar(55),
  tag varchar(30)
);



