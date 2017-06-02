package com.tuba.yuanyc.globallistener;


import listener.Listener;
import listener.WeakCustomListeners;
import listener.WeakGeneralListeners;
import listener.WeakSimpleListeners;

/**
 * Author ：yuanyc
 * Time ：2017/6/2
 * Description ：
 */

public class MyServer {
    private ModeEnum modeEnum;
    private Listener.GeneralListener generalListener;
    private WeakGeneralListeners weakGeneralListeners = new WeakGeneralListeners();
    private WeakSimpleListeners<SimpleEnum> weakSimpleListeners = new WeakSimpleListeners<>();
    private WeakCustomListeners<MyEventInfo> weakCustomListeners = new WeakCustomListeners<>();

    public void setModeEnum(ModeEnum modeEnum) {
        this.modeEnum = modeEnum;
    }

    /**
     * 注册监听器(单纯的使用监听器)
     *
     * @param generalListener
     */
    public void setGeneralListener(Listener.GeneralListener generalListener) {
        this.generalListener = generalListener;
    }

    /**
     * 注册监听器(一般使用)
     *
     * @param generalListener
     */
    public void setWeakGeneralListener(Listener.GeneralListener generalListener) {
        //将注册的监听器存储在弱引用集合中
        weakGeneralListeners.add(generalListener);
    }

    /**
     * 注册监听器(简单使用)
     *
     * @param simpleListener
     */
    public void setWeakSimpleListener(Listener.SimpleListener<SimpleEnum> simpleListener) {
        weakSimpleListeners.add(simpleListener);
    }

    /**
     * 注册监听器(自定义使用)
     *
     * @param customListener
     */
    public void setWeakCustomListener(Listener.CustomListener<MyEventInfo> customListener) {
        weakCustomListeners.add(customListener);
    }


    public MyServer() {

    }

    public void execute() {
        new MyThread().run();
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            switch (modeEnum) {
                case MELERLY:
                    generalListener.onEvent();
                    break;
                case GENERAL:
                    weakGeneralListeners.conveyEvent();
                    break;
                case SIMPLE:
                    weakSimpleListeners.conveyEvent(SimpleEnum.SIMPLE);
                    break;
                case CUSTOM:
                    weakCustomListeners.conveyEvent(new MyEventInfo());
                    break;
            }
        }
    }

}
