package com.wqb.website.supports.util;

import org.springframework.cglib.core.ReflectUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

/**
 * 反射助手
 *
 * @author Shoven
 * @date 2018-10-12 15:24
 */
public class ReflectHelper {

    private static final Charset ORIGINAL_CHARSET = ISO_8859_1;

    /**
     * 字符串编码转化（避免中文乱码）
     *
     * @param obj     实体对象
     * @param charset 字符集
     */
    public static void convertCharacterEncoding(Object obj, Charset charset) throws Exception{
        if (obj == null) {
            return;
        }
        PropertyDescriptor[] beanProperties = ReflectUtils.getBeanProperties(obj.getClass());
        for (PropertyDescriptor beanProperty : beanProperties) {
            if (beanProperty.getPropertyType().isAssignableFrom(String.class)) {
                Method readMethod = beanProperty.getReadMethod();
                Method writeMethod = beanProperty.getWriteMethod();
                Object result = readMethod.invoke(obj);
                if (result == null) {
                    continue;
                }
                String writeString = new String(result.toString().getBytes(ORIGINAL_CHARSET), charset);
                writeMethod.invoke(obj, writeString);
            }
        }
    }

    /**
     * Map 转 Object
     *
     * @param map
     * @param beanClass
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Object obj = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }
        return obj;
    }

    /**
     * Object转 Map
     *
     * @param obj
     * @return
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null) {
            return null;
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Map<String, Object> map = new HashMap<>(propertyDescriptors.length);
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }

    /**
     * set对象的属性
     *
     * @param obj    实例对象
     * @param name   属性名
     * @param value  值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     */
    public static void setProperty(Object obj, String name, Object value) throws Exception {
        obj.getClass().getDeclaredField(name);
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        PropertyDescriptor[] beanProperties = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor beanProperty : beanProperties) {
            if (beanProperty.getName().equals(name)) {
                Method writeMethod = beanProperty.getWriteMethod();
                writeMethod.invoke(obj, value);
            }
        }
    }

    /**
     * get对象的属性
     *
     * @param obj   实例对象
     * @param name  属性名
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     */
    public static Object getProperty(Object obj, String name) throws Exception {
        // 属性不存在抛异常
        Field declaredField = obj.getClass().getDeclaredField(name);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }


    /**
     * 查找类上使用了该注解的属性名称
     *
     * @param cls
     * @param aClass
     * @return
     */
    public static String getPropertyNameByAnnotation(Class cls, Class<? extends Annotation> aClass) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(aClass)) {
                return field.getName();
            }
        }
        throw new RuntimeException(cls.getSimpleName() + " without property use @" +
                aClass.getSimpleName() + " annotation");
    }

    /**
     * 获取父类参数类型
     *
     * @param cls
     * @param index
     * @param <T>
     * @return
     */
    public static <T> Class<T> getSuperClassGenericType(Class cls, int index) {
        String simpleName = cls.getSimpleName();
        Type genType = cls.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            throw new RuntimeException(simpleName + "'s superclass not ParameterizedType");
        } else {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (!(params[index] instanceof Class)) {
                    throw new RuntimeException(simpleName + "not set the actual class on superclass generic parameter");
                } else {
                    return (Class<T>)params[index];
                }
            } else {
                throw new RuntimeException(String.format("Warn: Index: %s, Size of %s's ParameterizedType: %s .",
                        index, cls.getSimpleName(), params.length));
            }
        }
    }

    /**
     * 获取接口参数类型
     *
     * @param cls
     * @param index
     * @param <T>
     * @return
     */
    public static <T> Class<T> getInterfaceGenericType(Class cls, int index) {
        String simpleName = cls.getSimpleName();
        Type[] genericInterfaces = cls.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (!(genericInterface instanceof ParameterizedType)) {
                continue;
            }
            Type[] params = ((ParameterizedType) genericInterface).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (params[index] instanceof Class) {
                    return (Class<T>) params[index];
                }
            }
        }
        throw new RuntimeException("Class " +  simpleName  + " not ParameterizedType");
    }

    /**
     * 父类是否存在此参数类型
     *
     * @param cls
     * @param needle
     * @return
     */
    public static boolean existSuperClassGenericType(Class cls, Class needle) {
        Type genType = cls.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
            if (params == null || params.length == 0) {
                return false;
            }
            for (Type param : params) {
                if (param instanceof Class && ((Class) param).isAssignableFrom(needle)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 接口是否存在此参数类型
     *
     * @param cls
     * @param needle
     * @return
     */
    public static boolean existInterfaceGenericType(Class cls, Class needle) {
        Type[] genericInterfaces = cls.getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            if (!(genericInterface instanceof ParameterizedType)) {
                continue;
            }
            Type[] params = ((ParameterizedType) genericInterface).getActualTypeArguments();
            if (params == null || params.length == 0) {
                return false;
            }
            for (Type param : params) {
                if (param instanceof Class && ((Class) param).isAssignableFrom(needle)) {
                    return true;
                }
            }
        }
        return false;
    }
}
