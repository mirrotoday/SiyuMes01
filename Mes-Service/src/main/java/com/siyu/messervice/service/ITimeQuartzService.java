package com.siyu.messervice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.siyu.messervice.entity.TimeQuartz;
import result.ApiResult;

/**
* @author SiYu
* @description 针对表【timequartz】的数据库操作Service
* @createDate 2023-01-10 11:46:24
*/
public interface ITimeQuartzService extends IService<TimeQuartz> {
    /**
     * 新增后台事务数据
     * @param timeQuartz
     * @return
     */
    ApiResult createTimeQuartz(TimeQuartz timeQuartz);

    /**
     * 更新
     * @param timeQuartz
     * @return
     */
    ApiResult updateTimeQuartz(TimeQuartz timeQuartz);

    /**
     * 删除
     * @param quartzNumber
     * @return
     */
    ApiResult deleteTimeQuartz(String quartzNumber);

    /**
     * 开始事务
     * @param quartzNumber
     * @param className
     * @param cron
     * @return
     */
    ApiResult begainTimeQuartz(String quartzNumber, String className, String cron);

    /**
     * 结束
     * @param quartzNumber
     * @return
     */
    ApiResult endTimeQuartz(String quartzNumber);
}
