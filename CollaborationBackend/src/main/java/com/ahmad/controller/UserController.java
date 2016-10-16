package com.ahmad.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.UserDetail;
import com.ahmad.utility.FileUpload;
import com.ahmad.viewmodel.ProfileModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;


@RestController
public class UserController {

	@Autowired
	UserDetail userDetail;

	@Autowired
	UserDetailDAO userDetailDAO;

	// @GetMapping("/users/")
	public ResponseEntity<List<UserDetail>> getAllUsers() {
		List<UserDetail> listOfUsers = userDetailDAO.listUserDetails();
		if (listOfUsers == null) {
			return new ResponseEntity<List<UserDetail>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<UserDetail>>(listOfUsers, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserDetail> getUser(@PathVariable("userId") String userId) {
		userDetail = userDetailDAO.getUserDetail(userId);
		if (userDetail == null) {
			return new ResponseEntity<UserDetail>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDetail>(userDetail, HttpStatus.OK);
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<ProfileModel> updateStatus(@PathVariable("userId") String userId,
			@RequestBody ProfileModel profileModel) {
		System.out.println(profileModel.getUserStatus());
		userDetail = userDetailDAO.getUserDetail(userId);
		if (userDetail == null)
			return new ResponseEntity<ProfileModel>(HttpStatus.NOT_FOUND);
		userDetail.setUserStatus(profileModel.getUserStatus());
		userDetailDAO.saveOrUpdateUserDetail(userDetail);
		return new ResponseEntity<ProfileModel>(profileModel, HttpStatus.OK);
	}

	@PostMapping("/user/upload/")
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(value = "userId") String userJson,
			@RequestParam(value = "image") MultipartFile profilePiture) {
		System.out.println(userJson);
		User user = new User();
		JSONObject obj = new JSONObject(userJson);
		//JsonNode jsonNode = convertJsonFormat(obj);
		ObjectMapper mapper = new ObjectMapper().registerModule(new JsonOrgModule());
		user = mapper.convertValue(obj, User.class);
		System.out.println(user.getUserId());

		String path = "E:\\dtWS\\Collaboration\\collaboration\\WebContent\\assets\\images\\users\\";
		FileUpload.uploadImage(path, profilePiture, user.getUserId());
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}

class User {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
