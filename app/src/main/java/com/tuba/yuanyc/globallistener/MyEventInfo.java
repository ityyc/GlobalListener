package com.tuba.yuanyc.globallistener;

import listener.BaseEventInfo;

/**
 * Author ：yuanyc
 * Time ：2017/6/2
 * Description ：
 */

public class MyEventInfo extends BaseEventInfo<CustomEnum>{
    public CustomEnum customEnum;

    public CustomEnum getCustomEnum() {
        return CustomEnum.CUSTOM;
    }
    public String getData(){
        return "这是返回的数据";
    }
}
