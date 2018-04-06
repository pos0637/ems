
/**
 * 对象是否为空
 * 
 * @param {any} object 对象
 * @returns 是否为空
 */
$.isEmpty = function (object) {
    return (typeof (object) === 'undefined') || (object === null);
};

/**
 * 对象是否为非空字符串
 * 
 * @param {any} object 对象
 * @returns 是否为非空字符串
 */
$.isNotBlank = function (object) {
    return (!$.isEmpty(object) && (object !== ''));
};

/**
 * 将元素属性赋值给集合
 * 
 * @param {any} map 集合
 * @param {any} element 元素
 * @param {any} ...attrs 属性名称
 * @returns 集合
 */
$.assignAttr = function (map, element, ...attrs) {
    for (let i = 2; i < arguments.length; ++i) {
        let attr = arguments[i];
        let value = element.attr(attr);
        if (!$.isEmpty(value))
            map[attr] = value;
    }

    return map;
};

/**
 * 获取URL地址
 * 
 * @param {any} url 相对URL地址
 * @returns URL地址
 */
$.url = function (url) {
    let server = $.config.request.server;
    return server.scheme + '://' + server.host + ':' + server.port + '/' + server.application + server.path + url;
};

/**
 * 获取URL请求参数
 *
 * @returns URL请求参数
 */
$.getUrlParams = function () {
    let param = window.location.search;
    let pattern = /([^?&=]+)=([^&#]*)/g;
    let dict = {};
    let search = null;
    if (typeof param === 'object' && param instanceof Location)
        search = param.search;
    else if (typeof param === 'string')
        search = param;
    else
        return {};

    search.replace(pattern, function (rs, $1, $2) {
        let key = decodeURIComponent($1);
        let value = decodeURIComponent($2);
        dict[key] = value;
        return rs;
    });

    return dict;
};

/**
 * 获取参数列表
 * 
 * @param {any} list 列表
 * @param {any} key 属性名称
 * @returns 参数列表
 */
$.getParams = function (list, key) {
    if ($.isEmpty(list))
        return [];

    let ret = [];
    $.each(list, function (i, item) {
        ret.push(item[key]);
    });

    return ret;
};

/**
 * 获取结果
 * 
 * @param {any} key 属性名称
 * @returns 值
 */
$.getResult = function (key) {
    let element = top.$('meta[name="' + key + '"]');
    return (element.length === 0) ? undefined : element.attr('content');
};

/**
 * 设置结果
 * 
 * @param {any} key 属性名称
 * @param {any} value 值
 */
$.setResult = function (key, value) {
    let element = top.$('meta[name="' + key + '"]');
    (element.length === 0) ? top.$('head').append('<meta name="' + key + '" content="' + value + '" />') : element.attr('content', value);
};

/**
 * 关闭当前窗口
 */
$.closeWindow = function () {
    parent.layer.close(parent.layer.getFrameIndex(window.name));
};

/**
 * 获取UUID
 *
 * @returns UUID
 */
$.uuid = function () {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

/**
 * 获取属性
 * 
 * @param {any} attributeName 属性名称
 * @param {any} defaultValue 默认值
 * @returns 属性
 */
$.fn.getAttr = function (attributeName, defaultValue) {
    return $.isEmpty(this.attr(attributeName)) ? defaultValue : this.attr(attributeName);
};

/**
 * 填充子元素
 * 
 * @param {any} data 数据
 */
$.fn.fill = function (data) {
    if ($.isEmpty(data))
        return;

    let _this = this;
    this.each(function () {
        $.each(data, function (key, value) {
            let elements = _this.find('[name="' + key + '"]');
            if (elements.length === 1) {
                elements.val(elements.attr('type') === 'checkbox' ? [value] : value);
            }
            else {
                if (elements.attr('type') === 'checkbox')
                    !$.isEmpty(value) && elements.val(value.split(','));
                else
                    elements.val([value]);
            }
        });
    });
};

/**
 * URL请求参数
 */
$.urlParams = $.getUrlParams();