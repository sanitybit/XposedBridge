package de.robv.android.xposed.callbacks;

import android.os.Bundle;
/**
 * Base class for Xposed callbacks.
 *
 * This class only keeps a priority for ordering multiple callbacks.
 * The actual (abstract) callback methods are added by subclasses.
 */
@SuppressWarnings({"JavaDoc", "DeprecatedIsStillUsed"})
public abstract class XCallback implements Comparable<XCallback> {
	/**
	 * Callback priority, higher number means earlier execution.
	 *
	 * <p>This is usually set to {@link #PRIORITY_DEFAULT}. However, in case a certain callback should
	 * be executed earlier or later a value between {@link #PRIORITY_HIGHEST} and {@link #PRIORITY_LOWEST}
	 * can be set instead. The values are just for orientation though, Xposed doesn't enforce any
	 * boundaries on the priority values.
	 */
	public final int priority;

	/** @deprecated This constructor can't be hidden for technical reasons. Nevertheless, don't use it! */
	@Deprecated
	public XCallback() {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Base class for Xposed callback parameters.
	 */
	@SuppressWarnings({"unused", "RedundantSuppression"})
	public static abstract class Param {
		@Deprecated
		protected Param() {
			throw new UnsupportedOperationException("STUB");
		}

		/**
		 * This can be used to store any data for the scope of the callback.
		 *
		 * <p>Use this instead of instance variables, as it has a clear reference to e.g. each
		 * separate call to a method, even when the same method is called recursively.
		 *
		 * @see #setObjectExtra
		 * @see #getObjectExtra
		 */
		public synchronized Bundle getExtra() {
			throw new UnsupportedOperationException("STUB");
		}

		/**
		 * Returns an object stored with {@link #setObjectExtra}.
		 */
		public Object getObjectExtra(String key) {
			throw new UnsupportedOperationException("STUB");
		}

		/**
		 * Stores any object for the scope of the callback. For data types that support it, use
		 * the {@link Bundle} returned by {@link #getExtra} instead.
		 */
		public void setObjectExtra(String key, Object o) {
			throw new UnsupportedOperationException("STUB");
		}
	}

	/** @hide */
	@Override
	public int compareTo(XCallback other) {
		throw new UnsupportedOperationException("STUB");
	}

	/** The default priority, see {@link #priority}. */
	public static final int PRIORITY_DEFAULT = 50;

	 /** Execute this callback late, see {@link #priority}. */
	public static final int PRIORITY_LOWEST = -10000;

	/** Execute this callback early, see {@link #priority}. */
	public static final int PRIORITY_HIGHEST = 10000;
}
