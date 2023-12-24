package com.lazovskyi.recordservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class RecordDto {

    private String table;

    private List<Map<String, String>> records;
}
