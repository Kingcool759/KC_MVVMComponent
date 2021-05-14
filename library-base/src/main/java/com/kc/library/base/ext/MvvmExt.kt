package com.kc.library.base.base;

import java.lang.reflect.ParameterizedType

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 */

fun <VM> getVmClass(obj: Any): Class<VM> {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
}

fun <V> getVClass(obj: Any): Class<V> {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>
}