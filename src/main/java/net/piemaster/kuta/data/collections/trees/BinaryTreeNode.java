package net.piemaster.kuta.data.collections.trees;

/**
 * A node within a binary tree data structure, which limits nodes to a maximum of two children,
 * referred to as {@code left} and {@code right}.
 */
public class BinaryTreeNode extends TreeNode {

    /**
     * The 'left' child of this node.
     */
    private BinaryTreeNode left;

    /**
     * The 'left' child of this node.
     */
    private BinaryTreeNode right;

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

}
