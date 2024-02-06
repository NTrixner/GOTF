package at.trixner.gotf.model.structure;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Description {
    private List<String> text;
    private List<Map<String, String>> conditionalText;
}
