package com.example.translationapp.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslatorUpdateDto {
    private String wordFirstLang;
    private String sentenceFirstLang;
    private String wordSecondLang;
    private String sentenceSecondLang;
}
