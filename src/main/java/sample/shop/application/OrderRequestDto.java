package sample.shop.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import sample.shop.domain.Delivery;
import sample.shop.domain.Member;
import sample.shop.domain.OrderMenu;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDto {
    private Member member;
    private List<OrderMenu> orderMenus;
    private Delivery delivery;
}
