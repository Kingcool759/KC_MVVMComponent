//package com.kc.library.base.dialog;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.graphics.Color;
//import android.os.Environment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import com.blankj.utilcode.util.AppUtils;
//import com.blankj.utilcode.util.ToastUtils;
//import com.chiatai.iorder.R;
//import com.chiatai.iorder.common.Constants;
//import com.chiatai.iorder.common.DataPointNew;
//import com.chiatai.iorder.util.AppUtil;
//import com.chiatai.iorder.util.BuriedPointUtil;
//import com.chiatai.iorder.util.LifecycleUtil;
//import com.liulishuo.okdownload.DownloadTask;
//import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
//import com.liulishuo.okdownload.core.listener.DownloadListener3;
//import com.ooftf.master.widget.dialog.ui.OptDialog;
//import com.tbruyelle.rxpermissions2.RxPermissions;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//
///**
// * @author ooftf
// * @email 994749769@qq.com
// * @date 2019/8/23 0023
// */
//public class DownloadDialog extends Dialog {
//    DownloadTask task;
//    TextView tvDownloadMsg;
//    TextView title;
//    View retry;
//    View install;
//    View cancel;
//    ProgressBar progress;
//
//
//    @SuppressLint("CheckResult")
//    public static void show(@NonNull AppCompatActivity context, String apkUrl, boolean isForce) {
//        new RxPermissions(context).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(aBoolean -> {
//            if (aBoolean) {
//                new com.chiatai.iorder.widget.DownloadDialog(context, apkUrl, isForce).show();
//            } else {
//                OptDialog optDialog = new PermissionDeniedDialog(context).setNegativeListener((view, dialogPos) -> {
//                    dialogPos.dismiss();
//                    if (isForce) {
//                        ToastUtils.showShort("获取存储权限失败,即将退出APP");
//                        Observable.timer(2, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> AppUtils.exitApp());
//                    }
//                })
//                        .setTitleText("权限申请")
//                        .setCancelableChain(false)
//                        .setContentText("为了保证您正常的使用此功能，需要获取您的储存使用权限，请允许。");
//                optDialog.show();
//                LifecycleUtil.addOnStartListener(context.getLifecycle(), () -> {
//                    boolean permissions = PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, context.getPackageName());
//                    if (permissions && optDialog.isShowing()) {
//                        optDialog.dismiss();
//                        new com.chiatai.iorder.widget.DownloadDialog(context, apkUrl, isForce).show();
//                    }
//                });
//
//            }
//        });
//    }
//
//    private DownloadDialog(@NonNull AppCompatActivity context, String apkUrl, boolean isForce) {
//        this(context, apkUrl, isForce, getFile("IOrder.apk"));
//    }
//
//    private DownloadDialog(@NonNull AppCompatActivity context, String apkUrl, boolean isForce, File file) {
//        super(context, R.style.Theme_AppCompat_Dialog_Alert);
//        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.app_update_progress_dialog, null);
//        progress = view.findViewById(R.id.pb);
//        tvDownloadMsg = view.findViewById(R.id.download_msg);
//        title = view.findViewById(R.id.title);
//        retry = view.findViewById(R.id.retry);
//        install = view.findViewById(R.id.install);
//        cancel = view.findViewById(R.id.cancel);
//        setContentView(view);
//        setCanceledOnTouchOutside(false);
//        if (isForce) {
//            setCancelable(false);
//            cancel.setVisibility(View.GONE);
//        } else {
//            setCancelable(true);
//            cancel.setVisibility(View.VISIBLE);
//        }
//        task = downLoad(file.getName(), apkUrl, file.getParentFile());
//        progress.setProgress(0);
//        LifecycleUtil.postOnDestroy(context.getLifecycle(), () -> task.cancel());
//        retry.setOnClickListener(v -> {
//            progress.setProgress(0);
//            task.enqueue(downloadListener);
//        });
//        install.setOnClickListener(v -> {
//            AppUtils.installApp(file);
//            if (runnable != null) {
//                runnable.run();
//            }
//
//        });
//        cancel.setOnClickListener(v -> {
//            task.cancel();
//            dismiss();
//        });
//    }
//
//    public com.chiatai.iorder.widget.DownloadDialog setTitle(String title) {
//        this.title.setText(title);
//        return this;
//    }
//
//    public com.chiatai.iorder.widget.DownloadDialog setMsg(String msg) {
//        this.tvDownloadMsg.setText(msg);
//        return this;
//    }
//
//    static File getFile(String fileName) {
//        String root = Environment.getExternalStorageDirectory().getPath();
//        return new File(root, fileName);
//    }
//
//    public DownloadTask downLoad(String filename, String url, File parentFile) {
//
//        DownloadTask task = new DownloadTask.Builder(url, parentFile)
//                .setFilename(filename)
//                // the minimal interval millisecond for callback progress
//                .setMinIntervalMillisCallbackProcess(30)
//                .setAutoCallbackToUIThread(true)
//                // do re-download even if the task has already been completed in the past.
//                .setPassIfAlreadyCompleted(false)
//                .build();
//        task.enqueue(getListener());
//        return task;
//
//    }
//
//    DownloadListener3 downloadListener;
//
//    @NotNull
//    private DownloadListener3 getListener() {
//        if (downloadListener == null) {
//            downloadListener = new DownloadListener3() {
//                @Override
//                public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {
//
//                }
//
//                @Override
//                public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {
//
//                }
//
//                @Override
//                public void progress(@NonNull DownloadTask task, long currentOffset, long contentLength) {
//                    Log.e("downloadApk2-progress", currentOffset + "," + contentLength);
//                    int progressInt = (int) (currentOffset * 100 / contentLength);
//                    progress.setProgress(progressInt);
//                }
//
//                @Override
//                protected void started(@NonNull DownloadTask task) {
//                    tvDownloadMsg.setText("正在下载最新安装包...");
//                    tvDownloadMsg.setTextColor(Color.parseColor("#888888"));
//                    retry.setVisibility(View.GONE);
//                }
//
//                @Override
//                protected void completed(@NonNull DownloadTask task) {
//                    tvDownloadMsg.setText("下载成功");
//                    install.setVisibility(View.VISIBLE);
//                    AppUtils.installApp(task.getFile());
//                    retry.setVisibility(View.GONE);
//                }
//
//                @Override
//                protected void canceled(@NonNull DownloadTask task) {
//                    tvDownloadMsg.setText("下载失败...");
//                    tvDownloadMsg.setTextColor(Color.RED);
//                    retry.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                protected void error(@NonNull DownloadTask task, @NonNull Exception e) {
//                    tvDownloadMsg.setText("下载失败...");
//                    tvDownloadMsg.setTextColor(Color.RED);
//                    retry.setVisibility(View.VISIBLE);
//                    BuriedPointUtil.buriedPointKF(DataPointNew.UPLOADERROR, e.toString());
//                }
//
//                @Override
//                protected void warn(@NonNull DownloadTask task) {
//                    tvDownloadMsg.setText("下载失败...");
//                    tvDownloadMsg.setTextColor(Color.RED);
//                    retry.setVisibility(View.VISIBLE);
//                }
//            };
//        }
//        return downloadListener;
//    }
//
//    Runnable runnable;
//
//
//    @SuppressLint("CheckResult")
//    public static void showXinEDownloadDialog(AppCompatActivity context) {
//        new RxPermissions(context).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(aBoolean -> {
//            if (aBoolean) {
//                showXeDialog(context);
//            } else {
//                ToastUtils.showShort("获取存储权限失败");
//            }
//        });
//
//    }
//
//    @SuppressLint("CheckResult")
//    public static void showXinEDownloadDialog(Fragment fragment) {
//        new RxPermissions(fragment).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(aBoolean -> {
//            if (aBoolean) {
//                showXeDialog((AppCompatActivity) fragment.getActivity());
//            } else {
//                ToastUtils.showShort("获取存储权限失败");
//            }
//        });
//
//    }
//
//    private static void showXeDialog(AppCompatActivity context) {
//        com.chiatai.iorder.widget.DownloadDialog downloadDialog = new com.chiatai.iorder.widget.DownloadDialog(context, Constants.XINE_APK, false, getFile("xinePay.apk"));
//        downloadDialog.setTitle("下载信e付插件").setMsg("正在下载信e付插件...");
//        downloadDialog.show();
//        LifecycleUtil.addOnStartListener(context.getLifecycle(), () -> {
//            if (AppUtil.isAvilible(context, Constants.PLUGIN_PKGNAME) && downloadDialog.isShowing()) {
//                downloadDialog.dismiss();
//            }
//        });
//    }
//}
