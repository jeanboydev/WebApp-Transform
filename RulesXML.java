import java.util.HashMap;
import java.util.Map;

public class RulesXML {

    public static final String SUFFIX_WECHAT = ".wxml";
    private static final String SUFFIX_ALIPAY = ".axml";
    public static Map<String, String> weChatToAlipay = new HashMap<>();

    static {
        weChatToAlipay.put(SUFFIX_WECHAT, SUFFIX_ALIPAY);
        weChatToAlipay.put("wx:", "a:");
        weChatToAlipay.put("bindtouchstart", "onTouchStart");
        weChatToAlipay.put("bindtouchmove", "onTouchMove");
        weChatToAlipay.put("bindtouchcancel", "onTouchCancel");
        weChatToAlipay.put("bindtouchend", "onTouchEnd");
        weChatToAlipay.put("bindtap", "onTap");
        weChatToAlipay.put("bindlongtap", "onLongTap");
        weChatToAlipay.put("bindinput", "onInput");
        weChatToAlipay.put("bindchange", "onChange");
        weChatToAlipay.put("bindsubmit", "onSubmit");
        weChatToAlipay.put("bindblur", "onBlur");
        weChatToAlipay.put("bindfocus", "onFocus");
        weChatToAlipay.put("bindscrolltoupper", "onScrollToUpper");
        weChatToAlipay.put("bindscrolltolower", "onScrollToLower");
        weChatToAlipay.put("bindscroll", "onScroll");
        weChatToAlipay.put("bindcontroltap", "onControlTap");
        weChatToAlipay.put("bindmarkertap", "onMarkerTap");
        weChatToAlipay.put("bindregionchange", "onRegionChange");
        weChatToAlipay.put("<span", "<text");
        weChatToAlipay.put("</span>", "</text>");
    }
}
