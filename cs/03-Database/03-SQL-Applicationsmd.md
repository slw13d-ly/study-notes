# 03. SQL 활용

## SECTION 079. SQL의 개념

### 1. SQL(Structured Query Language)의 개요
- **SQL** - 관계형 데이터베이스에서 데이터를 정의·조작·제어하기 위한 **표준 질의 언어**
- 관계형 데이터베이스를 사용할 때 필요한 작업을 명령문 형태로 수행
- SQL의 기능은 크게 **DDL / DML / DCL**로 구분

> ⭐ `SQL = DDL + DML + DCL`

### 2. DDL(Data Definition Language, 데이터 정의어)
- 데이터베이스의 **구조와 객체를 정의·변경·삭제**할 때 사용
- 대상 - `SCHEMA / DOMAIN / TABLE / VIEW / INDEX`

| 명령어 | 기능 |
|---|---|
| **CREATE** | 스키마·도메인·테이블·뷰·인덱스 **정의** |
| **ALTER** | 테이블에 대한 정의 **변경** |
| **DROP** | 스키마·도메인·테이블·뷰·인덱스 **삭제** |

> ⭐ `DDL = CREATE / ALTER / DROP`

### 3. DML(Data Manipulation Language, 데이터 조작어)
- 사용자가 데이터베이스의 **실제 데이터를 검색·삽입·삭제·갱신**하기 위해 사용하는 언어

| 명령어 | 기능 |
|---|---|
| **SELECT** | 튜플 검색 |
| **INSERT** | 새로운 튜플 삽입 |
| **DELETE** | 튜플 삭제 |
| **UPDATE** | 튜플 내용 변경 |

> ⭐ `DML = SELECT / INSERT / DELETE / UPDATE`

### 4. DCL(Data Control Language, 데이터 제어어)
- 데이터의 **보안 / 무결성 / 회복 / 병행 제어** 등을 정의하는 데 사용
- 데이터베이스 관리자가 데이터 관리를 목적으로 사용

| 명령어 | 기능 |
|---|---|
| **COMMIT** | 트랜잭션 결과를 데이터베이스에 반영 |
| **ROLLBACK** | 아직 반영되지 않은 변경 내용 취소 |
| **GRANT** | 사용자에게 권한 부여 |
| **REVOKE** | 사용자 권한 취소 |

> ⭐ `DCL = COMMIT / ROLLBACK / GRANT / REVOKE`

---

## SECTION 080. DDL

### 1. DDL의 개요
- DB 구조와 객체를 정의하는 언어
- 주요 명령어
  - `CREATE SCHEMA`
  - `CREATE DOMAIN`
  - `CREATE TABLE`
  - `CREATE VIEW`
  - `CREATE INDEX`
  - `ALTER TABLE`
  - `DROP`

### 2. CREATE SCHEMA
- **스키마(Schema)**를 정의하는 명령문
- 스키마 이름과 소유자·허가권자를 지정

```sql
CREATE SCHEMA 스키마명 AUTHORIZATION 사용자_id;
```

> ⭐ `CREATE SCHEMA = 스키마 정의`

### 3. CREATE DOMAIN
- **도메인(Domain)**을 정의하는 명령문
- 하나의 속성이 가질 수 있는 **값의 범위**를 정의
- `DEFAULT` - 기본값 지정
- `CONSTRAINT` - 제약 조건 이름 지정
- `CHECK` - 허용 가능한 값의 조건 지정

```sql
CREATE DOMAIN 도메인명 [AS] 데이터_타입
    [DEFAULT 기본값]
    [CONSTRAINT 제약조건명 CHECK (범위값)];
```

#### 교재에 제시된 대표 SQL 데이터 타입
| 종류 | 대표 데이터 타입 |
|---|---|
| **정수형** | `INTEGER`, `SMALLINT` |
| **실수형** | `FLOAT`, `REAL`, `DOUBLE PRECISION` |
| **고정길이 문자** | `CHAR(n)`, `CHARACTER(n)` |
| **가변길이 문자** | `VARCHAR(n)`, `CHARACTER VARYING(n)` |
| **고정길이 비트열** | `BIT(n)` |
| **날짜** | `DATE` |
| **시간** | `TIME` |

> ⭐ `DOMAIN = 속성값 범위 정의`

