package at.trixner.gotf.model.structure;

import at.trixner.gotf.model.attributes.ActiveAttribute;
import at.trixner.gotf.model.attributes.DefensiveAttribute;
import at.trixner.gotf.model.attributes.Resource;
import at.trixner.gotf.model.gameModes.GameMode;
import at.trixner.gotf.model.inventory.Armor;
import at.trixner.gotf.model.inventory.Equipment;
import at.trixner.gotf.model.inventory.Item;
import at.trixner.gotf.model.origins.Origin;
import at.trixner.gotf.model.perks.MultiPerk;
import at.trixner.gotf.model.perks.Perk;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "gotfItem")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ActiveAttribute.class, name = "ActiveAttribute"),
        @JsonSubTypes.Type(value = DefensiveAttribute.class, name = "DefensiveAttribute"),
        @JsonSubTypes.Type(value = Resource.class, name = "Resource"),
        @JsonSubTypes.Type(value = GameMode.class, name = "GameMode"),
        @JsonSubTypes.Type(value = Origin.class, name = "Origin"),
        @JsonSubTypes.Type(value = Perk.class, name = "Perk"),
        @JsonSubTypes.Type(value = MultiPerk.class, name = "MultiPerk"),
        @JsonSubTypes.Type(value = Equipment.class, name = "Equipment"),
        @JsonSubTypes.Type(value = Item.class, name = "Item"),
        @JsonSubTypes.Type(value = Misc.class, name = "Misc"),
})
public class GotfItem extends GotfType implements Serializable {
}
