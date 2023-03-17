package com.nhom14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.nhom14.annotation.CrossOriginsList;
import com.nhom14.dto.OrderMessageDTO;
import com.nhom14.dto.SocketMessageDTO;

@Controller
public class WebsocketController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@MessageMapping("/order.register/{orderId}")
	@CrossOriginsList
	public void registerSocket(@DestinationVariable Long orderId, @Payload OrderMessageDTO orderMessageDTO) throws Exception {
		System.out.println("register: "+orderId);
		template.convertAndSend("/order/orderId/"+orderId, orderMessageDTO);
	}
	
	@MessageMapping("/order.send/{orderId}")
	@CrossOriginsList
	public void orderProcess(@DestinationVariable Long orderId, @Payload OrderMessageDTO orderMessageDTO) throws Exception {
		System.out.println("send: "+orderId);
		template.convertAndSend("/order/orderId/"+orderId, orderMessageDTO);
	}
	
}
