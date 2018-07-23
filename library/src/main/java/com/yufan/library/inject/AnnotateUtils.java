package com.yufan.library.inject;

import com.yufan.library.base.BaseVu;
import com.yufan.library.inter.Vu;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotateUtils {
    public static void injectViews(BaseVu vu) {
        Class<? extends Vu> object = vu.getClass(); // 获取activity的Class
        Field[] fields = object.getDeclaredFields(); // 通过Class获取activity的所有字段
        for (Field field : fields) { // 遍历所有字段
            // 获取字段的注解，如果没有ViewInject注解，则返回null
            Find viewInject = field.getAnnotation(Find.class);
            if (viewInject != null) {
                int viewId = viewInject.value(); // 获取字段注解的参数，这就是我们传进去控件Id
                if (viewId != -1) {
                    try {
                        // 获取类中的findViewById方法，参数为int
                        Method method = object.getMethod("findViewById", int.class);
                        // 执行该方法，返回一个Object类型的View实例
                        Object resView = method.invoke(vu, viewId);
                        field.setAccessible(true);
                        // 把字段的值设置为该View的实例
                        field.set(vu, resView);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static int getLayoutId(BaseVu vu){
        Class<? extends Vu> object = vu.getClass(); // 获取activity的Class
        FindLayout viewInject = object.getAnnotation(FindLayout.class);
        int value=  viewInject.layout();
       return value;
    }
    public static int getStateParentId(BaseVu vu){
        Class<? extends Vu> object = vu.getClass(); // 获取activity的Class
        FindLayout viewInject = object.getAnnotation(FindLayout.class);
        int value=  viewInject.stateLayoutParent();
        return value;
    }
    public static int getRecyclerView(BaseVu vu){
        Class<? extends Vu> object = vu.getClass(); // 获取activity的Class
        FindRecyclerView viewInject = object.getAnnotation(FindRecyclerView.class);
        int value=  viewInject.value();
        return value;
    }
}