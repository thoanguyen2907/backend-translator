package com.example.translationapp.mapper;

import com.example.translationapp.dto.TranslatorResponseDto;
import com.example.translationapp.model.entity.TranslationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TranslatorMapper {
    TranslatorResponseDto toTranslatorResponse(TranslationEntity translationEntity);
}
