package com.lazovskyi.recordservice.controller;

import com.lazovskyi.recordservice.api.RecordApi;
import com.lazovskyi.recordservice.dto.RecordDto;
import com.lazovskyi.recordservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RecordController implements RecordApi {


    private final RecordService recordService;

    @Override
    public ResponseEntity<Void> addRecord(RecordDto recordDto) {
        recordService.addRecord(recordDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RecordDto> getAllRecord(String rawTableName) {
        List<Map<String, String>> allRecords = recordService.getAllRecords(rawTableName);
        RecordDto response = new RecordDto(rawTableName, allRecords);
        return ResponseEntity.ok().body(response);
    }
}
