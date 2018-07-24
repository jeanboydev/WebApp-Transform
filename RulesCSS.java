import java.util.HashMap;
import java.util.Map;

public class RulesCSS {

    public static final String SUFFIX_WECHAT = ".wxss";
    private static final String SUFFIX_ALIPAY = ".acss";
    public static Map<String, String> weChatToAlipay = new HashMap<>();

    static {
        weChatToAlipay.put(SUFFIX_WECHAT, SUFFIX_ALIPAY);
    }
}
