package com.example.recycleview;

import android.view.View;

/**
 * Created by p_billylu on 2018/7/30.
 */

public interface OnItemClickLitener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view , int position);
}
