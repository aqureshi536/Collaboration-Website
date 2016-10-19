package com.ahmad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.FriendDAO;
import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.Friend;
import com.ahmad.model.UserDetail;
import com.ahmad.viewmodel.BothUserModel;
import com.ahmad.viewmodel.UserModel;

@RestController
public class FriendController {

	@Autowired
	Friend friend;

	@Autowired
	FriendDAO friendDAO;

	@Autowired
	UserDetailDAO userDetailDAO;

	@Autowired
	UserDetail userDetail;

	@GetMapping("/friends/{userId}")
	public ResponseEntity<List<UserDetail>> getAllFriends(@PathVariable("userId") String userId) {

		// List<UserDetail> listOfFriends = returnfriendList(userId, 'A');
		List<UserDetail> listOfUsers = new ArrayList<>();
		List<Friend> listOfFriends = friendDAO.getFriends(userId, 'A');
		listOfFriends.forEach(friend -> {
			userDetail = new UserDetail();
			userDetail = userDetailDAO.getUserDetail(friend.getFriendUser());
			listOfUsers.add(userDetail);
		});
		if (listOfFriends != null && !listOfFriends.isEmpty())
			return new ResponseEntity<List<UserDetail>>(listOfUsers, HttpStatus.OK);
		return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/friends/user/send/request/")
	public ResponseEntity<Void> sendFriendRequest(@RequestParam("user1") String user1,
			@RequestParam("user2") String friendUser, UriComponentsBuilder ucBuider) {
		friend = new Friend();
		friend.setUser1(user1);
		friend.setFriendUser(friendUser);
		friend.setStatus('P');
		friendDAO.sendRequest(friend);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuider.path("/friend/id").buildAndExpand(friend.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@GetMapping("/friends/user/{userId}")
	public ResponseEntity<List<UserModel>> getAllUsers(@PathVariable("userId") String userId) {
		List<UserModel> listOfUserModel = new ArrayList<>();
		List<UserDetail> listOfUsers = userDetailDAO.listUserDetails();
		List<Friend> usersFromFriend = friendDAO.listFriends();

		/*
		 * We check for every user whether his entry is present like whether he
		 * has sent the request to anyone with the specific user if the result
		 * gets so we will be sending the user with status accordingly else we
		 * will make status of that user N = not available
		 */
		boolean isPresent = false;
		for (UserDetail user : listOfUsers) {
			UserModel userModel = new UserModel();
			for (Friend friend : usersFromFriend) {
				if (userId.equals(friend.getUser1()) && user.getUserId().equals(friend.getFriendUser())) {
					/*
					 * to check the self user as user requesting should not be
					 * displayed on the list
					 */
					if (!userId.equals(user.getUserId())) {
						userModel.setUserDetail(user);
						userModel.setStatus(friend.getStatus());
						isPresent = true;
						listOfUserModel.add(userModel);
						break;
					}
				} else {
					{
						isPresent = false;
					}
				}
			}
			if (!isPresent) {
				if (!userId.equals(user.getUserId())) {
					userModel.setUserDetail(user);
					userModel.setStatus('N');
					listOfUserModel.add(userModel);
				}
			}

		}

		return new ResponseEntity<List<UserModel>>(listOfUserModel, HttpStatus.OK);
	}

	@GetMapping("/friends/user/notification/{userId}")
	public ResponseEntity<List<UserDetail>> listFriendNotifications(@PathVariable("userId") String userId) {

		List<Friend> listFriends = friendDAO.getRequested(userId, 'P');
		List<UserDetail> listUsers = new ArrayList<>();
		for (Friend friend : listFriends) {
			UserDetail userDetail = new UserDetail();
			userDetail = userDetailDAO.getUserDetail(friend.getUser1());
			listUsers.add(userDetail);

		}
		if (listUsers != null) {
			return new ResponseEntity<List<UserDetail>>(listUsers, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);

	}

	@PutMapping(value = "/friends/user/notification/accept", consumes = "application/json")
	public ResponseEntity<Void> acceptFriendRequest(@RequestBody BothUserModel bothUserModel) {

		friend = friendDAO.getFriend(bothUserModel.getUser1(), bothUserModel.getFriendUser(), 'P');
		friend.setStatus('A');
		friendDAO.acceptRequest(friend);

		Friend friendToAdd = new Friend();
		friendToAdd.setStatus('A');
		friendToAdd.setUser1(bothUserModel.getFriendUser());
		friendToAdd.setFriendUser(bothUserModel.getUser1());
		friendDAO.acceptRequest(friendToAdd);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping(value = "/friends/user/notification/reject/request/")
	public ResponseEntity<Void> rejectFriendRequest(@RequestBody BothUserModel bothUserModel) {

		friend = friendDAO.getFriend(bothUserModel.getUser1(), bothUserModel.getFriendUser(), 'P');
		friendDAO.rejectRequest(friend);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// ############# Custom Methods #############

	// This method will return list of friends by searching the friend table

	public List<UserDetail> returnfriendList(String userId, char status) {

		List<Friend> listFriends = friendDAO.getOfFriends(userId, status);
		List<UserDetail> listUsers = new ArrayList<>();
		for (Friend friend : listFriends) {
			UserDetail userDetail = new UserDetail();
			userDetail = userDetailDAO.getUserDetail(friend.getFriendUser());
			listUsers.add(userDetail);

		}
		// System.out.println(listUsers);
		if (listUsers != null)
			return listUsers;

		return null;

	}

	/*
	 * COmmented for testing
	 * 
	 * @GetMapping("/friends/user/{userId}") public
	 * ResponseEntity<List<UserModel>> getUnFriends(@PathVariable("userId")
	 * String userId) { List<UserModel> listOfUserModel = new ArrayList<>();
	 * List<UserDetail> listOfUsers = userDetailDAO.listUserDetails();
	 * List<Friend> usersFromFriend = friendDAO.getUnFriends(userId, 'A');
	 * 
	 * /* trying to find the persons who have there status pending or rejected
	 * in the friend table and get there details and give to the view (if the
	 * user exist in friend table status will we same as friend status else it
	 * will be N=Not avaialable)
	 * 
	 * boolean isPresent = false; for (UserDetail user : listOfUsers) {
	 * UserModel userModel = new UserModel();
	 * 
	 * for (Friend friend : usersFromFriend) {
	 * 
	 * if (user.getUserId().equals(friend.getUser1())) {
	 * 
	 * userModel.setUserDetail(user); userModel.setStatus(friend.getStatus());
	 * isPresent = true; break; } else { isPresent = false; } } if (!isPresent)
	 * { userModel.setStatus('N'); userModel.setUserDetail(user); } if
	 * (!userModel.getUserDetail().getUserId().equals(userId))
	 * listOfUserModel.add(userModel); } return new
	 * ResponseEntity<List<UserModel>>(listOfUserModel, HttpStatus.OK); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}