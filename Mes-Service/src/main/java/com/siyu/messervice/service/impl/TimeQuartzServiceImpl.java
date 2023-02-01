package com.siyu.messervice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.siyu.messervice.entity.TimeQuartz;
import com.siyu.messervice.mapper.ITimeQuartzMapper;
import com.siyu.messervice.service.ITimeQuartzService;
import org.springframework.stereotype.Service;
import result.ApiResult;

import javax.annotation.Resource;

/**
* @author SiYu
* @description 针对表【timequartz】的数据库操作Service实现
* @createDate 2023-01-10 11:46:24
*/
@Service
public class TimeQuartzServiceImpl extends ServiceImpl<ITimeQuartzMapper, TimeQuartz>
    implements ITimeQuartzService {

    @Resource
    private ITimeQuartzMapper timeQuartzMapper;

    @Override
    public ApiResult createTimeQuartz(TimeQuartz timeQuartz) {
      int isExit = timeQuartzMapper.selectByNumber(timeQuartz.getNumber().toString());
       if(isExit > 0){
           return  new ApiResult().error("新增失败,该编码已存在");
       }
        int count = timeQuartzMapper.insert(timeQuartz);
        if(count > 0){
            return new ApiResult().success(timeQuartz);
        }else{
            return new ApiResult().error("新增失败!");
        }
    }

    @Override
    public ApiResult updateTimeQuartz(TimeQuartz timeQuartz) {
        return null;
    }

    @Override
    public ApiResult deleteTimeQuartz(String quartzNumber) {
        return null;
    }

    @Override
    public ApiResult begainTimeQuartz(String quartzNumber, String className, String cron) {
        return null;
    }

    @Override
    public ApiResult endTimeQuartz(String quartzNumber) {
        return null;
    }
}




