package com.example.translationapp.translators;

import com.example.translationapp.dto.TranslatorResponseDto;
import com.example.translationapp.dto.TranslatorUpdateDto;
import com.example.translationapp.model.entity.TranslationEntity;

import java.util.ArrayList;
import java.util.List;

public class TranslatorTestApi {
    public static TranslationEntity makeTranslationEntityForSaving(final TranslatorUpdateDto updateDto) {
        return TranslationEntity.builder()
                .id(1)
                .wordFirstLang(updateDto.getWordFirstLang())
                .sentenceFirstLang(updateDto.getSentenceFirstLang())
                .wordSecondLang(updateDto.getWordSecondLang())
                .sentenceSecondLang(updateDto.getSentenceSecondLang())
                .build();
    }
    public static TranslationEntity makeTranslationEntityResponse(final TranslatorUpdateDto updateDto) {
        return TranslationEntity.builder()
                .id(1)
                .wordFirstLang(updateDto.getWordFirstLang())
                .sentenceFirstLang(updateDto.getSentenceFirstLang())
                .wordSecondLang(updateDto.getWordSecondLang())
                .sentenceSecondLang(updateDto.getSentenceSecondLang())
                .build();
    }
    public static TranslatorUpdateDto prepareTranslationUpdateRequest() {
        return TranslatorUpdateDto.builder()
                .wordFirstLang("Hello")
                .sentenceFirstLang("Hello, how are you?")
                .wordSecondLang("Hola")
                .sentenceSecondLang("Hola, ¿cómo estás?")
                .build();
    }
    public static List<TranslationEntity> createMockTranslationEntityList() {
        List<TranslationEntity> translations = new ArrayList<>();

        translations.add(TranslationEntity.builder()
                .id(1)
                .wordFirstLang("Hello")
                .sentenceFirstLang("Hello, how are you?")
                .wordSecondLang("Hola")
                .sentenceSecondLang("Hola, ¿cómo estás?")
                .build());
        translations.add(TranslationEntity.builder()
                .id(2)
                .wordFirstLang("Goodbye")
                .sentenceFirstLang("Goodbye, see you soon!")
                .wordSecondLang("Adiós")
                .sentenceSecondLang("Adiós, ¡hasta pronto!")
                .build());
        translations.add(TranslationEntity.builder()
                .id(3)
                .wordFirstLang("Thank you")
                .sentenceFirstLang("Thank you for your help.")
                .wordSecondLang("Gracias")
                .sentenceSecondLang("Gracias por tu ayuda.")
                .build());

        return translations;
    }
    public static List<TranslatorResponseDto> createMockTranslationResponseList() {
        List<TranslationEntity> translationEntities = createMockTranslationEntityList();

        return translationEntities.stream()
                .map(translation -> TranslatorResponseDto.builder()
                        .id(translation.getId())
                        .wordFirstLang(translation.getWordFirstLang())
                        .sentenceFirstLang(translation.getSentenceFirstLang())
                        .wordSecondLang(translation.getWordSecondLang())
                        .sentenceSecondLang(translation.getSentenceSecondLang())
                        .build())
                .toList();
    }

}
