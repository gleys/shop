package sample.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.shop.domain.Category;
import sample.shop.repository.CategoryRepository;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public UUID create(String name) {

        validName(name);
        Category category  = new Category(name);
        categoryRepository.save(category);
        return category.getId();
    }

    @Transactional
    public void setParent(CategoryRequestDto request) {
        Category parent = findCategory(request.getParentId());
        Category child = findCategory(request.getChildId());

        child.setParentCategory(parent);
    }

    @Transactional
    public void addChild(CategoryRequestDto request) {
        Category parent = findCategory(request.getParentId());
        Category child = findCategory(request.getChildId());

        parent.addChildCategory(child);
    }

    public Category findCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리 입니다."));
    }

    public void validName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("카테고리명을 입력하셔야 합니다.");
        }
    }

}
