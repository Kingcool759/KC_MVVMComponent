package com.kc.library.base.dialog

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.AppUtils
import com.kc.library.base.R
import com.kc.library.base.databinding.AppUpdateProgressLayoutBinding
import com.liulishuo.okdownload.DownloadTask
import com.liulishuo.okdownload.core.cause.ResumeFailedCause
import com.liulishuo.okdownload.core.listener.DownloadListener3
import java.io.File


/**
 * @data on 5/27/21 1:34 PM
 * @auther
 * @describe 下载安装进度
 */
class DownloadDialog(
    context: Context,
    var apkUrl: String,
    var isForce: Boolean,
    var file: File
) : BaseDialog(context, R.style.Theme_AppCompat_Dialog_Alert) {

    val vBinding by lazy {
        AppUpdateProgressLayoutBinding.inflate(layoutInflater)
    }

    init {
        setContentView(vBinding.root)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isForce) {
            setCancelable(false)
            vBinding.cancel.visibility = View.GONE
        } else {
            setCancelable(true)
            vBinding.cancel.visibility = View.VISIBLE
        }
        val task = downLoad(file.name, apkUrl, file.parentFile)
        vBinding.pb.progress = 0
//        LifecycleUtil.postOnDestroy(context.lifecycle) { task!!.cancel() }
        vBinding.retry.setOnClickListener { v: View? ->
            vBinding.pb.progress = 0
            task!!.enqueue(getListener())
        }
        vBinding.install.setOnClickListener { v: View? ->
            AppUtils.installApp(file)
        }
        vBinding.cancel.setOnClickListener { v: View? ->
            task!!.cancel()
            dismiss()
        }
    }

    /**
     * 下载对应apk文件
     */
    fun downLoad(filename: String?, url: String?, parentFile: File?): DownloadTask? {
        val task = DownloadTask.Builder(url!!, parentFile!!)
            .setFilename(filename) // the minimal interval millisecond for callback progress
            .setMinIntervalMillisCallbackProcess(30)
            .setAutoCallbackToUIThread(true) // do re-download even if the task has already been completed in the past.
            .setPassIfAlreadyCompleted(false)
            .build()
        task.enqueue(getListener())
        return task
    }

    /**
     * 监听下载apk状态
     */
    private fun getListener(): DownloadListener3 {
        var downloadListener: DownloadListener3? = null
        if (downloadListener == null) {
            downloadListener = object : DownloadListener3() {
                override fun retry(task: DownloadTask, cause: ResumeFailedCause) {}
                override fun connected(
                    task: DownloadTask,
                    blockCount: Int,
                    currentOffset: Long,
                    totalLength: Long
                ) {
                }

                override fun progress(
                    task: DownloadTask,
                    currentOffset: Long,
                    contentLength: Long
                ) {
                    Log.e("downloadApk2-progress", "$currentOffset,$contentLength")
                    val progressInt = (currentOffset * 100 / contentLength).toInt()
                    vBinding.pb.progress = progressInt
                }

                override fun started(task: DownloadTask) {
                    vBinding.downloadMsg.text = "正在下载最新安装包..."
                    vBinding.downloadMsg.setTextColor(Color.parseColor("#888888"))
                    vBinding.retry.visibility = View.GONE
                }

                override fun completed(task: DownloadTask) {
                    vBinding.downloadMsg.text = "下载成功"
                    vBinding.install.visibility = View.VISIBLE
                    AppUtils.installApp(task.file)
                    vBinding.retry.visibility = View.GONE
                }

                override fun canceled(task: DownloadTask) {
                    vBinding.downloadMsg.text = "下载失败..."
                    vBinding.downloadMsg.setTextColor(Color.RED)
                    vBinding.retry.visibility = View.VISIBLE
                }

                override fun error(task: DownloadTask, e: Exception) {
                    vBinding.downloadMsg.text = "下载失败..."
                    vBinding.downloadMsg.setTextColor(Color.RED)
                    vBinding.retry.visibility = View.VISIBLE
                }

                override fun warn(task: DownloadTask) {
                    vBinding.downloadMsg.setText("下载失败...")
                    vBinding.downloadMsg.setTextColor(Color.RED)
                    vBinding.retry.visibility = View.VISIBLE
                }
            }
        }
        return downloadListener
    }

}