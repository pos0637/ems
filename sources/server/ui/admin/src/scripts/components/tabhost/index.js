
import BaseComponent from '../base';

/**
 * 标签容器
 * 
 * @export
 * @class TabHost
 * @extends {BaseComponent}
 */
export default class TabHost extends BaseComponent {
    constructor(props) {
        super(props);

        /**
         * 设置filter
         */
        this.element.attr('lay-filter', this.id);

        /**
         * 菜单
         */
        this.menu = this.element.attr('menu');
    }

    render() {
        let _this = this;
        layui.element.on('tab(' + this.id + ')', function () {
            _this._onTabChanged($(this).attr('lay-id'));
        });
    }

    /**
     * 新建标签页面
     * 
     * @param {any} title 标题
     * @param {any} url URL地址
     * @param {any} uuid 索引
     * @memberof TabHost
     */
    newTab(title, url, uuid) {
        // 检查是否已经打开标签页面
        if (this.element.find('li[lay-id="' + uuid + '"]').length === 0) {
            layui.element.tabAdd(this.id, {
                title: title,
                content: '<iframe src="' + $.viewUrl(url) + '"></iframe>',
                id: uuid
            });
        }

        layui.element.tabChange(this.id, uuid);
    }

    /**
     * 移除标签页面
     * 
     * @param {any} uuid 索引
     * @memberof TabHost
     */
    removeTab(uuid) {
        layui.element.tabDelete(this.id, uuid);
    }

    /**
     * 标签页面改变事件
     *
     * @param {any} uuid 新标签页面索引     
     * @memberof TabHost
     */
    _onTabChanged(uuid) {
        if ($.isEmpty(this.menu))
            return;

        let menu = $.manager.getComponent(this.menu.replace(/#/g, ''));
        if ($.isEmpty(menu))
            return;

        menu.setSelected(uuid);
    }
}

/**
 * DOM节点属性
 */
TabHost.filter = 'div.TabHost';

/**
 * 依赖模块
 */
TabHost.imports = ['element'];