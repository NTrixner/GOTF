package at.trixner.gotf.mapper;

import at.trixner.gotf.model.ListProgression;
import at.trixner.gotf.templatemodel.Perk;

import java.util.Arrays;

public class PerkToTemplate {
    public static Perk map(at.trixner.gotf.model.Perk model)
    {
        Perk template = new Perk();
        template.setName(model.getName());
        template.setLabel(model.getLabel());
        template.setTags(String.join(", ", model.getTags()));
        template.setDescription(DescriptionMapper.map(model.getDescription()));
        template.setRequirements(RequirementsMapper.map(model.getRequirements()));
        template.setCost(model.getCost());

        if(model.getProgression() != null)
        {
            if(model.getProgression() instanceof ListProgression progression)
            {
                template.setRanks(ListProgressionMapper.map(progression));
            }
        }

        return template;
    }
}
