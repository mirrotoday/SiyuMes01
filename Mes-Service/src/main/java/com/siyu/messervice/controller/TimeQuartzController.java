package com.siyu.messervice.controller;

import com.siyu.messervice.entity.TimeQuartz;
import com.siyu.messervice.service.ITimeQuartzService;
import org.springframework.web.bind.annotation.*;
import result.ApiResult;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

/**
 * @author SiYu
 */
@RequestMapping("/api/Quartz")
@RestController
public class TimeQuartzController {

    @Resource
    private ITimeQuartzService timeQuartzService;

    /**
     * 新增事务记录
     * @param timeQuartz
     * @return
     */
    @PostMapping("/createQuartz")
    public ApiResult createTimeQuartz(@RequestBody TimeQuartz timeQuartz){
        return  timeQuartzService.createTimeQuartz(timeQuartz);
    }

    /**
     * 更新事务配置数据
     * @param timeQuartz
     * @return
     */
    @PostMapping("/updateQuartz")
    public ApiResult updateTimeQuartz(@RequestBody TimeQuartz timeQuartz){
        return  timeQuartzService.updateTimeQuartz(timeQuartz);
    }

    /**
     * 删除已有的事务数据
     * @param quartzNumber
     * @return
     */
    @PostMapping("/deleteQuartz")
    public ApiResult deleteTimeQuartz(@RequestBody String quartzNumber){
        return  timeQuartzService.deleteTimeQuartz(quartzNumber);
    }

    /**
     * 开始执行事务，更新事务的状态并执行
     * @param quartzNumber 事务的编码
     * @param className 类名全路径
     * @param cron 周期表达式
     * @return
     */
    @PostMapping("/begainQuartz")
    public ApiResult begainTimeQuartz(@RequestParam String quartzNumber, String className, String cron){
        return  timeQuartzService.begainTimeQuartz(quartzNumber,className,cron);
    }

    /**
     * 结束事务,即更新事务的状态
     * @param quartzNumber 事务的编码
     * @return
     */
    @PostMapping("/endQuartz")
    public ApiResult endTimeQuartz(@RequestBody String quartzNumber){
        return  timeQuartzService.endTimeQuartz(quartzNumber);
    }
}
