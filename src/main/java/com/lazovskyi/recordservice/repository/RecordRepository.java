package com.lazovskyi.recordservice.repository;

import com.lazovskyi.recordservice.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(nativeQuery = true, value = "CREATE TABLE IF NOT EXISTS :tableName (id SERIAL PRIMARY KEY);")
    void createTable(@Param("tableName") String tableName);

    @Query(nativeQuery = true, value = "ALTER TABLE :tableName ADD COLUMN :columnName VARCHAR(255);")
    void addColumn(@Param("tableName") String tableName, @Param("columnName") String columnName);
}
