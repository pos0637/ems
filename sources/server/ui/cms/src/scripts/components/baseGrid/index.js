
import BaseComponent from '../base';

/**
 * 基础表格
 * 
 * @export
 * @class BaseGrid
 * @extends {BaseComponent}
 */
export default class BaseGrid extends BaseComponent {
    constructor(props) {
        super(props);

        /** 
         * 请求地址
         */
        this.url = this.element.attr('url');

        /** 
         * 查询栏
         */
        this.querybar = $(this.element.attr('querybar'));

        /** 
         * 操作栏
         */
        this.actionbar = $(this.element.attr('actionbar'));

        /** 
         * 是否自动加载
         */
        this.autoload = this.element.getAttr('autoload', 'true') === 'true';

        /** 
         * 是否可以刷新
         */
        this.refreshable = this.element.getAttr('refreshable', 'true') === 'true';

        /** 
         * 表格布局
         */
        this.layout = this._getLayout();
    }

    /**
     * 获取布局
     * 
     * @returns 布局
     * @memberof BaseGrid
     */
    _getLayout() {
        return null;
    }

    /**
     * 获取查询数据
     * 
     * @param {any} querybar 查询栏
     * @returns 查询数据
     * @memberof BaseGrid
     */
    _getQuerybarData(querybar) {
        let elements = querybar.find('input,select,textarea');
        let data = {};

        $.each(elements, function (i, element) {
            if ($.isEmpty(element.name))
                return;

            if (/^checkbox|radio$/.test(element.type) && !element.checked)
                return;

            let value = element.value;
            if (element.type === 'checkbox') {
                if (data[element.name])
                    value = data[element.name] + ',' + value;
            }

            if ($.isNotBlank(value))
                data[element.name] = value;
        });

        return data;
    }

    /**
     * 绑定事件
     * 
     * @memberof BaseGrid
     */
    _bindEvents() {
        this._bindQuerybarButtonEvent();
        this._bindActionBarButtonEvent();
        this._bindGridButtonEvent();
    }

    /**
     * 绑定查询栏按钮事件
     *
     * @memberof BaseGrid
     */
    _bindQuerybarButtonEvent() {
        if ($.isEmpty(this.querybar))
            return;

        let _this = this;
        let elements = this.querybar.find('.layui-btn');
        $.each(elements, function () {
            let botton = $(this);
            let method = botton.attr('lay-event');
            if ($.isEmpty(method))
                return;

            botton.off('click');
            botton.on('click', function () {
                if (method === 'query')
                    _this._onQueryButtonClick($(this));
                else if (method === 'create')
                    _this._onCreateButtonClick($(this));
                else if (method === 'edit')
                    _this._onEditButtonClick($(this));
                else if (method === 'delete')
                    _this._onDeleteSelectedButtonClick($(this));
            });
        });
    }

    /**
     * 绑定操作栏按钮事件
     *
     * @memberof BaseGrid
     */
    _bindActionBarButtonEvent() {
        if ($.isEmpty(this.actionbar))
            return;

        let _this = this;
        let elements = this.actionbar.find('.layui-btn');
        $.each(elements, function () {
            let botton = $(this);
            let method = botton.attr('lay-event');
            if ($.isEmpty(method))
                return;

            botton.off('click');
            botton.on('click', function () {
                if (method === 'query')
                    _this._onQueryButtonClick($(this));
                else if (method === 'create')
                    _this._onCreateButtonClick($(this));
                else if (method === 'edit')
                    _this._onEditButtonClick($(this));
                else if (method === 'delete')
                    _this._onDeleteSelectedButtonClick($(this));
            });
        });
    }

    /**
     * 绑定表格内部按钮事件
     * 
     * @memberof BaseGrid
     */
    _bindGridButtonEvent() {
        let _this = this;
        let elements = this.element.find('.layui-btn');
        $.each(elements, function () {
            let botton = $(this);
            let method = botton.attr('lay-event');
            if ($.isEmpty(method))
                return;

            botton.off('click');
            botton.on('click', function () {
                if (method === 'open')
                    _this._onOpenButtonClick(botton);
                else if (method === 'create')
                    _this._onCreateButtonClick(botton);
                else if (method === 'edit')
                    _this._onEditButtonClick(botton);
                else if (method === 'delete')
                    _this._onDeleteButtonClick(botton);
            });
        });
    }

    /**
     * 查询按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof BaseGrid
     */
    _onQueryButtonClick(sender) {
    }

    /**
     * 查看按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof BaseGrid
     */
    _onOpenButtonClick(sender) {
    }

    /**
     * 创建按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof BaseGrid
     */
    _onCreateButtonClick(sender) {
    }

    /**
     * 编辑按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof BaseGrid
     */
    _onEditButtonClick(sender) {
    }

    /**
     * 删除按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof BaseGrid
     */
    _onDeleteButtonClick(sender) {
    }

    /**
     * 删除选中项目按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof BaseGrid
     */
    _onDeleteSelectedButtonClick(sender) {
    }
}