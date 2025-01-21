package com.example.translationapp.mapper;

import com.example.translationapp.dto.TranslatorResponseDto;
import com.example.translationapp.dto.TranslatorUpdateDto;
import com.example.translationapp.model.entity.TranslationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TranslatorMapper {
    TranslatorResponseDto toTranslatorResponse(TranslationEntity translationEntity);

    void updateTranslatorEntityFromTranslatorUpdate(TranslatorUpdateDto translatorUpdateDto, @MappingTarget TranslationEntity translationEntity);
}
