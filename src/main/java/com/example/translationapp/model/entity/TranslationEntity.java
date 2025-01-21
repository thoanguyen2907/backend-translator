package com.example.translationapp.model.entity;

import com.example.translationapp.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "translates")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranslationEntity extends BaseEntity {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("wordFirstLang")
    @Column(name = "word_first_lang", nullable = false)
    private String wordFirstLang;

    @JsonProperty("sentenceFirstLang")
    @Column(name = "sentence_first_lang", nullable = false)
    private String sentenceFirstLang;

    @JsonProperty("wordSecondLang")
    @Column(name = "word_second_lang", nullable = false)
    private String wordSecondLang;

    @JsonProperty("sentenceSecondLang")
    @Column(name = "sentence_second_lang", nullable = false)
    private String sentenceSecondLang;
}
