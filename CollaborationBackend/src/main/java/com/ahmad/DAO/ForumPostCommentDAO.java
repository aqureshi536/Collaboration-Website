package com.ahmad.DAO;

import java.util.List;

import com.ahmad.model.ForumPostComment;

public interface ForumPostCommentDAO {

	void saveOrUpdateForumPostComment(ForumPostComment forumPostComment);

	void deleteForumPostComment(String forumPostCommentId);

	ForumPostComment getForumPostComment(String forumPostCommentId);

	List<ForumPostComment> listForumPostComments();
}
