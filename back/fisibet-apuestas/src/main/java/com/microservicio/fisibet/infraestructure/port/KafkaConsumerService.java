package com.microservicio.fisibet.infraestructure.port;

import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService
{
	/*@KafkaListener(topics = "Animal3Topic", groupId = "Group1002",containerFactory = "animalListener")
	public void listen(Animal animal) {
		System.out.println("Received '" + animal +"' from the AnimalTopic." );
		System.out.println("Recibido: "+ animal.getAnimalType() + " " + animal.getPruebaModel().getName());
	}*/
}
