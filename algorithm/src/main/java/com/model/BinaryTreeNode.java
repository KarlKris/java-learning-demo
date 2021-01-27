package com.model;

//二叉树
public class BinaryTreeNode {
	
	public String value;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	public BinaryTreeNode(String value,BinaryTreeNode left,BinaryTreeNode right) {
		// TODO Auto-generated constructor stub
		this.value=value;
		this.left=left;
		this.right=right;
	}
	
	public BinaryTreeNode(String value) {
		// TODO Auto-generated constructor stub
		this(value,null,null);
	}
	
	

}
