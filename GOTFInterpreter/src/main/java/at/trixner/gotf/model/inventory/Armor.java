package at.trixner.gotf.model.inventory;

import at.trixner.gotf.model.structure.Defense;
import at.trixner.gotf.model.structure.GotfItem;
import at.trixner.gotf.model.structure.GotfType;
import lombok.Data;

import java.util.List;


@Data
public class Armor extends Equipment {
    private String recipe, material, type, size;
    private Long value;
    private List<Defense> defenses;
    private List<ArmorAttribute> attributes;
}
