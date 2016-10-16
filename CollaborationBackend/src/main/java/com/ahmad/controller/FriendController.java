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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.FriendDAO;
import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.Friend;
import com.ahmad.model.UserDetail;

@RestController
public class FriendController {

	@Autowired
	Friend friend;

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
			List<UserDetail> listFriends = returnfriendList(userId, 'A');
			if (listFriends != null)
				return new ResponseEntity<List<UserDetail>>(listFriends, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<UserDetail>>(HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/friends/user/{userId}")
	public ResponseEntity<List<UserDetail>> getUnFriends(@PathVariable("userId") String userId) {

		try {
			if (userDetailDAO.getUserDetail(userId) == null) {
				return new ResponseEntity<List<UserDetail>>(HttpStatus.NOT_FOUND);
			}
			// Called a custom method in class which will give me the list of
			// friends
			// List<UserDetail> listFriends = returnfriendList(userId, 'A');
			List<Friend> listFriends = friendDAO.getFriendsN(userId, 'A');
			List<UserDetail> listUsers = new ArrayList<>();
			for (Friend friend : listFriends) {
				UserDetail userDetail = new UserDetail();

				if (friend.getUser1().equals(userId))
					userDetail = userDetailDAO.getUserDetail(friend.getUser2());

				else if (friend.getUser2().equals(userId))
					userDetail = userDetailDAO.getUserDetail(friend.getUser1());

				listUsers.add(userDetail);

			}
			System.out.println(listUsers);

			List<UserDetail> listOfUsers = userDetailDAO.listUserDetails();
			System.out.println(listOfUsers.size());

			if (listFriends.size() != 0 || listFriends != null) {
				// If the user had made any friend the remove else return whole
				// users
				listOfUsers.removeAll(listUsers);
			}

			System.out.println(listOfUsers.size());
			System.out.println(userId);
			/* Remove the the user who is acessing the friend list */
			for (UserDetail user : listOfUsers) {

				System.out.println(user.getUserId());
				if (userId.equals(user.getUserId())) {
					listOfUsers.remove(user);
					break;
				}

			}
			System.out.println(listOfUsers.size());

			if (!listOfUsers.isEmpty() || listOfUsers != null)
				return new ResponseEntity<List<UserDetail>>(listOfUsers, HttpStatus.OK);

			else
				return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return new ResponseEntity<List<UserDetail>>(HttpStatus.BAD_REQUEST);

	}

	@PostMapping("/friends/user/send/request/")
	public ResponseEntity<Void> sendFriendRequest(@RequestParam("user1") String user1,
			@RequestParam("user2") String user2, UriComponentsBuilder ucBuider) {

		if (userDetailDAO.getUserDetail(user1) == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		
		
		if(friendDAO.getFriend(user1, user2, 'P')!=null){
			System.out.println("in 1");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else if (friendDAO.getFriend(user2, user1, 'P')!=null){
			System.out.println("in 2");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else{
		friend.setUser1(user1);
		friend.setUser2(user2);
		friend.setStatus('P');
		friendDAO.sendRequest(friend);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuider.path("/friend/id").buildAndExpand(friend.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}

	@GetMapping("/friends/user/notification/{userId}")
	public ResponseEntity<List<UserDetail>> listFriendNotifications(@PathVariable("userId") String userId) {

		List<UserDetail> listOfUsers = returnfriendList(userId, 'P');

		if (listOfUsers != null) {
			return new ResponseEntity<List<UserDetail>>(listOfUsers, HttpStatus.OK);
		}
		return new ResponseEntity<List<UserDetail>>(HttpStatus.NO_CONTENT);

	}

	@PutMapping("/friends/user/notification/accept")
	public ResponseEntity<Void> acceptFriendRequest(@RequestParam("user1") String user1,
			@RequestParam("user2") String user2) {

		friend = friendDAO.getFriend(user1, user2, 'P');
		if (friend != null) {
			friend.setStatus('A');
			friendDAO.acceptRequest(friend);
		} else {
			friend = friendDAO.getFriend(user2, user1, 'P');
			friend.setStatus('A');
			friendDAO.acceptRequest(friend);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/friends/user/notification/reject")
	public ResponseEntity<Void> rejectFriendRequest(@RequestParam("user1") String user1,
			@RequestParam("user2") String user2) {

		friend = friendDAO.getFriend(user1, user2, 'P');
		if (friend != null) {
			friend.setStatus('R');
			friendDAO.acceptRequest(friend);
		} else {
			friend = friendDAO.getFriend(user2, user1, 'P');
			friend.setStatus('R');
			friendDAO.acceptRequest(friend);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// ############# Custom Methods #############

	// This method will return list of friends

	public List<UserDetail> returnfriendList(String userId, char status) {

		/*
		 * We will be getting list of friends which will contain two columns
		 * user1 and user2 as when both users are friend of each other both
		 * should be able to see there own friend list and so we have the value
		 * in only one row for both of them as one is friend of other so
		 * automatically the other is also friend of first so we dont need to
		 * create another row as we can iterate and check between both the
		 * columns...... :)
		 * 
		 */

		List<Friend> listFriends = friendDAO.getFriends(userId, status);
		List<UserDetail> listUsers = new ArrayList<>();
		for (Friend friend : listFriends) {
			UserDetail userDetail = new UserDetail();

			if (friend.getUser1().equals(userId))
				userDetail = userDetailDAO.getUserDetail(friend.getUser2());

			else if (friend.getUser2().equals(userId))
				userDetail = userDetailDAO.getUserDetail(friend.getUser1());

			listUsers.add(userDetail);

		}
		System.out.println(listUsers);
		if (listUsers != null)
			return listUsers;

		return null;
	}
	// ###################################################################
}
