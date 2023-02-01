/*
 *   编写日期：2022/6/8 11:54
 *   作者：张赵
 *   说明：
 */

package result;

import lombok.Data;


public class ApiResultMsg {
    /**
     * 状态，true：成功，false：失败
     */
    public boolean success;

    /**
     * 状态码
     */
    public Integer code;

    /**
     * 操作消息
     */
    public String message;

    /**
     * 时间戳
     */
    public long timeStamp;

    /**
     * 记录条数，查询使用
     */
    public long total;
}
