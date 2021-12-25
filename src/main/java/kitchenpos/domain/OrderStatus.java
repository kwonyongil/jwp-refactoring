package kitchenpos.domain;

import kitchenpos.dto.OrderRequest;

import java.util.Objects;

public enum OrderStatus {
    COOKING, MEAL, COMPLETION;

    public static boolean isCompleted(OrderStatus orderStatus) {
        return OrderStatus.COMPLETION == orderStatus;
    }

    public static boolean isMeal(OrderStatus orderStatus) {
        return OrderStatus.MEAL == orderStatus;
    }

    public static boolean isCooking(OrderStatus orderStatus) {
        return OrderStatus.COOKING == orderStatus;
    }
}
