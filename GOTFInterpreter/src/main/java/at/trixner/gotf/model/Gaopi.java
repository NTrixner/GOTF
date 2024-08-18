package at.trixner.gotf.model;

import at.trixner.gotf.model.gameModes.GameModeList;
import at.trixner.gotf.model.inventory.Item;
import at.trixner.gotf.model.origins.OriginList;
import at.trixner.gotf.model.perks.Perk;
import at.trixner.gotf.model.structure.GotfCategory;
import at.trixner.gotf.model.structure.GotfCategoryList;
import at.trixner.gotf.model.structure.GotfItem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Gaopi implements Serializable {
    GameModeList gameModes;
    GameModeList attributes;
    GotfCategoryList<? extends Perk> perks;
    OriginList origins;
    GotfCategoryList<? extends Item> inventory;
    List<GotfCategory<GotfItem>> additionalData;
}
