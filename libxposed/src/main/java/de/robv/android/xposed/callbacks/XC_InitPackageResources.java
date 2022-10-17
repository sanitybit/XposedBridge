package de.robv.android.xposed.callbacks;

import android.content.res.XResources;

import de.robv.android.xposed.IXposedHookInitPackageResources;

/**
 * This class is only used for internal purposes, except for the {@link InitPackageResourcesParam}
 * subclass.
 */
public abstract class XC_InitPackageResources extends XCallback implements IXposedHookInitPackageResources {
	/**
	 * Wraps information about the resources being initialized.
	 */
	public static final class InitPackageResourcesParam extends XCallback.Param {
		/** The name of the package for which resources are being loaded. */
		public String packageName;

		/**
		 * Reference to the resources that can be used for calls to
		 * {@link XResources#setReplacement(String, String, String, Object)}.
		 */
		public XResources res;
	}
}
