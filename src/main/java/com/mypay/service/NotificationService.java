package com.mypay.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mypay.domain.User;
import com.mypay.dto.NotificationDTO;

@Service
public class NotificationService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${mypay.notificationservice.send.url}")
    private String sendNotificationServiceUrl;

    public void send(User recipient, String message) throws Exception {
        NotificationDTO notification = new NotificationDTO(recipient.getEmail(), message);
        ResponseEntity<Map> response = restTemplate.postForEntity(sendNotificationServiceUrl, notification, Map.class);

        HttpStatusCode statusCode = response.getStatusCode();
        String responseMessage = response.getBody().get("message").toString();

        if (statusCode.is2xxSuccessful() && responseMessage == "true") return;

        throw new Exception("Não foi possível enviar a notificação.");
    }
}
