package at.trixner.gotf.model.perks;

import at.trixner.gotf.model.structure.GotfItem;
import lombok.Data;

import java.util.List;

@Data
public class MultiPerk extends Perk {
    private List<String> value;
    private List<List<String>> params;
}
