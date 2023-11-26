package at.trixner.gotf.templatemodel;

import lombok.Data;

import java.util.List;

@Data
public class Perk {
    private String name, label, requirements, tags, description;
    private Long cost;
    private List<Rank> ranks;
}
