# 04. SQL 활용

## SECTION 086. 프로시저(Procedure)

### 1. 프로시저의 개요
- **프로시저(Procedure)** - 절차형 SQL을 활용하여 특정 기능을 수행하는 **일련의 연속적인 SQL 작업**
- 호출을 통해 실행되며, 미리 작성하여 **데이터베이스에 저장**해 두고 여러 프로그램에서 호출하여 사용 가능
- 데이터베이스에 저장되어 수행되므로 **Stored Procedure(저장 프로시저)**라고도 함
- 시스템의 **일일 마감 작업 / 일괄(Batch) 작업** 등에 주로 사용

> ⭐ `프로시저 = SQL 작업을 미리 저장 → 호출해서 실행`  
> ⭐ `일일 마감 / Batch 작업에 주로 사용`

### 2. 프로시저의 구성

| 구성 요소 | 핵심 |
|---|---|
| **DECLARE** | 프로시저의 명칭, 변수, 인수, 데이터 타입 등을 정의하는 선언부 |
| **BEGIN / END** | 프로시저의 시작과 종료 |
| **CONTROL** | 조건문·반복문 등을 이용해 순차적으로 처리 |
| **SQL** | DML·DCL 등을 이용한 조회·추가·수정·삭제 작업 |
| **EXCEPTION** | 실행 중 예외 발생 시 처리 방법 정의 |
| **TRANSACTION** | 수행한 작업을 DB에 적용할지 취소할지 결정 |

> ⭐ `프로시저 구성 = DECLARE → BEGIN → CONTROL/SQL → EXCEPTION/TRANSACTION → END`

### 3. 프로시저 생성
- 프로시저 생성 → **`CREATE PROCEDURE`**
- 같은 이름의 프로시저가 이미 있을 때 대체하려면 → **`OR REPLACE`**

```sql
CREATE [OR REPLACE] PROCEDURE 프로시저명(파라미터)
[지역변수 선언]
BEGIN
    프로시저 BODY;
END;
```

#### 주요 요소
- **OR REPLACE**
  - 선택적(Optional)
  - 동일한 이름의 프로시저가 이미 존재하면 기존 프로시저를 대체

- **프로시저명**
  - 생성할 프로시저의 이름

- **파라미터**
  - **IN** - 호출 프로그램이 프로시저에게 값을 전달
  - **OUT** - 프로시저가 호출 프로그램에게 값을 반환
  - **INOUT** - 호출 프로그램이 값을 전달하고, 프로시저 실행 후 변경된 값을 다시 반환
  - **매개변수명** - 전달받을 변수 이름
  - **자료형** - 변수의 데이터 타입

- **프로시저 BODY**
  - 프로시저의 실제 코드를 작성하는 부분
  - `BEGIN`으로 시작하고 `END`로 끝남
  - `BEGIN ~ END` 사이에는 **적어도 하나의 SQL문**이 있어야 함

> ⭐ `IN = 입력 / OUT = 출력 / INOUT = 입력 + 출력`  
> ⭐ `OR REPLACE = 기존 프로시저 대체`

### 4. 프로시저 실행
- 프로시저 실행 → **`EXECUTE` / `EXEC` / `CALL`**

```sql
EXECUTE 프로시저명;
EXEC 프로시저명;
CALL 프로시저명;
```

#### 인수가 있는 경우
```sql
EXECUTE 프로시저명(인수);
```

> ⭐ `프로시저 실행 = EXECUTE / EXEC / CALL`

### 5. 프로시저 제거
- 프로시저 제거 → **`DROP PROCEDURE`**

```sql
DROP PROCEDURE 프로시저명;
```

> ⭐ `생성 CREATE PROCEDURE → 실행 EXECUTE·EXEC·CALL → 삭제 DROP PROCEDURE`

---

## SECTION 087. 트리거(Trigger)

### 1. 트리거의 개요
- **트리거(Trigger)** - 데이터베이스에서 데이터의
  - `INSERT`
  - `UPDATE`
  - `DELETE`

  등의 **이벤트(Event)가 발생할 때 관련 작업이 자동으로 수행되는 절차형 SQL**

- 데이터베이스에 저장되어 자동 수행
- 주요 사용 목적
  - **데이터 변경**
  - **무결성(Integrity) 유지**
  - **로그 메시지 출력**
- 트리거는 테이블과 연관되어 자동 수행되므로 잘못 작성하면 데이터에 영향을 줄 수 있음

