package com.joey.jframe;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.View;

import com.joey.base.util.LogUtils;
import com.joey.ui.CheckedModel;
import com.joey.ui.adapter.CheckedAdapter;
import com.joey.ui.general.BaseActivity;

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
                        }).create();
                dialog.show();
            }
                break;
            case R.id.btn_loading:
                break;
            case R.id.btn_icon:
                break;
            case R.id.btn_list:
                createListDialog();
                break;
            case R.id.btn_custom:
                break;

            default:
        }
    }

    private void createListDialog(){
        List<CheckedModel> list = new ArrayList<>();
        list.add(new CheckedModel("1","测试项目1"));
        list.add(new CheckedModel("2","测试项目2"));
        list.add(new CheckedModel("3","测试项目3"));
        list.add(new CheckedModel("4","测试项目4"));
        CheckedAdapter adapter = new CheckedAdapter(this,list,R.layout.item_check_simple_1);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("列表测试")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogUtils.a("测试点击"+which);
                    }
                })
                .create();
        dialog.show();
    }
}