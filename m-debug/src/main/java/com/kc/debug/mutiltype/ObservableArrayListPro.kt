package com.kc.debug.mutiltype

import androidx.databinding.ObservableArrayList

class ObservableArrayListPro<T> : ObservableArrayList<T>() {
    fun notifyChange(item: T) {
        val index = indexOf(item)
        if (remove(item)) {
            add(index, item)
        }
    }

    fun notifyChange(index: Int) {
        val item = get(index)
        if (remove(item)) {
            add(index, item)
        }
    }

    fun notifyChange() {
        val arrayList = ArrayList(this)
        clear()
        addAll(arrayList)
    }
}