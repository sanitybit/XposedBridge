package de.robv.android.xposed;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

public class XRemotePreferences implements SharedPreferences {

    public XRemotePreferences(String packageName) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public String getString(String key, String defValue) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public int getInt(String key, int defValue) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public long getLong(String key, long defValue) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public float getFloat(String key, float defValue) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public boolean contains(String key) {
        throw new UnsupportedOperationException("STUB");
    }

    /** @deprecated Not supported by this implementation. */
    @Deprecated
    @Override
    public Editor edit() {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        throw new UnsupportedOperationException("STUB");
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        throw new UnsupportedOperationException("STUB");
    }
}
