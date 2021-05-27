package com.kc.library.base.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/4 0004
 */
public class LifecycleUtil {

    public static boolean isShow(Lifecycle lifecycle) {
        return lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    public static boolean isTouch(Lifecycle lifecycle) {
        return lifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }

    public static boolean isDestroy(Lifecycle lifecycle) {
        return lifecycle.getCurrentState().isAtLeast(Lifecycle.State.DESTROYED);
    }

    public static void postOnResume(Lifecycle lifecycle, Runnable runnable) {
        if (isTouch(lifecycle)) {
            runnable.run();
        } else {
            lifecycle.addObserver(new LifecycleObserver() {
                @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
                void onResume() {
                    runnable.run();
                    lifecycle.removeObserver(this);
                }
            });
        }
    }

    public static void postNextOnResume(Lifecycle lifecycle, Runnable runnable) {
        lifecycle.addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            void onResume() {
                runnable.run();
                lifecycle.removeObserver(this);
            }
        });
    }

    public static void postOnStart(Lifecycle lifecycle, Runnable runnable) {
        if (isShow(lifecycle)) {
            runnable.run();
        } else {
            lifecycle.addObserver(new LifecycleObserver() {
                @OnLifecycleEvent(Lifecycle.Event.ON_START)
                void onStart() {
                    runnable.run();
                    lifecycle.removeObserver(this);
                }
            });
        }
    }

    public static void postOnNextStart(Lifecycle lifecycle, Runnable runnable) {
        lifecycle.addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            void onStart() {
                runnable.run();
                lifecycle.removeObserver(this);
            }
        });
    }

    public static LifecycleObserver addOnStartListener(Lifecycle lifecycle, Runnable runnable) {
        LifecycleObserver lifecycleObserver = new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            void onStart() {
                runnable.run();
            }
        };
        lifecycle.addObserver(lifecycleObserver);
        return lifecycleObserver;
    }

    public static void postOnDestroy(Lifecycle lifecycle, Runnable runnable) {
        lifecycle.addObserver(new LifecycleObserver() {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            void onDestroy() {
                runnable.run();
                lifecycle.removeObserver(this);
            }
        });
    }
}
