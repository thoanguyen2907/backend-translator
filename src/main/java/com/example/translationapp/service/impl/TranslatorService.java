package com.example.translationapp.service.impl;


import com.example.translationapp.dto.TranslatorResponseDto;
import com.example.translationapp.dto.TranslatorUpdateDto;
import com.example.translationapp.exception.ErrorCode;
import com.example.translationapp.exception.TranslatorRuntimeException;
import com.example.translationapp.mapper.TranslatorMapper;
import com.example.translationapp.model.entity.TranslationEntity;
import com.example.translationapp.repository.TranslatorRepository;
import com.example.translationapp.service.ITranslatorService;
import com.example.translationapp.shared.PageList;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TranslatorService implements ITranslatorService {

    private final TranslatorRepository translatorRepository;

    private final TranslatorMapper translatorMapper;

    @Override
    public PageList<TranslatorResponseDto> getAllTranslation(String keyword, Pageable pageable) {
        var translationPage = (keyword == null || keyword.isBlank())
                ? translatorRepository.findAllOrderedById(pageable)
                : translatorRepository.findByWordFirstLangContaining(keyword, pageable);

        var translatorResponseList = translationPage.getContent()
                .stream()
                .map(translatorMapper::toTranslatorResponse)
                .toList();
        return buildPaginatingResponse(translatorResponseList, pageable.getPageSize(), pageable.getPageNumber(), translationPage.getTotalElements());
    }

    @Override
    public List<TranslationEntity> save(List<TranslationEntity> translationEntities) {
        return translatorRepository.saveAll(translationEntities);
    }

    @Override
    public TranslatorResponseDto updateTranslator(Integer translatorId, TranslatorUpdateDto translatorUpdateDto) throws TranslatorRuntimeException {
        var foundTranslator = translatorRepository.findById(translatorId).orElse(null);
        if(foundTranslator == null) {
            throw new TranslatorRuntimeException(ErrorCode.ID_NOT_FOUND);
        }
        translatorMapper.updateTranslatorEntityFromTranslatorUpdate(translatorUpdateDto, foundTranslator);
        var updatedTranslator = translatorRepository.save(foundTranslator);
        return translatorMapper.toTranslatorResponse(updatedTranslator);
    }

    private PageList<TranslatorResponseDto> buildPaginatingResponse(final List<TranslatorResponseDto> responses,
                                                                 final int pageSize,
                                                                 final int currentPage,
                                                                 final long total) {
        return PageList.<TranslatorResponseDto>builder()
                .records(responses)
                .limit(pageSize)
                .offset(currentPage)
                .totalRecords(total)
                .totalPage((int) Math.ceil(total * 1.0 / pageSize))
                .build();

    }
}
