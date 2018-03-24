
import Hint from './hint';

/**
 * 请求
 * 
 * @export
 * @class Request
 */
export default class Request {
    constructor(url, params) {
        this.url = $.url(url);
        this.params = params;
    }

    get(success, error, complete) {
        return this.do('get', success, error, complete);
    }

    post(success, error, complete) {
        return this.do('post', success, error, complete);
    }

    put(success, error, complete) {
        return this.do('put', success, error, complete);
    }

    delete(success, error, complete) {
        return this.do('delete', success, error, complete);
    }

    batchDelete(success, error, complete) {
        this.params = {
            delete: JSON.stringify(this.params)
        };

        return this.do('post', success, error, complete);
    }

    /**
     * 执行请求
     * 
     * @param {string} method 请求方式
     * @param {function} success 请求成功处理函数
     * @param {function} error 请求失败处理函数
     * @param {function} complete 请求完成处理函数
     * @memberof Request
     */
    do(method, success, error, complete) {
        let layerId = layui.layer.load();

        $.ajax({
            url: this.url,
            type: method,
            async: true,
            data: this.params,
            dataType: 'json',
            success: (result) => {
                if (!this.checkResult(result)) {
                    error && error(result);
                    return;
                }

                (success) ? success(result) : this.onSuccess();
            },
            error: (XMLHttpRequest) => {
                (error) ? error(XMLHttpRequest.responseText) : this.onError(XMLHttpRequest.responseText);
            },
            complete: () => {
                layui.layer.close(layerId);
                complete && complete();
            }
        });
    }

    /**
     * 检查返回结果是否正确
     * 
     * @param {any} result 返回结果
     * @returns 是否正确
     * @memberof Request
     */
    checkResult(result) {
        return (result && (result[$.config.request.response.statusName] === $.config.request.response.successCode));
    }

    /**
     * 成功处理函数
     *
     * @memberof Request
     */
    onSuccess() {
        Hint.showSuccessMsg('操作成功!');
    }

    /**
     * 错误处理函数
     * 
     * @param {string} result 结果
     * @memberof Request
     */
    onError(result) {
        let handler = () => { Hint.showErrorMsg('操作失败!'); };
        if (result && (result.length > 0)) {
            try {
                let message = JSON.parse(result)[$.config.request.response.msgName];
                $.isEmpty(message) ? handler() : Hint.showErrorMsg('操作失败:' + message);
            }
            catch (e) {
                handler();
            }
        }
        else {
            handler();
        }
    }
}

/**
 * 依赖模块
 */
Request.imports = ['layer'];