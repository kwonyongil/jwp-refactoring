package kitchenpos.menu.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kitchenpos.domain.Name;
import kitchenpos.domain.Price;
import kitchenpos.menu.domain.Menu;

public class MenuResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductResponse> menuProducts;

    protected MenuResponse() {
    }

    private MenuResponse(Long id, Name name, Price price, Long menuGroupId,
                         List<MenuProductResponse> menuProducts) {
        this.id = id;
        this.name = name.value();
        this.price = price.value();
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
    }

    public static MenuResponse from(Menu menu) {
        return new MenuResponse(menu.id(), menu.name(), menu.price(), menu.menuGroupId(),
                menu.readOnlyMenuProducts()
                        .stream()
                        .map(MenuProductResponse::from)
                        .collect(Collectors.toList())
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductResponse> getMenuProducts() {
        return menuProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuResponse that = (MenuResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
