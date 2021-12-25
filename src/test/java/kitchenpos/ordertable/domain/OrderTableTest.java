package kitchenpos.ordertable.domain;

import static kitchenpos.common.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kitchenpos.ordertable.exception.InvalidOrderTableEmptyException;
import kitchenpos.tablegroup.domain.TableGroup;

class OrderTableTest {

	@DisplayName("주문테이블 비어있음 유무 수정: 단체 지정된 테이블이면 예외발생")
	@Test
	void changeEmptyIfNotTableGroup_having_table_group() {
		final OrderTable 단체주문테이블 = orderTable(1L, null, 2, true);

		tableGroup(2L, Arrays.asList(단체주문테이블,
			orderTable(2L, null, 3, true)));

		assertThatExceptionOfType(InvalidOrderTableEmptyException.class)
			.isThrownBy(() -> 단체주문테이블.changeEmptyIfNotTableGroup(false));
	}

	@DisplayName("주문테이블의 손님 수 수정: 테이블이 비어있으면 예외발생")
	@Test
	void changeNumberOfGuestsIfNotEmpty_empty_order_table() {
		final OrderTable 빈_테이블 = orderTable(1L, null, 0, true);

		assertThatExceptionOfType(IllegalArgumentException.class)
			.isThrownBy(() -> 빈_테이블.changeNumberOfGuestsIfNotEmpty(4));
	}
}