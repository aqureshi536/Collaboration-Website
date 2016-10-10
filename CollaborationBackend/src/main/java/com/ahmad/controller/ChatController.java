package com.ahmad.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ahmad.model.Message;
import com.ahmad.model.OutputMessage;

@Controller
@RequestMapping("/")
public class ChatController {

	/*
	 * don’t forget that we defined the prefix /app in WebSocketConfig so it
	 * will map as '/app/chat'
	 */

	/* also '/topic' in the websocket config */

	@CrossOrigin
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message, String userEmail) {
		return new OutputMessage(message, new Date(), userEmail);
	}

}
