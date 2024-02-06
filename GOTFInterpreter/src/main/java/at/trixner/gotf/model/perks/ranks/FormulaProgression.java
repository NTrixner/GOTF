package at.trixner.gotf.model.perks.ranks;

import at.trixner.gotf.model.structure.Description;
import lombok.Data;

@Data
public class FormulaProgression extends Progression {
    private String value;
    private Description description;
    private Long amountWritten;
}
