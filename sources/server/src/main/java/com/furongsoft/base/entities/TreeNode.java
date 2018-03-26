package com.furongsoft.base.entities;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.LinkedList;
import java.util.List;

/**
 * 树节点类型
 *
 * @author Alex
 */
public class TreeNode<T> {
    /**
     * 对象
     */
    @JSONField(unwrapped = true)
    public T object;

    /**
     * 子对象
     */
    public List<TreeNode<T>> children = new LinkedList<>();

    public TreeNode() {
    }

    public TreeNode(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }
}
