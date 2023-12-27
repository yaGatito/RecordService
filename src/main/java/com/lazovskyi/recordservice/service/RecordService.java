package com.lazovskyi.recordservice.service;

import com.lazovskyi.recordservice.dto.RecordDto;
import com.lazovskyi.recordservice.model.Record;

import java.util.List;
import java.util.Map;

public interface RecordService {
    void addRecord(RecordDto recordDto);

    List<Map<String, String>> getAllRecords(String tableName);
}
