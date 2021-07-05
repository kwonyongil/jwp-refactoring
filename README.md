# 키친포스

## 할일
- [X] 패키지 코드를 보고 요구사항 README.md 에 작성
- [X] 요구사항을 토대로 테스트 코드를 작성
    - [X] BusinessObject에 대한 테스트 코드 작성
    - [X] @SpringBootTest를 이용한 통합 테스트 또는 @ExtendWith(MockitoExtension.class)를 이용한 단위 테스트 코드를 작성
    - [ ] Controller에 대한 테스트 코드 작성을 권장하지만 필수는 아니다(필요시 작성)
    
## 요구 사항
### 메뉴그룹 관련
* 메뉴 그룹을 생성할수 있다
* 메뉴 그룹 목록을 조회할 수 있다 
### 메뉴 관련
* 메뉴를 등록할 수 있다
  * 가격이 0원 이상이어야 한다
  * 메뉴 그룹이 지정되어 있어야 한다
  * 등록된 상품 메뉴만 등록 가능하다
  * 메뉴 가격이 상품 가격의 총 합보다 클 수 없다
* 메뉴 목록을 조회할 수 있다
### 주문 관련
* 주문을 등록할 수 있다
  * 주문 항목이 존재해야 한다
  * 등록된 메뉴만 주문 할 수 있다
  * 주문 테이블이 존재해야 한다
  * 주문 테이블은 비어있지 않아야 한다
* 주문을 조회할 수 있다  
* 주문 상태를 변경할 수 있다
  * 존재하는 주문이여야 한다
  * 완료된 주문은 변경할 수 없다
### 상품 관련
* 상품을 등록할 수 있다
  * 가격이 없거나 마이너스인 상품은 등록할 수 없다
* 상품 목록을 조회할 수 있다
### 단체 지정 관련
* 단체 지정을 할 수 있다
  * 2개 이상의 주문 테이블을 단체 지정 한다
  * 주문 테이블은 모두 등록 되어 있어야 한다
  * 빈 테이블이어야만 한다
  * 단체 지정되지 않은 테이블이어야 한다
* 테이블 그룹을 해제할 수 있다
  * 주문 테이블의 주문 상태가 `조리`, `식사` 이면 해제할 수 없다
### 주문 테이블 관련
* 주문 테이블을 생성할 수 있다
* 주문 테이블 목록을 조회할 수 있다  
* 주문 테이블 빈 테이블 설정
  * 존재하지 않는 주문 테이블은 비울 수 없다
  * 그룹 설정 된 테이블은 비울 수 없다
  * 주문 상태가 `조리`, `식사` 이면 비울 수 없다
* 방문한 고객 수 변경
  * 변경할 손님 수는 0보다 커야 한다
  * 등록된 주문 테이블만 변경할 수 있다
  * 빈 주문 테이블이어선 안된다
  

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