> ⭐ `트리거 = 이벤트 발생 → 자동 실행`  
> ⭐ 이벤트 핵심 → `INSERT / UPDATE / DELETE`

### 2. 프로시저와 트리거의 핵심 차이
| 프로시저 | 트리거 |
|---|---|
| 사용자가 직접 호출하여 실행 | 특정 이벤트 발생 시 **자동 실행** |
| `EXECUTE / EXEC / CALL` 사용 | 별도 실행 명령 없이 자동 수행 |
| 일괄 처리 등에 활용 | 무결성 유지·로그·자동 처리 등에 활용 |

> ⭐ `프로시저 = 호출` / `트리거 = 자동`

### 3. 트리거의 구성

| 구성 요소 | 핵심 |
|---|---|
| **DECLARE** | 트리거의 명칭, 변수, 상수, 데이터 타입 등을 정의 |
| **EVENT** | 트리거가 실행되는 조건 |
| **BEGIN / END** | 트리거의 시작과 종료 |
| **CONTROL** | 조건문·반복문 등을 이용한 처리 |
| **SQL** | 데이터 조회·추가·수정·삭제 |
| **EXCEPTION** | 실행 중 예외 발생 시 처리 방법 정의 |

> ⭐ 프로시저에는 `TRANSACTION`이 있지만 교재의 **트리거 구성에는 TRANSACTION이 없음**

### 4. 트리거 생성
- 트리거 생성 → **`CREATE TRIGGER`**

```sql
CREATE [OR REPLACE] TRIGGER 트리거명 동작시기 동작
ON 테이블명
[REFERENCING NEW | OLD AS 테이블명]
[FOR EACH ROW]
[WHEN 조건식]
BEGIN
    트리거 BODY;
END;
```

#### OR REPLACE
- 선택적(Optional)
- 동일한 이름의 트리거가 이미 있으면 기존 트리거 대체

#### 동작시기
| 동작시기 | 핵심 |
|---|---|
| **BEFORE** | 테이블이 변경되기 **전** 실행 |
| **AFTER** | 테이블이 변경된 **후** 실행 |

> ⭐ `BEFORE = 전 / AFTER = 후`

#### 동작(Event)
- **INSERT** - 새로운 튜플 삽입 시 실행
- **DELETE** - 기존 튜플 삭제 시 실행
- **UPDATE** - 기존 튜플 수정 시 실행

#### NEW / OLD
- **NEW** - 추가되거나 수정되어 **새롭게 들어올 튜플의 값**
- **OLD** - 수정되거나 삭제되기 **이전 튜플의 값**

> ⭐ `NEW = 새 값 / OLD = 기존 값`

#### FOR EACH ROW
- 각 튜플마다 트리거가 적용되도록 지정

#### WHEN 조건식
- 선택적(Optional)
- 트리거를 적용할 튜플의 조건 지정

#### 트리거 BODY
- 트리거의 실제 코드를 작성
- `BEGIN`으로 시작하고 `END`로 종료
- `BEGIN ~ END` 사이에는 적어도 하나 이상의 SQL문이 필요

### 5. 트리거 생성 형태 예시

```sql
CREATE TRIGGER 트리거명 BEFORE INSERT
ON 테이블명
REFERENCING NEW AS new_table
FOR EACH ROW
WHEN (new_table.속성 IS NULL)
BEGIN
    new_table.속성 := '값';
END;
```

> ⭐ 문제에서는 `BEFORE/AFTER`, `INSERT/UPDATE/DELETE`, `NEW/OLD`를 묶어서 자주 확인

### 6. 트리거 제거
- 트리거 제거 → **`DROP TRIGGER`**

```sql
DROP TRIGGER 트리거명;
```

> ⭐ `CREATE TRIGGER → 이벤트 발생 시 자동 실행 → DROP TRIGGER`

---

## SECTION 088. 사용자 정의 함수

### 1. 사용자 정의 함수의 개요
- **사용자 정의 함수(User Defined Function)** - 프로시저와 유사하게 SQL을 이용하여 일련의 작업을 연속적으로 처리하고, 종료 시 처리 결과를 **단일 값으로 반환하는 절차형 SQL**
- 프로시저와 달리 **RETURN을 통해 값을 반환**
- 출력 파라미터가 없음
- 프로시저를 호출하여 사용할 수 없음
- 함수 내부에서는 교재 기준 **SELECT를 통한 조회 작업**을 수행
- `SUM()`, `AVG()` 등의 내장 함수처럼 DML에서 반환값을 활용하는 용도로 사용

