package at.trixner.gotf.templatemodel;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Perk {
    private String name, label, requirements, tags, description;
    private Long cost;
    private List<Rank> ranks;
    public FamilyProgression families;
}
