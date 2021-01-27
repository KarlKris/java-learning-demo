package com.model;

//红黑树用的结点
public class RedBlackTreeNode {

	public final String RED="RED";
	private int value;
	private String color;
	private RedBlackTreeNode left;
	private RedBlackTreeNode right;
	private RedBlackTreeNode parent;
	
	
	public RedBlackTreeNode(int value){
		this.value=value;
		this.color=RED;
		this.left=null;
		this.right=null;
		this.parent=null;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public RedBlackTreeNode getLeft() {
		return left;
	}


	public void setLeft(RedBlackTreeNode left) {
		this.left = left;
	}


	public RedBlackTreeNode getRight() {
		return right;
	}


	public void setRight(RedBlackTreeNode right) {
		this.right = right;
	}


	public RedBlackTreeNode getParent() {
		return parent;
	}


	public void setParent(RedBlackTreeNode parent) {
		this.parent = parent;
	}




	public String getRED() {
		return RED;
	}


	@Override
	public String toString() {
//		String l,r;
//		if(left==null){
//			l="NIL";
//		}else{
//			l=String.valueOf(left.getValue());
//		}
//		if(right==null){
//			r="NIL";
//		}else{
//			r=String.valueOf(right.getValue());
//		}
		String p;
		if(parent==null){
			p="NIL";
		}else{
			p=String.valueOf(parent.getValue());
		}
		return "[ " + value +" " +color+" "+p+"]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RedBlackTreeNode other = (RedBlackTreeNode) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	
	
	
	

}
