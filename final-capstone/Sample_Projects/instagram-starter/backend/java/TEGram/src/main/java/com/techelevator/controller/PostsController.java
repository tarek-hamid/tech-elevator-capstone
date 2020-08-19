package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.authentication.RequestAuthProvider;
import com.techelevator.authentication.UserCreationException;
import com.techelevator.model.Comment;
import com.techelevator.model.CommentDAO;
import com.techelevator.model.FavoriteDAO;
import com.techelevator.model.LikeDAO;
import com.techelevator.model.Post;
import com.techelevator.model.PostDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDao;
import com.techelevator.model.UserPostsViewModel;

@RestController
public class PostsController {

	/*
	 Verb    	API                            	Description                        	Request Body        	Response Body
	======    	===============                	======================            	===================    	=====================
	√ GET       api/posts                    	Get all posts with comments        	None                	Array of posts
	√ GET     	api/posts/{id}                	Get post by Id with comments    	None                	Post item
	POST    	api/posts                    	Create new post                    	Post item            	Post item

	√ POST    	api/posts/{id}/comments        	Create comment for post            	Comment item        	Comment item
	√ DELETE    api/posts/comments/{id}			Delete comment from post        	None                	None

	√ GET       api/posts/{id}/likes        	Get all likes for post            	None                	Array of Users who like post
	√ POST    	api/posts/{id}/likes        	Like a post                        	Like item            	Number of likes?
	√ DELETE    api/posts/{id}/likes        	Un-like a post                    	None                	Number of likes?

	√ POST    	api/posts/{id}/favorites    	Add post as favorite            	Favorite item        	None
	√ DELETE    api/posts/{id}/favorites    	Dis-favor a post                	None                	None

	√ GET       api/favorites                	Get favorite posts for user        	None                	Array of posts

	√ GET       api/users/{name}            	Get User and Posts                	None                	ViewModel containing user (Id, Name, image) and array of posts 
	 */
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private CommentDAO commentDao;
	
	@Autowired
	private LikeDAO likeDao;
	
	@Autowired
	private FavoriteDAO favoriteDao;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping(path="/posts")
	public List<Post> posts(HttpServletRequest request) {
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
		return postDao.getAllPosts(currentUser.getId());
	}
	
	@GetMapping(path="/posts/{id}")
	public Post post(@PathVariable long id, HttpServletRequest request) {
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
		return postDao.getPostById(id, currentUser.getId());
	}

	@PostMapping(path="/posts")
	public Post post(@Valid @RequestBody Post input,
        BindingResult result, HttpServletRequest request
    ) throws UserCreationException {
        if(result.hasErrors()) {
            String errorMessages = "";
            for(ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        }
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
        Post post = new Post();
        post.setUserId(currentUser.getId());
        post.setImage(input.getImage());
        post.setCaption(input.getCaption());
        post = postDao.createPost(post);
        return post;
    }

	@PostMapping(path="/posts/{id}/comments")
	public Comment comment(@Valid @RequestBody Comment input,
        BindingResult result, @PathVariable long id, HttpServletRequest request
    ) throws UserCreationException {
        if(result.hasErrors()) {
            String errorMessages = "";
            for(ObjectError error : result.getAllErrors()) {
                errorMessages += error.getDefaultMessage() + "\n";
            }
            throw new UserCreationException(errorMessages);
        }
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
        Comment comment = new Comment();
        comment.setPostId(id);
        comment.setUserId(currentUser.getId());
        comment.setMessage(input.getMessage());
        comment = commentDao.createComment(comment);
        return comment;
    }
	
	@DeleteMapping(path="/posts/comments/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteComment(@PathVariable long id) {
		commentDao.deleteComment(id);
	}
	
	@GetMapping(path="/posts/{id}/likes")
	public List<User> postLikes(@PathVariable long id) {
		return likeDao.getAllLikesByPostId(id);
	}

	@PostMapping(path="/posts/{id}/likes")
	@ResponseStatus(value = HttpStatus.OK)
	public String likePost(@PathVariable long id, HttpServletRequest request) {
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
		return "\"" + likeDao.likePostByUserId(id,currentUser.getId()) + "\"";
	}
	
	@DeleteMapping(path="/posts/{id}/likes")
	@ResponseStatus(value = HttpStatus.OK)
	public String unlikePost(@PathVariable long id, HttpServletRequest request) {
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
		return "\"" + likeDao.unlikePostByUserId(id,currentUser.getId()) + "\"";
	}

	@PostMapping(path="/posts/{id}/favorites")
	@ResponseStatus(value = HttpStatus.OK)
	public void favorPost(@PathVariable long id, HttpServletRequest request) {
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
		favoriteDao.favorPostByUserId(id,currentUser.getId());
	}
	
	@DeleteMapping(path="/posts/{id}/favorites")
	@ResponseStatus(value = HttpStatus.OK)
	public void disfavorPost(@PathVariable long id, HttpServletRequest request) {
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
		favoriteDao.disfavorPostByUserId(id,currentUser.getId());
	}
	
	@GetMapping(path="/favorites")
	public List<Post> favorites(HttpServletRequest request) {
		User currentUser = (User) request.getAttribute(RequestAuthProvider.USER_KEY);
		return postDao.getFavoritesByUserId(currentUser.getId());
	}

	@GetMapping(path="/users/{username}")
	public UserPostsViewModel userPosts(@PathVariable String username) {
		UserPostsViewModel userPostsViewModel = new UserPostsViewModel();
		User currentUser = userDao.getUserByUsername(username);
		userPostsViewModel.setUsername(currentUser.getUsername());
		userPostsViewModel.setUserPosts(postDao.getFavoritesByUserId(currentUser.getId()));
		return userPostsViewModel;
	}

}
