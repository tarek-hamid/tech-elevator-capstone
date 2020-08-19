DO $$
DECLARE
  _newUserOneId int;
  _newUserTwoId int;
  _newUserThreeId int;
  _newUserFourId int;
  _newPostOneId int;
  _newPostTwoId int;
  _newPostThreeId int;
  _newPostFourId int;
  _newPostFiveId int;
  _newPostSixId int;
  _newPostSevenId int;
  _newPostEightId int;
  _newPostNineId int;
  _newPostTenId int;
  _newCommentOneId int;
  _newCommentTwoId int;
  _newCommentThreeId int;
BEGIN
	-- Zap tables under test
	DELETE FROM favorites;
	DELETE FROM likes;
	DELETE FROM comments;
	DELETE FROM posts;
	DELETE FROM users;

	-- Password for ALL users is 'greatwall'
	INSERT INTO users (username, password, salt, role, image) VALUES
	(
		'Rick Astley',
		'FjZDm+sndmsdEDwNtfr6NA==',
		'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
		'user',
		'https://ui-avatars.com/api/?name=Rick+Astley&length=2&size=128&rounded=true&color=FFD700&background=8B4513&uppercase=false&bold=true'
	) RETURNING id INTO _newUserOneId;
	INSERT INTO users (username, password, salt, role, image) VALUES
	(
		'bernice92',
		'FjZDm+sndmsdEDwNtfr6NA==',
		'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
		'user',
		'https://ui-avatars.com/api/?name=bernice92&length=2&size=128&rounded=true&color=AFEEEE&background=556B2F&uppercase=false&bold=true'
	) RETURNING id INTO _newUserTwoId;
	INSERT INTO users (username, password, salt, role, image) VALUES
	(
		'Eustace Strytch',
		'FjZDm+sndmsdEDwNtfr6NA==',
		'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
		'user',
		'https://ui-avatars.com/api/?name=Eustace%20Strytch&length=2&size=128&rounded=true&color=AFEEEE&background=2F4F4F&uppercase=false&bold=true'
	) RETURNING id INTO _newUserThreeId;
	INSERT INTO users (username, password, salt, role, image) VALUES
	(
		'Tech Elevator',
		'FjZDm+sndmsdEDwNtfr6NA==',
		'kidcasB0te7i0jK0fmRIGHSm0mYhdLTaiGkEAiEvLp7dAEHWnuT8n/5bd2V/mqjstQ198iImm1xCmEFu+BHyOz1Mf7vm4LILcrr17y7Ws40Xyx4FOCt8jD03G+jEafpuVJnPiDmaZQXJEpEfekGOvhKGOCtBnT5uatjKEuVWuDA=',
		'user',
		'https://ui-avatars.com/api/?name=Tech+Elevator&length=2&size=128&rounded=true&color=F0FFFF&background=663399&uppercase=false&bold=true'
	) RETURNING id INTO _newUserFourId;
	
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserOneId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554345353/TEMP%20-%20TE%20Gram/lp2huzhbruifuyetet2n.jpg', N'My first selfie!')
		RETURNING id INTO _newPostOneId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserTwoId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554345419/TEMP%20-%20TE%20Gram/eggnutbc7exmk5jiudvx.jpg', N'I hope it looks like this on opening day!')
		RETURNING id INTO _newPostTwoId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserThreeId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554486244/TEMP%20-%20TE%20Gram/fdehu3xbcu8vdxqpe8rl.jpg', N'Not a fan.')
		RETURNING id INTO _newPostThreeId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserFourId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554407052/TEMP%20-%20TE%20Gram/ptofflxnm45iou3kbbin.png', N'Cohort 7 Group Shot!')
		RETURNING id INTO _newPostFourId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserFourId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554407104/TEMP%20-%20TE%20Gram/vwiuai7ve7gakrgqpiqx.jpg', N'Throwback to Cohort 0!')
		RETURNING id INTO _newPostFiveId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserFourId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554407132/TEMP%20-%20TE%20Gram/eyjkj7rir8fui9s6gp7v.jpg', N'What a funny group!')
		RETURNING id INTO _newPostSixId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserFourId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554407230/TEMP%20-%20TE%20Gram/wkiue8zjrlnxetuovhdq.png', N'Getting ready for matchmaking! #dreamjob')
		RETURNING id INTO _newPostSevenId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserFourId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554407270/TEMP%20-%20TE%20Gram/n0lbjc1rwsaqgvqdgihe.jpg', N'Hear great war stories from experienced developers!')
		RETURNING id INTO _newPostEightId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserOneId, N'https://res.cloudinary.com/tech-elevator/image/upload/v1554317361/TEMP%20-%20TE%20Gram/djkwllxzdq63n6gtd8vj.jpg', N'Blaze, at ATTENTION')
		RETURNING id INTO _newPostNineId;
	INSERT INTO posts(user_id, image, caption) VALUES(_newUserOneId, N'https://cdn.pixabay.com/photo/2016/03/25/02/24/cute-1278095__340.jpg', N'Future terror!')
		RETURNING id INTO _newPostTenId;

	INSERT INTO comments(post_id, user_id, message) VALUES(_newPostTwoId, _newUserThreeId, N'It certainly did not!')
		RETURNING id INTO _newCommentOneId;
	INSERT INTO comments(post_id, user_id, message) VALUES(_newPostTwoId, _newUserOneId, N'I remember this game though!')
		RETURNING id INTO _newCommentTwoId;
	INSERT INTO comments(post_id, user_id, message) VALUES(_newPostSixId, _newUserOneId,  N'I think that was Cohort[6]!')
		RETURNING id INTO _newCommentThreeId;

	INSERT INTO likes(user_id, post_id) VALUES(_newUserTwoId, _newPostOneId);
	INSERT INTO likes(user_id, post_id) VALUES(_newUserTwoId, _newPostTwoId);
	INSERT INTO likes(user_id, post_id) VALUES(_newUserTwoId, _newPostFourId);
	INSERT INTO likes(user_id, post_id) VALUES(_newUserTwoId, _newPostFiveId);
	INSERT INTO likes(user_id, post_id) VALUES(_newUserTwoId, _newPostSixId);
	INSERT INTO likes(user_id, post_id) VALUES(_newUserTwoId, _newPostEightId);
	INSERT INTO likes(user_id, post_id) VALUES(_newUserThreeId, _newPostOneId);
	INSERT INTO likes(user_id, post_id) VALUES(_newUserThreeId, _newPostTwoId);

	INSERT INTO favorites(user_id, post_id) VALUES(_newUserOneId, _newPostTwoId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserOneId, _newPostSixId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserTwoId, _newPostOneId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserTwoId, _newPostTwoId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserTwoId, _newPostThreeId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserTwoId, _newPostFiveId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserThreeId, _newPostOneId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserThreeId, _newPostTwoId);
	INSERT INTO favorites(user_id, post_id) VALUES(_newUserFourId, _newPostFourId);

END$$;
