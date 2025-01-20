package com.example.translationapp;

import com.example.translationapp.model.entity.TranslationEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class TranslationappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslationappApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<List<TranslationEntity>> typeReference = new TypeReference<List<TranslationEntity>>() {};
//			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("seedData/translation.json");
//
//			if (inputStream == null) {
//				System.err.println("File not found: translations.json");
//				return;
//			}
//			try {
//				List<TranslationEntity> translations = mapper.readValue(inputStream, typeReference);
//				System.out.println(translations);
//				System.out.println("Translations loaded successfully!");
//			} catch (IOException e) {
//				System.err.println("Error loading translations: " + e.getMessage());
//			}
//		};
//	}
}
