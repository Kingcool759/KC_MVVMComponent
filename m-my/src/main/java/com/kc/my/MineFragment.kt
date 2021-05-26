package com.kc.my

import android.Manifest
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.os.Environment
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.AppUtils
import com.example.mykotlindemo.utils.toast
import com.google.android.material.appbar.AppBarLayout
import com.kc.library.base.base.BaseMvvMFragment
import com.kc.library.base.router.RouterFragmentPath
import com.kc.my.databinding.FragmentMineBinding
import com.liulishuo.okdownload.DownloadTask
import com.liulishuo.okdownload.core.cause.ResumeFailedCause
import com.liulishuo.okdownload.core.listener.DownloadListener3
import com.permissionx.guolindev.PermissionX
import java.io.File

@Route(path = RouterFragmentPath.User.MINE_FRAGMENT)
class MineFragment : BaseMvvMFragment<FragmentMineBinding, MineViewModel>() {

    private val appPackageNameJava = "com.example.mydemo"  //我的Java系列包名
    private val javaIntentActivityPath =
        "com.example.mydemo.activity.MainActivity"  //指定要跳转的包内acvitity
    private val appPackageNameKotlin = "com.example.mykotlindemo"  //我的Kotlin系列包名
    private val kotlinIntentActivityPath =
        "com.example.mykotlindemo.main.MainActivity"  //我的Kotlin系列包内activity
    private val appPackageNameDiyView = "com.example.diyview"  //我的Diyview系列包名
    private val diyViewIntentActivityPath =
        "com.example.diyview.main.MainActivity"  //我的DiyView系列包内activity

