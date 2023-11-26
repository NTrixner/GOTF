package at.trixner.gotf.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "gotfType")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Armor.class, name="Armor"),
        @JsonSubTypes.Type(value=Perk.class, name="Perk"),
        @JsonSubTypes.Type(value=MultiPerk.class, name="MultiPerk"),
        @JsonSubTypes.Type(value=Weapon.class, name="Weapon")
})
public class GotfType implements Serializable {
}
