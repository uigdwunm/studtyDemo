import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/5/23 13:59
 **/
public class test {
    public static void main(String[] args) {

        int a = 20;
        Long mortgageTerm = Long.valueOf(a)  > 30 ? Long.valueOf(a) : 30;

        LocalDateTime.now().plusDays(mortgageTerm);
    }
}