> ⭐ `사용자 정의 함수 = 프로시저와 유사 + RETURN 1개`  
> ⭐ `프로시저는 직접 실행 / 함수는 DML 속에서 값으로 사용`

### 2. 프로시저 vs 사용자 정의 함수

| 구분 | 프로시저 | 사용자 정의 함수 |
|---|---|---|
| **반환값** | 없거나 **1개 이상 가능** | **1개** |
| **파라미터** | 입력·출력 가능 | **입력만 가능** |
| **사용 가능 명령문** | DML, DCL | SELECT |
| **호출** | 프로시저·사용자 정의 함수 활용 가능 | 사용자 정의 함수 활용 |
| **사용 방법** | 실행문으로 사용 | **DML에 포함하여 사용** |

> ⭐ `프로시저 = OUT 가능 / 함수 = RETURN 1개`  
> ⭐ `함수는 실행 명령문보다 DML 안에서 사용`

### 3. 사용자 정의 함수의 구성

| 구성 요소 | 핵심 |
|---|---|
| **DECLARE** | 함수명, 변수, 인수, 데이터 타입 등을 정의 |
| **BEGIN / END** | 함수의 시작과 종료 |
| **CONTROL** | 조건문·반복문 처리 |
| **SQL** | `SELECT`를 이용한 데이터 조회 |
| **EXCEPTION** | 예외 처리 |
| **RETURN** | 호출 프로그램에 반환할 값 또는 변수 지정 |

> ⭐ 프로시저 구성과 유사하지만 **사용자 정의 함수에는 RETURN이 추가**

### 4. 사용자 정의 함수 생성
- 사용자 정의 함수 생성 → **`CREATE FUNCTION`**
- 동일한 이름의 함수가 있을 때 대체 → **`OR REPLACE`**

```sql
CREATE [OR REPLACE] FUNCTION 사용자정의함수명(파라미터)
[지역변수 선언]
BEGIN
    사용자 정의 함수 BODY;
    RETURN 반환값;
END;
```

교재 예제에서는 반환 자료형을 지정하는 형태도 사용한다.

```sql
CREATE FUNCTION 함수명(파라미터)
RETURN 반환자료형
IS
BEGIN
    ...
    RETURN 반환값;
END;
```

#### 주요 요소
- **IN**
  - 호출 프로그램이 사용자 정의 함수에 값을 전달할 때 지정
- **매개변수명**
  - 호출 프로그램에서 전달되는 값을 저장할 변수 이름
- **자료형**
  - 변수의 데이터 타입
- **RETURN 반환값**
  - 호출 프로그램으로 돌려줄 값 또는 변수

> ⭐ 사용자 정의 함수 생성에서 `RETURN 자료형`과 본문의 `RETURN 반환값`을 구분

### 5. 사용자 정의 함수 실행
- 사용자 정의 함수는 **DML에서 속성값이 놓일 위치에 함수명을 사용**하여 실행

#### SELECT
```sql
SELECT 사용자정의함수명 FROM 테이블명;
```

#### INSERT
```sql
INSERT INTO 테이블명(속성명)
VALUES (사용자정의함수명);
```

#### DELETE
```sql
DELETE FROM 테이블명
WHERE 속성명 = 사용자정의함수명;
```

#### UPDATE
```sql
UPDATE 테이블명
SET 속성명 = 사용자정의함수명;
```

> ⭐ 함수 자체가 테이블을 `INSERT/DELETE/UPDATE`하는 것이 아니라, **함수의 반환값을 DML의 값으로 활용**

### 6. 사용자 정의 함수 제거
- 사용자 정의 함수 제거 → **`DROP FUNCTION`**

```sql
DROP FUNCTION 사용자정의함수명;
```

> ⭐ `CREATE FUNCTION → DML 안에서 사용 → DROP FUNCTION`

---

## SECTION 089. DBMS 접속 기술

### 1. DBMS 접속의 개요
- **DBMS 접속** - 사용자가 데이터를 사용하기 위해 **응용 시스템을 이용하여 DBMS에 접근하는 것**
- 응용 시스템은 사용자가 보낸 데이터를 바탕으로 SQL을 실행하고 DBMS에서 전달받은 결과를 사용자에게 전달
- 인터넷을 통해 공동으로 사용하는 웹 응용 프로그램은 웹 응용 시스템을 통해 DBMS에 접근
- 웹 응용 시스템은
  - **웹 서버(Web Server)**
  - **WAS(Web Application Server)**

  로 구성되며 서버 내부에 DBMS를 설치하거나 별도의 DBMS 서버와 연결할 수 있음

