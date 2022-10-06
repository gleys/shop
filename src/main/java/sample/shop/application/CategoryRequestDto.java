package sample.shop.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import sample.shop.domain.Category;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CategoryRequestDto {
    private UUID parentId;
    private UUID childId;
}
