package indi.uhyils.ast.link;

import com.github.javaparser.ast.Node;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月21日 09时17分
 */
public class MethodLineNode {

    private static final AtomicInteger index = new AtomicInteger(0);

    /**
     * 方法行类型
     */
    private MethodLineTypeEnum methodLineType;

    /**
     * 流程图的形状
     */
    private NodeShape nodeShape;

    /**
     * 是否是block
     */
    private Boolean blockNode = false;

    /**
     * 子节点
     */
    private List<MethodLineNode> childNodes = new LinkedList<>();

    /**
     * 箭头上的语言
     */
    private List<String> languageOnArrow = new ArrayList<>();

    /**
     * 节点中的内容
     */
    private String content;

    public MethodLineNode() {
    }

    public MethodLineTypeEnum getMethodLineType() {
        return methodLineType;
    }

    public void setMethodLineType(MethodLineTypeEnum methodLineType) {
        this.methodLineType = methodLineType;
    }

    public NodeShape getNodeShape() {
        return nodeShape;
    }

    public void setNodeShape(NodeShape nodeShape) {
        this.nodeShape = nodeShape;
    }

    public void addChildNode(MethodLineNode childNode, String languageOnArrow) {
        this.childNodes.add(childNode);
        this.languageOnArrow.add(languageOnArrow);
    }


    public String getContent() {
        return content;
    }

    public void setContent(Node node, String content) {
        if (node.getComment().isPresent()) {
            this.content = node.getComment().get().getContent().replace("\r\n", "\t").replace("\n", "\t").replace("->", "=>");
        } else {
            this.content = content.replace("\r\n", "\t").replace("\n", "\t").replace("->", "=>");
        }
        this.content = index.getAndIncrement() + ":" + this.content;
    }

    public List<MethodLineNode> getChildNodes() {
        return childNodes;
    }

    public List<String> getLanguageOnArrow() {
        return languageOnArrow;
    }

    public Boolean getBlockNode() {
        return blockNode;
    }

    public void setBlockNode(Boolean blockNode) {
        this.blockNode = blockNode;
    }
}
