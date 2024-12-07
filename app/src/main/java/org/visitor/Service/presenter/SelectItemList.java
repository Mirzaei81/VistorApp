package org.visitor.Service.presenter;

import android.view.View;
import android.widget.TextView;

public interface SelectItemList<T> {
    void onSelectItem(T object, int position, View view, View view2);
    void onSelectItemForCats(T object, int position, View view, TextView textView, boolean booleanAdd);

}