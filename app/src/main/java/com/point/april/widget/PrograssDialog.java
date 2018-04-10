package com.point.april.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.point.april.R;

public class PrograssDialog extends Dialog {

	public PrograssDialog(Context context) {
		super(context);
	}

	public PrograssDialog(Context context, int theme) {
		super(context, theme);
	}

	@SuppressLint("NewApi")
	public static class Builder {
		private Context context;
		private String message;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}


		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}


		public Dialog create() {
			LayoutInflater inflater = (LayoutInflater) context.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
			View view = inflater.inflate(R.layout.dialog_loading, null);
			LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
			ImageView image = (ImageView) view.findViewById(R.id.img);
			TextView tips = (TextView) view.findViewById(R.id.tipTextView);
			loadingDialog.setContentView(layout);

			layout.getBackground().setAlpha(0);
			image.setImageResource(R.drawable.loading); // TODO
			AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
			animationDrawable.start();
			Window dialogWindow = loadingDialog.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			lp.width=LayoutParams.WRAP_CONTENT;
			lp.height=LayoutParams.WRAP_CONTENT;
			dialogWindow.setGravity(Gravity.CENTER);
			dialogWindow.setAttributes(lp);
			tips.setText(message);
			loadingDialog.setContentView(layout);

			return loadingDialog;
		}

	}
}
