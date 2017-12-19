/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lespaul361.commons.commonroutines.utilities.Streams.XML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Each element in XML is stored as a <code>Node</code>
 *
 * @author Charles Hamilton
 */
public class Node {

    private List<com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute> attributes = new ArrayList<>();
    private String name = "";
    private String value = "";
    private List<com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Node> childrenNodes = new ArrayList<>();
    private int id;
    com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Node parentNode;
    private int lastFindID = -1;
    private boolean isCDATA = false;
    private RootNode rootNode = null;

    /**
     * Constructs a new <code>Node</code> with a name
     *
     * @param name the name of the node
     * @return new <code>Node</code> with the current node as the parent
     */
    public Node createNode(String name) {
        Node retNode = createNode();
        retNode.setName(name);
        return retNode;
    }

    /**
     * Creates a child node for this node
     *
     * @return <code>Node</code>
     */
    public Node createNode() {
        Node retNode = new Node();
        retNode.parentNode = this;
        childrenNodes.add(retNode);
        return retNode;
    }

    /**
     * @return <code>String</code> the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Adds an <code>Attribute</code> to this node
     *
     * @param attribute <code>Attribute</code> to add
     */
    public void addAttribute(com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute attribute) {
        attributes.add(attribute);
    }

    /**
     * Creates and adds an <code>Attribute</code> to this node
     *
     * @param name the name of the node
     * @param value The value of the node
     */
    public void addAttribute(String name, String value) {
        com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute attribute = new Attribute(value, name);
        attributes.add(attribute);
    }

    /**
     * Adds an array of <code>Attribute</code> to this node
     *
     * @param attributes array of <code>Attribute</code> to add
     */
    public void addAttributes(Attribute[] attributes) {
        this.attributes.addAll(Arrays.asList(attributes));
    }

    /**
     * Gets the <code>List</code> of <code>Attribute</code> for this
     * <code>Node</code>
     *
     * @return <code>List</code> of <code>Attribute</code>
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Removes an <code>Attribute</code> from this <code>Node</code>
     *
     * @param attribute <code>Attribute</code> to remove
     */
    public void removeAttribute(com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute attribute) {
        try {
            attributes.remove(attribute);
        } catch (Exception e) {
        }
    }

    /**
     * Removes an <code>Attribute</code> from this <code>Node</code>
     *
     * @param name name of the <code>Attribute</code> to remove
     */
    public void removeAttribute(String name) {
        try {
            for (com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Attribute attribute : attributes) {
                if (attribute.getName().equalsIgnoreCase(name)) {
                    attributes.remove(attribute);
                    break;
                }
            }
        } catch (Exception e) {
        }

    }

    /**
     * Finds the first child <code>Node</code> of this <code>Node</code> with
     * the specified name
     *
     * @param nodeName the name of the <code>Node</code>
     * @return the <code>Node</code> if found otherwise returns
     * <code>null</code>
     */
    public Node findFirstChildNode(String nodeName) {
        lastFindID = -1;
        for (Node node : childrenNodes) {
            if (node.name.equalsIgnoreCase(nodeName)) {
                lastFindID = node.getId();
                return node;
            }
        }

        return null;
    }

    /**
     * Finds the next child <code>Node</code> of this <code>Node</code> with the
     * specified name
     *
     * @param nodeName the name of the <code>Node</code>
     * @return the <code>Node</code> if found otherwise returns
     * <code>null</code>
     */
    public Node findNextChildNode(String nodeName) {
        boolean returnNode = lastFindID > 0;
        if (!returnNode) {
            return findFirstChildNode(nodeName);
        }
        for (Node node : childrenNodes) {
            if (node.name.equalsIgnoreCase(nodeName)) {
                if (returnNode) {
                    lastFindID = node.getId();
                    return node;
                } else {
                    if (node.getId() == lastFindID) {
                        returnNode = true;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Finds the <code>Node</code> of the specified path
     *
     * @param nodePath the path of the <code>Node</code>
     * @return the <code>Node</code> if found otherwise returns
     * <code>null</code>
     * <p>
     * <strong>Example:</strong><br>
     * node1\node2\node3
     */
    public Node findNode(String nodePath) {
        String nextNodeString = "";
        String currentNodeString = "";
        if (nodePath.substring(0, 1).equalsIgnoreCase("\\")) {
            nextNodeString = nodePath.substring(1);
        } else {
            nextNodeString = nodePath;
        }
        if (nextNodeString.contains("\\")) {
            int pos1 = nextNodeString.indexOf("\\");
            currentNodeString = nextNodeString.substring(0, pos1);
            nextNodeString = nextNodeString.substring(pos1);
        }

        Node curTmp = this.findFirstChildNode(currentNodeString);
        Node retNode = null;
        while (true) {
            if (curTmp == null) {
                break;
            } else {
                retNode = curTmp.findNode(nextNodeString);
                if (retNode != null) {
                    break;
                }
            }
            curTmp = this.findNextChildNode(currentNodeString);
        }
        return retNode;
    }

    private RootNode getRootNode() {
        if (this.rootNode != null) {
            return this.rootNode;
        }

        Node cur = this;
        while (true) {
            if (cur instanceof RootNode) {
                this.rootNode = (RootNode) cur;
                return this.rootNode;
            } else {
                cur = cur.parentNode;
            }
            if (cur == null) {
                break;
            }
        }
        return null;
    }

    void addNodeCopy(Node childNode) {
        this.childrenNodes.add(childNode);
        childNode.parentNode = this;
    }

    Node copyThisNode() {
        Node copy = new Node();
        copy.name = this.name;
        copy.value = this.value;
        copy.id = this.id;
        List<Attribute> attributes = new ArrayList<>();
        attributes.addAll(this.attributes);
        copy.attributes = attributes;
        try {
            if (childrenNodes != null) {
                for (Node node : childrenNodes) {
                    copy.addNodeCopy(node.copyThisNode());
                }

            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace().toString());
        }

        return copy;
    }

    void addNodeAddedListenter(NodeAddedListener lst) {
        NodeEvent ne = new NodeEvent(parentNode, -1);

        lst.nodeAdded(null);
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the isCDATA
     */
    boolean isIsCDATA() {
        return isCDATA;
    }

    /**
     * @param isCDATA the isCDATA to set
     */
    void setIsCDATA(boolean isCDATA) {
        this.isCDATA = isCDATA;
    }

    /**
     * Gets the path of the <code>Node</code>
     *
     * @return <code>String</code> with the path
     */
    public String getNodePath() {
        String ret = this.name;
        Node parent = this.parentNode;

        while (parent != null) {
            ret = parent.getName() + "/" + ret;
        }

        if (ret.substring(0, 1).equalsIgnoreCase("/")) {
            ret = ret.substring(1);
        }

        return ret;
    }

    /**
     * Gets the child <code>Node</code> of this <code>Node</code>
     *
     * @return <code>List</code> of <code>Node</code>
     */
    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }

}

class NodeEvent {

    private com.github.lespaul361.commons.commonroutines.utilities.Streams.XML.Node parentNode;
    private int nodeID;

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public NodeEvent(Node parentNode, int nodeID) {
        this.parentNode = parentNode;
        this.nodeID = nodeID;
    }

}

interface NodeAddedListener {

    void nodeAdded(NodeEvent nae);
}

interface NodeRemovedListener {

    void nodeRemoved(NodeEvent nae);
}
