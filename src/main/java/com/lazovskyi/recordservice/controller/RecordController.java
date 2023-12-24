package com.lazovskyi.recordservice.controller;

import com.lazovskyi.recordservice.api.RecordApi;
import com.lazovskyi.recordservice.dto.RecordDto;
import com.lazovskyi.recordservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> getAllRecord() {
        return ResponseEntity.ok("STUB");
    }
}