### 4. CREATE TABLE
- **테이블(Table)**을 정의하는 명령문
- 속성의 이름·데이터 타입·기본값·NULL 허용 여부와 각종 제약 조건을 지정

```sql
CREATE TABLE 테이블명 (
    속성명 데이터_타입 [DEFAULT 기본값] [NOT NULL],
    ...
    [PRIMARY KEY (기본키_속성명, ...)]
    [UNIQUE (대체키_속성명, ...)]
    [FOREIGN KEY (외래키_속성명, ...)
        REFERENCES 참조테이블(기본키_속성명, ...)
        [ON DELETE 옵션]
        [ON UPDATE 옵션]]
    [CONSTRAINT 제약조건명 CHECK (조건식)]
);
```

#### 주요 제약 조건

| 제약 조건 | 핵심 |
|---|---|
| **DEFAULT** | 기본값 지정 |
| **NOT NULL** | NULL 입력 금지 |
| **PRIMARY KEY** | 기본키 지정 |
| **UNIQUE** | 대체키의 값이 중복되지 않도록 지정 |
| **FOREIGN KEY ~ REFERENCES** | 외래키와 참조 테이블 지정 |
| **CONSTRAINT** | 제약 조건에 이름 지정 |
| **CHECK** | 속성값의 조건 지정 |

> ⭐ `PRIMARY KEY = 기본키`  
> ⭐ `FOREIGN KEY + REFERENCES = 참조 관계`  
> ⭐ `CHECK = 입력값 조건 검사`

#### FOREIGN KEY의 참조 동작
외래키가 참조하는 기본키 값이 삭제·변경될 때 사용할 수 있는 옵션

| 옵션 | 의미 |
|---|---|
| **NO ACTION** | 참조되는 값에 변화가 있어도 관련 작업을 수행하지 않음 |
| **CASCADE** | 참조되는 튜플의 삭제·변경과 함께 관련 튜플도 연쇄적으로 삭제·변경 |
| **SET NULL** | 관련 외래키 값을 `NULL`로 변경 |
| **SET DEFAULT** | 관련 외래키 값을 지정된 기본값으로 변경 |

> ⭐ `CASCADE = 연쇄 반영 / SET NULL = NULL / SET DEFAULT = 기본값`

### 5. CREATE VIEW
- **뷰(View)**를 정의하는 명령문
- `SELECT`문의 실행 결과를 이용하여 뷰를 생성
- 속성명을 생략하면 `SELECT`문의 속성명이 그대로 사용됨

```sql
CREATE VIEW 뷰명[(속성명, 속성명, ...)]
AS SELECT문;
```

> ⭐ `VIEW = SELECT 결과를 이용한 가상 테이블`

### 6. CREATE INDEX
- **인덱스(Index)**를 정의하는 명령문

```sql
CREATE [UNIQUE] INDEX 인덱스명
ON 테이블명(속성명 [ASC | DESC], ...)
[CLUSTER];
```

- `UNIQUE` - 중복 값이 없는 속성으로 인덱스 생성
- `ASC` - 오름차순
- `DESC` - 내림차순
- `CLUSTER` - 사용한 인덱스를 클러스터드 인덱스로 지정

> ⭐ `INDEX = 검색을 위한 접근 구조`  
> ⭐ `ASC = 오름차순 / DESC = 내림차순`

### 7. ALTER TABLE
- 테이블 정의를 **변경**하는 명령문
- `ADD` - 새로운 속성 추가
- `ALTER` - 특정 속성의 기본값 변경
- `DROP COLUMN` - 특정 속성 삭제

```sql
ALTER TABLE 테이블명 ADD 속성명 데이터_타입 [DEFAULT 기본값];

ALTER TABLE 테이블명
ALTER 속성명 [SET DEFAULT 기본값];

ALTER TABLE 테이블명
DROP COLUMN 속성명 [CASCADE];
```

> ⭐ `ALTER TABLE = ADD / ALTER / DROP COLUMN`

### 8. DROP
- 스키마·도메인·테이블·뷰·인덱스·제약 조건 등을 제거

