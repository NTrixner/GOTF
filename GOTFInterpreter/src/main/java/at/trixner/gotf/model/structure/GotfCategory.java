package at.trixner.gotf.model.structure;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GotfCategory extends GotfType implements Serializable {
    private String category;
    private List<GotfType> value;
}
