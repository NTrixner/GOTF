package at.trixner.gotf.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class GotfSystem implements Serializable {
    private String systemName;
    private List<String> authors;
    private Date created;
    private String description;
    private Gaopi gapoi;
}
