package com.dangerousarea.gollum.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dangerousarea.gollum.common.define.DataStatusDefine;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.GameMapper;
import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.service.GameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Resource
    private GameMapper gameMapper;

    @Override
    public CommonResult<Game> create(Game game, HttpServletRequest request) {

        if(game.getBrandId() == null || game.getStoreId() == null  || game.getThemeId() == null){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
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
    public CommonResult<List<Game>> getBrandGames(Long brandId, HttpServletRequest request) {
        //TODO 分页插件
        return null;
    }
}
