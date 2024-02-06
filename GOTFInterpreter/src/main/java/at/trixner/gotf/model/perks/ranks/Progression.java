package at.trixner.gotf.model.perks.ranks;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=ListProgression.class, name="list"),
        @JsonSubTypes.Type(value=FormulaProgression.class, name="formula"),
        @JsonSubTypes.Type(value=FamiliesProgression.class, name="families")
})
public class Progression {
}
