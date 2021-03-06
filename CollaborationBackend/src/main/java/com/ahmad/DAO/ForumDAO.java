package com.ahmad.DAO;

import java.util.List;

import com.ahmad.model.Forum;

public interface ForumDAO {

	void saveOrUpdateForum(Forum forum);

	void deleteForum(String forumId);

	Forum getForum(String forumId);

	List<Forum> listForums();
	
	List<Forum> listForumByCreatedAt(char status);
}
