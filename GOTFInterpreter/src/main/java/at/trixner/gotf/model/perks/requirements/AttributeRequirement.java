package at.trixner.gotf.model.perks.requirements;

import lombok.Data;

@Data
public class AttributeRequirement extends Requirement{
    private String stat;
    private int value;
}