```sql
DROP SCHEMA 스키마명 [CASCADE | RESTRICT];
DROP DOMAIN 도메인명 [CASCADE | RESTRICT];
DROP TABLE 테이블명 [CASCADE | RESTRICT];
DROP VIEW 뷰명 [CASCADE | RESTRICT];
DROP INDEX 인덱스명 [CASCADE | RESTRICT];
DROP CONSTRAINT 제약조건명;
```

#### CASCADE / RESTRICT
- **CASCADE** - 제거할 객체를 참조하는 **다른 객체까지 함께 제거**
- **RESTRICT** - 다른 객체가 참조하고 있으면 **제거를 취소**

> ⭐ `CASCADE = 같이 삭제 / RESTRICT = 참조 중이면 삭제 금지`

---

## SECTION 081. DCL

### 1. DCL의 개요
- 데이터의 **보안·무결성·회복·병행 제어**를 위한 언어
- 사용자 권한 관리와 트랜잭션 제어에 사용
- 주요 명령어
  - `GRANT`
  - `REVOKE`
  - `COMMIT`
  - `ROLLBACK`
  - `SAVEPOINT`

### 2. GRANT / REVOKE

#### 사용자 등급 지정
```sql
GRANT 사용자등급 TO 사용자_id [IDENTIFIED BY 암호];

REVOKE 사용자등급 FROM 사용자_id;
```

- `GRANT` - 권한 부여
- `REVOKE` - 권한 취소

#### 테이블 및 속성에 대한 권한
```sql
GRANT 권한_리스트
ON 객체
TO 사용자
[WITH GRANT OPTION];

REVOKE [GRANT OPTION FOR] 권한_리스트
ON 객체
FROM 사용자
[CASCADE];
```

- 권한 종류 예 - `ALL / SELECT / INSERT / DELETE / UPDATE / ALTER`
- **WITH GRANT OPTION** - 권한을 받은 사용자가 다른 사용자에게 같은 권한을 부여할 수 있음
- **GRANT OPTION FOR** - 다른 사람에게 권한을 부여할 수 있는 권한만 취소
- **CASCADE** - 권한 취소 시 해당 권한을 전달받은 사용자들의 권한까지 연쇄적으로 취소

> ⭐ `GRANT = 권한 부여 / REVOKE = 권한 취소`  
> ⭐ `WITH GRANT OPTION = 권한 재부여 가능`

### 3. COMMIT
- 트랜잭션이 성공적으로 수행되었다고 판단되면 변경 내용을 **데이터베이스에 영구적으로 반영**
- `COMMIT` 수행 후에는 일반적인 `ROLLBACK`으로 이전 상태로 되돌릴 수 없음
- **Auto Commit** 기능을 설정하면 DML 성공 시 자동 `COMMIT`, 실패 시 자동 `ROLLBACK`되도록 할 수 있음

```sql
COMMIT;
```

> ⭐ `COMMIT = 변경 내용 확정`

### 4. ROLLBACK
- 아직 `COMMIT`되지 않은 변경 내용을 취소하고 데이터베이스를 이전 상태로 복구
- 트랜잭션 전체가 정상적으로 끝나지 못한 경우 수행

```sql
ROLLBACK;
```

> ⭐ `ROLLBACK = COMMIT 전 변경 취소`

### 5. SAVEPOINT
- 트랜잭션 중간에 **저장점**을 지정
- `ROLLBACK` 시 지정한 저장점까지의 변경 내용만 취소 가능

```sql
SAVEPOINT 저장점명;
```

> ⭐ `SAVEPOINT = 부분 ROLLBACK 기준점`

---

## SECTION 082. DML

### 1. DML의 개요
- 저장된 데이터를 사용자가 원하는 방식으로 **검색·삽입·삭제·갱신**하기 위해 사용

| 명령어 | 기능 |
|---|---|
| **SELECT** | 데이터 검색 |
| **INSERT** | 데이터 삽입 |
| **DELETE** | 데이터 삭제 |
| **UPDATE** | 데이터 갱신 |

### 2. 삽입문 - INSERT INTO
- 테이블에 새로운 튜플을 삽입

```sql
INSERT INTO 테이블명[(속성명1, 속성명2, ...)]
VALUES (데이터1, 데이터2, ...);
```

- 속성명과 값의 **개수와 자료형이 서로 일치**해야 함
- 모든 속성을 사용할 경우 속성명 생략 가능
- `SELECT`문의 검색 결과를 이용하여 여러 튜플을 한 번에 삽입할 수도 있음

