package com.example.translationapp.repository;

import com.example.translationapp.model.entity.TranslationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<TranslationEntity, Integer> {
    Page<TranslationEntity> findByWordFirstLangContaining(String keyword, Pageable pageable);
}
