package listener;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Author ：yuanyc
 * Time ：2017/6/1
 * Description ：
 * 自定义的监听器集合（弱引用）, 特点：<br>
 * 1、只会以弱引用持有监听器，避免内存泄露<br>
 * 2、通知时发现引用不存在就会自动清理<br>
 */

public class WeakCustomListeners<E extends BaseEventInfo> {
    /**
     * 是否迭代中的标识（事件是否在通知中）
     */
    private boolean iterable;
    /**
     * 缓存集合（缓存那些在迭代中添加的监听器）
     */
    private LinkedList<Listener.CustomListener> cacheList = new LinkedList<>();
    /**
     * 弱引用集合
     */
    private WeakArrayList<Listener.CustomListener> references = new WeakArrayList<>();

    /**
     * 添加监听器
     *
     * @param customListener
     */
    public synchronized void add(Listener.CustomListener<E> customListener) {
        //检查是否在迭代中
        if (iterable) {
            //如果在迭代中，则将要添加的监听器存在缓存中
            cacheList.add(customListener);
        } else {
            //否则直接存在弱引用集合中
            references.add(customListener);
        }
    }

    public synchronized void conveyEvent(E e) {
        //将是否通知中的标志置为true
        iterable = true;
        //遍历软引用集合中，逐个通知
        for (WeakReference<Listener.CustomListener> listener : references.getElementData()) {
            if (listener.get() != null) {
                listener.get().onEvent(e);
            }
        }
        iterable = false;
        //遍历缓存集合，逐个通知
        for (Listener.CustomListener listener : cacheList) {
            listener.onEvent(e);
            //将监听器添加到弱引用中
            add(listener);
        }
        //每次通知完就清除缓存集合
        cacheList.clear();
    }
}
