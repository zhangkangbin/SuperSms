package com.example.util;


import android.content.Context;
import android.widget.Toast;

public class tool {
	// Toast
	private static Toast toast;

	/**
	 * ∂Ã ±º‰œ‘ æToast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShort(Context context, CharSequence message) {
		if (null == toast) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
			// toast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			toast.setText(message);
		}
		toast.show();
	}
}
