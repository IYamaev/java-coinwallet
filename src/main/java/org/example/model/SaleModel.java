package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleModel {
    private long id;
    private long coinId;
    private String name;
    private String ticker;
    private long price;
    private long qty;
    private String type;
}
