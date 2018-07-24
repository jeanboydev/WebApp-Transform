import java.util.HashMap;
import java.util.Map;

public class RulesJSON {

    public static final String SUFFIX_JSON = ".json";
    public static Map<String, String> weChatToAlipay = new HashMap<>();

    static {
        weChatToAlipay.put(SUFFIX_JSON, SUFFIX_JSON);
        weChatToAlipay.put("navigationBarTitleText", "defaultTitle");
        weChatToAlipay.put("navigationBarBackgroundColor", "titleBarColor");
        weChatToAlipay.put("enablePullDownRefresh", "pullRefresh");
        weChatToAlipay.put("\"disableScroll\": true", "\"allowsBounceVertical\": \"NO\"");
        weChatToAlipay.put("\"disableScroll\": false", "\"allowsBounceVertical\": \"YES\"");
        weChatToAlipay.put("list", "items");
        weChatToAlipay.put("iconPath", "icon");
        weChatToAlipay.put("selectedIconPath", "activeIcon");
        weChatToAlipay.put("text", "name");

    }
}
