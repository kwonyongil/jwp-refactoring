package kitchenpos.table.domain;

import kitchenpos.common.exception.InvalidOrderTableException;
import kitchenpos.common.exception.InvalidTableGroupSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static kitchenpos.table.domain.OrderTableTest.빈자리;
import static kitchenpos.table.domain.OrderTableTest.이인석;
import static kitchenpos.table.domain.TableGroupTest.테이블그룹;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderTablesTest {

    private OrderTable 임시자리;

    @BeforeEach
    void setUp() {
        임시자리 = OrderTable.ofEmptyTable();
    }

    @Test
    @DisplayName("주문 테이블 생성")
    public void create() {
        // given

        // when
        OrderTables orderTables = new OrderTables(Arrays.asList(임시자리, 임시자리));
        // then
        assertThat(orderTables).isEqualTo(new OrderTables(Arrays.asList(임시자리, 임시자리)));
    }

    @DisplayName("테이블 그룹을 등록할 경우")
    static class TableGroupCreateTest {
        @Test
        @DisplayName("주문 테이블의 개수가 2개 이상이어야 한다.")
        public void invalidTableSizeExceptionTest() {
            // given
            // when
            // then
            assertThatThrownBy(() -> new OrderTables(Collections.singletonList(빈자리)))
                    .isInstanceOf(InvalidTableGroupSizeException.class);
        }

        @Test
        @DisplayName("테이블을 비어있어야 한다.")
        public void notEmptyTableTest() {
            // given
            // when
            // then
            assertThatThrownBy(() -> new OrderTables(Arrays.asList(이인석, 빈자리)))
                    .isInstanceOf(InvalidOrderTableException.class);
        }

        @Test
        @DisplayName("테이블 그룹이 지정되어있으면 안된다.")
        public void nonNullTableGroupTest() {
            // given
            // when
            // then
            assertThatThrownBy(() -> new OrderTables(Arrays.asList(new OrderTable(테이블그룹, 2, true), 빈자리)))
                    .isInstanceOf(InvalidOrderTableException.class);
        }
    }

    @Test
    @DisplayName("테이블 그룹 지정")
    public void initTableGroupTest() {
        // given
        OrderTables orderTables = new OrderTables(Arrays.asList(임시자리, 임시자리));
        // when
        orderTables.initTableGroup(테이블그룹);
        // then
        assertThat(orderTables.getOrderTables()).contains(new OrderTable(테이블그룹, 0, false));
    }

    @Test
    @DisplayName("단체 지정 해제")
    public void ungroupTest() {
        // given
        OrderTable 임시자리 = new OrderTable(0, true);
        OrderTables orderTables = new OrderTables(Arrays.asList(임시자리, 임시자리));
        orderTables.initTableGroup(테이블그룹);
        // when
        orderTables.ungroup();
        // then
        for (OrderTable orderTable : orderTables.getOrderTables()) {
            assertThat(orderTable.getTableGroup()).isNull();
        }
    }

}