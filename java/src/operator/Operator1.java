package operator;

public class Operator1 {

    public static void main(String[] args){

        //변수 초기화
        int a = 5;
        int b = 2;

        //덧셈
        int sum = a + b;
        System.out.println("a + b = " + sum); // 출력 a + b = 7

        //뺄셈
        int diff = a - b;
        System.out.println("a - b = " + diff);

        //곱셈
        int multi = a * b;
        System.out.println("a * b = " + multi);

        //나눗셈
        int div = a / b; // 5 / 2 -> 2.5 int 형이기 때문에 소수점 표현 x. 형변환 때 다시
        System.out.println("a / b = " + div);

        //나머지
        int mod = a % b;
        System.out.println("a % b = " + mod);

        //int z = 10 / 0; // by zero 에러 발생 - 수학적으로 0으로 나눌 수 없음
    }
}
