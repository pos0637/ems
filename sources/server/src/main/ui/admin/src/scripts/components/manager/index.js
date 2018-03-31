
import Tracker from '../../misc/tracker';
import Popup from '../../misc/popup';
import Hint from '../../misc/hint';
import Request from '../../misc/request';
import Form from '../form';
import Upload from '../upload';
import BaseGrid from '../baseGrid';
import DataGrid from '../dataGrid';
import TreeGrid from '../treeGrid';
import Tree from '../tree';
import Menu from '../menu';
import TabHost from '../tabhost';

/**
 * 组件管理器
 * 
 * @export
 * @class Manager
 */
export default class Manager {
    /**
     * 初始化
     * 
     * @static
     * @memberof Manager
     */
    static initialize() {
        // TODO: auto generated
        let classes = [Popup, Hint, Request, Form, Upload, BaseGrid, DataGrid, TreeGrid, Tree, Menu, TabHost];
        let modules = [];

        // 获取所有依赖模块
        for (let i = 0; i < classes.length; ++i) {
            let clazz = classes[i];
            let imports = clazz.imports ? clazz.imports : [];
            modules = modules.concat(imports);
        }

        modules = $.unique(modules);
        Tracker.trace('modules: ' + modules);

        // 加载所有依赖模块
        layui.use(modules, () => {
            Tracker.trace('layui is ready');
            for (let i = 0; i < classes.length; ++i) {
                let clazz = classes[i];
                let filter = clazz.filter ? clazz.filter : null;
                if ($.isEmpty(filter))
                    continue;

                // 创建组件
                let elements = $(filter);
                $(elements).each(function (index, element) {
                    let id = element.getAttribute('id') || element.getAttribute('lay-filter');
                    let component = new clazz({ id: id, element: $(this) });
                    Manager.components[element.id] = component;
                });
            }

            Tracker.trace('components is ready');
            $.each(Manager.components, function (key, component) {
                component.render();
            });

            Tracker.trace('components render finish');
        });
    }

    /**
     * 获取组件
     * 
     * @param {any} id 组件索引
     * @returns 组件
     */
    static getComponent(id) {
        return Manager.components[id];
    }
}

/** 
 * 组件列表
 */
Manager.components = {};

export function initialize() {
    Manager.initialize();
    $.manager = Manager;
}