package com.site.mountain.common;

public class Method {

    public static String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String methodName = e.getMethodName();
        return methodName;
    }

    public static void getaaa() {
        System.out.println(getMethodName());
    }

    public static void main(String[] args) {
        getaaa();
    }

    //获取一个double类型的随机数
    public static double mathsuijiDouble(double min,double max){
        double res=0;
        double a=max-min;
        double ranl=(Math.random()*a);

        return ranl+min;
    }
}

