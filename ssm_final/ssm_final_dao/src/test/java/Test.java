import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Lisheng in 19:32 2019/9/5
 */
public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int init = 5;

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j <= Math.pow(2,i) * init; j++) {
                //System.out.print(j+" ");
                list.add(j);
            }
        }
        System.out.println(list.get(num-1));
    }
}
