package at.trixner.gotf.model;

import lombok.Data;

import java.util.List;

@Data
public class ListProgression extends Progression{
    private List<PerkIteration> value;
}
