package com.lazovskyi.recordservice.api;

import com.lazovskyi.recordservice.dto.RecordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
public interface RecordApi {

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> addRecord(@RequestBody RecordDto recordDto);

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> getAllRecord();
}
