package at.trixner.gotf.mapper;

import at.trixner.gotf.model.FamiliesProgression;
import at.trixner.gotf.templatemodel.FamilyProgression;

import java.util.*;

public class FamilyProgressionMapper {
    public static FamilyProgression map(FamiliesProgression progression) {

        FamilyProgression out = new FamilyProgression();
        out.setHeaders(progression.getItemHeaders());
        out.setEntries(new TreeMap<>());
        progression.getValue().forEach(family -> {
            ArrayList<List<String>> familyEntries = new ArrayList<>();
            out.getEntries().put(family.getFamily(), familyEntries);
            family.getValues().forEach(entry -> {
                ArrayList<String> entryColumns = new ArrayList<>();
                out.getHeaders().forEach(header -> {
                    entryColumns.add(entry.get(header).toString());
                });
                familyEntries.add(entryColumns);
            });
        });

        return out;
    }
}