### 2. 웹 응용 시스템의 구조

```text
사용자 → 웹 서버 → WAS → DBMS
```

- 사용자는 웹 서버에 접속하여 데이터를 요청
- 웹 서버는 사용자의 서비스 요청을 처리
- WAS는 요청을 전달받아 처리하고 DBMS와 데이터를 주고받음
- DBMS의 처리 결과는 다시 WAS와 웹 서버를 거쳐 사용자에게 전달

> ⭐ `사용자 → Web Server → WAS → DBMS`

### 3. DBMS 접속 기술
- DBMS 접속 기술 - DBMS에 접근하기 위해 사용하는 **API 또는 API 사용을 편리하게 해주는 프레임워크**

#### JDBC(Java DataBase Connectivity)
- Java 언어로 다양한 데이터베이스에 접속하고 SQL문을 수행할 때 사용하는 **표준 API**
- Java SE에 포함
- 관련 클래스 → `java.sql`, `javax.sql`
- 접속하려는 DBMS에 맞는 **드라이버(Driver)** 필요

> ⭐ `JDBC = Java + DB 연결 표준 API + DBMS Driver 필요`

#### ODBC(Open DataBase Connectivity)
- 데이터베이스에 접근하기 위한 **표준 개방형 API**
- 개발 언어와 관계없이 사용 가능
- Microsoft에서 발표
- 여러 종류의 데이터베이스에 접근 가능
- DBMS별 드라이버가 필요하지만 **ODBC 문장을 사용하여 DBMS 종류에 관계없이 접근 가능**

> ⭐ `ODBC = 언어 독립적 + 표준 개방형 DB 접속 API`

#### MyBatis
- JDBC 코드를 간소화하여 사용할 수 있는 **SQL Mapping 기반 오픈 소스 접속 프레임워크**
- JDBC로 직접 DB에 접속할 때 필요한 복잡한 코드를 간소화
- SQL 문장을 분리하여 **XML 파일** 등에 작성하고 Mapping하여 실행
- JDBC를 사용할 수 있는 DBMS에 적용 가능
- SQL·질의 처리가 중요한 환경에 적합

> ⭐ `MyBatis = JDBC 간소화 + SQL Mapping + XML`

### 4. JDBC / ODBC / MyBatis 비교

| 기술 | 핵심 키워드 |
|---|---|
| **JDBC** | Java / 표준 API / `java.sql`, `javax.sql` / Driver |
| **ODBC** | 언어 독립 / 표준 개방형 API / Driver |
| **MyBatis** | JDBC 간소화 / SQL Mapping / XML / 오픈 소스 프레임워크 |

> ⭐ `JDBC = Java`  
> ⭐ `ODBC = 언어 독립`  
> ⭐ `MyBatis = SQL Mapping`

### 5. 동적 SQL(Dynamic SQL)
- **동적 SQL** - 개발 언어에 삽입되는 SQL 코드 일부 또는 전체를 **문자열 변수에 넣어 처리**하는 방식
- 조건에 따라 SQL 구문을 동적으로 변경하여 처리 가능
- 하나의 소스 코드에서 상황에 따라 서로 다른 SQL문 실행 가능
- 입력값이 없는 경우에도 상황에 맞춰 SQL을 구성할 수 있어 불필요한 조건 처리를 줄일 수 있음
- 프로그램 실행 중 SQL이 변경될 수 있어
  - 프로그래밍하기 어렵고
  - 오류를 찾기 어려움
- 정적 SQL보다 실행 속도는 느릴 수 있지만 **다양한 조건을 적용하는 유연한 개발**이 가능

> ⭐ `동적 SQL = SQL을 문자열 변수에 저장 → 실행 중 SQL 변경 가능`

### 6. 정적 SQL vs 동적 SQL

| 구분 | 정적 SQL(Static SQL) | 동적 SQL(Dynamic SQL) |
|---|---|---|
| **SQL 처리** | Cursor 등을 이용한 **고정된 SQL** | 문자열 변수에 담아 **동적으로 처리** |
| **SQL 변경** | 실행 전에 SQL이 정해짐 | 실행 중 조건에 따라 변경 가능 |
| **실행 속도** | **빠름** | **느림** |
| **사전 검사** | **가능** | **불가능** |
| **특징** | 구조가 고정적 | 다양한 조건에 대응하기 유연 |

> ⭐ `정적 = 빠름 / 사전 검사 O`  
> ⭐ `동적 = 유연 / 느림 / 사전 검사 X`