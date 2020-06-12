package com.dangerousarea.gollum.domain.dto;

import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.domain.entities.GamePayment;
import com.dangerousarea.gollum.domain.entities.Store;
import com.dangerousarea.gollum.domain.entities.Theme;
import lombok.Data;

import java.util.List;

@Data
public class GameDto extends Game {
    private Theme theme;
    private Store store;
    private List<GamePayment> payments;
}
