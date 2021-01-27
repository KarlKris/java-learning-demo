package com.util;


import com.model.RedBlackTreeNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class TreePrintUtil {
    public static void pirnt(RedBlackTreeNode root) {
        // 找到左边的最大偏移量
        int maxLeftOffset = findMaxOffset(root, 0, true);
        int maxRightOffset = findMaxOffset(root, 0, false);
        int offset = Math.max(maxLeftOffset, maxRightOffset);
        // 计算最大偏移量
        Map<Integer, PrintLine> lineMap = new HashMap();
        calculateLines(root, offset, lineMap, 0, true);
        Iterator<Integer> lineNumbers = lineMap.keySet().iterator();
        int maxLine = 0;
        while (lineNumbers.hasNext()) {
            int lineNumber = lineNumbers.next();
            if (lineNumber > maxLine) {
                maxLine = lineNumber;
            }
        }
        for (int i = 0; i <= maxLine; i++) {
            PrintLine line = lineMap.get(i);
            if (line != null) {
                System.out.println(line.getLineString());
            }
        }

    }

    private static void calculateLines(RedBlackTreeNode parent, int offset, Map<Integer, PrintLine> lineMap, int level,
                                       boolean right) {
        if (parent == null) {
            return;
        }
        int nameoffset = parent.toString().length() / 2;
        PrintLine line = lineMap.get(level);
        if (line == null) {
            line = new PrintLine();
            lineMap.put(level, line);
        }
        line.putString(right ? offset : (offset - nameoffset), parent.toString());
        // 判断有没有下一级
        if (parent.getLeft() == null && parent.getRight() == null) {
            return;
        }
        // 如果有，添加分割线即/\
        PrintLine separateLine = lineMap.get(level + 1);
        if (separateLine == null) {
            separateLine = new PrintLine();
            lineMap.put(level + 1, separateLine);
        }
        if (parent.getLeft() != null) {
            separateLine.putString(offset - 1, "/");
            calculateLines(parent.getLeft(), offset - nameoffset - 1, lineMap, level + 2, false);
        }
        if (parent.getRight() != null) {
            separateLine.putString(offset + nameoffset + 1, "\\");
            calculateLines(parent.getRight(), offset + nameoffset + 1, lineMap, level + 2, true);
        }

    }

    /**
     * 需要打印的某一行
     * 
     * @author zhuguohui
     *
     */
    private static class PrintLine {
        /**
         * 记录了offset和String的map
         */
        Map<Integer, String> printItemsMap = new HashMap<>();
        int maxOffset = 0;

        public void putString(int offset, String info) {
            printItemsMap.put(offset, info);
            if (offset > maxOffset) {
                maxOffset = offset;
            }
        }

        public String getLineString() {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i <= maxOffset; i++) {
                String info = printItemsMap.get(i);
                if (info == null) {
                    buffer.append(" ");
                } else {
                    buffer.append(info);
                    i += info.length();
                }
            }
            return buffer.toString();
        }

    }

    private static int findMaxOffset(RedBlackTreeNode parent, int offset, boolean findLeft) {
        if (parent != null) {
            offset += parent.toString().length();
        }
        if (findLeft && parent.getLeft() != null) {
            offset += 1;
            return findMaxOffset(parent.getLeft(), offset, findLeft);
        }
        if (!findLeft && parent.getRight() != null) {
            return findMaxOffset(parent.getRight(), offset, findLeft);
        }
        return offset;
    }
    
    /**
     * 使用树形结构显示
     */
    public static void displayTree(RedBlackTreeNode root){
        Stack globalStack=new Stack();
        globalStack.push(root);
       // int nBlank=32;
        int nBlank=32;
        boolean isRowEmpty=false;
        String dot="............................";
        System.out.println(dot+dot+dot);
        while (isRowEmpty==false){
            Stack localStack=new Stack();
            isRowEmpty=true;
            for (int j=0;j<nBlank;j++)//{
            {

                System.out.print("-");
            }
            while (globalStack.isEmpty()==false){
                //里面的while循环用于查看全局的栈是否为空
                RedBlackTreeNode temp=(RedBlackTreeNode)globalStack.pop();
                if (temp!=null){
                    System.out.print(temp);

                    localStack.push(temp.getLeft());
                    localStack.push(temp.getRight());
                    //如果当前的节点下面还有子节点，则必须要进行下一层的循环
                    if (temp.getLeft()!=null||temp.getRight()!=null){
                        isRowEmpty=false;

                    }
                }else {
                    //如果全局的栈则不为空
                    System.out.print("#!");
                    localStack.push(null);
                    localStack.push(null);

                }


                //打印一些空格
                for (int j=0;j<nBlank*2-2;j++){
                    //System.out.print("&");
                    System.out.print(" ");
                }




            }//while end


            System.out.println();
            nBlank/=2;
            //这个while循环用来判断，local栈是否为空,不为空的话，则取出来放入全局栈中
            while (localStack.isEmpty()==false){
                globalStack.push(localStack.pop());
            }

            // }
        }//大while循环结束之后，输出换行
        System.out.println(dot+dot+dot);

    }





}
