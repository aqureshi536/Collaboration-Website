package com.ahmad.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.DAO.FriendDAO;
import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.Friend;
import com.ahmad.model.UserDetail;

@RestController
public class FriendController {

	@Autowired
	FriendDAO friendDAO;

	@Autowired
	UserDetailDAO userDetailDAO;

	@GetMapping("/friends/{userId}")
	public ResponseEntity<List<UserDetail>> getAllFriends(@PathVariable("userId") String userId) {

		try {
			if (userDetailDAO.getUserDetail(userId) == null) {
				return new ResponseEntity<List<UserDetail>>(HttpStatus.NOT_FOUND);
			}
			// Called a custom method in class which will give me the list of
			// friends
			List<UserDetail> listFriends = returnfriendList(userId,'A');
			return new ResponseEntity<List<UserDetail>>(listFriends, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return new ResponseEntity<List<UserDetail>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/friends/user/{userId}")
	public ResponseEntity<List<UserDetail>> getUnFriends(@PathVariable("userId") String userId) {

		try {
			if (userDetailDAO.getUserDetail(userId) == null) {
				return new ResponseEntity<List<UserDetail>>(HttpStatus.NOT_FOUND);
			}
			// Called a custom method in class which will give me the list of
			// friends
			List<UserDetail> listFriends = returnfriendList(userId,'A');
			List<UserDetail> listOfUsers = userDetailDAO.listUserDetails();
			listOfUsers.removeAll(listFriends);
			return new ResponseEntity<List<UserDetail>>(listOfUsers, HttpStatus.OK);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			return new ResponseEntity<List<UserDetail>>(HttpStatus.BAD_REQUEST);
		}

	}

	// ############# Custom Methods #############

	// This method will return list of friends

	public List<UserDetail> returnfriendList(String userId,char status) {

		/*
		 * We will be getting list of friends which will contain two columns user1
		 * and user2 as when both users are friend of each other both should be
		 * able to see there own friend list and so we have the value in only
		 * one row for both of them as one is friend of other so automatically
		 * the other is also friend of first so we dont need to create another
		 * row as we can iterate and check between both the columns...... :)
		 * 
		 */

		List<Friend> listFriends = friendDAO.getFriends(userId,status);
		List<UserDetail> listUsers = new ArrayList<>();
		listFriends.forEach(friend -> {
			UserDetail userDetail = new UserDetail();

			if (friend.getUser1() == userId)
				userDetail = userDetailDAO.getUserDetail(friend.getUser2());

			else if (friend.getUser2() == userId)
				userDetail = userDetailDAO.getUserDetail(friend.getUser1());

			listUsers.add(userDetail);

		});
		return listUsers;
	}
	// ###################################################################
}
