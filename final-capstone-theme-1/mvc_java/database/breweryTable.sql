-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS brewery;

CREATE TABLE brewery (
  id SERIAL PRIMARY KEY,
  brewery_name varchar(32) NOT NULL,
  phone_number varchar(13) NOT NULL,
  --hours_of_operation varchar(32) NOT NULL,
  website varchar(32) NOT NULL,
  email varchar(32) NOT NULL,
  address varchar(255) NOT NULL,
  history varchar (255),
  active boolean,
);

COMMIT;