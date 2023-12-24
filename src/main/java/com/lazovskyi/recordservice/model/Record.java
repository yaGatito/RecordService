package com.lazovskyi.recordservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String table;

    @Transient
    private Map<String, String> records;
}
//"table" : "products",
//"records" : [
//        {
//        "entryDate": "03-01-2023",
//        "itemCode": "11111",
//        "itemName": "Test Inventory 1",
//        "itemQuantity": "20",
//        "status": "Paid"
//        },
//        {
//        "entryDate": "03-01-2023",
//        "itemCode": "11111",
//        "itemName": "Test Inventory 2",
//        "itemQuantity": "20",
//        "status": "Paid"
//        }
//] }
