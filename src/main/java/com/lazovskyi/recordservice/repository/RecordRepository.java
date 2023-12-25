package com.lazovskyi.recordservice.repository;

import com.lazovskyi.recordservice.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
