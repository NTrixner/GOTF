package at.trixner.gotf.mapper;

import at.trixner.gotf.model.perks.ranks.ListProgression;
import at.trixner.gotf.model.perks.PerkIteration;
import at.trixner.gotf.texModel.Rank;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ListProgressionMapper {
    public static List<Rank> map(ListProgression progression, Map<String, String> replaceVals) {
        return progression
                .getValue()
                .stream()
                .sorted(Comparator.comparing(PerkIteration::getRank))
                .map(rank -> {
                    Rank r = new Rank();
                    r.setCost(rank.getCost());
                    r.setRank(RomanNumeralMapper.roman(rank.getRank()));
                    r.setDescription(DescriptionMapper.map(rank.getDescription()));
                    return r;
                }).toList();
    }
}
