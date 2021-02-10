package android.content.res;

/**
 * Instances of this class can be used for {@link XResources#setReplacement(String, String, String, Object)}
 * and its variants. They forward the resource request to a different {@link android.content.res.Resources}
 * instance with a possibly different ID.
 *
 * <p>Usually, instances aren't created directly but via {@link XModuleResources#fwd}.
 */
public class XResForwarder {
	/**
	 * Creates a new instance.
	 *
	 * @param res The target {@link android.content.res.Resources} instance to forward requests to.
	 * @param id The target resource ID.
	 */
	public XResForwarder(Resources res, int id) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the target {@link android.content.res.Resources} instance. */
	public Resources getResources() {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the target resource ID. */
	public int getId() {
		throw new UnsupportedOperationException("STUB");
	}
}
