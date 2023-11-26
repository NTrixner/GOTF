package at.trixner.gotf.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.List;


@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM)
@JsonSubTypes({
        @JsonSubTypes.Type(value = String.class),
        @JsonSubTypes.Type(value = List.class)
})
public class TextItem {
}
