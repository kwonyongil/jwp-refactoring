package kitchenpos.utils;

import java.math.BigDecimal;
import java.util.List;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.dto.MenuGroupRequest;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.domain.OrderTable;
import kitchenpos.order.domain.TableGroup;
import kitchenpos.order.dto.OrderLineItemRequest;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.order.dto.OrderTableRequest;
import kitchenpos.order.dto.TableGroupRequest;
import kitchenpos.product.domain.Product;
import kitchenpos.product.dto.ProductRequest;

public class DomainFixtureFactory {
    public static Product createProduct(Long id, String name, BigDecimal price) {
        return Product.from(id, name, price);
    }

    public static ProductRequest createProductRequest(String name, BigDecimal price) {
        return new ProductRequest(name, price);
    }

    public static MenuGroup createMenuGroup(Long id, String name) {
        return MenuGroup.from(id, name);
    }

    public static MenuGroupRequest createMenuGroupRequest(String name) {
        return new MenuGroupRequest(name);
    }

    public static Menu createMenu(Long id, String name, BigDecimal price, MenuGroup menuGroup) {
        return Menu.from(id, name, price, menuGroup);
    }

    public static MenuRequest createMenuRequest(String name, BigDecimal price, long menuGroupId,
                                  List<MenuProductRequest> menuProducts) {
        return new MenuRequest(name, price, menuGroupId, menuProducts);
    }

    public static MenuProduct createMenuProduct(Product product, long quantity) {
        return MenuProduct.from(product, quantity);
    }

    public static OrderTable createOrderTable(Long id, int numberOfGuests, boolean empty) {
        return OrderTable.from(id, numberOfGuests, empty);
    }

    public static OrderTableRequest createOrderTableRequest(int numberOfGuests, boolean empty) {
        return new OrderTableRequest(numberOfGuests, empty);
    }

    public static Order createOrder() {
        return new Order();
    }

    public static OrderRequest createOrderRequest(Long orderTableId, OrderStatus orderStatus, List<OrderLineItemRequest> orderLineItems) {
        return new OrderRequest(orderTableId, orderStatus, orderLineItems);
    }

    public static OrderLineItem createOrderLineItem(Menu menu, long quantity) {
        return OrderLineItem.from(menu, quantity);
    }

    public static OrderLineItemRequest createOrderLineItemRequest(long menuId, long quantity) {
        return new OrderLineItemRequest(menuId, quantity);
    }

    public static TableGroup createTableGroup(Long id, List<OrderTable> orderTables) {
        return TableGroup.from(id, orderTables);
    }

    public static TableGroupRequest createTableGroupRequest(List<Long> orderTables) {
        return new TableGroupRequest(orderTables);
    }
}
