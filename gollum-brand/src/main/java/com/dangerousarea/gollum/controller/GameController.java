package com.dangerousarea.gollum.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.dto.GameDto;
import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "场次服务")
@RestController
@Slf4j
@RequestMapping("/game")
public class GameController extends BaseController {

    @Autowired
    private GameService gameService;

    @ApiOperation("创建场次")
    @PostMapping
    public CommonResult<GameDto> create(@RequestBody GameDto game){
        game.setBrandId(getLoginBrandId());
        game.setOperator(getLoginUserId());
        return gameService.create(game, getRequest());
    }

    @ApiOperation("删除场次")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id){
        return gameService.delete(id, getLoginBrandId(), getRequest());
    }

    @ApiOperation("编辑场次信息")
    @PutMapping("")
    public CommonResult eidt(@RequestBody Game game){
        game.setOperator(getLoginUserId());
        game.setUpdateTime(new Date());
        game.setBrandId(getLoginBrandId());
        return gameService.update(game, getRequest());
    }

    @ApiOperation("获取当前品牌场次")
    @GetMapping("/all")
    public CommonResult<IPage<Game>> allGames(Page<Game> page, Game game){
        return gameService.getBrandGames(getLoginBrandId(), page, game, getRequest());
    }

    @ApiOperation("获取当前品牌场次详细信息")
    @GetMapping("/detail/all")
    public CommonResult<IPage<GameDto>> allGameDetails(Page<Game> page, Game game){
        return gameService.getBrandGameDetails(getLoginBrandId(), page, game, getRequest());
    }
}
