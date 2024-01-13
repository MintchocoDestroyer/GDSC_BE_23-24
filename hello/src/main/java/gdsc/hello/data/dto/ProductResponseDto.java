package gdsc.hello.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private Long number;
    private String name;
    private int price;
    private int stock;
}
