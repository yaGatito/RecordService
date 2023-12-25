package com.lazovskyi.recordservice.service.impl;

import com.lazovskyi.recordservice.dto.RecordDto;
import com.lazovskyi.recordservice.repository.RecordRepository;
import com.lazovskyi.recordservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private static final String RECORD_TABLE_PREFIX = "records";

    private final RecordRepository recordRepository;
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;

    @Transactional
    @Override
    public void addRecord(RecordDto recordDto) {
        // Table section
        String table = prefixTableFolder(recordDto.getTable());
        createTableIfNotExists(table);

        // Columns section
        Set<String> uniqueColumns = recordDto.getRecords().stream().flatMap(r -> r.keySet().stream()).collect(Collectors.toSet());
        addColumnsIfNotExists(table, uniqueColumns);

        // Record section
        recordDto.getRecords().forEach((record) -> addRecordInternal(table, record));
    }

    /**
     * Prefix used to separate table so the name of table should be prefixed
     * @param rawTableName used in DTO layer
     * @return ready for performing operations table name
     */
    private String prefixTableFolder(String rawTableName) {
        return RECORD_TABLE_PREFIX + "_" + rawTableName;
    }

    @Override
    @Transactional
    public List<Map<String, String>> getAllRecords(String rawTableName) {
        String table = prefixTableFolder(rawTableName);

        // Getting all columns (except 'id') for specific table
        List<String> columns = getAllColumns(table);
        String idColumnName = "id";
        columns.remove(idColumnName);

        // Constructing query
        String columnList = String.join(",", columns);
        String nativeSql = "SELECT " + columnList + " FROM " + table;

        // Send query
        List<Object[]> results = entityManager.createNativeQuery(nativeSql).getResultList();

        // Processing query
        List<Map<String, String>> records = new ArrayList<>();
        for (Object[] row : results) {
            // Process each row of data
            Map<String, String> entity = new HashMap<>();
            // Set to 1 to skip "id" property
            int startIndex = 0;
            for (int i = startIndex; i < columns.size(); i++) {
                // Process each column of row
                entity.put(columns.get(i), row[i] == null ? "" : (String) row[i]);
            }
            records.add(entity);
        }

        return records;
    }

    @Transactional
    public List<String> getAllColumns(String table) {
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
        return jdbcTemplate.queryForList(sql, String.class, table);
    }

    @Transactional
    public void addRecordInternal(String table, Map<String, String> properties) {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(table);

        String columns = String.join(", ", properties.keySet());
        String values = String.join(", ", properties.values());

        String jpql = String.valueOf(query.append(columns).append(" VALUES ").append(values));

        entityManager.createNativeQuery(jpql).executeUpdate();
    }

    @Transactional
    public void addColumnsIfNotExists(String table, Set<String> columns) {
        columns.forEach(column -> addColumnIfNotExists(table, column));
    }

    @Transactional
    public void addColumnIfNotExists(String table, String column) {
        if (!existsColumn(table, column))
        {
            addColumn(table, column);
        }
    }

    @Transactional
    public boolean existsColumn(String table, String column) {
        String jpql = "SELECT COUNT(*) FROM information_schema.columns WHERE table_name = ? AND column_name = ?";
        Query query = entityManager.createNativeQuery(jpql);
        query.setParameter(1, table);
        query.setParameter(2, column);
        return ((Number) query.getSingleResult()).intValue() > 0;
    }

    @Modifying
    public void createTableIfNotExists(String table) {
        String jpql = String.format("CREATE TABLE IF NOT EXISTS %s (id SERIAL PRIMARY KEY)", table);
        entityManager.createNativeQuery(jpql).executeUpdate();
    }

    @Modifying
    public void addColumn(String table, String column) {
        String jpql = String.format("ALTER TABLE %s ADD COLUMN %s VARCHAR(255) DEFAULT NULL", table, column);
        entityManager.createNativeQuery(jpql).executeUpdate();
    }
}
