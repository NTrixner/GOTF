package at.trixner.gotf.model.origins;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OriginList implements Serializable {
    private List<Origin> value;
}
