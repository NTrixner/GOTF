package at.trixner.gotf.mapper;

import java.util.Map;

public class StringReplacer {
    public static String replace(String name, Map<String, String> replaceVals) {
        String out = name;
        for (Map.Entry<String, String> replaceVal : replaceVals.entrySet()) {
            out = out.replace("{{" + replaceVal.getKey() + "}}", replaceVal.getValue());
        }
        return out;
    }

    public static Long replaceToLong(String name, Map<String, String> replaceVals) {
        return Long.parseLong(replace(name, replaceVals));
    }
}
