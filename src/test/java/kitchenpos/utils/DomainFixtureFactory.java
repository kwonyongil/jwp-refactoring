package kitchenpos.utils;

import java.math.BigDecimal;
import java.util.List;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuProducts;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menuGroup.domain.MenuGroup;
import kitchenpos.menuGroup.dto.MenuGroupRequest;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.order.domain.OrderLineItems;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.dto.OrderLineItemRequest;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.orderTable.domain.OrderTable;
import kitchenpos.orderTable.domain.OrderTables;
import kitchenpos.tableGroup.domain.TableGroup;
import kitchenpos.orderTable.dto.OrderTableRequest;
import kitchenpos.tableGroup.dto.TableGroupRequest;
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

    public static Menu createMenu(String name, BigDecimal price, Long menuGroupId, MenuProducts menuProducts) {
        return Menu.from(name, price, menuGroupId, menuProducts);
    }

    public static MenuRequest createMenuRequest(String name, BigDecimal price, long menuGroupId,
                                                List<MenuProductRequest> menuProducts) {
        return new MenuRequest(name, price, menuGroupId, menuProducts);
    }

    public static MenuProduct createMenuProduct(Long productId, long quantity) {
        return MenuProduct.from(productId, quantity);
    }

    public static OrderTable createOrderTable(Long id, int numberOfGuests, boolean empty) {
        return OrderTable.from(id, numberOfGuests, empty);
    }

    public static OrderTableRequest createOrderTableRequest(int numberOfGuests, boolean empty) {
        return new OrderTableRequest(numberOfGuests, empty);
    }

    public static Order createOrder(Long orderTableId, OrderLineItems orderLineItems) {
        return Order.from(orderTableId, orderLineItems);
    }

    public static OrderRequest createOrderRequest(Long orderTableId, OrderStatus orderStatus,
                                                  List<OrderLineItemRequest> orderLineItems) {
        return new OrderRequest(orderTableId, orderStatus, orderLineItems);
    }

    public static OrderLineItem createOrderLineItem(Long menuId, long quantity) {
        return OrderLineItem.from(menuId, quantity);
    }

    public static OrderLineItemRequest createOrderLineItemRequest(long menuId, long quantity) {
        return new OrderLineItemRequest(menuId, quantity);
    }

    public static TableGroup createTableGroup(Long id) {
        return TableGroup.from(id);
    }

    public static TableGroupRequest createTableGroupRequest(List<Long> orderTables) {
        return new TableGroupRequest(orderTables);
    }
}
