package com.point.april.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.point.april.R;

public class ToastDialog extends Dialog {

	public ToastDialog(Context context) {
		super(context);
	}

	public ToastDialog(Context context, int theme) {
		super(context, theme);
	}

	@SuppressLint("NewApi")
	public static class Builder {
		private Context context;
		private String message;
		private boolean mType;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public void setImageView(Boolean type){
			this.mType = type;	
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param //title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}


		public ToastDialog create() {
			LayoutInflater inflater = (LayoutInflater) context.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final ToastDialog dialog = new ToastDialog(context, R.style.ToastDialog);
			View layout = inflater.inflate(R.layout.mytoast, null);
			dialog.setContentView(layout);
			Window dialogWindow = dialog.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			lp.y=120;
			lp.width=LayoutParams.WRAP_CONTENT;
			lp.height=LayoutParams.WRAP_CONTENT;
			dialogWindow.setGravity(Gravity.BOTTOM |Gravity.CENTER_HORIZONTAL);
			dialogWindow.setAttributes(lp);
			((RelativeLayout) layout.findViewById(R.id.toast_layout)).getBackground().setAlpha(175);
			((TextView) layout.findViewById(R.id.tv_toast_msg)).setText(message);
			if(mType==false){
				((ImageView) layout.findViewById(R.id.img_toast)).setBackgroundResource(R.drawable.icon_toast_false);
			}else{
				((ImageView) layout.findViewById(R.id.img_toast)).setBackgroundResource(R.drawable.icon_toast_true);
			}
			dialog.setContentView(layout);
			return dialog;
		}

	}
}
