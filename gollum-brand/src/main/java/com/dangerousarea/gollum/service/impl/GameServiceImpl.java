package com.dangerousarea.gollum.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangerousarea.gollum.common.define.DataStatusDefine;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.GameMapper;
import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.domain.entities.Theme;
import com.dangerousarea.gollum.domain.search.GameSearch;
import com.dangerousarea.gollum.service.GameService;
import com.dangerousarea.gollum.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

    @Resource
    private GameMapper gameMapper;

    @Autowired
    private ThemeService themeService;

    @Override
    public CommonResult<Game> create(Game game, HttpServletRequest request) {

        if(game.getBrandId() == null || game.getThemeId() == null){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }

        Theme theme = themeService.getThemeById(game.getThemeId());
        if(game.getStoreId() != null && !game.getStoreId().equals(theme.getStoreId())) {
            //如果前端传入的门店Id和主题所在门店Id不同，则使用主题所在门店Id
            game.setStoreId(theme.getStoreId());
        }

        int result = gameMapper.insert(game);
        if(result > 0){
            return CommonResult.success(game);
        }
        return CommonResult.error(ErrorCodes.FAIL_TO_INSERT);
    }

    @Override
    public CommonResult delete(Long id, Long brandId, HttpServletRequest request) {
        if(id == null){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }
        UpdateWrapper<Game> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("data_status", DataStatusDefine.DELETE)
                .eq("id", id).eq("brand_id", brandId);
        int result = gameMapper.update(null, updateWrapper);
        if(result > 0){
            return CommonResult.success(true);
        }
        return CommonResult.error(ErrorCodes.FAIL_TO_DELETE);
    }

    @Override
    public CommonResult<Game> update(Game game, HttpServletRequest request) {
        int result = gameMapper.updateById(game);
        if(result > 0){
            return CommonResult.success(game);
        }
        return CommonResult.error(ErrorCodes.FAIL_TO_UPDATE);
    }

    @Override
    public CommonResult<IPage<Game>> getBrandGames(Long brandId, Page<Game> page, Game game
            , HttpServletRequest request) {
        QueryWrapper<Game> wrapper = new QueryWrapper();
        wrapper.eq("brand_id", brandId);

        if(game.getStoreId() != null)
            wrapper.eq("store_id", game.getStoreId());
        if(game.getThemeId() != null)
            wrapper.eq("theme_id", game.getThemeId());
        if(game.getStartTime() != null)
            wrapper.ge("start_time", game.getStartTime());
        if(game.getEndTime() != null)
            wrapper.lt("end_time", game.getEndTime());

        return CommonResult.success(gameMapper.selectPage(page, wrapper));
    }
}
