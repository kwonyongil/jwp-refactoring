package kitchenpos.tablegroup;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.AcceptanceTest;
import kitchenpos.HttpStatusAssertion;
import org.springframework.http.MediaType;

import java.util.Map;

public class TableGroupAcceptanceTestSupport extends AcceptanceTest {
    public ExtractableResponse<Response> 단체_지정_생성_요청(Map<String, Object> params) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(params)
                .when().post("/api/table-groups")
                .then().log().all()
                .extract();
    }

    public ExtractableResponse<Response> 단체_지정_삭제_요청(ExtractableResponse<Response> createResponse) {
        return RestAssured
                .given().log().all()
                .when().delete(createResponse.header("Location"))
                .then().log().all()
                .extract();
    }

    public void 단체_지정_생성_완료(ExtractableResponse<Response> response) {
        HttpStatusAssertion.CREATED(response);
    }

    public void 단체_지정_삭제_완료(ExtractableResponse<Response> response) {
        HttpStatusAssertion.NO_CONTENT(response);
    }
}