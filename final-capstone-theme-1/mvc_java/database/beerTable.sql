BEGIN;

DROP TABLE IF EXISTS beer;

CREATE TABLE beer (
  beer_id SERIAL NOT NULL,
  name varchar(32) NOT NULL,
  description varchar(256) NOT NULL,
  abv int NOT NULL,
  beer_type varchar(32) NOT NULL,
  brewery_id int NOT NULL,

  constraint beer_id primary key (beer_id),
  constraint fk_brewery_id foreign key (brewery_id) references brewery (brewery_id)

);

COMMIT;