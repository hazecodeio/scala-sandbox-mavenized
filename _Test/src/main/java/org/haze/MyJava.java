package org.haze;

import java.util.ArrayList;
import java.util.List;

public class MyJava <T> {
    public static String callJavaMethod() {
        List<? super Integer> l = new ArrayList<>();
        l.add(1);
        return "This String is returned by a Java method!";
    }


}
