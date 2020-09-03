BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS beer;

CREATE TABLE beer (
  beer_id SERIAL NOT NULL,
  name varchar(32),
  description varchar(256),
  abv DECIMAL,
  beer_type varchar(32),
  brewery_id int,

  constraint pk_beer_id primary key (beer_id),
  constraint fk_brewery_id foreign key (brewery_id) references brewery (brewery_id)

);

COMMIT;