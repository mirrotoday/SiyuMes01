package com.siyu.messervice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.siyu.messervice.entity.TimeQuartz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author SiYu
* @description 针对表【timequartz】的数据库操作Mapper
* @createDate 2023-01-10 11:46:24
* @Entity com.siyu.messervice.entity.TimeQuartz
*/
@Mapper
public interface ITimeQuartzMapper extends BaseMapper<TimeQuartz> {
    /**
     * 插入数据
     * @param entity
     * @return
     */
    @Override
    int insert(TimeQuartz entity);

    /**
     * 编码是否存在
     * @param number
     * @return
     */
    @Select("select COUNT(id) from timequartz where number = #{number}")
    int selectByNumber(String number);
}




