package com.site.mountain.common;

public class SliceMethodFactory {

    public SliceMethod createSliceMethod(String sliceType) {
        SliceMethod sliceMethod = null;
        if (sliceType.equals("xml")) {
            sliceMethod = new StandardXmlSliceMethod();
        } else if (sliceType.equals("json")) {
            sliceMethod = new StandardJsonSliceMethod();
        } else if (sliceType.equals("xa_json")) {// 雄安json格式，与我们自定义格式不一致，这里特殊处理
            sliceMethod = new StandardXAJsonSliceMethod();
        }
        return sliceMethod;
    }
}
