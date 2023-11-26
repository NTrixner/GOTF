package at.trixner.gotf.mapper;

import at.trixner.gotf.model.Description;

public class DescriptionMapper {
    public static String map(Description description) {
        return String.join("\\\\\n", description.getText());
    }
}
