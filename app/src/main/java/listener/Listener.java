package listener;

/**
 * Author ：yuanyc
 * Time ：2017/6/1
 * Description ：抽象Listener
 */
public interface Listener {
    /**
     * 一般的监听器
     */
    interface GeneralListener extends Listener {
        /**
         * 通知监听器
         */
        void onEvent();
    }

    /**
     * 简单的监听器
     * @param <E>
     */
    interface SimpleListener<E extends Enum<?>> extends Listener {
        /**
         * 通知监听器
         * @param event
         */
        void onEvent(E event);
    }

    /**
     * 自定义的监听器
     * @param <E>
     */
    interface CustomListener<E extends BaseEventInfo> extends Listener {
        /**
         * 通知监听器
         * @param eventInfo 事件信息(里面可以获得事件类型,可以自行继承或实现{@link BaseEventInfo}以支持更多的信息传递)
         */
        void onEvent(E eventInfo);
    }


}
