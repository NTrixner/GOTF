package at.trixner.gotf.model.perks.ranks;

import lombok.Data;

import java.util.List;

@Data
public class FamiliesProgression extends Progression{
    private List<String> itemHeaders;
    private List<FamiliesEntries> value;
}
