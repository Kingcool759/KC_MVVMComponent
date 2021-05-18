package com.kc.library.base.utils;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import io.reactivex.annotations.NonNull;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 9/9/2019
 */

//ViewModel创建时的含参数的工厂类
public class ParamViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    Object[] param;
    Class[] paramClass;

    /**
     * @param param
     */
    public ParamViewModelFactory(Object... param) {
        this.param = param;
        paramClass = new Class[param.length];
        for (int i = 0; i < param.length; i++) {
            paramClass[i] = param[i].getClass();
        }
    }

    /**
     * @param param
     */
    public ParamViewModelFactory(Class[] paramClass, Object[] param) {
        this.param = param;
        this.paramClass = paramClass;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return getConstructor(modelClass).newInstance(param);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            throw new CreateViewModelException("ParamViewModelFactory create " + modelClass.getName() + " error", e);
        }
    }

    private <T extends ViewModel> Constructor<T> getConstructor(@NonNull Class<T> modelClass) throws NoSuchMethodException {
        try {
            return modelClass.getConstructor(paramClass);
        } catch (NoSuchMethodException e) {
            for (int i = 0; i < paramClass.length; i++) {
                if (Application.class.isAssignableFrom(paramClass[i])) {
                    paramClass[i] = Application.class;
                }
            }
            try {
                return modelClass.getConstructor(paramClass);
            } catch (NoSuchMethodException e2) {
                return (Constructor<T>) modelClass.getConstructors()[0];
            }
        }
    }

    static class CreateViewModelException extends RuntimeException {

        public CreateViewModelException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}