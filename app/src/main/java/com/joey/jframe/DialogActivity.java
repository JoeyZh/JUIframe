package com.joey.jframe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.joey.base.util.LogUtils;
import com.joey.ui.CheckedModel;
import com.joey.ui.adapter.CheckedAdapter;
import com.joey.ui.general.BaseActivity;
import com.joey.ui.widget.JProgressDialogHelper;
import com.joey.ui.widget.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey on 2018/3/19.
 */

public class DialogActivity extends BaseActivity {

    @Override
    public void onBindView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("对话框测试");
        setContentView(R.layout.activity_dialog);
    }

    public void showCustomDialog(View view) {
        switch (view.getId()) {
            case R.id.btn_title_msg: {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("标题")
                        .setMessage("这是一段文本，用来测对话框的文字显示")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNeutralButton("中立文字", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
            }
            break;
            case R.id.btn_list:
                createListDialog();
                break;
            case R.id.btn_loading: {
                JProgressDialogHelper.build(this, "加载中...").show();
            }
            break;
            case R.id.btn_icon: {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("图标")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("这是一个有图标的Dialog文本，用来测对话框的文字显示")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNeutralButton("中立文字", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                dialog.show();
            }
            break;
            case R.id.btn_msg:
                createMsg();
            case R.id.btn_toast:
                ToastHelper.show(this, "我是一个标准toast");
                break;

            case R.id.btn_snack:
                Snackbar.make(view, "这是一个SnackBar", Snackbar.LENGTH_LONG)
                        .setAction("点击取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                break;
            default:
                break;
        }
    }

    private void createMsg() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("这是一个只有文本的Dialog，用来测对话框的文字显示")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("中立文字", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }

    private void createListDialog() {
        List<CheckedModel> list = new ArrayList<>();
        list.add(new CheckedModel("1", "测试项目1"));
        list.add(new CheckedModel("2", "测试项目2"));
        list.add(new CheckedModel("3", "测试项目3"));
        list.add(new CheckedModel("4", "测试项目4"));
        CheckedAdapter adapter = new CheckedAdapter(this, list, R.layout.item_check_simple_1);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("列表测试")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogUtils.a("测试点击" + which);
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("中立文字", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }
}
