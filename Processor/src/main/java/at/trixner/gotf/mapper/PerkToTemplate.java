package at.trixner.gotf.mapper;

import at.trixner.gotf.model.FamiliesProgression;
import at.trixner.gotf.model.FormulaProgression;
import at.trixner.gotf.model.ListProgression;
import at.trixner.gotf.templatemodel.TemplatePerk;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.parser.ParseException;

import javax.script.ScriptException;

public class PerkToTemplate {
    public static TemplatePerk map(at.trixner.gotf.model.Perk model) throws EvaluationException, ParseException {
        TemplatePerk template = new TemplatePerk();
        template.setName(model.getName());
        template.setLabel(model.getLabel());
        template.setTags(String.join(", ", model.getTags()));
        template.setDescription(DescriptionMapper.map(model.getDescription()));
        template.setRequirements(RequirementsMapper.map(model.getRequirements()));
        template.setCost(model.getCost());

        if (model.getProgression() != null) {
            if (model.getProgression() instanceof ListProgression progression) {
                template.setRanks(ListProgressionMapper.map(progression));
            }
            if (model.getProgression() instanceof FamiliesProgression progression) {
                template.setFamilies(FamilyProgressionMapper.map(progression));
            }
            if (model.getProgression() instanceof FormulaProgression progression) {
                template.setRanks(FormulaProgressionMapper.map(progression, model));
            }
        }

        return template;
    }
}
