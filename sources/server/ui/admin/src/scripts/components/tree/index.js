
import BaseComponent from '../base';
import Request from '../../misc/request';

/**
 * 树状菜单
 * 
 * @export
 * @class Tree
 * @extends {BaseComponent}
 */
export default class Tree extends BaseComponent {
    constructor(props) {
        super(props);

        /** 
         * 请求地址
         */
        this.url = this.element.attr('url');

        /** 
         * 是否自动加载
         */
        this.autoload = this.element.getAttr('autoload', 'true') === 'true';

        /** 
         * 点击事件回调函数
         */
        this.onClick = this.element.attr('onClick');
    }

    render() {
        // 获取查询数据
        let params = {};
        (!$.isEmpty(this.querybar)) && $.extend(params, this._getQuerybarData(this.querybar), params);

        // 空数据处理函数
        let handler = (content) => {
            this.element.empty();
            this.element.append(content);
        };

        // 请求数据
        if (this.autoload) {
            new Request(this.url, params).get((result) => {
                this.element.empty();
                layui.tree({
                    elem: this.element,
                    spread: true,
                    nodes: JSON.parse(result[$.config.request.response.dataName]),
                    click: (data, node) => this._onClick(data, node)
                });
            }, () => {
                handler('<div class="layui-none">数据接口请求异常</div>');
            });
        }
        else {
            handler('<div class="layui-none">无数据</div>');
        }
    }

    /**
     * 点击事件回调函数
     *
     * @param {any} data 数据
     * @param {any} node 节点
     * @memberof Tree
     */
    _onClick(data, node) {
        this.element.find('.selected').each(function () {
            $(this).removeClass('selected').css('color', 'black');
        });

        node.addClass('selected').css('color', 'red');

        this.onClick && eval(this.onClick);
    }
}

/**
 * DOM节点属性
 */
Tree.filter = 'ul.Tree';

/**
 * 依赖模块
 */
Tree.imports = ['tree'];