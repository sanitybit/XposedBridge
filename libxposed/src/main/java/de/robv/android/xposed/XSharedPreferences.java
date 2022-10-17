package de.robv.android.xposed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * This class is basically the same as SharedPreferencesImpl from AOSP, but
 * read-only and without listeners support. Instead, it is made to be
 * compatible with all ROMs.
 */
@SuppressWarnings({"unused", "JavaDoc", "RedundantSuppression"})
public final class XSharedPreferences implements SharedPreferences {
	/**
	 * Read settings from the specified file.
	 * @param prefFile The file to read the preferences from.
	 */
	public XSharedPreferences(File prefFile) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Read settings from the default preferences for a package.
	 * These preferences are returned by {@link PreferenceManager#getDefaultSharedPreferences}.
	 * @param packageName The package name.
	 */
	public XSharedPreferences(String packageName) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Read settings from a custom preferences file for a package.
	 * These preferences are returned by {@link Context#getSharedPreferences(String, int)}.
	 * @param packageName The package name.
	 * @param prefFileName The file name without ".xml".
	 */
	public XSharedPreferences(String packageName, String prefFileName) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Tries to make the preferences file world-readable.
	 *
	 * <p><strong>Warning:</strong> This is only meant to work around permission "fix" functions that are part
	 * of some recoveries. It doesn't replace the need to open preferences with {@code MODE_WORLD_READABLE}
	 * in the module's UI code. Otherwise, Android will set stricter permissions again during the next save.
	 *
	 * <p>This will only work if executed as root (e.g. {@code initZygote()}) and only if SELinux is disabled.
	 *
	 * @return {@code true} in case the file could be made world-readable.
     * @deprecated
	 */
	@SuppressLint("SetWorldReadable")
	public boolean makeWorldReadable() {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns the file that is backing these preferences.
	 *
	 * <p><strong>Warning:</strong> The file might not be accessible directly.
	 */
	public File getFile() {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Reload the settings from file if they have changed.
	 *
	 * <p><strong>Warning:</strong> With enforcing SELinux, this call might be quite expensive.
	 */
	public synchronized void reload() {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Check whether the file has changed since the last time it has been loaded.
	 *
	 * <p><strong>Warning:</strong> With enforcing SELinux, this call might be quite expensive.
	 */
	public synchronized boolean hasFileChanged() {
		throw new UnsupportedOperationException("STUB");
	}
	/** @hide */
	@Override
	public Map<String, ?> getAll() {
		throw new UnsupportedOperationException("STUB");
	}

	/** @hide */
	@Override
	public String getString(String key, String defValue) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @hide */
	@Override
	public Set<String> getStringSet(String key, Set<String> defValues) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @hide */
	@Override
	public int getInt(String key, int defValue) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @hide */
	@Override
	public long getLong(String key, long defValue) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @hide */
	@Override
	public float getFloat(String key, float defValue) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @hide */
	@Override
	public boolean getBoolean(String key, boolean defValue) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @hide */
	@Override
	public boolean contains(String key) {
		throw new UnsupportedOperationException("STUB");
	}

	/** @deprecated Not supported by this implementation. */
	@Deprecated
	@Override
	public Editor edit() {
		throw new UnsupportedOperationException("read-only implementation");
	}

	/** @deprecated Not supported by this implementation. */
	@Deprecated
	@Override
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		throw new UnsupportedOperationException("listeners are not supported in this implementation");
	}

	/** @deprecated Not supported by this implementation. */
	@Deprecated
	@Override
	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
		throw new UnsupportedOperationException("listeners are not supported in this implementation");
	}

}
