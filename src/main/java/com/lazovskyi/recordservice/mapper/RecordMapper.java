package com.lazovskyi.recordservice.mapper;

import com.lazovskyi.recordservice.dto.RecordDto;
import com.lazovskyi.recordservice.model.Record;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecordMapper {

    RecordMapper INSTANCE = Mappers.getMapper(RecordMapper.class);

//    Record mapToEntity(RecordDto recordDto);
//
//    RecordDto mapToDto(Record record);

}
