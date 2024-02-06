package at.trixner.gotf.model.perks;

import at.trixner.gotf.model.perks.ranks.Progression;
import at.trixner.gotf.model.perks.requirements.Requirement;
import at.trixner.gotf.model.structure.Description;
import at.trixner.gotf.model.structure.GotfItem;
import lombok.Data;

import java.util.List;

@Data
public class Perk  extends GotfItem {
    private String name, label, type;
    private String cost;
    private List<Requirement> requirements;
    private List<String> tags;
    private Description description;
    private Progression progression;
}
