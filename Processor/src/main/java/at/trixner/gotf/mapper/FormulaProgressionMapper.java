package at.trixner.gotf.mapper;

import at.trixner.gotf.model.FormulaProgression;
import at.trixner.gotf.model.Perk;
import at.trixner.gotf.templatemodel.Rank;
import com.google.code.mathparser.MathParser;
import com.google.code.mathparser.impl.MathParserImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormulaProgressionMapper {
    public static final MathParser parser = new MathParserImpl();

    public static List<Rank> map(FormulaProgression progression, Perk model) {
        String value = progression.getValue();
        long amount = progression.getAmountWritten();
        List<Rank> ranks = new ArrayList<>();
        long costOldRank = model.getCost();
        for (long newRank = 2; newRank < amount - 1; newRank++) {
            Map<String, String> replaceVals = new HashMap<>();
            replaceVals.put("costOldRank", costOldRank + "");
            replaceVals.put("newRank", newRank + "");
            long costNewRank = parser.calculate(StringReplacer.replace(value, replaceVals))
                    .doubleValue()
                    .longValue();

            Rank rank = new Rank();
            rank.setRank(RomanNumeralMapper.roman(newRank));
            rank.setCost(costNewRank);
            rank.setDescription("");

            ranks.add(rank);

            costOldRank = costNewRank;
        }
        return ranks;
    }
}
