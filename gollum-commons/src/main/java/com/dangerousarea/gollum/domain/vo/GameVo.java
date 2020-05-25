package com.dangerousarea.gollum.domain.vo;

import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.domain.entities.Store;
import com.dangerousarea.gollum.domain.entities.Theme;
import lombok.Data;

@Data
public class GameVo extends Game {
    private Theme theme;
    private Store store;
}
