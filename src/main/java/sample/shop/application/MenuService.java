package sample.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.shop.domain.Category;
import sample.shop.domain.Item;
import sample.shop.domain.Menu;
import sample.shop.domain.MenuItem;
import sample.shop.repository.CategoryRepository;
import sample.shop.repository.ItemRepository;
import sample.shop.repository.MenuRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final ItemRepository itemRepository;
    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public UUID create(MenuRequestDto request) {
        Menu menu = checkCondition(request);
        menuRepository.save(menu);
        return menu.getId();
    }

    @Transactional
    public void changePrice(UUID menuId, BigDecimal price) {
        Menu menu = getMenu(menuId);
        menu.changePrice(price);
    }

    @Transactional
    public void changeCategory(UUID menuId, Category category) {
        Menu menu = getMenu(menuId);
        menu.changeCategory(category);
    }

    @Transactional
    public void editMenuItem(UUID menuId, MenuItem before, MenuItem after) {
        Menu menu = getMenu(menuId);
        menu.editMenuItem(before, after);
    }

    @Transactional
    public void removeMenuItem(UUID menuId, MenuItem target) {
        Menu menu = getMenu(menuId);
        menu.removeItem(target);
    }

    private Menu checkCondition(MenuRequestDto request) {
        String menuName = request.getName();
        BigDecimal menuPrice = request.getPrice();
        List<MenuRequestDto.itemForm> itemForms = request.getItemForms();
        UUID categoryId = request.getCategoryId();

        if (itemForms.size() < 1) {
            throw new IllegalArgumentException("메뉴를 구성하는 상품의 종류는 반드시 1개 이상이어야 합니다.");
        }


        Category findCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리 입니다."));

        Menu menu = new Menu(menuName, menuPrice, findCategory);

        BigDecimal sum = BigDecimal.ZERO;

        for (MenuRequestDto.itemForm itemForm : itemForms) {
            Item findItem = itemRepository.findById(itemForm.getItemId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

            if (itemForm.getQuantity() < 0) {
                throw new IllegalArgumentException("상품의 개수는 반드시 1개 이상이어야 합니다.");
            }
            sum = sum.add(findItem.getPrice().multiply(BigDecimal.valueOf(itemForm.getQuantity())));
            MenuItem menuItem = MenuItem.createMenuItem(findItem, itemForm.getQuantity());
            menu.addItem(menuItem);
        }

        //메뉴의 가격이 구성된 상품의 가격보다 비싸면 오류발생
        if (sum.compareTo(request.getPrice()) > 0) {
            throw new IllegalArgumentException("메뉴의 가격은 상품의 총합 보다 적어야합니다.");
        }

        return menu;
    }

    private Menu getMenu(UUID menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴 입니다."));
        return menu;
    }



}
