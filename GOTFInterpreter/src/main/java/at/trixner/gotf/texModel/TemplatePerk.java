package at.trixner.gotf.texModel;

import lombok.Data;

import java.util.List;

@Data
public class TemplatePerk {
    private String name, label, requirements, tags, description;
    private Long cost;
    private List<Rank> ranks;
    public FamilyProgression families;
}
