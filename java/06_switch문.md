## switch문

- if문으로 작성된 코드와 비교 - [소스](src/cond/Switch1.java)
- 비교 연산자의 경우 switch문이 더 간결

[소스](src/cond/Switch2.java)

```text
switch (조건식) {
    case value1:
        //조건식의 결과 값이 value1일 때 실행
        break;
    case value2:
        //조건식의 결과 값이 value2일 때 실행
        break;
    default:
        //조건식의 결과 값이 위의 어떤 값에도 해당하지 않을 때 실행
    }
```
- `if`, `else-if`, `else`구조와 동일
- 참/거짓 결과가 아닌 단순히 값 비교. 값이 같은지 확인하는 연산만 가능(문자도 가능)

#### break문이 없을 경우

[소스](src/cond/Switch3.java)

- `int grade = 20`
- `case2`에서 `break`문이 없기에 중단하지 않고 바로 `case3` 코드 실행
  - `coupon = 3000;` 수행시 `break`문을 만나 `switch`문 밖으로 빠져나감 
- `발급 받은 쿠폰 3000` 출력

#### + 자바 14 새로운 switch문

[소스](src/cond/Switch4.java)

- switch문이 생각보다 깔끔하지 않아 등장
- -> 사용
- 선택된 데이터 반환