package listener;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Author ：yuanyc
 * Time ：2017/6/1
 * Description ：
 */

public class WeakArrayList<V> {
    /**
     * GC(垃圾回收器)回收的对象会存在此引用队列中
     */
    private ReferenceQueue<V> referenceQueue;
    /**
     * 弱引用集合
     */
    private LinkedList<WeakReference> elementData;

    /**
     * 构造方法
     */
    public WeakArrayList() {
        referenceQueue = new ReferenceQueue<>();
        elementData = new LinkedList<>();
    }

    /**
     * 向弱引用集合中添加一个元素
     * @param v
     * @return
     */
    public boolean add(V v) {
        poll();
        if (null != v) {
            for (WeakReference itemData : elementData) {
                if (itemData.get() == v) {
                    return false;
                }
            }
            return elementData.add(new WeakReference(v, referenceQueue));
        }
        return false;
    }


    /**
     * 获取弱引用集合
     *
     * @return
     */
    public LinkedList<WeakReference> getElementData() {
        poll();
        return elementData;
    }

    /**
     * 清除被GC回收的对象
     */
    private void poll() {
        Object obj = referenceQueue.poll();
        if (obj != null) {
            elementData.remove(obj);
        }
    }

}
