package com.ICM.MQTT;

import com.ICM.MQTT.Models.UsuariosModel;
import com.ICM.MQTT.Repositories.UsuariosRepository;
import com.ICM.MQTT.Services.UsuariosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSubscriber {
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private IMqttClient mqttClient;

    /*
        public void subscribeToTopic(String topic) {
            try {
                mqttClient.subscribe(topic, new IMqttMessageListener() {
                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        String payload = new String(message.getPayload());
                        System.out.println("Mensaje MQTT recibido en el tema " + topic + ": " + payload);
                        // Procesa el mensaje según tus necesidades
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

     */
    public void subscribeToTopic(String topic) {
        try {
            mqttClient.subscribe(topic, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String payload = new String(message.getPayload());
                    System.out.println("Mensaje MQTT recibido en el tema " + topic + ": " + payload);

                    ObjectMapper objectMapper = new ObjectMapper();

                    try {
                        UsuariosModel usuariosModel = objectMapper.readValue(payload, UsuariosModel.class);

                        // Guarda el objeto en la base de datos utilizando el servicio
                        usuariosService.CreateUser(usuariosModel);
                    } catch (JsonProcessingException e) {
                        System.err.println("Error al deserializar el JSON: " + e.getMessage());
                    }
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

