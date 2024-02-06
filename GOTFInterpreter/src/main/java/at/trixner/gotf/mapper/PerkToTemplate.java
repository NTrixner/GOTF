package at.trixner.gotf.mapper;

import at.trixner.gotf.model.perks.ranks.FamiliesProgression;
import at.trixner.gotf.model.perks.ranks.FormulaProgression;
import at.trixner.gotf.model.perks.ranks.ListProgression;
import at.trixner.gotf.model.perks.Perk;
import at.trixner.gotf.texModel.TemplatePerk;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.parser.ParseException;

import java.util.Map;

public class PerkToTemplate {
    public static TemplatePerk map(Perk model, Map<String, String> replaceVals) throws EvaluationException, ParseException {
        TemplatePerk template = new TemplatePerk();
        template.setName(model.getName());
        template.setLabel(model.getLabel());
        template.setTags(String.join(", ", model.getTags()));
        template.setDescription(DescriptionMapper.map(model.getDescription()));
        template.setRequirements(RequirementsMapper.map(model.getRequirements()));
        template.setCost(model.getCost() == null ? null : StringReplacer.replaceToLong(model.getCost(), replaceVals));

        if (model.getProgression() != null) {
            if (model.getProgression() instanceof ListProgression progression) {
                template.setRanks(ListProgressionMapper.map(progression, replaceVals));
            }
            if (model.getProgression() instanceof FamiliesProgression progression) {
                template.setFamilies(FamilyProgressionMapper.map(progression, replaceVals));
            }
            if (model.getProgression() instanceof FormulaProgression progression) {
                template.setRanks(FormulaProgressionMapper.map(progression, model, replaceVals));
            }
        }

        return template;
    }
}
