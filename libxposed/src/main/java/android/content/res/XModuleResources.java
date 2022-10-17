package android.content.res;

import android.content.Context;
import android.util.DisplayMetrics;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.IXposedHookZygoteInit.StartupParam;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

/**
 * Provides access to resources from a certain path (usually the module's own path).
 */
@SuppressWarnings({"unused", "RedundantSuppression"})
public class XModuleResources extends Resources {
	/**
	 * Create a new Resources object on top of an existing set of assets in an
	 * AssetManager.
	 *
	 * @param assets  Previously created AssetManager.
	 * @param metrics Current display metrics to consider when
	 *                selecting/computing resource values.
	 * @param config  Desired device configuration to consider when
	 * @deprecated Resources should not be constructed by apps.
	 * See {@link Context#createConfigurationContext(Configuration)}.
	 */
	public XModuleResources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
		super(assets, metrics, config);
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Creates a new instance.
	 *
	 * <p>This is usually called with {@link StartupParam#modulePath} from
	 * {@link IXposedHookZygoteInit#initZygote} and {@link InitPackageResourcesParam#res} from
	 * {@link IXposedHookInitPackageResources#handleInitPackageResources} (or {@code null} for
	 * system-wide replacements).
	 *
	 * @param path The path to the APK from which the resources should be loaded.
	 * @param origRes The resources object from which settings like the display metrics and the
	 *                configuration should be copied. May be {@code null}.
	 */
	public static XModuleResources createInstance(String path, XResources origRes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Creates an {@link XResForwarder} instance that forwards requests to {@code id} in this resource.
	 */
	public XResForwarder fwd(int id) {
		throw new UnsupportedOperationException("STUB");
	}
}
