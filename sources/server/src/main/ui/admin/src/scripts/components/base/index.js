
/**
 * 基础组件
 * 
 * @export
 * @class BaseComponent
 */
export default class BaseComponent {
    constructor(props) {
        /**
         * DOM节点索引
         */
        this.id = props.id;

        /**
         * DOM节点
         */
        this.element = props.element;
    }

    /**
     * 渲染组件
     * 
     * @memberof BaseComponent
     */
    render() {
    }
}