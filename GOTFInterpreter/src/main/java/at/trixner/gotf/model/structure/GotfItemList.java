package at.trixner.gotf.model.structure;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class GotfItemList<T extends GotfItem> implements Serializable {
    private List<T> value;
}
