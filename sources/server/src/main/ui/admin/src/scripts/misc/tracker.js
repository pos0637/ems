
/**
 * 日志
 * 
 * @export
 * @class Tracker
 */
export default class Tracker {
    /**
     * 跟踪信息
     * 
     * @param {any} content 
     * @memberof Tracker
     */
    static trace(content) {
        console.trace('trace: ' + content);
    }

    /**
     * 提示信息
     * 
     * @param {any} content 
     * @memberof Tracker
     */
    static info(content) {
        console.info('info: ' + content);
    }

    /**
     * 提示异常
     * 
     * @param {any} content 
     * @memberof Tracker
     */
    static error(content) {
        console.error('error: ' + content);
    }
}