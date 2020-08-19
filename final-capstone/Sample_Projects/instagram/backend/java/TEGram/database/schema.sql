BEGIN TRANSACTION;

DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS favorites;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password
  salt varchar(256) NOT NULL,          -- Password Salt
  role varchar(255) NOT NULL default('user'),
  image varchar(200)
);

CREATE TABLE posts (
	id				SERIAL 			NOT NULL,
	user_id			INT				NOT NULL,
	image			VARCHAR(200)	NOT NULL,
	caption			VARCHAR(100),
	DATETIME_stamp	TIMESTAMP		DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT pk_posts PRIMARY KEY (id),
	CONSTRAINT fk_posts FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE comments (
	id				SERIAL			NOT NULL,
	post_id			INT				NOT NULL,
	user_id			INT				NOT NULL,
	message			VARCHAR(200)	NOT NULL,
	DATETIME_stamp	TIMESTAMP		DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT pk_comments PRIMARY KEY (id),
	CONSTRAINT fk_comments_posts FOREIGN KEY (post_id) REFERENCES posts(id),
	CONSTRAINT fk_comments_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE likes (
	user_id			INT				NOT NULL,
	post_id			INT				NOT NULL,

	CONSTRAINT pk_likes PRIMARY KEY (user_id, post_id),
	CONSTRAINT fk_likes_users FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fk_likes_posts FOREIGN KEY (post_id) REFERENCES posts(id)

);

CREATE TABLE favorites (
	user_id			INT				NOT NULL,
	post_id			INT				NOT NULL,

	CONSTRAINT pk_favorites PRIMARY KEY (user_id, post_id),
	CONSTRAINT fk_favorites_users FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fk_favorites_posts FOREIGN KEY (post_id) REFERENCES posts(id)

);

--
-- Test Setup Script as function.
-- This function is needed in order to return the new_user_one_id, etc. variables to the JUnit test code. 
-- JDBC/Postgres does not appear to support mixing INSERTs and SELECTs in a SQL code block as does Sql Server.
--
DROP FUNCTION IF EXISTS test_setup;

CREATE FUNCTION test_setup() RETURNS RECORD AS $$
DECLARE 
  ret RECORD;
  _newUserOneId int;
  _newUserTwoId int;
  _newPostOneId int;
  _newPostTwoId int;
  _newPostThreeId int;
  _newCommentOneId int;
  _newCommentTwoId int;
BEGIN
	-- Zap tables under test
	DELETE FROM favorites;
	DELETE FROM likes;
	DELETE FROM comments;
	DELETE FROM posts;
	DELETE FROM users;
	-- Insert two users: legoman and bottletopguy
	-- Password for both users is 'greatwall'
	INSERT INTO users (username, password, salt, role, image) VALUES
	(
		'legoman',
		'FjZDm+sndmsdEDwNtfr6NA==',
		'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
		'user',
		'https://ui-avatars.com/api/?name=legoman&length=2&size=128&rounded=true&color=FFD700&background=556B2F&uppercase=false&bold=true'
	) RETURNING id INTO _newUserOneId;
	INSERT INTO users (username, password, salt, role, image) VALUES
	(
		'bottletopguy',
		'FjZDm+sndmsdEDwNtfr6NA==',
		'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
		'user',
		'https://ui-avatars.com/api/?name=bottletopguy&length=2&size=128&rounded=true&color=FFD700&background=556B2F&uppercase=false&bold=true'
	) RETURNING id INTO _newUserTwoId;
	-- Insert test posts
	-- legoman posts postOne and postThree
	-- bottletopguy posts postTwo
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserOneId, 'https://inhabitat.com/files/2010/03/Jan-Vormann-Lego-Brick.jpg',N'Inspired....😍') 
		RETURNING id INTO _newPostOneId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserTwoId, 'https://inhabitat.com/files/2010/03/Jan-Vormann-Lego-Brick.jpg',N'Whatever') 
		RETURNING id INTO _newPostTwoId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserOneId, 'https://www.k9ofmine.com/wp-content/uploads/2017/06/best-dog-booties-800x534.jpg',N'Staying DRY') 
		RETURNING id INTO _newPostThreeId;
	-- Insert test comments
	-- bottletopguy comments on postOne
	-- legoman comments on postTwo
	-- neither user comments on postThree
	INSERT INTO comments(post_id, user_id, message) VALUES(_newPostOneId, _newUserTwoId, N'this reminds me of a scene from my favorite lego movie')
		RETURNING id INTO _newCommentOneId;
	INSERT INTO comments(post_id, user_id, message) VALUES(_newPostTwoId, _newUserOneId, N'Some other text')
		RETURNING id INTO _newCommentTwoId;
	-- Insert test likes
	-- bottletopguy likes postOne
	-- legoman likes postTwo
	-- neither likes postThree
	INSERT INTO likes(post_id, user_id) VALUES(_newPostOneId, _newUserTwoId);
	INSERT INTO likes(post_id, user_id) VALUES(_newPostTwoId, _newUserOneId);
	-- Insert test favorites
	-- bottletopguy favors postOne
	-- legoman favors postTwo
	-- neither favors postThree
	INSERT INTO favorites(post_id, user_id) VALUES(_newPostOneId, _newUserTwoId);
	INSERT INTO favorites(post_id, user_id) VALUES(_newPostTwoId, _newUserOneId);
	-- Gather and return the test User and Post IDs
	ret := (_newUserOneId::INT, _newUserTwoId::INT, _newPostOneId::INT, _newPostTwoId::INT, _newPostThreeId::INT, _newCommentOneId::INT, _newCommentTwoId::INT);
RETURN ret;
END;$$ LANGUAGE plpgsql;

COMMIT TRANSACTION;
