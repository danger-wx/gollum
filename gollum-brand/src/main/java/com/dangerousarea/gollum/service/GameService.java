package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.domain.entities.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GameService {
    /**
     * 创建游戏场次
     * @param game
     * @param request
     * @return
     */
    CommonResult<Game> create(Game game, HttpServletRequest request);

    /**
     * 删除游戏场次
     * @param id
     * @param brandId
     * @param request
     * @return
     */
    CommonResult delete(Long id, Long brandId, HttpServletRequest request);

    CommonResult<Game> update(Game game, HttpServletRequest request);

    CommonResult<List<Game>> getBrandGames(Long brandId, HttpServletRequest request);
}
