package com.microdaway.xypt.controller;

import com.microdaway.xypt.entity.Result;
import com.microdaway.xypt.entity.Rider;
import com.microdaway.xypt.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderController {
    @Autowired
    private RiderService riderService;

    //骑手认证
    @PostMapping("/certification")
    public Result examine(@RequestBody Rider rider) {
        riderService.examine(rider);
        return Result.success();
    }

    //获取骑手信息
    @GetMapping("/get")
    public Result<Rider> get() {
        Rider rider = riderService.get();
        return Result.success(rider);
    }

    //修改骑手信息
    @PutMapping("/update")
    public Result update(@RequestBody Rider rider) {
        riderService.update(rider);
        return Result.success();
    }

    //获取待审核的骑手
    @GetMapping("/awaitExamine")
    public Result<List<Rider>> awaitExamine() {
        List<Rider> riderList = riderService.awaitExamine();
        return Result.success(riderList);
    }

    //审核通过
    @PutMapping("/examinePass")
    public Result examinePass(Integer id, Integer userId) {
        riderService.examinePass(id, userId);
        return Result.success();
    }
}
