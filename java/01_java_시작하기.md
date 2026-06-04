### 프로그래밍 언어와 자바

- 기계어 - 컴퓨터가 이해할 수 있는 언어
- 프로그래밍 언어 - 사람의 언어와 기계어의 다리 역할
  - 고급 언어 - 사람이 쉽게 이해할 수 있는 코드 언어. ex) java, C, C++, C#, Python
    - 컴파일 - 고급 언어로 작성된 소스 파일 → 컴퓨터가 읽을 수 있게(0과 1) 변환
    - 소스 파일 - 고급 언어로 작성한 파일
  - 저급 언어 - 기계어의 가까운 언어. 0과 1 ex) 어셈블리어

#### JAVA

- 모든 운영체제에서 실행 가능
- 객체 지향 프로그래밍(Objcet Oriented Programming, OOP)
- 메모리(RAM) 자동 정리
- 무료 라이브러리 풍부 - 프로그램 개발 기간 단축 가능

### JDK 설치

- JDK(Java Development Kit) - 자바 프로그램 개발 및 실행을 위한 Java SE(Standard Edition) 구현체
  - Open JDK
    - 버전 - JDK 8, JDK 11, JDK 17
  - Oracle JDK

#### 다운로드

[https://adoptium.net](https://adoptium.net/)

- 윈도우 사용자 - Download Temurin for Windows x64 다운

  ![alt text](/java/note_assets/01_java_시작하기/image.png)
  - 강의 버전에 맞춰서 다운 받음

    ![alt text](/java/note_assets/01_java_시작하기/image-1.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-2.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-3.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-4.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-5.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-6.png)

### 윈도우 환경 변수 설정

- 운영체제는 프로그램이 실행하면서 사용할 수 있는 값을 환경 변수 이름으로 관리
- JDK 설치 후 프로그램들이 JDK를 이용할 수 있도록 JAVA_HOME 환경 변수 생성, Path 환경 변수를 수정하는 것이 좋음

#### 환경 변수 설정

- 시스템 환경 변수 편집 검색 후 실행

  ![alt text](/java/note_assets/01_java_시작하기/image-7.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-8.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-9.png)

- `디렉터리 찾아보기`에서 설치 경로 입력

  ![alt text](/java/note_assets/01_java_시작하기/image-10.png)

- bin 디렉터리

  ![alt text](/java/note_assets/01_java_시작하기/image-11.png)
  - javac.exe - 자바 소스 파일 컴파일
  - java.exe - 자바 프로그램 실행 명령어

#### Path 환경 변수 설정

- javac, java 명령어는 명령 프롬포트 또는 파워쉘에서 컴파일, 실행 시 사용
- bin 디렉터리 안에 있어도 어떤 위치에서 사용 가능하도록 Path 환경 변수 경로 추가

  ![alt text](/java/note_assets/01_java_시작하기/image-12.png)

- 새로 만들기 → `%JAVA_HOME%\bin` 입력 → 확인

  ![alt text](/java/note_assets/01_java_시작하기/image-13.png)

  ![alt text](/java/note_assets/01_java_시작하기/image-14.png)

#### 환경 변수 설정 확인

```powershell
PS C:\Users\SAMSUNG> java -version

>> openjdk version "17.0.19" 2026-04-21
>> OpenJDK Runtime Environment Temurin-17.0.19+10 (build 17.0.19+10)
>> OpenJDK 64-Bit Server VM Temurin-17.0.19+10 (build 17.0.19+10, mixed mode, sharing)
```

### 바이트코드 파일과 자바 가상 머신

- 바이트코드 파일
  - 소스 파일(.java) 작성 후 컴파일 시, 컴파일 결과 확장명(.class)인 바이트코드 파일 생성
- 자바 가상 머신 JVM(Java Virtual Machine)
  - 바이트코드 파일 → java 명령어(기계어 번역 실행) → JDK와 함께 설치된 JVM을 구동시켜 바이트코드 파일을 완전히 기계어로 번역 + 실행
  - 운영체제에서 이해하는 기계어로 변역해야 하므로 운영체제 별 다르게 설치 (바이트코드 파일은 운영체제와 상관없이 모두 동일)

### 소스 작성 및 실행

- 디렉터리 구조

  ```text
  java
  |-- src                         ← 소스파일 저장되는 디렉터리
  |   |-- ch01
  |       |-- sec06               ← 패키지 디렉터리(ch01, sec06)
  |           |-- Hello.java      ← 소스파일
  |
  |-- bin                         ← 바이트코드 파일 저장 디렉터리
  ```

  - 패키지(package) - 소스파일 및 컴파일된 바이트코드 파일을 쉽게 관리하기 위함. 파일 시스템의 디렉터리와 비슷

- 소스 작성 - [소스](src/HelloJava.java)

- 컴파일 단계
  - Hello.java 같은 소스 코드 작성
  - 자바 컴파일러를 통해 소스 코드 컴파일
    - `javac` 프로그램 사용(자바가 제공)
      - `.java` → `.class` 파일 생성
    - 자바 소스 코드 → 바이트코드로 변환해 자바 가상 머신이 더 빠르게 실행될 수 있게 최적화 + 문법 오류 검출
  - 자바 프로그램 실행
    - java 프로그램 사용(자바가 제공)
    - 자바 가상 머신(JVM) 실행되면서 프로그램 작동 → hello.class를 실행

- 실행 과정
  1. HelloJava 프로그램 실행
  2. main() 메서드 실행 - 시작점
  3. System.out.println("hello java") 을 만나 문자열 hello java 출력
  4. main() 메서드의 {} 블럭이 끝나면 프로그램 종료

- 클래스 선언

  - 클래스 명명 규칙
    - 숫자 시작 x
    - 공백 포함 x
    - 소스파일명과 대소문자가 완전히 일치

  - 클래스 블록 - 중괄호 {...} 영역
    - 클래스의 정의 내용 작성
    - 범위를 나타냄

- 메소드 선언
  - main() 메소드 블록 - 중괄호 {...} 영역
    - 바이트코드 파일 실행 시 메소드 블록 실행 => 프로그램 실행 시작점

- 콘솔뷰에 출력
  - System.out.println() - 콘솔에 값 출력 기능

### 주석

- 한 줄 주석(single line comment) - //
- 여러 줄 주석(multi line comment) - /* ... */
- 도큐먼트 주석 - /**_ ... _/ javadoc 명령어, API 도큐먼트 생성 시 사용
- 컴파일 과정에서 사라짐