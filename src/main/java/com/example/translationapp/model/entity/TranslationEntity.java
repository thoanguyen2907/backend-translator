package com.example.translationapp.model.entity;

import com.example.translationapp.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "translates")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranslationEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "word_first_lang", nullable = false)
    private String wordFirstLang;

    @Column(name = "sentence_first_lang", nullable = false)
    private String sentenceFirstLang;

    @Column(name = "word_second_lang", nullable = false)
    private String wordSecondLang;

    @Column(name = "sentence_second_lang", nullable = false)
    private String sentenceSecondLang;
}
