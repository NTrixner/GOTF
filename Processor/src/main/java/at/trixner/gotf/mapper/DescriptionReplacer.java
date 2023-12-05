package at.trixner.gotf.mapper;

import at.trixner.gotf.model.Description;

import java.util.ArrayList;
import java.util.Map;

public class DescriptionReplacer {
    public static Description replace(Description description, Map<String, String> replaceVals) {
        Description out = new Description();
        out.setText(new ArrayList<>());
        for (String s : description.getText()) {
            out.getText().add(StringReplacer.replace(s, replaceVals));
        }
        return out;
    }
}