```sql
INSERT INTO 테이블명[(속성명, ...)]
SELECT문;
```

> ⭐ `INSERT = INTO + VALUES`

### 3. 삭제문 - DELETE FROM
- 테이블에서 조건을 만족하는 튜플을 삭제

```sql
DELETE
FROM 테이블명
[WHERE 조건];
```

- 특정 레코드 삭제 → `WHERE` 사용
- `WHERE`를 생략하면 **테이블의 모든 튜플 삭제**
- 레코드만 삭제되며 **테이블 구조 자체는 남음**

> ⭐ `DELETE = 행 삭제 / 테이블 구조는 유지`

### 4. 갱신문 - UPDATE ~ SET
- 테이블에 있는 튜플의 내용을 변경

```sql
UPDATE 테이블명
SET 속성명 = 데이터 [, 속성명 = 데이터, ...]
[WHERE 조건];
```

- `SET` - 변경할 속성과 값을 지정
- `WHERE` - 변경할 튜플의 조건 지정

> ⭐ `UPDATE = SET으로 값 변경`

---

## SECTION 083. DML - SELECT-1

### 1. SELECT 일반 형식

```sql
SELECT [PREDICATE]
       [테이블명.]속성명 [AS 별칭],
       [그룹함수(속성명) [AS 별칭]], ...
FROM 테이블명, ...
[WHERE 조건]
[GROUP BY 속성명, ...]
[HAVING 조건]
[ORDER BY 속성명 [ASC | DESC], ...];
```

#### 주요 절
| 절 | 핵심 |
|---|---|
| **SELECT** | 검색할 속성 지정 |
| **FROM** | 검색할 테이블 지정 |
| **WHERE** | 튜플 검색 조건 지정 |
| **GROUP BY** | 특정 속성을 기준으로 그룹화 |
| **HAVING** | 그룹에 대한 조건 지정 |
| **ORDER BY** | 결과 정렬 |

> ⭐ 기본 구조 → `SELECT → FROM → WHERE → GROUP BY → HAVING → ORDER BY`

#### SELECT의 PREDICATE
| 옵션 | 의미 |
|---|---|
| **ALL** | 모든 튜플 검색 / 기본값 |
| **DISTINCT** | 중복 튜플 제거 |
| **DISTINCTROW** | 중복된 튜플을 제거하여 검색 |

> ⭐ `DISTINCT = 중복 제거`

#### 별칭
- `AS`를 이용해 속성·테이블 등에 다른 이름을 표시할 수 있음
- `AS`는 생략 가능

### 2. 기본 검색
- 모든 속성 검색 → `*`

```sql
SELECT *
FROM 테이블명;
```

- 특정 속성 검색

```sql
SELECT 속성명1, 속성명2
FROM 테이블명;
```

- 중복 제거

```sql
SELECT DISTINCT 속성명
FROM 테이블명;
```

- 산술식을 이용한 검색도 가능

```sql
SELECT 속성명 + 10
FROM 테이블명;
```

### 3. 조건 지정 검색
- `WHERE`절에 조건을 지정하여 필요한 튜플만 검색

#### 주요 비교 연산자
`=` / `<>` / `<` / `<=` / `>` / `>=`

#### 논리 연산자
- `NOT`
- `AND`
- `OR`

> ⭐ 논리 연산자 우선순위 → `NOT → AND → OR`

#### LIKE
문자 패턴을 이용하여 검색

| 기호 | 의미 |
|---|---|
| `%` | 모든 문자를 대표 |
| `_` | 문자 하나를 대표 |

```sql
WHERE 속성명 LIKE '김%';
```

> ⭐ `LIKE % = 여러 문자 / _ = 한 문자`

#### BETWEEN
일정 범위 안의 값을 검색

```sql
WHERE 속성명 BETWEEN 값1 AND 값2;
```

> ⭐ `BETWEEN A AND B = A 이상 B 이하`

#### NULL 검색
```sql
WHERE 속성명 IS NULL;

WHERE 속성명 IS NOT NULL;
```

> ⭐ `NULL 비교는 = 가 아니라 IS NULL`

### 4. 정렬 검색
- `ORDER BY`절에 속성을 지정하여 검색 결과를 정렬
- `ASC` - 오름차순
- `DESC` - 내림차순
- 정렬 방향을 생략하면 **ASC가 기본**

