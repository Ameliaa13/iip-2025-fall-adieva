import java.util.*;
public class dz{
    public static boolean ProstoeN(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите число больше 5: ");
        int n = sc.nextInt();

        if (n <= 5) {
            System.out.println("Число должно быть больше 5");
            return;
        }

        if (n % 2 == 0) {
            for (int i = 2; i < n; i++) {
                if (ProstoeN(i) && ProstoeN(n - i)) {
                    System.out.println(n + " = " + i + " + " + (n - i));
                    break;
                }
            }
        } else {
            for (int i = 2; i < n; i++) {
                if (ProstoeN(i)) {
                    for (int j = 2; j < n; j++) {
                        int k = n - i - j;
                        if (k > 1 && ProstoeN(j) && ProstoeN(k)) {
                            System.out.println(n + " = " + i + " + " + j + " + " + k);
                            return;
                        }
                    }
                }
            }
        }
    }
}