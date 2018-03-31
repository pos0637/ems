
import BaseComponent from '../base';
import Hint from '../../misc/hint';

/**
 * 上传组件
 * 
 * @export
 * @class Upload
 * @extends {BaseComponent}
 */
export default class Upload extends BaseComponent {
    constructor(props) {
        super(props);
    }

    render() {
        $.setResult($.config.upload.result, null);

        let accept, exts, size;
        if (!$.isEmpty($.urlParams['params'])) {
            let params = JSON.parse(unescape($.urlParams['params']));
            accept = params.accept;
            exts = params.exts;
            size = params.size;
        }

        let chooseFile = false;
        let layerId;

        layui.upload.render({
            elem: '.layui-upload-drag',
            url: $.url($.config.upload.url),
            accept: accept,
            exts: exts,
            size: size,
            auto: false,
            bindAction: '#upload',
            choose: function (obj) {
                chooseFile = true;
                obj.preview(function (index, file) {
                    $('#fileName').html(file.name);
                });
            },
            before: function () {
                if (!chooseFile) {
                    Hint.showErrorMsg('请选择文件!');
                    return;
                }

                layerId = layui.layer.load();
            },
            done: (result) => {
                layui.layer.closeAll(layerId);

                if (!this.checkResult(result)) {
                    Hint.showErrorMsg('上传失败!');
                    return;
                }

                $.setResult($.config.upload.result, result[$.config.upload.dataName]);
                $.closeWindow();
            },
            error: () => {
                layui.layer.closeAll(layerId);
                Hint.showErrorMsg('上传失败!');
            }
        });

        this._bindEvents();
    }

    /**
     * 检查返回结果是否正确
     * 
     * @param {any} result 返回结果
     * @returns 是否正确
     * @memberof Upload
     */
    checkResult(result) {
        return (result && (result[$.config.request.response.statusName] === $.config.request.response.successCode));
    }

    /**
     * 绑定事件
     * 
     * @memberof Upload
     */
    _bindEvents() {
        let _this = this;
        let elements = this.element.find('.layui-btn');
        $.each(elements, function () {
            let botton = $(this);
            let method = botton.attr('lay-event');
            if ($.isEmpty(method))
                return;

            botton.off('click');
            botton.on('click', function () {
                if (method === 'close')
                    _this._onCloseButtonClick(botton);
            });
        });
    }

    /**
     * 关闭按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof Upload
     */
    _onCloseButtonClick(sender) {
        $.closeWindow();
    }
}

/**
 * DOM节点属性
 */
Upload.filter = 'div.Upload';

/**
 * 依赖模块
 */
Upload.imports = ['upload', 'layer'];