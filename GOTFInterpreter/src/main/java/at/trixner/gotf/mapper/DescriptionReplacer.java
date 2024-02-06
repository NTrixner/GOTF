package at.trixner.gotf.mapper;

import at.trixner.gotf.model.structure.Description;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DescriptionReplacer {

    public static Description replace(Description description, Map<String, String> replaceVals) {
        Description out = new Description();
        out.setText(new ArrayList<>());
        for (String s : description.getText()) {
            out.getText().add(StringReplacer.replace(s, replaceVals));
        }
        return out;
    }

    public static String getDescription(String delimiter, Description description, Map<String, String> replaceVals) throws EvaluationException, ParseException {
        Map<String, String> replaceVals2 = replaceVals;
        if(description.getConditionalText() != null && !description.getConditionalText().isEmpty())
        {
            for(int i = 0; i < description.getConditionalText().size(); i++)
            {
                Map<String, String> conditional = description.getConditionalText().get(i);
                Map<String, String> conditionalReplaceVals = new HashMap<>();
                String condition = conditional.get("conditional");
                String value = conditional.get("value");

                for(Map.Entry<String, String> param : conditional.entrySet())
                {
                    if(!"conditional".equals(param.getKey()) && !"value".equals(param.getKey()))
                    {
                        String paramString = param.getValue();
                        paramString = StringReplacer.replace(paramString, replaceVals2);
                        String paramVal = new Expression(paramString).evaluate().getStringValue();
                        conditionalReplaceVals.put(param.getKey(), paramVal);
                        replaceVals2.put(param.getKey(), paramVal);
                    }
                }
                condition = StringReplacer.replace(condition, conditionalReplaceVals);
                condition = StringReplacer.replace(condition, replaceVals2);

                boolean counts = new Expression(condition)
                        .evaluate()
                        .getBooleanValue();

                if(counts)
                {
                    value = StringReplacer.replace(value, conditionalReplaceVals);
                    value = StringReplacer.replace(value, replaceVals2);
                    replaceVals2.put("conditionalText["+ i + "]", value);
                }
                else
                {
                    replaceVals2.put("conditionalText[" + i + "]", "");
                }
            }
        }

        Description replaced = replace(description, replaceVals2);
        return replaced
                .getText()
                .stream().filter(f -> !f.isEmpty())
                .collect(Collectors.joining(delimiter));
    }
}
