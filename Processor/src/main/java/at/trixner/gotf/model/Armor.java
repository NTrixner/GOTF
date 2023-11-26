package at.trixner.gotf.model;

import lombok.Data;

import java.util.List;


@Data
public class Armor extends GotfType {
    private String recipe, material, type, size;
    private Long value;
    private List<Defense> defenses;
    private List<ArmorAttribute> attributes;
}
