package at.trixner.gotf.model.structure;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GotfCategoryList<T extends GotfItem> implements Serializable {
    private List<GotfCategory<T>> value;
}
