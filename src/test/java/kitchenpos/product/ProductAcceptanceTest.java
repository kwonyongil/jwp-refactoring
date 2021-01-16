package kitchenpos.product;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("상품 관련 기능")
class ProductAcceptanceTest extends ProductAcceptanceTestSupport {
    @DisplayName("상품 생성")
    @Test
    void createProduct() {
        // Given
        Map<String, Object> params = new HashMap<>();
        params.put("name", "명동칼국수");
        params.put("price", 17_000);
        // When
        ExtractableResponse<Response> createResponse = 상품_생성_요청(params);
        // Then
        상품_생성_완료(createResponse);
    }

    @DisplayName("모든 상품 조회")
    @Test
    void findProducts() {
        // When
        ExtractableResponse<Response> findResponse = 모든_상품_목록_조회_요청();
        // Then
        상품_목록_응답(findResponse);
    }
}