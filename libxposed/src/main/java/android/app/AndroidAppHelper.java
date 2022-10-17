package android.app;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Contains various methods for information about the current app.
 *
 * <p>For historical reasons, this class is in the {@code android.app} package. It can't be moved
 * without breaking compatibility with existing modules.
 */
@SuppressWarnings({"unused", "RedundantSuppression"})
public final class AndroidAppHelper {
	private AndroidAppHelper() {}

	/**
	 * Returns the name of the current process. It's usually the same as the main package name.
	 */
	public static String currentProcessName() {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns information about the main application in the current process.
	 *
	 * <p>In a few cases, multiple apps might run in the same process, e.g. the SystemUI and the
	 * Keyguard which both have {@code android:process="com.android.systemui"} set in their
	 * manifest. In those cases, the first application that was initialized will be returned.
	 */
	public static ApplicationInfo currentApplicationInfo() {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns the Android package name of the main application in the current process.
	 *
	 * <p>In a few cases, multiple apps might run in the same process, e.g. the SystemUI and the
	 * Keyguard which both have {@code android:process="com.android.systemui"} set in their
	 * manifest. In those cases, the first application that was initialized will be returned.
	 */
	public static String currentPackageName() {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns the main {@link android.app.Application} object in the current process.
	 *
	 * <p>In a few cases, multiple apps might run in the same process, e.g. the SystemUI and the
	 * Keyguard which both have {@code android:process="com.android.systemui"} set in their
	 * manifest. In those cases, the first application that was initialized will be returned.
	 */
	public static Application currentApplication() {
		throw new UnsupportedOperationException("STUB");
	}

	/** @deprecated Use {@link XSharedPreferences} instead. */
	@SuppressWarnings("UnusedParameters")
	@Deprecated
	public static SharedPreferences getSharedPreferencesForPackage(String packageName, String prefFileName, int mode) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @deprecated Use {@link XSharedPreferences} instead. */
	@Deprecated
	public static SharedPreferences getDefaultSharedPreferencesForPackage(String packageName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @deprecated Use {@link XSharedPreferences#reload} instead. */
	@Deprecated
	public static void reloadSharedPreferencesIfNeeded(SharedPreferences pref) {
		throw new UnsupportedOperationException("STUB");
	}
}
