package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleCoinModel {
    private long id;
    private String name;
    private String ticker;
    private long price;
    private long qty;
}
