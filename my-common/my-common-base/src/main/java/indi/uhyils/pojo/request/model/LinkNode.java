package indi.uhyils.pojo.request.model;

import java.io.Serializable;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月12日 09时08分
 */
public class LinkNode<T extends Serializable> implements Serializable {

    private T data;

    private LinkNode<T> linkNode;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkNode<T> getLinkNode() {
        return linkNode;
    }

    public void setLinkNode(LinkNode<T> linkNode) {
        this.linkNode = linkNode;
    }

    public Boolean hasNext() {
        return linkNode != null;
    }
}
