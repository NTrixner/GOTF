package at.trixner.gotf.templatemodel;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FamilyProgression {
    public List<String> headers;
    public Map<String, List<List<String>>> entries;
}
