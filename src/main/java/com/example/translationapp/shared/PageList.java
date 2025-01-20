package com.example.translationapp.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageList<T> {
    private int offset;
    private int limit;
    private long totalRecords;
    private int totalPage;
    private List<T> records;
}
