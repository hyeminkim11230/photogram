package com.cos.photogramstart.util;

public class Script {
    public static String back(){
        StringBuffer sb = new StringBuffer();
        sb.append("<script");
//        sb.append("slert(" + msg + ")");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }
}
