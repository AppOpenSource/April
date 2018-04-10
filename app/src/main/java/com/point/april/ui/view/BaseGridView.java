package com.point.april.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 不可滑动的GridView
 * Created by liuz on 16/6/3.
 */
public class BaseGridView extends GridView {
	public boolean hasScrollBar = true;
	public BaseGridView(Context context) {
		super(context);
	}

	public BaseGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	/**
	 * 重写该方法，达到使GridView适应ScrollView的效果
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = heightMeasureSpec;
		if (hasScrollBar) {
			expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
					MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}


}
