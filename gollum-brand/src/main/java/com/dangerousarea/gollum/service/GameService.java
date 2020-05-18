package com.dangerousarea.gollum.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.domain.entities.Store;
import com.dangerousarea.gollum.domain.search.GameSearch;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GameService extends IService<Game> {
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

    CommonResult<IPage<Game>> getBrandGames(Long brandId, Page<Game> page, Game game, HttpServletRequest request);
}
