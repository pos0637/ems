
import BaseComponent from '../base';
import Request from '../../misc/request';

/**
 * 菜单
 * 
 * @export
 * @class Menu
 * @extends {BaseComponent}
 */
export default class Menu extends BaseComponent {
    constructor(props) {
        super(props);

        /**
         * 请求地址
         */
        this.url = this.element.attr('url');

        /**
         * 标签容器
         */
        this.tabhost = this.element.attr('tabhost');

        /**
         * 是否自动加载
         */
        this.autoload = this.element.getAttr('autoload', 'true') === 'true';

        /**
         * 是否可以刷新
         */
        this.refreshable = this.element.getAttr('refreshable', 'true') === 'true';

        /**
         * 布局模板
         */
        this.layout = {
            scroll: {
                begin: '<div class="layui-side-scroll">',
                end: '</div>'
            },
            ul: {
                begin: '<ul class="layui-nav layui-nav-tree" lay-filter="' + this.id + '">',
                end: '</ul>'
            },
            level1_open: {
                begin: '<li class="layui-nav-item layui-nav-itemed" uuid={{d.id}} title={{d.name}} url={{d.path}}><a class="" href="javascript:;">{{d.name}}</a>',
                selected: '<li class="layui-nav-item layui-nav-itemed layui-this" uuid={{d.id}} title={{d.name}} url={{d.path}}><a class="" href="javascript:;">{{d.name}}</a>',
                end: '</li>'
            },
            level1_close: {
                begin: '<li class="layui-nav-item" uuid={{d.id}} title={{d.name}} url={{d.path}}><a class="" href="javascript:;">{{d.name}}</a>',
                selected: '<li class="layui-nav-item layui-this" uuid={{d.id}} title={{d.name}} url={{d.path}}><a class="" href="javascript:;">{{d.name}}</a>',
                end: '</li>'
            },
            level2_container: {
                begin: '<dl class="layui-nav-child">',
                end: '</dl>'
            },
            level2: {
                begin: '<dd uuid={{d.id}} title={{d.name}} url={{d.path}}><a href="javascript:;">{{d.name}}</a>',
                selected: '<dd uuid={{d.id}} title={{d.name}} url={{d.path}} class="layui-this"><a href="javascript:;">{{d.name}}</a>',
                end: '</dd>'
            }
        };

        /** 
         * 是否设置默认菜单
         */
        this.setDefaultMenuItem = false;
    }

    render() {
        if ($.isEmpty(this.url))
            return;

        // 空数据处理函数
        let handler = (content) => {
            this.element.html(content);
            layui.element.render();
        };

        // 请求数据
        if (this.autoload) {
            new Request(this.url).get((result) => {
                let nodes = JSON.parse(result[$.config.request.response.dataName]);
                if (nodes.length > 0)
                    this._buildMenus(nodes);
                else
                    handler('<div class="layui-none">无数据</div>');
            }, () => {
                handler('<div class="layui-none">数据接口请求异常</div>');
            });
        }
        else {
            handler('<div class="layui-none">无数据</div>');
        }
    }

    /**
     * 设置选中菜单项目
     *
     * @param {any} uuid 菜单项目索引
     * @memberof Menu
     */
    setSelected(uuid) {
        let items = this.element.find('.layui-this');
        if (!$.isEmpty(items)) {
            items.each(function () {
                $(this).removeClass('layui-this');
            });
        }

        items = this.element.find('[uuid="' + uuid + '"]');
        if (!$.isEmpty(items) && (items.length > 0)) {
            $(items[0]).addClass('layui-this');
        }
    }

    /**
     * 建立菜单
     * 
     * @param {any} nodes 节点列表
     * @returns 菜单
     * @memberof Menu
     */
    _buildMenus(nodes) {
        this.setDefaultMenuItem = false;

        let content = this.layout.scroll.begin + this.layout.ul.begin;
        $.each(nodes, (index, node) => {
            content += this._buildSubMenus(node, 1);
        });
        content += this.layout.ul.end + this.layout.scroll.end;
        this.element.html(content);

        // 绑定事件
        layui.element.render();
        layui.element.on('nav(' + this.id + ')', (element) => {
            this._openTab(element.attr('title'), element.attr('url'), element.attr('uuid'));
        });

        // 打开默认页面
        let element = this.element.find('.layui-this');
        this._openTab(element.attr('title'), element.attr('url'), element.attr('uuid'));
    }

    /**
     * 建立子菜单
     * 
     * @param {any} node 节点
     * @param {any} level 层次
     * @returns 菜单
     * @memberof Menu
     */
    _buildSubMenus(node, level) {
        let content = '';
        if ($.isEmpty(node))
            return content;

        let isSelected = false;
        if (!this.setDefaultMenuItem) {
            if ((level === 1) && $.isEmpty(node.children)) {
                isSelected = true;
                this.setDefaultMenuItem = true;
            }
            else if (level === 2) {
                isSelected = true;
                this.setDefaultMenuItem = true;
            }
        }

        // 建立头部
        if (level === 1) {
            if (!$.isEmpty(node.open) && (node.open === 'false'))
                content += isSelected ? this.layout.level1_close.selected : this.layout.level1_close.begin;
            else
                content += isSelected ? this.layout.level1_open.selected : this.layout.level1_open.begin;
        } else if (level === 2) {
            content += isSelected ? this.layout.level2.selected : this.layout.level2.begin;
        } else {
            return content;
        }

        // 渲染内容
        content = layui.laytpl(content).render(node);

        // 遍历子菜单
        if (!$.isEmpty(node.children) && (node.children.length > 0)) {
            if (level === 1)
                content += this.layout.level2_container.begin;

            $.each(node.children, (index, node) => {
                content += this._buildSubMenus(node, level + 1);
            });

            if (level === 1)
                content += this.layout.level2_container.end;
        }

        // 建立尾部
        if (level === 1) {
            if (!$.isEmpty(node.open) && (node.open === 'false'))
                content += this.layout.level1_close.end;
            else
                content += this.layout.level1_open.end;
        } else if (level === 2) {
            content += this.layout.level2.end;
        } else {
            return content;
        }

        return content;
    }

    /**
     * 打开标签页
     * 
     * @param {any} title 标题
     * @param {any} url 地址
     * @param {any} uuid 菜单索引
     * @memberof Menu
     */
    _openTab(title, url, uuid) {
        if ($.isEmpty(this.tabhost))
            return;

        let tabhost = $.manager.getComponent(this.tabhost.replace(/#/g, ''));
        if ($.isEmpty(tabhost))
            return;

        tabhost.newTab(title, url, uuid);
    }
}

/**
 * DOM节点属性
 */
Menu.filter = 'div.Menu';

/**
 * 依赖模块
 */
Menu.imports = ['element', 'laytpl'];