```sql
SELECT *
FROM 테이블명
ORDER BY 속성명 ASC;
```

- 여러 속성으로 정렬 가능

```sql
ORDER BY 속성명1 ASC, 속성명2 DESC;
```

> ⭐ `ORDER BY 기본 = ASC`

### 5. 하위 질의(Subquery)
- 질의문 안에 또 다른 `SELECT`문을 포함하는 형태
- 안쪽 질의의 결과를 이용해 바깥쪽 질의를 수행

```sql
SELECT ...
FROM ...
WHERE 속성명 IN (
    SELECT 속성명
    FROM ...
    WHERE ...
);
```

#### 하위 질의에서 자주 사용하는 형태
- `IN`
- `NOT IN`
- `EXISTS`
- `NOT EXISTS`

> ⭐ `Subquery = SELECT문 안의 SELECT문`

### 6. 복수 테이블 검색
- 둘 이상의 테이블을 대상으로 검색 수행
- 테이블 간 관련 속성을 조건으로 연결하여 필요한 데이터를 검색

```sql
SELECT A.속성명, B.속성명
FROM 테이블A A, 테이블B B
WHERE A.공통속성 = B.공통속성;
```

> ⭐ `복수 테이블 검색 = 테이블 연결 조건 필요`

---

## SECTION 084. DML - SELECT-2

### 1. 그룹 함수
- `GROUP BY`절로 지정된 그룹별로 속성값에 대한 계산 수행

| 함수 | 기능 |
|---|---|
| **COUNT** | 튜플 수 |
| **SUM** | 합계 |
| **AVG** | 평균 |
| **MAX** | 최대값 |
| **MIN** | 최소값 |
| **STDDEV** | 표준편차 |
| **VARIANCE** | 분산 |

> ⭐ `COUNT / SUM / AVG / MAX / MIN`

### 2. ROLLUP / CUBE
- `GROUP BY`와 함께 사용하여 **소계·합계**를 구할 때 사용

#### ROLLUP
- 지정된 속성을 기준으로 **계층적인 부분합과 전체합**을 계산

```sql
GROUP BY ROLLUP(속성명1, 속성명2);
```

#### CUBE
- 지정된 속성들의 **모든 조합에 대한 소계와 전체합**을 계산

```sql
GROUP BY CUBE(속성명1, 속성명2);
```

> ⭐ `ROLLUP = 계층적 소계`  
> ⭐ `CUBE = 가능한 조합별 소계`

### 3. WINDOW 함수
- `GROUP BY`를 사용하지 않고도 튜플 단위의 결과를 유지하면서 집계·순위 계산 가능
- `OVER()` 안에서
  - `PARTITION BY` - 그룹 기준
  - `ORDER BY` - 순위·정렬 기준 지정

```sql
함수() OVER(
    [PARTITION BY 속성명]
    [ORDER BY 속성명]
)
```

#### 주요 순위 함수
| 함수 | 핵심 |
|---|---|
| **ROW_NUMBER()** | 동일 값이어도 고유한 순번 부여 |
| **RANK()** | 공동 순위가 있으면 다음 순위가 건너뜀 |
| **DENSE_RANK()** | 공동 순위가 있어도 다음 순위를 연속 부여 |

> ⭐ `RANK = 1,1,3 / DENSE_RANK = 1,1,2`

### 4. 그룹 지정 검색
- `GROUP BY` - 특정 속성을 기준으로 튜플을 그룹화
- `HAVING` - 그룹 함수 결과에 대한 조건 지정

```sql
SELECT 속성명, COUNT(*)
FROM 테이블명
GROUP BY 속성명
HAVING COUNT(*) >= 조건값;
```

> ⭐ `WHERE = 개별 튜플 조건 / HAVING = 그룹 조건`

### 5. 집합 연산자를 이용한 통합 질의
- 두 개 이상의 `SELECT` 결과를 하나의 결과로 통합
- 두 `SELECT`문에서 검색되는 **속성의 수와 대응 속성의 데이터 타입이 같아야 함**

```sql
SELECT 속성명1, 속성명2, ...
FROM 테이블명
UNION | UNION ALL | INTERSECT | EXCEPT
SELECT 속성명1, 속성명2, ...
FROM 테이블명
[ORDER BY 속성명 [ASC | DESC]];
```

