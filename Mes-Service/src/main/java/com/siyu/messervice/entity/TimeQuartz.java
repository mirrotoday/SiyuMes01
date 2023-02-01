package com.siyu.messervice.entity;

import cn.hutool.core.lang.id.NanoId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author SiYu
 * @TableName timequartz
 */
@TableName(value ="timequartz")
@Data
public class TimeQuartz implements Serializable {
    /**
     * 初始化ID
     * 初始化创建时间
     */
    public TimeQuartz() {
        //初始化生成ID
        this.id = NanoId.randomNanoId(20);
        //初始化创建时间
        this.creattime = new Date();
    }
    /**
     * 主键ID
     */
    @TableId
    private String id;
    /**
     * 类路径
     */
    @TableId
    private String number;
    /**
     * 类路径
     */
    private String classname;

    /**
     * 类路径对应的成员方法
     */
    private String classmethod;

    /**
     * 周期执行表达式
     */
    private String cron;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creattime;

    /**
     * 生效时间
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date effdatetime;

    /**
     * 结束时间
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date leffdatetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TimeQuartz other = (TimeQuartz) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClassname() == null ? other.getClassname() == null : this.getClassname().equals(other.getClassname()))
            && (this.getClassmethod() == null ? other.getClassmethod() == null : this.getClassmethod().equals(other.getClassmethod()))
            && (this.getCron() == null ? other.getCron() == null : this.getCron().equals(other.getCron()))
            && (this.getCreattime() == null ? other.getCreattime() == null : this.getCreattime().equals(other.getCreattime()))
            && (this.getEffdatetime() == null ? other.getEffdatetime() == null : this.getEffdatetime().equals(other.getEffdatetime()))
            && (this.getLeffdatetime() == null ? other.getLeffdatetime() == null : this.getLeffdatetime().equals(other.getLeffdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClassname() == null) ? 0 : getClassname().hashCode());
        result = prime * result + ((getClassmethod() == null) ? 0 : getClassmethod().hashCode());
        result = prime * result + ((getCron() == null) ? 0 : getCron().hashCode());
        result = prime * result + ((getCreattime() == null) ? 0 : getCreattime().hashCode());
        result = prime * result + ((getEffdatetime() == null) ? 0 : getEffdatetime().hashCode());
        result = prime * result + ((getLeffdatetime() == null) ? 0 : getLeffdatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", classname=").append(classname);
        sb.append(", classmethod=").append(classmethod);
        sb.append(", cron=").append(cron);
        sb.append(", creattime=").append(creattime);
        sb.append(", effdatetime=").append(effdatetime);
        sb.append(", leffdatetime=").append(leffdatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}