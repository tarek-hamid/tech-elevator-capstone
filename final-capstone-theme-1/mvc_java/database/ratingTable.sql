BEGIN;

DROP TABLE IF EXISTS rating;

CREATE TABLE rating (
  rating_id SERIAL NOT NULL,
  beer_id int NOT NULL,
  rating int NOT NULL, 
  rating_description varchar(256) NOT NULL,

  constraint rating_id primary key (rating_id),
  constraint fk_beer_id foreign key (beer_id) references beer (beer_id)

);

COMMIT;