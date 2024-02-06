package at.trixner.gotf.mapper;

import at.trixner.gotf.model.perks.MultiPerk;
import at.trixner.gotf.model.perks.Perk;
import at.trixner.gotf.texModel.TemplatePerk;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiPerkToTemplate {
    public static List<TemplatePerk> map(MultiPerk perk, Map<String, String> replaceVals) throws EvaluationException, ParseException {
        List<TemplatePerk> outputPerk = new ArrayList<>();
        List<String> values = perk.getValue();
        for(int i = 0; i < values.size(); i++)
        {
            replaceVals.put("value", values.get(i));

            if(perk.getParams() != null)
            {
                List<String> params = perk.getParams().get(i);
                for(int p = 0; p < params.size(); p++)
                {
                    replaceVals.put("param" + (p+1), params.get(p));
                }
            }
            Perk newPerk = new Perk();
            newPerk.setName(StringReplacer.replace(perk.getName(), replaceVals));
            newPerk.setLabel(StringReplacer.replace(perk.getLabel(), replaceVals));
            newPerk.setCost(perk.getCost());
            newPerk.setRequirements(perk.getRequirements());
            newPerk.setTags(perk.getTags());
            newPerk.setDescription(DescriptionReplacer.replace(perk.getDescription(), replaceVals));
            newPerk.setProgression(perk.getProgression());
            outputPerk.add(PerkToTemplate.map(newPerk, replaceVals));
        }
        return outputPerk;
    }
}