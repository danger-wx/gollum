package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "场次服务")
@RestController
@Slf4j
@RequestMapping("/game")
public class GameController extends BaseController {

    @Autowired
    private GameService gameService;

    @ApiOperation("创建场次")
    @PostMapping
    public CommonResult<Game> create(@RequestBody Game game){
        game.setBrandId(getLoginBrandId());
        return gameService.create(game, getRequest());
    }

    @ApiOperation("删除场次")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id){
        return gameService.delete(id, getLoginBrandId(), getRequest());
    }
}
