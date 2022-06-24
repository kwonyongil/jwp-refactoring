package kitchenpos.order.ui;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.order.application.TableService;
import kitchenpos.order.domain.OrderTable;
import kitchenpos.order.dto.OrderTableRequest;
import kitchenpos.order.dto.OrderTableResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableRestController {
    private final TableService tableService;

    public TableRestController(final TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/api/tables")
    public ResponseEntity<OrderTableResponse> create(@RequestBody final OrderTableRequest orderTableRequest) {
        final OrderTable created = tableService.create(orderTableRequest.toOrderTable());
        OrderTableResponse orderTableResponse = OrderTableResponse.of(created);
        final URI uri = URI.create("/api/tables/" + created.id());
        return ResponseEntity.created(uri)
                .body(orderTableResponse);
    }

    @GetMapping("/api/tables")
    public ResponseEntity<List<OrderTableResponse>> list() {
        return ResponseEntity.ok()
                .body(tableService.list().stream()
                        .map(OrderTableResponse::of)
                        .collect(Collectors.toList()));
    }

    @PutMapping("/api/tables/{orderTableId}/empty")
    public ResponseEntity<OrderTableResponse> changeEmpty(
            @PathVariable final Long orderTableId,
            @RequestBody final OrderTableRequest orderTableRequest
    ) {
        return ResponseEntity.ok()
                .body(OrderTableResponse.of(
                        tableService.changeEmpty(orderTableId, orderTableRequest.toOrderTable())));
    }

    @PutMapping("/api/tables/{orderTableId}/number-of-guests")
    public ResponseEntity<OrderTableResponse> changeNumberOfGuests(
            @PathVariable final Long orderTableId,
            @RequestBody final OrderTableRequest orderTableRequest
    ) {
        return ResponseEntity.ok()
                .body(OrderTableResponse.of(
                        tableService.changeNumberOfGuests(orderTableId, orderTableRequest.toOrderTable())));
    }
}
