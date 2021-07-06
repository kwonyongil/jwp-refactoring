package kitchenpos.menu.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kitchenpos.common.exception.ErrorResponse;
import kitchenpos.menu.exception.NoMenuProductListException;
import kitchenpos.menu.exception.OverSumPriceException;
import kitchenpos.menugroup.exception.NoSuchMenuGroupException;
import kitchenpos.product.exception.NoProductException;

@RestControllerAdvice
public class MenuRestControllerAdvice {

    @ExceptionHandler(NoSuchMenuGroupException.class)
    public ResponseEntity<ErrorResponse> noSuchMenuGroupException(NoSuchMenuGroupException e) {
        return getBadResponse(e.getMessage());
    }

    @ExceptionHandler(NoMenuProductListException.class)
    public ResponseEntity<ErrorResponse> noMenuProductListException(NoMenuProductListException e) {
        return getBadResponse(e.getMessage());
    }

    @ExceptionHandler(OverSumPriceException.class)
    public ResponseEntity<ErrorResponse> OverSumPriceException(OverSumPriceException e) {
        return getBadResponse(e.getMessage());
    }

    @ExceptionHandler(NoProductException.class)
    public ResponseEntity<ErrorResponse> noProductException(OverSumPriceException e) {
        return getBadResponse(e.getMessage());
    }

    private ResponseEntity<ErrorResponse> getBadResponse(String message) {
        ErrorResponse errResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message);
        return ResponseEntity.badRequest().body(errResponse);
    }

}
