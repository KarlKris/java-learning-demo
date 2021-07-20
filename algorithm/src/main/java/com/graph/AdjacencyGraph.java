package com.graph;

import java.util.*;

/**
 * @author li-yuanwen
 * @date 2021/7/4 17:57
 * 基于邻接表的数据结构的图
 **/
public class AdjacencyGraph<T> implements Graph<T> {

    /** 顶点数组 **/
    private Vertex<T>[] vertices;
    /** 下一顶点所在下标 **/
    private int nextIndex;


    public AdjacencyGraph(int vSize) {
        this.vertices = (Vertex<T>[]) new Vertex[vSize];
    }

    @Override
    public boolean add(T t) {
        int index = findIndex(t);
        if (index >= 0) {
            return false;
        }

        if (nextIndex >= this.vertices.length) {
            // 扩容
            expansion(2 * this.vertices.length);
        }

        this.vertices[nextIndex++] = new Vertex<>(t);

        return true;
    }

    @Override
    public int v() {
        return vertices.length;
    }

    @Override
    public int e() {
        int count = 0;
        for (Vertex<T> vertex : this.vertices) {
            Vertex<T> temp = vertex;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
        }
        return count / 2;
    }

    @Override
    public Iterable<T> allV() {
        return VertexItr::new;
    }


    @Override
    public void addEdge(T v, T w) {
        int vIndex = findIndex(v);
        if (vIndex < 0) {
            throw new RuntimeException("not found Vertex");
        }
        int wIndex = findIndex(w);
        if (wIndex < 0) {
            throw new RuntimeException("not found Vertex");
        }


    }

    @Override
    public Iterable<T> adj(T v) {
        int index = findIndex(v);
        if (index < 0) {
            return null;
        }
        return this.vertices[index];
    }

    /** 查找顶点所在下标 **/
    private int findIndex(T v) {
        for (int i = 0; i < this.vertices.length; i++) {
            if (this.vertices[i] == null) {
                break;
            }
            if (Objects.equals(this.vertices[i].data, v)) {
                return i;
            }
        }
        return -1;
    }

    /** 扩容 **/
    private void expansion(int targetSize) {
        if (targetSize <= this.vertices.length) {
            return;
        }
        Vertex<T>[] newVertexArray = (Vertex<T>[]) new Vertex[targetSize];
        System.arraycopy(this.vertices, 0, newVertexArray, 0, this.vertices.length);
        this.vertices = newVertexArray;
    }


    /**
     * 抽象顶点链表
     * @param <T>
     */
    private static class Vertex<T> implements Iterable<T> {
        /** 存储数据 **/
        private T data;
        /** next **/
        private Vertex<T> next;
        /** 剩余长度 **/
        private int size;


        Vertex(T data) {
            this.data = data;
        }

        void setNext(Vertex<T> next) {
            this.next = next;
        }

        @Override
        public Iterator<T> iterator() {
            return new ListVertexItr();
        }


        private class ListVertexItr implements Iterator<T> {

            private Vertex<T> nextReturn;

            ListVertexItr() {
                nextReturn = Vertex.this;
            }

            @Override
            public boolean hasNext() {
                return nextReturn != null;
            }

            @Override
            public T next() {
                if (nextReturn == null) {
                    throw new NoSuchElementException();
                }

                Vertex<T> lastReturned = nextReturn;

                nextReturn = lastReturned.next;

                return lastReturned.data;
            }


        }
    }



    private class VertexItr implements Iterator<T> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        @Override
        public boolean hasNext() {
            return cursor < nextIndex;
        }

        @Override
        public T next() {
            int i = cursor;
            if (i >= nextIndex) {
                throw new NoSuchElementException();
            }
            Vertex[] elementData = AdjacencyGraph.this.vertices;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return (T) elementData[lastRet = i];
        }
    }


}
