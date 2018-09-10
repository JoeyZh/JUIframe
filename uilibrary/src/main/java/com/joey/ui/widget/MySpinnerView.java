package com.joey.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.joey.ui.CheckedModel;
import com.joey.ui.R;
import com.joey.ui.adapter.CheckedAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey on 2017/5/16.
 * 下拉列表。类似与Android原生的Spinner
 */

public class MySpinnerView extends LinearLayout {

    private ListView listView;
    private EditText etInput;
    private CheckedAdapter adapter;
    private PopupWindow popWin;
    private ImageButton spinnerBtn;
    private TextView tvSpinnerName;
    private View divider;
    private List<CheckedModel> allModels;
    private TextView tvEmpty;

    private Handler handler = new Handler();
    private View anchor;

    private int popGravity;
    private String defText;
    private String selectedIds;
    private OnPopItemClickListener onPopItemClickListener;
    private OnPopItemDesClickListener onPopItemDesClickListener;
    private OnShowListener onShowListener;
    private CheckedAdapter.OnItemCheckListener checkListener = new CheckedAdapter.OnItemCheckListener() {
        @Override
        public void onItemCheck(int position, View view, CheckedModel model) {
            updateSelectedText();
            if (onPopItemClickListener != null) {
                onPopItemClickListener.onItemClick(MySpinnerView.this, position, model);
            }
            if (adapter.getType() == CheckedAdapter.TYPE_MULTI_SELECTED) {
                return;
            }
            dismissPopWindow();
        }

        @Override
        public void onItemDisCheck(int position, View view, CheckedModel model) {
            updateSelectedText();
            if (onPopItemDesClickListener != null) {
                onPopItemDesClickListener.onItemDesClick(MySpinnerView.this, position, model);
            }
            if (adapter.getType() == CheckedAdapter.TYPE_MULTI_SELECTED) {
                return;
            }
            dismissPopWindow();
        }
    };

    public MySpinnerView(Context context) {
        super(context);
        init(context);
        defText = "";
    }

    public MySpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MySpinnerView, -1, 0);
        int hint = a.getResourceId(R.styleable.MySpinnerView_hint, -1);
        if (hint == -1) {
            defText = a.getString(R.styleable.MySpinnerView_hint);
        } else {
            defText = context.getResources().getString(hint);
        }
        if (!TextUtils.isEmpty(defText)) {
            setHint(defText);
        }
        tvSpinnerName.setGravity(Gravity.CENTER);
        a.recycle();

    }

    public MySpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MySpinnerView, defStyleAttr, 0);
        int hint = a.getResourceId(R.styleable.MySpinnerView_hint, -1);
        if (hint == -1) {
            defText = a.getString(R.styleable.MySpinnerView_hint);
        } else {
            defText = context.getResources().getString(hint);
        }
        if (!TextUtils.isEmpty(defText)) {
            setHint(defText);
        }
        a.recycle();
    }

    public void setPopGravity(int gravity) {
        popGravity = gravity;
    }

    private void init(final Context context) {
        setOrientation(HORIZONTAL);
        View view = inflate(context, R.layout.layout_spinner, this);
        divider = view.findViewById(R.id.divider_spinner);
        tvSpinnerName = (TextView) view.findViewById(R.id.tv_spinner_name);
        tvSpinnerName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEnabled())
                    return;
                showPopWindow();
            }
        });
        spinnerBtn = (ImageButton) view.findViewById(R.id.img_spinner_indicator);
        spinnerBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEnabled())
                    return;
                showPopWindow();
            }
        });
        View popView = View.inflate(context, R.layout.layout_spinner_list, null);
        etInput = (EditText) popView.findViewById(R.id.et_spinner_filter);
        etInput.setVisibility(GONE);
        listView = (ListView) popView.findViewById(R.id.lv_spinner_list);
        popWin = new PopupWindow(popView, -1, -2);
        popWin.setOutsideTouchable(true);
        /**
         * 默认PopupWindow是没有焦点和不可点击的<br/>
         * 不加的话,在某些手机上可以无法点击
         */
        popWin.setFocusable(true);
        popWin.setBackgroundDrawable(new BitmapDrawable());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (!isEnabled())
                    return;
                adapter.setSelected(arg2, arg1);
                if (adapter.getType() == CheckedAdapter.TYPE_NORMAL)
                    dismissPopWindow();
            }
        });
        popWin.setOnDismissListener(new PopupWindow.OnDismissListener() {

            public void onDismiss() {
                Animation rotate = AnimationUtils.loadAnimation(
                        context, R.anim.rotate_ccw_180);
                spinnerBtn.startAnimation(rotate);

                /**
                 * 这样修改，现象看来是popupWindow点击小三角消失不了的操作（其实是消失了又显示了
                 * 因为popUpWindow显示时，点击其他非popUp区域popUp都会消失,之后又点击spinnerBtn导致再次显示）
                 * 处理方式 点击spinnerBtn，将其设为不可用状态，直到PopUpWindow消失之后500毫秒，
                 * 才将spinnerBtn设置可用
                 */
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        spinnerBtn.setEnabled(true);
                    }
                }, 500);
            }
        });
        listView.setEmptyView(popView.findViewById(R.id.linear_empty));
        tvEmpty = (TextView) popView.findViewById(R.id.tv_empty);
        tvEmpty.setText("选择列表为空");
