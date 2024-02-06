package at.trixner.gotf.model.perks.requirements;

import lombok.Data;

@Data
public class PerkRequirement extends Requirement{
    private String perk;
    private int rank;
}
