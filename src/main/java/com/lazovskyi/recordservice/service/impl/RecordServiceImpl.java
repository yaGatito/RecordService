package com.lazovskyi.recordservice.service.impl;

import com.lazovskyi.recordservice.dto.RecordDto;
import com.lazovskyi.recordservice.mapper.RecordMapper;
import com.lazovskyi.recordservice.model.Record;
import com.lazovskyi.recordservice.repository.RecordRepository;
import com.lazovskyi.recordservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    @Override
    public void addRecord(RecordDto recordDto) {
        System.out.println(recordDto.toString());
        recordDto.getRecords().forEach((r) ->
                recordRepository.save(Record.builder().records(r).table(recordDto.getTable()).build()));
    }

    @Override
    public List<Record> getAllRecord() {
        return recordRepository.findAll();
    }

    private void alterTable() {
    }
}
