/*
 *   编写日期：2022/6/8 11:54
 *   作者：张赵
 *   说明：
 */

package result;

import lombok.Data;
import utils.DateUtils;

public class ApiResult<T> extends ApiResultMsg {
    /**
     * 扩展
     */
    public Object extend;

    /**
     * 结果
     */
    public T data;
    /**
     * @return 操作成功
     */
    public ApiResult<T> success() {
        return this.success("操作成功！");
    }

    /**
     * @param message 消息体
     * @return 操作成功
     */
    public ApiResult<T> success(String message) {
        return this.result(message, 0, true, null, 0, null);
    }

    /**
     * @param data 消息体
     * @return 操作成功
     */
    public ApiResult<T> success(T data) {
        return this.result("操作成功！", 0, true, data, 0, null);
    }

    /**
     * 操作失败
     */
    public ApiResult<T> error() {
        return this.error("对不起，操作失败！");
    }

    /**
     * @param message 消息体
     * @return 操作失败
     */
    public ApiResult<T> error(String message) {
        return this.result(message, -1, false, null, 0, null);
    }

    /**
     * @param data 数据
     * @return 操作结果
     */
    public ApiResult<T> result(T data) {
        return this.result(data, 0);
    }

    /**
     * @param data  数据
     * @param total 记录条数
     * @return 操作结果
     */
    public ApiResult<T> result(T data, long total) {
        return this.result(data, total, null);
    }

    /**
     * @param data   数据
     * @param total  记录条数
     * @param extend 扩展
     * @return 操作结果
     */
    public ApiResult<T> result(T data, long total, Object extend) {
        return this.result("操作成功！", 0, true, data, total, extend);
    }

    /**
     * @param message 消息体
     * @param code    错误代码
     * @param success 状态，true：成功，false：失败
     * @param data    数据
     * @param total   记录条数
     * @param extend  扩展
     * @return 操作结果
     */
    public ApiResult<T> result(String message, int code, boolean success, T data, long total, Object extend) {
        ApiResult<T> result = new ApiResult<>();
        result.message = message;
        result.code = code;
        result.success = success;
        result.data = data;
        result.total = total;
        result.extend = extend;
        result.timeStamp = DateUtils.getTick();
        return result;
    }
}
