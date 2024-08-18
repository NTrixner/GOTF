package at.trixner.gotf.model.attributes;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AttributeList implements Serializable {
    private List<Attribute> attributes;
    private List<Resource> resources;
}
