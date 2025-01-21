package com.example.translationapp.repository;

import com.example.translationapp.model.entity.TranslationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<TranslationEntity, Integer> {
    Page<TranslationEntity> findByWordFirstLangContainingIgnoreCase(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM translates t ORDER BY t.id ASC", nativeQuery = true)
    Page<TranslationEntity> findAllOrderedById(Pageable pageable);

}
