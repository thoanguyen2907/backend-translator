package com.example.translationapp.translators;

import com.example.translationapp.dto.TranslatorResponseDto;
import com.example.translationapp.dto.TranslatorUpdateDto;
import com.example.translationapp.exception.ErrorCode;
import com.example.translationapp.exception.TranslatorRuntimeException;
import com.example.translationapp.mapper.TranslatorMapper;
import com.example.translationapp.model.entity.TranslationEntity;
import com.example.translationapp.repository.TranslatorRepository;
import com.example.translationapp.shared.PageList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static com.example.translationapp.translators.TranslatorTestApi.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class TranslatorDelegateImplTests {
    private static final String API_URL = "/api/v1/translators";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslatorRepository translatorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TranslatorMapper translatorMapper;


    @Test
    public void givenTranslatorUpdate_whenUpdatingTranslator_thenReturnUpdatedTranslator() throws Exception {
        var mockTranslatorId =  1000;
        var translatorRequest = TranslatorUpdateDto.builder()
                .wordFirstLang("Goodbye")
                .sentenceFirstLang("Goodbye, see you soon!")
                .wordSecondLang("Adiós")
                .sentenceSecondLang("Adiós, ¡hasta pronto!")
                .build();
        when(translatorRepository.findById(mockTranslatorId)).thenReturn(Optional.of(makeTranslationEntityForSaving(translatorRequest)));


        var translatorUpdateRequest = TranslatorUpdateDto.builder()
                .wordFirstLang("Goodbye")
                .sentenceFirstLang("Goodbye, see you soon!")
                .wordSecondLang("Adiós")
                .sentenceSecondLang("Adiós, ¡hasta pronto!")
                .build();

        var updateTranslator = makeTranslationEntityForSaving(translatorUpdateRequest);
        updateTranslator.setSentenceSecondLang("Tam biet");

        when(translatorRepository.save(any(TranslationEntity.class))).thenReturn(updateTranslator);

        var expectedTranslatorResponse = translatorMapper.toTranslatorResponse(updateTranslator);

        mockMvc.perform(MockMvcRequestBuilders.patch(API_URL + "/" + mockTranslatorId)
                        .content(objectMapper.writeValueAsString(translatorUpdateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sentenceSecondLang").value(expectedTranslatorResponse.getSentenceSecondLang()));
    }

    @Test
    public void givenInvalidTranslatorId_whenUpdatingProduct_thenThrowError() throws Exception {
        var mockTranslatorId = 10000;

        var translatorRequest = TranslatorUpdateDto.builder()
                .wordFirstLang("Hello")
                .sentenceFirstLang("Hello, how are you?")
                .wordSecondLang("Hola")
                .sentenceSecondLang("Hola, ¿cómo estás?")
                .build();

        when(translatorRepository.findById(mockTranslatorId)).thenThrow(new TranslatorRuntimeException(ErrorCode.ID_NOT_FOUND));

        mockMvc.perform(MockMvcRequestBuilders.patch(API_URL + "/" + mockTranslatorId)
                        .content(objectMapper.writeValueAsString(translatorRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.details.message").value("Could not find the Id"));
    }
    @Test
    public void givenRequestForListTranslator_whenRequestListTranslator_thenReturnsListTranslator() throws Exception {
        int offset = 1;
        int limit = 3;
        String keyword = "";
        var translatorList = createMockTranslationEntityList();
        var translatorResponseList = createMockTranslationResponseList();
        var totalRecords = translatorList.size();
        Pageable pageable = PageRequest.of(offset, limit);
        Page<TranslationEntity> page = new PageImpl<>(translatorList, pageable, totalRecords);

        var translatorPageList = PageList.<TranslatorResponseDto>builder()
                .records(translatorResponseList)
                .limit(pageable.getPageSize())
                .offset(pageable.getPageNumber())
                .totalRecords(totalRecords)
                .totalPage((int) Math.ceil(totalRecords * 1.0 / pageable.getPageSize()))
                .build();

        when(translatorRepository.findAllOrderedById(pageable)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get(API_URL)
                        .param("offset", String.valueOf(offset))
                        .param("limit", String.valueOf(limit))
                        .param("keyword", keyword)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.details.records.length()", is(totalRecords)))
                .andExpect(jsonPath("$.details.records[0].sentenceSecondLang",
                        is(translatorPageList.getRecords().get(0).getSentenceSecondLang())))
                .andExpect(jsonPath("$.details.records[1].sentenceSecondLang",
                        is(translatorPageList.getRecords().get(1).getSentenceSecondLang())));
    }

}
