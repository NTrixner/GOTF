package at.trixner.gotf.model;

import lombok.Data;

import java.util.Map;

@Data
public class FormulaProgression extends Progression {
    private String value;
    private Description description;
    private Long amountWritten;
}
