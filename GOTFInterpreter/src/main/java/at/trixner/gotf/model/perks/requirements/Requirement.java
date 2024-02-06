package at.trixner.gotf.model.perks.requirements;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AttributeRequirement.class, name = "attribute"),
        @JsonSubTypes.Type(value = PerkRequirement.class, name = "perk"),
        @JsonSubTypes.Type(value = TagRanksRequirement.class, name = "tagRanks"),
})
public class Requirement {
    private String description;
}
