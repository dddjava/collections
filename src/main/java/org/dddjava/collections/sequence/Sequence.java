package org.dddjava.collections.sequence;

import java.util.LinkedList;
import java.util.List;

/**
 * 列
 */
public class Sequence<T> {
    LinkedList<T> list;

    Sequence(LinkedList<T> list) {
        this.list = list;
    }

    /**
     * 列の大きさ
     * @return 要素の数
     */
    public int size() {
        return list.size();
    }

    /**
     * 空の列か検査する
     * @return 空であればtrue
     */
    public boolean isEmpty() {
        return size() == 0;
    }
}
