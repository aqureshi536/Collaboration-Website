package com.ahmad.DAO;

import java.util.List;

import com.ahmad.model.Friend;

public interface FriendDAO {

	List<Friend> getFriends(String userId, char status);

	void sendRequest();

	void acceptRequest();

	void rejectRequest();
}
