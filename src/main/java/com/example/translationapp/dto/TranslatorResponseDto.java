package com.example.translationapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslatorResponseDto {
    private Integer id;
    private String wordFirstLang;
    private String sentenceFirstLang;
    private String wordSecondLang;
    private String sentenceSecondLang;
}
