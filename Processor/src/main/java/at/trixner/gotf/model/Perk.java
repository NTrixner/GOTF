package at.trixner.gotf.model;

import lombok.Data;

import java.util.List;

@Data
public class Perk extends GotfType {
    private String name, label, type;
    private Long cost;
    private List<PerkRequirement> requirements;
    private List<String> tags;
    private Description description;
    private Progression progression;
}
