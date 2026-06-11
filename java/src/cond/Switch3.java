package cond;

public class Switch3 {

    public static void main(String[] args) {

        int grade = 2;

        int coupon;
        switch (grade) {
            case 1:
                coupon = 1000;
                break;
            case 2: //break문이 없으면 다음 문장으로 넘어감
            case 3:
                coupon = 3000;
                break;
            default:
                coupon = 500;
        }
        System.out.println("발급 받은 쿠폰 " + coupon);
    }
}
