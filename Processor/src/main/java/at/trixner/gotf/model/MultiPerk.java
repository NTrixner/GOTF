package at.trixner.gotf.model;

import lombok.Data;

import java.util.List;

@Data
public class MultiPerk extends Perk{
    private List<String> value;
}
