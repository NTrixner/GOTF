package at.trixner.gotf.model;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Data
public class FamiliesEntries {
    private String family;
    List<Properties> values;
}
