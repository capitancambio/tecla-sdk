package ca.idi.tecla.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;

public class SEPManager {

	/**
	 * Start the Switch Event Provider and attempt a connection with a Tecla Shield with the address provided
	 */
	public static void start(Context context) {
		//		logD(CLASS_TAG, "Starting TeclaService...");
		if (!isRunning(context)) {
			Intent sepIntent = new Intent(SwitchEventProvider.NAME);
			context.startService(sepIntent);
		} else {
			//			logW(CLASS_TAG, "Tecla Service already running!");
		}
	}

	public static boolean stop(Context context) {
		Intent sepIntent = new Intent(SwitchEventProvider.NAME);
		return context.stopService(sepIntent);
	}

	private static boolean isRunning(Context context) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service_info : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (SwitchEventProvider.class.getName().equals(service_info.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

}