//        test();
        setAnchor(this);

    }

    public void setAnchor(View view) {
        anchor = view;
    }

    public void setDataChanged(List<CheckedModel> list) {
        adapter.setDataChanged(list);

    }

    public void addSelected(CheckedModel model) {
        adapter.addSelected(model);
    }

    public void disSelected(CheckedModel model) {
        adapter.disSelected(model.getId());
    }

    /**
     * 打开
     */
    private void showPopWindow() {
        Animation rotate = AnimationUtils.loadAnimation(getContext(),
                R.anim.rotate_180);
        rotate.setFillAfter(true);
        spinnerBtn.startAnimation(rotate);

//        MyLog.i("userNameET width = " + userNameET.getWidth());
        popWin.setWidth(anchor.getWidth());

        popWin.showAsDropDown(anchor, 0, 0);
        if (onShowListener != null) {
            onShowListener.onShow();
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void showPopWindow(String text) {
        etInput.setText(text);
        if (popWin.isShowing()) {
            return;
        }
        showPopWindow();
    }

//    private int measurePopWinHeight() {
//        int colheight = getResources().getDimensionPixelOffset(R.dimen.text_mid_size);
//        int editheight = 0;
//        if (etInput.getVisibility() == VISIBLE) {
//            editheight = colheight + getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin);
//        }
//        if (adapter.getCount() > 10) {
//            int height = listView.getHeight();
//            int listH = colheight * 8 + 9;
//            if (height > listH) {
//                height = listH;
//            }
//            return height + editheight;
//        }
//        return 0;
//    }

    private void dismissPopWindow() {

        if (popWin.isShowing()) {
            popWin.dismiss();
        }
    }

    public void setOnPopItemClickListener(OnPopItemClickListener onPopItemClickListener) {
        this.onPopItemClickListener = onPopItemClickListener;
    }

    public void setOnPopItemDesClickListener(OnPopItemDesClickListener onPopItemDesClickListener) {
        this.onPopItemDesClickListener = onPopItemDesClickListener;
    }

    public void setOnShowListener(OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
    }

    private void updateSelectedText() {
        List<CheckedModel> models = adapter.getSelectedList();
        if (models.isEmpty()) {
            setText("");
            selectedIds = "";
            return;
        }
        String name = models.get(0).getName();
        selectedIds = models.get(0).getId();
        for (int i = 1; i < models.size(); i++) {
            name += " , " + models.get(i).getName();
            selectedIds += "," + models.get(i).getId();
        }
        if (isEnabled())
            setText(name);
        else {
            setHint(name);
            setText("");
        }
    }

    public void setText(CharSequence text) {
        tvSpinnerName.setText(text);
    }

    public void setHint(CharSequence hint) {
        tvSpinnerName.setHint(hint);
    }

    public void setHint(int hint) {
        tvSpinnerName.setHint(hint);
    }

    public void setText(int text) {
        tvSpinnerName.setText(text);
    }

    public void setAdapter(List<CheckedModel> list, int layoutId) {
        allModels = list;
        setAdapter(list, layoutId, CheckedAdapter.TYPE_NORMAL);
    }

    public void setAdapter(List<CheckedModel> list, int layoutId, int type) {
        if (list == null)
            return;
        allModels = list;
        adapter = new CheckedAdapter(getContext(), list, layoutId, type);
        listView.setAdapter(adapter);
        adapter.setOnCheckListener(checkListener);
        notifyDataSetChanged();
    }

    public String getSelectedIds() {
        return selectedIds;
    }

    public void setSelected(int position) {
        adapter.setSelected(position);
    }

    public void setSeletedById(String... id) {
        adapter.setSelectedByIds(id);
    }

    public void notifyDataSetChanged() {
        if (adapter.getCount() == 0) {
            setHint(defText);
        }
        adapter.notifyDataSetChanged();
        updateSelectedText();
    }

    public void clearSelected(int index) {
        adapter.clearSelected(index);
    }

    public void clearSelected() {
        setText("");
        adapter.clearSelected();
    }

    public void showSearchBar(final OnSearchListener listener) {
        etInput.setVisibility(VISIBLE);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listener.onSearch(MySpinnerView.this, etInput.getText().toString());
            }
        });
        etInput.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    private void stopSearch() {
        etInput.setText("");
    }

    public void test() {
        ArrayList<CheckedModel> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CheckedModel model = new CheckedModel();
            model.setName("测试" + i);
            model.setId("00000" + i);
            list.add(model);
        }
        adapter = new CheckedAdapter(getContext(), list, R.layout.item_check_simple_1);
        listView.setAdapter(adapter);
        adapter.setOnCheckListener(checkListener);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            divider.setVisibility(VISIBLE);
            spinnerBtn.setVisibility(VISIBLE);
        } else {
            divider.setVisibility(GONE);
            spinnerBtn.setVisibility(GONE);
        }
    }

    public interface OnPopItemClickListener {
        void onItemClick(View view, int index, CheckedModel model);

    }

    public interface OnPopItemDesClickListener {
        void onItemDesClick(View view, int index, CheckedModel model);

    }


    public interface OnShowListener {
        void onShow();

    }

    public interface OnSearchListener {
        void onSearch(View View, String text);
    }
}
