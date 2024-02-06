package at.trixner.gotf.model.structure;

import lombok.Data;

@Data
public class Defense {
    private DamageType type;
    private Long amount;
}
