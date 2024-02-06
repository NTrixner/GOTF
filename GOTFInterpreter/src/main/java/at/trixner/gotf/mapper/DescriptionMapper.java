package at.trixner.gotf.mapper;

import at.trixner.gotf.model.structure.Description;

public class DescriptionMapper {
    public static String map(Description description) {
        return String.join("\\newline\n", description.getText());
    }
}
