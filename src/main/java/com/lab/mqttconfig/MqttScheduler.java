package com.lab.mqttconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.mqttconfig.MqttGateway;
import com.lab.mqttconfig.dto.AddFaceRequest;
import com.lab.mqttconfig.service.StudentService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MqttScheduler {

    private final MqttGateway mqttGateway;
    private final StudentService studentService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MqttScheduler(MqttGateway mqttGateway, StudentService studentService) {
        this.mqttGateway = mqttGateway;
        this.studentService = studentService;
    }

    public void sendDeleteRequest() {
        List<AddFaceRequest> addFaceRequests = studentService.getAllAddFaceRequests();
        addFaceRequests.forEach(addFaceRequest -> {
            String json = null;
            try {
                json = convertToAddFaceRequestJson(addFaceRequest);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            mqttGateway.sendToMqtt(json, "mqtt/face/1792832");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String convertToAddFaceRequestJson(AddFaceRequest addFaceRequest) throws JsonProcessingException {
        Map<String, Object> message = new HashMap<>();
        message.put("operator", "EditPerson");
        message.put("messageId", "uniqueMessageId12345"); // Consider generating unique IDs dynamically

        Map<String, Object> info = new HashMap<>();
        info.put("customId", addFaceRequest.getCustomId());
        info.put("name", addFaceRequest.getName());
        info.put("gender", addFaceRequest.getGender());
        info.put("tempCardType", addFaceRequest.getTempCardType());
        info.put("personType", addFaceRequest.getPersonType());
        info.put("cardType", addFaceRequest.getCardType());
        info.put("pic", addFaceRequest.getPic());

        message.put("info", info);

        return objectMapper.writeValueAsString(message);
    }

}