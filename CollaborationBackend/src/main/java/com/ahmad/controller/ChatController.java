package com.ahmad.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ahmad.model.Message;
import com.ahmad.model.OutputMessage;

@Controller

public class ChatController {

	/*
	 * don’t forget that we defined the prefix '/app' in WebSocketConfig so it
	 * will map as '/app/chat'
	 */

	/* also '/topic' in the websocket config */

	@CrossOrigin
	@MessageMapping("/chat")  // url pattern such as request mapping
	@SendTo("/topic/message")  // data will be sent after processing to the url
	public OutputMessage sendMessage(Message message, String userEmail) throws Exception {
		return new OutputMessage(message, new Date(), userEmail);
	}

}