    override fun isFitsSystemWindow(): Boolean = false
    override fun onLoad(view: View) {
        super.onLoad(view)
        //appbarlayout滑动监听事件
        dataBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset < -500) {
                dataBinding.endScrollContentView.visibility = View.VISIBLE
            } else {
                dataBinding.endScrollContentView.visibility = View.INVISIBLE
            }
        })

        viewModel.goToJavaDemoLiveData.observe(this, {
            if (it) {
                if (checkPackageSurvival(appPackageNameJava)) {
//                    //第一种方式：通过包名+指定Activity路径跳转
//                    val intent = Intent(Intent.ACTION_MAIN)
//                    val componentName = ComponentName(
//                        appPackageNameJava,
//                        javaIntentActivityPath
//                    )
//                    intent.component = componentName
//                    intent.putExtra("", "") //这里通过Intent传值。
//                    startActivity(intent)
                    //第二种方式：通过包名跳转到另一个app的启动页
//                val intent: Intent = activity!!.packageManager.getLaunchIntentForPackage("com.example.mydemo")!!
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                startActivity(intent)
                    checkPackageBehind(context!!, appPackageNameJava)  //封装好的第二种方式打开，并且添加后台判断操作
                } else {
                    toast("对应的app还没有安装喔！！！")
                    //安装操作
                }
            }
        })

        viewModel.goToKotlinDemoLiveData.observe(this, {
            if (it) {
                if (checkPackageSurvival(appPackageNameKotlin)) {
                    val intent = Intent(Intent.ACTION_MAIN)
                    val componentName = ComponentName(
                        appPackageNameKotlin,
                        kotlinIntentActivityPath
                    )
                    intent.component = componentName
                    intent.putExtra("", "")
                    startActivity(intent)
                } else {
                    toast("对应的app还没有安装喔！！！")
                    //安装操作
                }
            }
        })

        viewModel.goToDiyDemoLiveData.observe(this, {
            if (it) {
                if (checkPackageSurvival(appPackageNameDiyView)) {
                    val intent = Intent(Intent.ACTION_MAIN)
                    val componentName = ComponentName(
                        appPackageNameDiyView,
                        diyViewIntentActivityPath
                    )
                    intent.component = componentName
                    intent.putExtra("", "")
                    startActivity(intent)
                } else {
                    toast("对应的app还没有安装喔！！！")
                    //安装操作
//                    /**
//                     * 第一种方式：通过跳转uri方式跳转web页面下载apk
//                     */
//                    val downApkPath = "https://www.pgyer.com/kcDiyView"
//                    val uri = Uri.parse(downApkPath)
//                    val intent = Intent(Intent.ACTION_VIEW, uri)
//                    activity!!.startActivity(intent)
                    /**
                     * 第二种方式：直接下载Apk（可以将需要安装的apk上传至github仓库统一管理）
                     */
//                    val apkUrl = "https://github.com/Kingcool759/gitApks/blob/main/APK/diyview.apk" //这里不是文件地址
//                    val apkDownLoadUrl = "https://github.com.cnpmjs.org/Kingcool759/gitApks/blob/main/APK/diyview.apk" //(加镜像也不成)文件下载地址
                    applyPermissonS()
                }
            }
        })
    }

    /**
     * 检查另一个app包是否已被安装
     */
    private fun checkPackageSurvival(packageName: String): Boolean {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = activity!!.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return packageInfo != null
    }

    /**
     * 判断另一个app是否在后台运行并直接启动
     * 此时activity为它的最后打开页面，也就是后台状态的当前activity
     * 如果没有在后台打开则直接从启动页打开，也就是第二种开启第二个app方式。
     */
    private fun checkPackageBehind(context: Context, packageName: String) {
        if (checkPackageSurvival(packageName)) {
            openPackage(context, packageName);
        } else {
            toast("对应的app还没有安装喔！！！")
            //安装操作
        }
    }

    /**
     * 通过包名打开另一个app
     */
    @SuppressLint("WrongConstant")
    fun getAppOpenIntentByPackageName(context: Context, packageName: String?): Intent? {
        //Activity完整名
        var mainAct: String? = null
        //根据包名寻找
        val pkgMag = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.flags = Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK
        val list: List<ResolveInfo> = pkgMag.queryIntentActivities(
            intent,
            PackageManager.GET_ACTIVITIES
        )
        for (i in list.indices) {
            val info: ResolveInfo = list[i]
            if (info.activityInfo.packageName.equals(packageName)) {
                mainAct = info.activityInfo.name
                break
            }
        }
        if (TextUtils.isEmpty(mainAct)) {
            return null
        }
        intent.component = ComponentName(packageName!!, mainAct!!)
        return intent
    }

    /**
     * 获取另一个app的上下文环境
     */
    private fun getPackageContext(context: Context, packageName: String): Context? {
        var pkgContext: Context? = null
        if (context.packageName == packageName) {
            pkgContext = context
        } else {
            // 创建第三方应用的上下文环境
            try {
                pkgContext = context.createPackageContext(
                    packageName, Context.CONTEXT_IGNORE_SECURITY
                            or Context.CONTEXT_INCLUDE_CODE
                )
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }
        return pkgContext
    }

    /**
     * 是否打开另一个app操作
     */
    private fun openPackage(context: Context, packageName: String): Boolean {
        val pkgContext = getPackageContext(context, packageName)
        val intent = getAppOpenIntentByPackageName(context, packageName)
        if (pkgContext != null && intent != null) {
            pkgContext.startActivity(intent)
            return true
        }
        return false
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
                    val progressInt = (currentOffset * 100 /( contentLength + 1)).toInt()
                    dataBinding.progressDownload.progress = progressInt
                }

                override fun started(task: DownloadTask) {
                    toast("正在下载最新安装包...")
                    dataBinding.progressDownload.visibility = View.VISIBLE
                }

                override fun completed(task: DownloadTask) {
                    toast("下载成功")
                    dataBinding.progressDownload.visibility = View.GONE
                    AppUtils.installApp(File(Environment.getExternalStorageDirectory()!!.absolutePath,"mus.apk"))
                }

                override fun canceled(task: DownloadTask) {
                    toast("下载失败...")
                }

                override fun error(task: DownloadTask, e: Exception) {
                    toast("下载失败...")
                    e.printStackTrace()
                }

                override fun warn(task: DownloadTask) {
                    toast("下载失败...")
                }
            }
        }
        return downloadListener
    }

    /**
     * 获取手机文件路径
     */
    private fun getFile(fileName: String, parentPath: String): File {
        if (!TextUtils.isEmpty(parentPath)) {
            val parentPathFile = File(parentPath)
            if (!parentPathFile.exists()) {
                parentPathFile.mkdirs()
            }
        }
        val file = File(parentPath, fileName)
        if (file.exists()) {
            file.delete()
        }
        return file
    }

    //欠缺：
    //1、 手动赋予存储权限（隐私权限，需要代码给）
    //2、 自动安装需要自己实现，调用路径完成安装
    //3、 蒲公英的apk下载路径中key有时效性，会在一段时间后失效，需要上传至码云/阿里云稳定存储（github不挂代理无法访问）
    /**
     * 申请下载权限
     */
    private fun applyPermissonS() {
        PermissionX.init(this)
            .permissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .setDialogTintColor(Color.parseColor("#008577"), Color.parseColor("#83e8dd"))
            .onExplainRequestReason { scope, deniedList ->
                val message = "PermissionX需要您同意以下权限才能正常使用"
                scope.showRequestReasonDialog(deniedList, message, "确定", "取消")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    toast("所有申请的权限都已通过")
                    val apkDownLoadUrl =
                        "https://raw.githubusercontent.com/Kingcool759/gitApks/main/APK/diyview.apk" //使用github吧，挂代理下载
//                        "https://gitee.com/armstrong759/apk/raw/master/apks/diyview.apk"//码云需要登陆才能下载，害！
//                       //蒲公英的下载地址会过期 "https://oss.pgyer.com/be6ae68a04fe302df4c78978c18b77a4.apk?auth_key=1621845750-2f9910c18a59ea950e726a79677286b3-0-d27ec2f29cb992ca74d8940a619d4ec2&response-content-disposition=attachment%3B+filename%3DDiyView_1.0.apk"
                    val parentPath =
                        Environment.getExternalStorageDirectory()!!.absolutePath //+ File.separator + "apkPath"
                    val parentFile = getFile("mus.apk", parentPath)
                    downLoad(parentFile.name, apkDownLoadUrl, parentFile.parentFile)
                } else {
                    toast("您拒绝了如下权限：$deniedList,请同意后再进行下载！")
                }
            }
    }
}