package com.joey.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.joey.ui.R;
import com.joey.ui.CheckedModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * create by Joey 2017-4-25
 * 多选控制公共Adapter
 */
public class CheckedAdapter<T extends CheckedModel> extends BaseModelAdapter<T> {

    private OnItemCheckListener checkListener;

    /**
     * 支持两次点击取消选中状态的单选容器
     */
    public final static int TYPE_SINGLE_DESELECTED = 0;
    /**
     * 普通状态的，点击选中，再次点击不能取消选中状态
     */
    public final static int TYPE_NORMAL = 1;
    /**
     * 多选状态CheckedItem
     */
    public final static int TYPE_MULTI_SELECTED = 2;

    public int type = TYPE_SINGLE_DESELECTED;

    private HashMap<String, T> selectdMap = new HashMap<>();


    public CheckedAdapter(Context context, List<T> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    public CheckedAdapter(Context context, List<T> dataList, int layoutId, int type) {
        super(context, dataList, layoutId);
        this.type = type;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;
        if (convertView == null) {
            convertView = View.inflate(context,
                    layoutId,
                    null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        BindViewHolder(position, convertView, vHolder);

        return convertView;
    }

    @Override
    public T getItem(int i) {
        return super.getItem(i);
    }

    private void BindViewHolder(int position, View convertView, ViewHolder holder) {
        CheckedModel item = getItem(position);
        if (TextUtils.isEmpty(item.getName())) {
            setText(holder.cbItem, item.getId());
        } else {
            setText(holder.cbItem, item.getName());
        }
        holder.cbItem.setTag(position);
        if (selectdMap.containsKey(item.getId())) {
            holder.cbItem.setChecked(true);
        } else {
            holder.cbItem.setChecked(false);
        }
    }

    public int getType() {
        return type;
    }

    public void setOnCheckListener(OnItemCheckListener listener) {
        checkListener = listener;
    }

    public void setDataChanged(List<T> list) {
        this.data = list;
        clearSelected();
        notifyDataSetChanged();
    }

    public CheckedModel getSelected() {
        if (selectdMap.isEmpty()) {
            return null;
        }
        for (String id : selectdMap.keySet()) {
            return selectdMap.get(id);
        }
        return null;
    }

    public String[] getSelectedIds() {
        List<String> list = new ArrayList<>();
        if (selectdMap.isEmpty()) {
            return new String[]{};
        }
        list = new ArrayList<>(selectdMap.keySet());
        return list.toArray(new String[list.size()]);
    }

    public List<T> getSelectedList() {
        return new ArrayList<>(selectdMap.values());
    }

    public void clearSelected(int index) {
        clearSelected(index, null);
    }

    public void clearSelected() {
        selectdMap.clear();
        notifyDataSetChanged();
    }

    public void selectedAll() {
        for (int i = 0; i < data.size(); i++) {
            selectdMap.put(getItem(i).getId(), getItem(i));
        }
        notifyDataSetChanged();
    }

    public void setSelectedByIds(String... ids) {
        for (int i = 0; i < ids.length; i++) {
            setSelectedById(ids[i]);
        }
    }

    public void addSelected(T model) {
        selectdMap.put(model.getId(), model);
        notifyDataSetChanged();
    }

    public void disSelected(String id) {
        selectdMap.remove(id);
        notifyDataSetChanged();
    }

    public void setSelected(int index) {
        setSelected(index, null);
    }

    public void setSelected(int index, View v) {
        CheckedModel model = getItem(index);
        if (model == null)
            return;
        if (type != TYPE_NORMAL && model != null && selectdMap.containsKey(model.getId())) {
            clearSelected(index, v);
            return;
        }
        if (type != TYPE_MULTI_SELECTED) {
            selectdMap.clear();
        }
        selectdMap.put(getItem(index).getId(), getItem(index));
        notifyDataSetChanged();
        if (v == null) {
            return;
        }
        if (checkListener != null) {
            checkListener.onItemCheck(index, v, getItem(index));
        }
    }

    public void setSelectedById(String id) {
        if (TextUtils.isEmpty(id)) {
            return;
        }
        for (int i = 0; i < getCount(); i++) {
            CheckedModel model = getItem(i);
            if (model == null)
                continue;
            if (id.equals(model.getId())) {
                setSelected(i, null);
                return;
            }
        }
    }

    private void clearSelected(int index, View v) {
        CheckedModel model = getItem(index);
        if (model == null)
            return;
        selectdMap.remove(model.getId());
        notifyDataSetChanged();
        if (checkListener != null) {
            checkListener.onItemDisCheck(index, v, model);
        }

    }

    class ViewHolder {
        private CheckBox cbItem;

        public ViewHolder(final View view) {
            cbItem = (CheckBox) view.findViewById(R.id.cb_check_item);
            cbItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    setSelected(position, v);

                }
            });
        }

    }

    public interface OnItemCheckListener {

        public void onItemCheck(int position, View view, CheckedModel model);

        public void onItemDisCheck(int postion, View view, CheckedModel model);
    }
}