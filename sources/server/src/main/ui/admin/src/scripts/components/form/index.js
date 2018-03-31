
import BaseComponent from '../base';
import Hint from '../../misc/hint';
import Popup from '../../misc/popup';
import Request from '../../misc/request';

/**
 * 表单
 * 
 * @export
 * @class Form
 * @extends {BaseComponent}
 */
export default class Form extends BaseComponent {
    constructor(props) {
        super(props);

        /**
         * 请求地址
         */
        this.url = this.element.attr('url');
        if (!$.isEmpty(this.url))
            this.url = layui.laytpl(this.url).render($.urlParams);

        /**
         * 是否自动加载
         */
        this.autoload = this.element.getAttr('autoload', 'false') === 'true';

        /**
         * 是否自动关闭
         */
        this.autoclose = this.element.getAttr('autoclose', 'true') === 'true';

        /**
         * 是否只读
         */
        this.readonly = (!$.isEmpty($.urlParams['readonly'])) && ($.urlParams['readonly'] === 'true');

        /**
         * 编辑器
         */
        this.editors = {};

        // 填充默认数据
        this._renderDefaultData();
    }

    render() {
        this.element.fill($.urlParams);

        let handler = () => {
            this._renderDate();
            this._renderEditor();
            layui.form.render();
            this._bindEvents();
        };

        // 请求数据
        if (this.autoload) {
            new Request(this.url).get((result) => {
                this._renderElements(result);
                handler();
            }, () => {
                Hint.showErrorMsg('获取数据失败!');
                handler();
            });
        }
        else {
            handler();
        }
    }

    /**
     * 填充默认数据
     * 
     * @memberof Form
     */
    _renderDefaultData() {
        this.element.find('input').each(function () {
            let element = $(this);
            let value = element.val();
            if ($.isNotBlank(value))
                element.val(layui.laytpl(value).render($.urlParams));
        });
    }

    /**
     * 渲染元素
     * 
     * @param {any} result 返回数据
     * @memberof Form
     */
    _renderElements(result) {
        // 处理只读属性
        if (this.readonly) {
            this.element.find('button').each(function () {
                let element = $(this);
                (element.attr('lay-event') === 'close') ? element.show() : element.hide();
            });

            this.element.find('input').addClass('layui-disabled').attr('disabled', 'disabled');
            this.element.find('select,textarea').attr('disabled', 'disabled');
        }

        // 合并参数
        let params = result[$.config.request.response.dataName];
        $.extend(params, $.urlParams, params);

        // 填充表单
        this.element[0].reset();
        this.element.fill(params);
    }

    /**
     * 渲染日期空间
     * 
     * @memberof Form
     */
    _renderDate() {
        let _this = this;
        this.element.find('input.DateTime').each(function () {
            let params = $.assignAttr({}, $(this), 'dateRange', 'dateType', 'dateFormat', 'dateMin', 'dateMax');
            let options = { elem: this };
            (params.dateRange === 'true') && (options['range'] = true);
            (!$.isEmpty(params.dateType)) && (options['type'] = params.dateType);
            (!$.isEmpty(params.dateFormat)) && (options['format'] = params.dateFormat);
            (!$.isEmpty(params.dateMin)) && (options['min'] = params.dateMin);
            (!$.isEmpty(params.dateMax)) && (options['max'] = params.dateMax);
            (!$.isEmpty(_this.val())) && (options['value'] = _this.val());
            layui.laydate.render(options);
        });
    }

    /**
     * 渲染编辑器
     * 
     * @memberof Form
     */
    _renderEditor() {
        layui.layedit.set({
            uploadImage: { url: $.url($.config.upload.url) }
        });

        let _this = this;
        this.element.find('textarea').each(function () {
            let params = $.assignAttr({}, $(this), 'id', 'name', 'height');
            if (!$.isEmpty(params.id) && !$.isEmpty(params.name))
                _this.editors[params.name] = layui.layedit.build(params.id, { height: params.height });
        });
    }

    /**
     * 绑定事件
     * 
     * @memberof Form
     */
    _bindEvents() {
        let _this = this;
        let elements = this.element.find('.layui-btn');
        $.each(elements, function () {
            let botton = $(this);
            let filter = botton.attr('lay-filter');
            let method = botton.attr('lay-event');
            if ($.isEmpty(method))
                return;

            botton.off('click');
            botton.on('click', function () {
                if (method === 'close')
                    _this._onCloseButtonClick(botton);
                else if (method === 'upload')
                    _this._onUploadButtonClick(botton);
            });

            !$.isEmpty(filter) && layui.form.on('submit(' + filter + ')', function (data) {
                _this._onSubmitButtonClick(botton, data.field);
            });
        });
    }

    /**
     * 提交按钮点击事件
     * 
     * @param {any} sender 组件
     * @param {any} params 参数
     * @memberof Form
     */
    _onSubmitButtonClick(sender, params) {
        $.each(this.editors, function (key, value) {
            params[key] = layui.layedit.getContent(value);
        });

        let event = sender.attr('lay-event');
        let handler = () => {
            Hint.showSuccessMsg('操作成功!');
            this.autoclose && $.closeWindow();
        };

        // 获取提交URL地址
        let url = sender.attr('url');
        if (!$.isEmpty(url))
            url = layui.laytpl(url).render($.urlParams);

        if (event === 'add') {
            new Request(url, params).post(handler);
        }
        else if (event === 'edit') {
            new Request(url, params).put(handler);
        }
        else if (event === 'submit') {
            let handlers = $.assignAttr({}, sender, 'onSuccess', 'onError');
            new Request(url, params).post((result) => {
                handlers.onSuccess && eval(handlers.onSuccess);
            }, (result) => {
                handlers.onError && eval(handlers.onError);
            });
        }
    }

    /**
     * 关闭按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof Form
     */
    _onCloseButtonClick(sender) {
        $.closeWindow();
    }

    /**
     * 上传按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof Form
     */
    _onUploadButtonClick(sender) {
        let params = $.assignAttr({}, sender, 'path', 'img', 'accept', 'exts', 'size');
        Popup.show('上传附件', '400px', '280px', 'upload.html?params=' + escape(JSON.stringify(params)), false, () => {
            let path = $.getResult($.config.upload.result);
            $.isNotBlank(path) && !$.isEmpty(params.path) && $(params.path).val(path);
            $.isNotBlank(path) && !$.isEmpty(params.img) && $(params.img).attr('src', $.imageUrl(path));
        });
    }
}

/**
 * DOM节点属性
 */
Form.filter = 'form';

/**
 * 依赖模块
 */
Form.imports = ['form', 'laydate', 'layedit', 'laytpl'];