| 집합 연산자 | 핵심 | 중복 |
|---|---|---|
| **UNION** | 두 결과의 합집합 | 제거 |
| **UNION ALL** | 두 결과를 그대로 합침 | 유지 |
| **INTERSECT** | 두 결과의 공통 튜플 | 제거 |
| **EXCEPT** | 첫 번째 결과에서 두 번째 결과를 제외 | 제거 |

> ⭐ `UNION = 합 / INTERSECT = 교 / EXCEPT = 차`  
> ⭐ `UNION ALL만 중복 유지`

---

## SECTION 085. DML - JOIN

### 1. JOIN의 개요
- 둘 이상의 테이블에 존재하는 관련 튜플들을 결합하여 하나의 결과로 표현
- 크게 **INNER JOIN / OUTER JOIN**으로 구분

> ⭐ `JOIN = 여러 테이블의 관련 행을 결합`

### 2. INNER JOIN
- 두 테이블에서 **JOIN 조건을 만족하는 튜플만** 검색
- `EQUI JOIN / NON-EQUI JOIN`으로 구분

#### EQUI JOIN
- 공통 속성을 기준으로 `=` 연산자를 이용하여 결합

```sql
SELECT 테이블1.속성명, 테이블2.속성명
FROM 테이블1, 테이블2
WHERE 테이블1.공통속성 = 테이블2.공통속성;
```

또는

```sql
SELECT ...
FROM 테이블1
JOIN 테이블2
ON 테이블1.공통속성 = 테이블2.공통속성;
```

#### NATURAL JOIN
- EQUI JOIN의 결과에서 **중복되는 공통 속성을 하나만 표시**

```sql
SELECT ...
FROM 테이블1 NATURAL JOIN 테이블2;
```

> ⭐ `EQUI JOIN = "=" 조건`  
> ⭐ `NATURAL JOIN = 중복 공통 속성 제거`

#### NON-EQUI JOIN
- `=`이 아닌 비교 연산자를 이용해 JOIN
- 예: `<`, `>`, `<=`, `>=`, `<>`, `BETWEEN` 등

```sql
SELECT ...
FROM 테이블1, 테이블2
WHERE NON_EQUI_JOIN_조건;
```

> ⭐ `NON-EQUI JOIN = = 이외의 비교 조건`

### 3. OUTER JOIN
- JOIN 조건을 만족하지 않는 튜플도 결과에 포함
- 일치하지 않는 부분은 **NULL**로 표시

#### LEFT OUTER JOIN
- INNER JOIN 결과 + **왼쪽 테이블의 일치하지 않는 튜플**

```sql
SELECT ...
FROM 테이블1
LEFT OUTER JOIN 테이블2
ON 테이블1.속성 = 테이블2.속성;
```

#### RIGHT OUTER JOIN
- INNER JOIN 결과 + **오른쪽 테이블의 일치하지 않는 튜플**

```sql
SELECT ...
FROM 테이블1
RIGHT OUTER JOIN 테이블2
ON 테이블1.속성 = 테이블2.속성;
```

#### FULL OUTER JOIN
- `LEFT OUTER JOIN + RIGHT OUTER JOIN`
- 양쪽 테이블의 일치하지 않는 튜플까지 모두 표시

```sql
SELECT ...
FROM 테이블1
FULL OUTER JOIN 테이블2
ON 테이블1.속성 = 테이블2.속성;
```

> ⭐ `LEFT = 왼쪽 전부 / RIGHT = 오른쪽 전부 / FULL = 양쪽 전부`

### 4. SELF JOIN
- **같은 테이블을 자기 자신과 JOIN**
- 하나의 테이블에 서로 관련된 두 속성이 존재할 때 사용
- 동일한 테이블을 두 번 사용하므로 **별칭(Alias)**으로 구분

```sql
SELECT A.속성명, B.속성명
FROM 테이블명 A, 테이블명 B
WHERE A.속성 = B.속성;
```

또는

```sql
SELECT A.속성명, B.속성명
FROM 테이블명 A
JOIN 테이블명 B
ON A.속성 = B.속성;
```

> ⭐ `SELF JOIN = 같은 테이블 + 서로 다른 별칭`