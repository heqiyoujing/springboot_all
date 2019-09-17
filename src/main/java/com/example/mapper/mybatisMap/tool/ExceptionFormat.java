package com.example.mapper.mybatisMap.tool;

import com.example.mapper.mybatisMap.constant.Const;

public class ExceptionFormat {
    public ExceptionFormat() {
    }

    public static StringBuffer getTraceInfo(Exception e) {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = e.getStackTrace();
        sb.append("Exception:" + e.getMessage());

        for(int i = 0; i < stacks.length; ++i) {
            sb.append("\tclass: ").append(stacks[i].getClassName()).append("\tmethod: ").append(stacks[i].getMethodName()).append("\tline: ").append(stacks[i].getLineNumber());
        }

        return sb;
    }

    public static String getExceptionMessage(Exception e) {
        String message = e.toString();
        if (message.lastIndexOf(":") != -1) {
            message = message.substring(0, message.lastIndexOf(":"));
        }

        String res = getTraceInfo(e).append(message).toString();
        int exceptionContentNum = Const.EXCEPTION_CONTENT.length;

        for(int i = 0; i < exceptionContentNum; ++i) {
            if (res.contains(Const.EXCEPTION_CONTENT[i])) {
                int time = (int)(System.currentTimeMillis() / 1000L);
                if (time - Const.CAN_NOT_GET_CONNECT_MAIL_LASTTIME > 600) {
                    Const.CAN_NOT_GET_CONNECT_MAIL_LASTTIME = time;
                }
            }
        }

        return res;
    }
}
