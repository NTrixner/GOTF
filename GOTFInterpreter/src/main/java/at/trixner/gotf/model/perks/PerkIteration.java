package at.trixner.gotf.model.perks;

import at.trixner.gotf.model.structure.Description;
import lombok.Data;

@Data
public class PerkIteration {
    private Long rank, cost;
    private Description description;
}
