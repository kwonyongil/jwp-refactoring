package kitchenpos.tableGroup.dto;


import java.time.LocalDateTime;
import kitchenpos.tableGroup.domain.TableGroup;

public class TableGroupResponse {
    private final Long id;
    private final LocalDateTime createdDate;

    private TableGroupResponse(Long id, LocalDateTime createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public static TableGroupResponse from(TableGroup tableGroup) {
        return new TableGroupResponse(tableGroup.id(), tableGroup.createdDate());
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
