package com.lazovskyi.recordservice.api;

import com.lazovskyi.recordservice.dto.RecordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public interface RecordApi {

    @PostMapping("/products/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> addRecord(@RequestBody RecordDto recordDto);

    @GetMapping("/{rawTableName}/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<RecordDto> getAllRecord(@PathVariable("rawTableName") String rawTableName);
}
