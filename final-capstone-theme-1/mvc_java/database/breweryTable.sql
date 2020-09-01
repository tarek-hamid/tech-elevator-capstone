-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS brewery;

CREATE TABLE brewery (
  brewery_id SERIAL NOT NULL,
  user_id int NOT NULL,
  brewery_name varchar(32) NOT NULL,
  phone_number varchar(13),
  open_from time,
  open_until time,
  website varchar(32),
  email varchar(32),
  address varchar(255),
  history varchar (255),
  active boolean,

  constraint pk_brewer_id primary key (brewery_id),
  constraint fk_user_id foreign key (user_id) references app_user (id)

);

COMMIT;