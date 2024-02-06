package at.trixner.gotf.mapper;

import at.trixner.gotf.model.perks.ranks.FormulaProgression;
import at.trixner.gotf.model.perks.Perk;
import at.trixner.gotf.texModel.Rank;
import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormulaProgressionMapper {


    public static List<Rank> map(FormulaProgression progression, Perk model, Map<String, String> replaceVals) throws EvaluationException, ParseException {
        String value = progression.getValue();
        long amount = progression.getAmountWritten();
        List<Rank> ranks = new ArrayList<>();
        long costOldRank = Long.parseLong(StringReplacer.replace(model.getCost(), replaceVals));
        for (long newRank = 2; newRank < amount + 1; newRank++) {
            replaceVals.put("costOldRank", costOldRank + "");
            replaceVals.put("oldRank", (newRank - 1) + "");
            replaceVals.put("newRank", newRank + "");
            long costNewRank = new Expression(StringReplacer.replace(value, replaceVals))
                    .evaluate()
                    .getNumberValue()
                    .longValue();

            Rank rank = new Rank();
            rank.setRank(RomanNumeralMapper.roman(newRank));
            rank.setCost(costNewRank);
            if (progression.getDescription() != null) {
                rank.setDescription(DescriptionReplacer.getDescription("\\\\\n", progression.getDescription(), replaceVals));
            } else {
                rank.setDescription("");
            }

            ranks.add(rank);

            costOldRank = costNewRank;
        }
        return ranks;
    }
}