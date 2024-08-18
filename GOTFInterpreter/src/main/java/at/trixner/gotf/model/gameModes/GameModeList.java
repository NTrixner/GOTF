package at.trixner.gotf.model.gameModes;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GameModeList implements Serializable {
    private List<GameMode> required;
    private List<GameMode> optional;
}
