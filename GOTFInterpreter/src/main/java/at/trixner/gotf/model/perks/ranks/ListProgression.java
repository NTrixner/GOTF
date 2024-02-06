package at.trixner.gotf.model.perks.ranks;

import at.trixner.gotf.model.perks.PerkIteration;
import lombok.Data;

import java.util.List;

@Data
public class ListProgression extends Progression{
    private List<PerkIteration> value;
}
