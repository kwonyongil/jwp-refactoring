package kitchenpos.order.domain.repository;

import kitchenpos.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByOrderTableIdAndOrderStatusIn(Long orderTableId, List<String> orderStatus);

    boolean existsByOrderTableIdInAndOrderStatusIn(List<Long> orderTableId,
        List<String> orderStatus);
}