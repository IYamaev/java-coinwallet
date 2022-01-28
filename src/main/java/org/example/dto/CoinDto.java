package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoinDto {
    private long id;
    private String name;
    private String ticker;
    private long price;
    private long qty;
    private String image;
}
