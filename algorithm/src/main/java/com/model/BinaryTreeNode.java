package com.model;

//二叉树
public class BinaryTreeNode {
	
	public String value;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	public BinaryTreeNode(String value,BinaryTreeNode left,BinaryTreeNode right) {
		this.value=value;
		this.left=left;
		this.right=right;
	}
	
	public BinaryTreeNode(String value) {
		this(value,null,null);
	}
	
	

}
