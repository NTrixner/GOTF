package at.trixner.gotf.model.structure;

import at.trixner.gotf.model.inventory.Armor;
import at.trixner.gotf.model.inventory.Weapon;
import at.trixner.gotf.model.perks.MultiPerk;
import at.trixner.gotf.model.perks.Perk;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;


@Data
public class GotfType implements Serializable {
}
