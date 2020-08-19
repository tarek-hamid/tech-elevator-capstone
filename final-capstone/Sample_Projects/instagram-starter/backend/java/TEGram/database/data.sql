DO $$
DECLARE
  _newUserOneId int;
  _newUserTwoId int;
  _newPostOneId int;
  _newCommentOneId int;
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
		'rrichards',
		'FjZDm+sndmsdEDwNtfr6NA==',
		'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
		'user',
		'https://ui-avatars.com/api/?name=rrichards&length=2&size=128&rounded=true&color=FFE4C4&background=8B4513&uppercase=false&bold=true'
	) RETURNING id INTO _newUserTwoId;

	-- legoman posts
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserOneId, 'https://inhabitat.com/files/2010/03/Jan-Vormann-Lego-Brick.jpg',N'Inspired....😍')
		RETURNING id INTO _newPostOneId;

	-- rrichards comments
	INSERT INTO comments(post_id, user_id, message) VALUES(_newPostOneId, _newUserTwoId, N'this reminds me of a scene from my favorite lego movie')
		RETURNING id INTO _newCommentOneId;

END$$;
