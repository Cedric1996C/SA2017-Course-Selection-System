DROP TABLE user IF EXISTS;
DROP  TABLE student IF EXISTS;

CREATE TABLE user (
  id            BIGINT IDENTITY PRIMARY KEY,
  username      VARCHAR(80),
  password      VARCHAR(80)
);

CREATE TABLE  student (
  id            BIGINT IDENTITY   PRIMARY  KEY,
  studentname   VARCHAR(80),
  department    VARCHAR(80),
  grade         VARCHAR(80),
  usual_grade   INT,
  design_grade  INT,
  exam_grade    INT
)