package com.lazovskyi.recordservice.service;

import com.lazovskyi.recordservice.dto.RecordDto;
import com.lazovskyi.recordservice.model.Record;

import java.util.List;

public interface RecordService {
    void addRecord(RecordDto recordDto);

    List<Record> getAllRecord();
}
