package com.kc.library.base.utils

import android.content.Context
import android.os.Process
import android.text.TextUtils
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/17 0017
 */
object ProcessUtils {
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        return null
    }

    fun getCurrentProcessName(): String? {
        return getProcessName(Process.myPid())
    }

    fun isMainProcess(context: Context): Boolean {
        val packageName = context.packageName
        val processName = getCurrentProcessName()
        if (packageName == null) {
            return true
        }
        return if (processName == null) {
            true
        } else packageName == processName
    }
}
