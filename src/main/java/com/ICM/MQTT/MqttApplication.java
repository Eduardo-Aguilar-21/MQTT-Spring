package com.ICM.MQTT;

import com.ICM.MQTT.MqttSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ICM.MQTT")
public class MqttApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MqttApplication.class, args);
		// Obtén una instancia de MqttSubscriber del contexto de Spring
		MqttSubscriber mqttSubscriber = context.getBean(MqttSubscriber.class);
		// Llama a subscribeToTopic en la instancia
		mqttSubscriber.subscribeToTopic("prueba");
	}
}
