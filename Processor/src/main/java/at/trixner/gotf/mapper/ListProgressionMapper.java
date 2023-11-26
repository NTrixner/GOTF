package at.trixner.gotf.mapper;

import at.trixner.gotf.model.Description;
import at.trixner.gotf.model.ListProgression;
import at.trixner.gotf.model.PerkIteration;
import at.trixner.gotf.templatemodel.Rank;

import java.util.Comparator;
import java.util.List;

public class ListProgressionMapper {
    public static List<Rank> map(ListProgression progression) {
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
