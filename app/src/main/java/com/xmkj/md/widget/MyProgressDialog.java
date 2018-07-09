package com.xmkj.md.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.xmkj.md.R;


public class MyProgressDialog extends Dialog {
	private static MyProgressDialog dialog = null;
	private Context context;

	public MyProgressDialog(Context context) {
		super(context);
		this.context = context;
	}

	public MyProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static MyProgressDialog createDialog(Context context){//创建dialog
		dialog = new MyProgressDialog(context, R.style.MyProgressDialog);
		dialog.setContentView(R.layout.view_myprogress_dialog);
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.gravity = Gravity.CENTER;
		lp.alpha = 0.5f;
		lp.dimAmount = 0.3f;
		return dialog;
	}

	public MyProgressDialog setMessage(String str){//给dialog添加文字
		TextView tv = (TextView)dialog.findViewById(R.id.id_tv_loadingmsg);
		if (tv != null){
			tv.setText(str);
		}
		return dialog;
	}


}
