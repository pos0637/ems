
/**
 * 弹出窗口
 * 
 * @export
 * @class Popup
 */
export default class Popup {
    /**
     * 弹出窗口
     * 
     * @static
     * @param {any} title 标题
     * @param {any} width 宽度
     * @param {any} height 高度
     * @param {any} url 地址
     * @param {any} isMaximize 是否最大化
     * @param {any} onClose 销毁后回调函数
     * @returns 弹出窗口索引
     * @memberof Popup
     */
    static show(title, width, height, url, isMaximize, onClose) {
        if ($.isEmpty(width))
            width = '700px';

        if ($.isEmpty(height))
            height = '400px';

        if (parseInt(width.replace(/[^0-9]/ig, '')) > $(window.top.document).width())
            width = $(window.top.document).width() + 'px';

        if (parseInt(height.replace(/[^0-9]/ig, '')) > $(window.top.document).height())
            height = $(window.top.document).height() + 'px';

        let layerId = top.layer.open({
            type: 2, // iframe层
            title: title,
            area: [width, height],
            fixed: true,
            scrollbar: true,
            maxmin: true,
            content: url,
            end: onClose
        });

        if (isMaximize)
            layui.layer.full(layerId);

        return layerId;
    }

    /**
     * 询问提示框
     * 
     * @static
     * @param {any} title 标题
     * @param {any} text 内容
     * @param {any} onConfirm 确认回调函数 
     * @memberof Popup
     */
    static confirm(title, text, onConfirm) {
        top.layer.confirm(text, {
            title: title,
            resize: false,
            btn: ['确定', '取消'],
            btnAlign: 'c', // 按钮居中对齐
            anim: 1, // 从上掉落
            icon: 3
        }, (layerId) => {
            top.layer.close(layerId);
            onConfirm && onConfirm();
        });
    }
}

/**
 * 依赖模块
 */
Popup.imports = ['layer'];