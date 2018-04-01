
import BaseComponent from '../base';
import Hint from '../../misc/hint';
import Request from '../../misc/request';

/**
 * 导航条组件
 * 
 * @export
 * @class NavBar
 * @extends {BaseComponent}
 */
export default class NavBar extends BaseComponent {
    constructor(props) {
        super(props);
    }

    render() {
        this._bindEvents();
    }

    /**
     * 绑定事件
     * 
     * @memberof NavBar
     */
    _bindEvents() {
        let _this = this;
        let elements = this.element.find('a');
        $.each(elements, function () {
            let botton = $(this);
            let method = botton.attr('lay-event');
            if ($.isEmpty(method))
                return;

            botton.off('click');
            botton.on('click', function () {
                if (method === 'logout')
                    _this._onLogoutButtonClick(botton);
            });
        });
    }

    /**
     * 注销按钮点击事件
     * 
     * @param {any} sender 组件
     * @memberof NavBar
     */
    _onLogoutButtonClick(sender) {
        let url = sender.attr('url');
        if ($.isEmpty(url))
            return;

        new Request(url).post(() => {
            Hint.showSuccessMsg('注销成功!');
            location.href = $.viewUrl($.config.authentication.login);
        }, () => {
            Hint.showErrorMsg('注销失败!');
        });
    }
}

/**
 * DOM节点属性
 */
NavBar.filter = 'div.NavBar';

/**
 * 依赖模块
 */
NavBar.imports = [];