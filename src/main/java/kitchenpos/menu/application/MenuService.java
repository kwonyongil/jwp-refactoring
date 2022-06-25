package kitchenpos.menu.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.domain.MenuProducts;
import kitchenpos.menu.domain.MenuRepository;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.product.application.ProductService;
import kitchenpos.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuGroupService menuGroupService;
    private final ProductService productService;

    public MenuService(
            final MenuRepository menuRepository,
            final MenuGroupService menuGroupService,
            final ProductService productService
    ) {
        this.menuRepository = menuRepository;
        this.menuGroupService = menuGroupService;
        this.productService = productService;
    }

    @Transactional
    public MenuResponse create(final MenuRequest menuRequest) {
        MenuGroup menuGroup = menuGroupService.findMenuGroup(menuRequest.getMenuGroupId());
        final List<MenuProduct> menuProducts = findMenuProducts(menuRequest.getMenuProducts());
        Menu menu = Menu.from(menuRequest.getName(), menuRequest.getPrice(), menuGroup);
        menu.addMenuProducts(MenuProducts.from(menuProducts));
        return MenuResponse.from(menuRepository.save(menu));
    }

    private List<MenuProduct> findMenuProducts(List<MenuProductRequest> menuProductRequests) {
        List<MenuProduct> menuProducts = new ArrayList<>();
        for (MenuProductRequest menuProductRequest : menuProductRequests) {
            final Product product = productService.findProduct(menuProductRequest.getProductId());
            menuProducts.add(MenuProduct.from(product, menuProductRequest.getQuantity()));
        }
        return menuProducts;
    }

    public List<MenuResponse> list() {
        final List<Menu> menus = menuRepository.findAll();
        return menus.stream()
                .map(MenuResponse::from)
                .collect(Collectors.toList());
    }
}
