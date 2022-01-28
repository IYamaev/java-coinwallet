package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleRegisterResponseDTO {
    private Sale sale;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Sale {
    private long id;
    private long coinId;
    private String name;
    private String ticker;
    private long price;
    private long qty;
    }
}
