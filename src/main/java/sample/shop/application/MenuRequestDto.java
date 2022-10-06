package sample.shop.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import sample.shop.domain.Category;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MenuRequestDto {
    private String name;
    private BigDecimal price;
    private List<itemForm> itemForms;
    private UUID categoryId;

    @Data
    class itemForm {
        private UUID itemId;
        private Long quantity;
    }
}
