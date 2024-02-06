package at.trixner.gotf.model.perks.requirements;

import lombok.Data;

import java.util.List;

@Data
public class TagRanksRequirement extends Requirement{
    private int value;
    private List<String> tags;
}
