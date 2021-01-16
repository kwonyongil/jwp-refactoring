package kitchenpos.ordertable.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Arrays;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.domain.Orders;
import kitchenpos.tablegroup.domain.TableGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class OrderTableTest {

	@DisplayName("테이블 상태 변경")
	@Test
	void changeEmpty() {
		//given
		OrderTable orderTable = new OrderTable(false);

		//when
		orderTable.changeEmpty(true);

		//then
		assertThat(orderTable.isEmpty()).isTrue();
	}

	@DisplayName("단체 설정이 되어있는 경우 테이블 상태를 변경할 수 없다.")
	@Test
	void changeEmptyWithGroupTable() {
		//given
		OrderTables orderTables = new OrderTables(
			  Arrays.asList(new OrderTable(false), new OrderTable(false)), 2);
		TableGroup tableGroup = new TableGroup(orderTables);
		ReflectionTestUtils.setField(tableGroup, "id", 1L);
		OrderTable orderTable = new OrderTable(1, false, tableGroup);

		//when, then
		assertThatIllegalArgumentException()
			  .isThrownBy(() -> orderTable.changeEmpty(true))
			  .withMessage("단체 지정된 테이블은 상태를 변경할 수 없습니다.");
	}

	@DisplayName("조리, 식사중인 테이블은 상태를 변경할 수 없다.")
	@Test
	void changeEmptyWithNotCompleteOrder() {
		//given
		Orders orders = new Orders(OrderStatus.MEAL.name());
		OrderTable orderTable = new OrderTable();
		ReflectionTestUtils.setField(orderTable, "orders", Arrays.asList(orders));

		//when, then
		assertThatIllegalArgumentException()
			  .isThrownBy(() -> orderTable.changeEmpty(true))
			  .withMessage("조리, 식사 상태의 테이블은 상태를 변경할 수 없습니다.");
	}

	@DisplayName("테이블의 게스트수를 변경한다.")
	@Test
	void changeNumberOfGuests() {
		//given
		OrderTables orderTables = new OrderTables(
			  Arrays.asList(new OrderTable(false), new OrderTable(false)), 2);
		TableGroup tableGroup = new TableGroup(orderTables);
		ReflectionTestUtils.setField(tableGroup, "id", 1L);
		OrderTable orderTable = new OrderTable(0, false, tableGroup);

		//when
		int newNumberOfGuest = 2;
		orderTable.changeNumberOfGuests(newNumberOfGuest);

		//then
		assertThat(orderTable.getNumberOfGuests()).isEqualTo(newNumberOfGuest);
	}

	@DisplayName("게스트수가 0 미만인 경우 게스트수를 변경할 수 없다.")
	@Test
	void changeNumberOfGuestsWithWrongGuestNumber() {
		//given
		OrderTables orderTables = new OrderTables(
			  Arrays.asList(new OrderTable(false), new OrderTable(false)), 2);
		TableGroup tableGroup = new TableGroup(orderTables);
		ReflectionTestUtils.setField(tableGroup, "id", 1L);
		OrderTable orderTable = new OrderTable(0, false, tableGroup);

		//when, then
		assertThatIllegalArgumentException()
			  .isThrownBy(() -> orderTable.changeNumberOfGuests(-1))
			  .withMessage("게스트 수는 0명 이상이어야 합니다.");
	}

	@DisplayName("테이블이 비어있 경우 게스트수를 변경할 수 없다.")
	@Test
	void changeNumberOfGuestsWithEmptyTable() {
		//given
		OrderTables orderTables = new OrderTables(
			  Arrays.asList(new OrderTable(false), new OrderTable(false)), 2);
		TableGroup tableGroup = new TableGroup(orderTables);
		ReflectionTestUtils.setField(tableGroup, "id", 1L);
		OrderTable orderTable = new OrderTable(0, true, tableGroup);

		//when, then
		assertThatIllegalArgumentException()
			  .isThrownBy(() -> orderTable.changeNumberOfGuests(2))
			  .withMessage("테이블이 비어있습니다.");
	}
}