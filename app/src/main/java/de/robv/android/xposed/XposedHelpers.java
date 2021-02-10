package de.robv.android.xposed;

import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * Helpers that simplify hooking and calling methods/constructors, getting and settings fields, ...
 */
@SuppressWarnings({"unused", "JavaDoc", "RedundantThrows", "RedundantSuppression"})
public final class XposedHelpers {
	/**
	 * Look up a class with the specified class loader.
	 *
	 * <p>There are various allowed syntaxes for the class name, but it's recommended to use one of
	 * these:
	 * <ul>
	 *   <li>{@code java.lang.String}
	 *   <li>{@code java.lang.String[]} (array)
	 *   <li>{@code android.app.ActivityThread.ResourcesKey}
	 *   <li>{@code android.app.ActivityThread$ResourcesKey}
	 * </ul>
	 *
	 * @param className The class name in one of the formats mentioned above.
	 * @param classLoader The class loader, or {@code null} for the boot class loader.
	 * @return A reference to the class.
	 * @throws ClassNotFoundError In case the class was not found.
	 */
	public static Class<?> findClass(String className, ClassLoader classLoader) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up and return a class if it exists.
	 * Like {@link #findClass}, but doesn't throw an exception if the class doesn't exist.
	 *
	 * @param className The class name.
	 * @param classLoader The class loader, or {@code null} for the boot class loader.
	 * @return A reference to the class, or {@code null} if it doesn't exist.
	 */
	public static Class<?> findClassIfExists(String className, ClassLoader classLoader) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a field in a class and set it to accessible.
	 *
	 * @param clazz The class which either declares or inherits the field.
	 * @param fieldName The field name.
	 * @return A reference to the field.
	 * @throws NoSuchFieldError In case the field was not found.
	 */
	public static Field findField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up and return a field if it exists.
	 * Like {@link #findField}, but doesn't throw an exception if the field doesn't exist.
	 *
	 * @param clazz The class which either declares or inherits the field.
	 * @param fieldName The field name.
	 * @return A reference to the field, or {@code null} if it doesn't exist.
	 */
	public static Field findFieldIfExists(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	private static Field findFieldRecursiveImpl(Class<?> clazz, String fieldName) throws NoSuchFieldException {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns the first field of the given type in a class.
	 * Might be useful for Proguard'ed classes to identify fields with unique types.
	 *
	 * @param clazz The class which either declares or inherits the field.
	 * @param type The type of the field.
	 * @return A reference to the first field of the given type.
	 * @throws NoSuchFieldError In case no matching field was not found.
	 */
	public static Field findFirstFieldByExactType(Class<?> clazz, Class<?> type) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method and hook it. See {@link #findAndHookMethod(String, ClassLoader, String, Object...)}
	 * for details.
	 */
	public static XC_MethodHook.Unhook findAndHookMethod(Class<?> clazz, String methodName, Object... parameterTypesAndCallback) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method and hook it. The last argument must be the callback for the hook.
	 *
	 * <p>This combines calls to {@link #findMethodExact(Class, String, Object...)} and
	 * {@link XposedBridge#hookMethod}.
	 *
	 * <p class="warning">The method must be declared or overridden in the given class, inherited
	 * methods are not considered! That's because each method implementation exists only once in
	 * the memory, and when classes inherit it, they just get another reference to the implementation.
	 * Hooking a method therefore applies to all classes inheriting the same implementation. You
	 * have to expect that the hook applies to subclasses (unless they override the method), but you
	 * shouldn't have to worry about hooks applying to superclasses, hence this "limitation".
	 * There could be undesired or even dangerous hooks otherwise, e.g. if you hook
	 * {@code SomeClass.equals()} and that class doesn't override the {@code equals()} on some ROMs,
	 * making you hook {@code Object.equals()} instead.
	 *
	 * <p>There are two ways to specify the parameter types. If you already have a reference to the
	 * {@link Class}, use that. For Android framework classes, you can often use something like
	 * {@code String.class}. If you don't have the class reference, you can simply use the
	 * full class name as a string, e.g. {@code java.lang.String} or {@code com.example.MyClass}.
	 * It will be passed to {@link #findClass} with the same class loader that is used for the target
	 * method, see its documentation for the allowed notations.
	 *
	 * <p>Primitive types, such as {@code int}, can be specified using {@code int.class} (recommended)
	 * or {@code Integer.TYPE}. Note that {@code Integer.class} doesn't refer to {@code int} but to
	 * {@code Integer}, which is a normal class (boxed primitive). Therefore it must not be used when
	 * the method expects an {@code int} parameter - it has to be used for {@code Integer} parameters
	 * though, so check the method signature in detail.
	 *
	 * <p>As last argument to this method (after the list of target method parameters), you need
	 * to specify the callback that should be executed when the method is invoked. It's usually
	 * an anonymous subclass of {@link XC_MethodHook} or {@link XC_MethodReplacement}.
	 *
	 * <p><b>Example</b>
	 * <pre class="prettyprint">
	 * // In order to hook this method ...
	 * package com.example;
	 * public class SomeClass {
	 *   public int doSomething(String s, int i, MyClass m) {
	 *     ...
	 *   }
	 * }
	 *
	 * // ... you can use this call:
	 * findAndHookMethod("com.example.SomeClass", lpparam.classLoader, String.class, int.class, "com.example.MyClass", new XC_MethodHook() {
	 *   &#64;Override
	 *   protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
	 *     String oldText = (String) param.args[0];
	 *     Log.d("MyModule", oldText);
	 *
	 *     param.args[0] = "test";
	 *     param.args[1] = 42; // auto-boxing is working here
	 *     setBooleanField(param.args[2], "great", true);
	 *
	 *     // This would not work (as MyClass can't be resolved at compile time):
	 *     //   MyClass myClass = (MyClass) param.args[2];
	 *     //   myClass.great = true;
	 *   }
	 * });
	 * </pre>
	 *
	 * @param className The name of the class which implements the method.
	 * @param classLoader The class loader for resolving the target and parameter classes.
	 * @param methodName The target method name.
	 * @param parameterTypesAndCallback The parameter types of the target method, plus the callback.
	 * @throws NoSuchMethodError In case the method was not found.
	 * @throws ClassNotFoundError In case the target class or one of the parameter types couldn't be resolved.
	 * @return An object which can be used to remove the callback again.
	 */
	public static XC_MethodHook.Unhook findAndHookMethod(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method in a class and set it to accessible.
	 * See {@link #findMethodExact(String, ClassLoader, String, Object...)} for details.
	 */
	public static Method findMethodExact(Class<?> clazz, String methodName, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up and return a method if it exists.
	 * See {@link #findMethodExactIfExists(String, ClassLoader, String, Object...)} for details.
	 */
	public static Method findMethodExactIfExists(Class<?> clazz, String methodName, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method in a class and set it to accessible.
	 * The method must be declared or overridden in the given class.
	 *
	 * <p>See {@link #findAndHookMethod(String, ClassLoader, String, Object...)} for details about
	 * the method and parameter type resolution.
	 *
	 * @param className The name of the class which implements the method.
	 * @param classLoader The class loader for resolving the target and parameter classes.
	 * @param methodName The target method name.
	 * @param parameterTypes The parameter types of the target method.
	 * @throws NoSuchMethodError In case the method was not found.
	 * @throws ClassNotFoundError In case the target class or one of the parameter types couldn't be resolved.
	 * @return A reference to the method.
	 */
	public static Method findMethodExact(String className, ClassLoader classLoader, String methodName, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up and return a method if it exists.
	 * Like {@link #findMethodExact(String, ClassLoader, String, Object...)}, but doesn't throw an
	 * exception if the method doesn't exist.
	 *
	 * @param className The name of the class which implements the method.
	 * @param classLoader The class loader for resolving the target and parameter classes.
	 * @param methodName The target method name.
	 * @param parameterTypes The parameter types of the target method.
	 * @return A reference to the method, or {@code null} if it doesn't exist.
	 */
	public static Method findMethodExactIfExists(String className, ClassLoader classLoader, String methodName, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method in a class and set it to accessible.
	 * See {@link #findMethodExact(String, ClassLoader, String, Object...)} for details.
	 *
	 * <p>This variant requires that you already have reference to all the parameter types.
	 */
	public static Method findMethodExact(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns an array of all methods declared/overridden in a class with the specified parameter types.
	 *
	 * <p>The return type is optional, it will not be compared if it is {@code null}.
	 * Use {@code void.class} if you want to search for methods returning nothing.
	 *
	 * @param clazz The class to look in.
	 * @param returnType The return type, or {@code null} (see above).
	 * @param parameterTypes The parameter types.
	 * @return An array with matching methods, all set to accessible already.
	 */
	public static Method[] findMethodsByExactParameters(Class<?> clazz, Class<?> returnType, Class<?>... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method in a class and set it to accessible.
	 *
	 * <p>This does'nt only look for exact matches, but for the best match. All considered candidates
	 * must be compatible with the given parameter types, i.e. the parameters must be assignable
	 * to the method's formal parameters. Inherited methods are considered here.
	 *
	 * @param clazz The class which declares, inherits or overrides the method.
	 * @param methodName The method name.
	 * @param parameterTypes The types of the method's parameters.
	 * @return A reference to the best-matching method.
	 * @throws NoSuchMethodError In case no suitable method was found.
	 */
	public static Method findMethodBestMatch(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method in a class and set it to accessible.
	 *
	 * <p>See {@link #findMethodBestMatch(Class, String, Class...)} for details. This variant
	 * determines the parameter types from the classes of the given objects.
	 */
	public static Method findMethodBestMatch(Class<?> clazz, String methodName, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a method in a class and set it to accessible.
	 *
	 * <p>See {@link #findMethodBestMatch(Class, String, Class...)} for details. This variant
	 * determines the parameter types from the classes of the given objects. For any item that is
	 * {@code null}, the type is taken from {@code parameterTypes} instead.
	 */
	public static Method findMethodBestMatch(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object[] args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns an array with the classes of the given objects.
	 */
	public static Class<?>[] getParameterTypes(Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Retrieve classes from an array, where each element might either be a Class
	 * already, or a String with the full class name.
	 */
	private static Class<?>[] getParameterClasses(ClassLoader classLoader, Object[] parameterTypesAndCallback) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns an array of the given classes.
	 */
	public static Class<?>[] getClassesAsArray(Class<?>... clazzes) {
		throw new UnsupportedOperationException("STUB");
	}

	private static String getParametersString(Class<?>... clazzes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor of a class and set it to accessible.
	 * See {@link #findMethodExact(String, ClassLoader, String, Object...)} for details.
	 */
	public static Constructor<?> findConstructorExact(Class<?> clazz, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up and return a constructor if it exists.
	 * See {@link #findMethodExactIfExists(String, ClassLoader, String, Object...)} for details.
	 */
	public static Constructor<?> findConstructorExactIfExists(Class<?> clazz, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor of a class and set it to accessible.
	 * See {@link #findMethodExact(String, ClassLoader, String, Object...)} for details.
	 */
	public static Constructor<?> findConstructorExact(String className, ClassLoader classLoader, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up and return a constructor if it exists.
	 * See {@link #findMethodExactIfExists(String, ClassLoader, String, Object...)} for details.
	 */
	public static Constructor<?> findConstructorExactIfExists(String className, ClassLoader classLoader, Object... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor of a class and set it to accessible.
	 * See {@link #findMethodExact(String, ClassLoader, String, Object...)} for details.
	 */
	public static Constructor<?> findConstructorExact(Class<?> clazz, Class<?>... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor and hook it. See {@link #findAndHookMethod(String, ClassLoader, String, Object...)}
	 * for details.
	 */
	public static XC_MethodHook.Unhook findAndHookConstructor(Class<?> clazz, Object... parameterTypesAndCallback) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor and hook it. See {@link #findAndHookMethod(String, ClassLoader, String, Object...)}
	 * for details.
	 */
	public static XC_MethodHook.Unhook findAndHookConstructor(String className, ClassLoader classLoader, Object... parameterTypesAndCallback) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor in a class and set it to accessible.
	 *
	 * <p>See {@link #findMethodBestMatch(Class, String, Class...)} for details.
	 */
	public static Constructor<?> findConstructorBestMatch(Class<?> clazz, Class<?>... parameterTypes) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor in a class and set it to accessible.
	 *
	 * <p>See {@link #findMethodBestMatch(Class, String, Class...)} for details. This variant
	 * determines the parameter types from the classes of the given objects.
	 */
	public static Constructor<?> findConstructorBestMatch(Class<?> clazz, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Look up a constructor in a class and set it to accessible.
	 *
	 * <p>See {@link #findMethodBestMatch(Class, String, Class...)} for details. This variant
	 * determines the parameter types from the classes of the given objects. For any item that is
	 * {@code null}, the type is taken from {@code parameterTypes} instead.
	 */
	public static Constructor<?> findConstructorBestMatch(Class<?> clazz, Class<?>[] parameterTypes, Object[] args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Thrown when a class loader is unable to find a class. Unlike {@link ClassNotFoundException},
	 * callers are not forced to explicitly catch this. If uncaught, the error will be passed to the
	 * next caller in the stack.
	 */
	public static final class ClassNotFoundError extends Error {
		private static final long serialVersionUID = -1070936889459514628L;

		/** @hide */
		public ClassNotFoundError(Throwable cause) {
			super(cause);
		}

		/** @hide */
		public ClassNotFoundError(String detailMessage, Throwable cause) {
			super(detailMessage, cause);
		}
	}

	/**
	 * Returns the index of the first parameter declared with the given type.
	 *
	 * @throws NoSuchFieldError if there is no parameter with that type.
	 * @hide
	 */
	public static int getFirstParameterIndexByType(Member method, Class<?> type) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns the index of the parameter declared with the given type, ensuring that there is exactly one such parameter.
	 *
	 * @throws NoSuchFieldError if there is no or more than one parameter with that type.
	 * @hide
	 */
	public static int getParameterIndexByType(Member method, Class<?> type) {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################
	/** Sets the value of an object field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setObjectField(Object obj, String fieldName, Object value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a {@code boolean} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setBooleanField(Object obj, String fieldName, boolean value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a {@code byte} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setByteField(Object obj, String fieldName, byte value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a {@code char} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setCharField(Object obj, String fieldName, char value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a {@code double} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setDoubleField(Object obj, String fieldName, double value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a {@code float} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setFloatField(Object obj, String fieldName, float value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of an {@code int} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setIntField(Object obj, String fieldName, int value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a {@code long} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setLongField(Object obj, String fieldName, long value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a {@code short} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static void setShortField(Object obj, String fieldName, short value) {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################
	/** Returns the value of an object field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static Object getObjectField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** For inner classes, returns the surrounding instance, i.e. the {@code this} reference of the surrounding class. */
	public static Object getSurroundingThis(Object obj) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a {@code boolean} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static boolean getBooleanField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a {@code byte} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static byte getByteField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a {@code char} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static char getCharField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a {@code double} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static double getDoubleField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a {@code float} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static float getFloatField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of an {@code int} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static int getIntField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a {@code long} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static long getLongField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a {@code short} field in the given object instance. A class reference is not sufficient! See also {@link #findField}. */
	public static short getShortField(Object obj, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################
	/** Sets the value of a static object field in the given class. See also {@link #findField}. */
	public static void setStaticObjectField(Class<?> clazz, String fieldName, Object value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code boolean} field in the given class. See also {@link #findField}. */
	public static void setStaticBooleanField(Class<?> clazz, String fieldName, boolean value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code byte} field in the given class. See also {@link #findField}. */
	public static void setStaticByteField(Class<?> clazz, String fieldName, byte value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code char} field in the given class. See also {@link #findField}. */
	public static void setStaticCharField(Class<?> clazz, String fieldName, char value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code double} field in the given class. See also {@link #findField}. */
	public static void setStaticDoubleField(Class<?> clazz, String fieldName, double value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code float} field in the given class. See also {@link #findField}. */
	public static void setStaticFloatField(Class<?> clazz, String fieldName, float value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code int} field in the given class. See also {@link #findField}. */
	public static void setStaticIntField(Class<?> clazz, String fieldName, int value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code long} field in the given class. See also {@link #findField}. */
	public static void setStaticLongField(Class<?> clazz, String fieldName, long value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code short} field in the given class. See also {@link #findField}. */
	public static void setStaticShortField(Class<?> clazz, String fieldName, short value) {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################
	/** Returns the value of a static object field in the given class. See also {@link #findField}. */
	public static Object getStaticObjectField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Returns the value of a static {@code boolean} field in the given class. See also {@link #findField}. */
	public static boolean getStaticBooleanField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code byte} field in the given class. See also {@link #findField}. */
	public static byte getStaticByteField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code char} field in the given class. See also {@link #findField}. */
	public static char getStaticCharField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code double} field in the given class. See also {@link #findField}. */
	public static double getStaticDoubleField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code float} field in the given class. See also {@link #findField}. */
	public static float getStaticFloatField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code int} field in the given class. See also {@link #findField}. */
	public static int getStaticIntField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code long} field in the given class. See also {@link #findField}. */
	public static long getStaticLongField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Sets the value of a static {@code short} field in the given class. See also {@link #findField}. */
	public static short getStaticShortField(Class<?> clazz, String fieldName) {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################
	/**
	 * Calls an instance or static method of the given object.
	 * The method is resolved using {@link #findMethodBestMatch(Class, String, Object...)}.
	 *
	 * @param obj The object instance. A class reference is not sufficient!
	 * @param methodName The method name.
	 * @param args The arguments for the method call.
	 * @throws NoSuchMethodError In case no suitable method was found.
	 * @throws InvocationTargetError In case an exception was thrown by the invoked method.
	 */
	public static Object callMethod(Object obj, String methodName, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Calls an instance or static method of the given object.
	 * See {@link #callMethod(Object, String, Object...)}.
	 *
	 * <p>This variant allows you to specify parameter types, which can help in case there are multiple
	 * methods with the same name, especially if you call it with {@code null} parameters.
	 */
	public static Object callMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Calls a static method of the given class.
	 * The method is resolved using {@link #findMethodBestMatch(Class, String, Object...)}.
	 *
	 * @param clazz The class reference.
	 * @param methodName The method name.
	 * @param args The arguments for the method call.
	 * @throws NoSuchMethodError In case no suitable method was found.
	 * @throws InvocationTargetError In case an exception was thrown by the invoked method.
	 */
	public static Object callStaticMethod(Class<?> clazz, String methodName, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Calls a static method of the given class.
	 * See {@link #callStaticMethod(Class, String, Object...)}.
	 *
	 * <p>This variant allows you to specify parameter types, which can help in case there are multiple
	 * methods with the same name, especially if you call it with {@code null} parameters.
	 */
	public static Object callStaticMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * This class provides a wrapper for an exception thrown by a method invocation.
	 *
	 * @see #callMethod(Object, String, Object...)
	 * @see #callStaticMethod(Class, String, Object...)
	 * @see #newInstance(Class, Object...)
	 */
	public static final class InvocationTargetError extends Error {
		private static final long serialVersionUID = -1070936889459514628L;

		/** @hide */
		public InvocationTargetError(Throwable cause) {
			super(cause);
		}
	}

	//#################################################################################################
	/**
	 * Creates a new instance of the given class.
	 * The constructor is resolved using {@link #findConstructorBestMatch(Class, Object...)}.
	 *
	 * @param clazz The class reference.
	 * @param args The arguments for the constructor call.
	 * @throws NoSuchMethodError In case no suitable constructor was found.
	 * @throws InvocationTargetError In case an exception was thrown by the invoked method.
	 * @throws InstantiationError In case the class cannot be instantiated.
	 */
	public static Object newInstance(Class<?> clazz, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Creates a new instance of the given class.
	 * See {@link #newInstance(Class, Object...)}.
	 *
	 * <p>This variant allows you to specify parameter types, which can help in case there are multiple
	 * constructors with the same name, especially if you call it with {@code null} parameters.
	 */
	public static Object newInstance(Class<?> clazz, Class<?>[] parameterTypes, Object... args) {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################

	/**
	 * Attaches any value to an object instance. This simulates adding an instance field.
	 * The value can be retrieved again with {@link #getAdditionalInstanceField}.
	 *
	 * @param obj The object instance for which the value should be stored.
	 * @param key The key in the value map for this object instance.
	 * @param value The value to store.
	 * @return The previously stored value for this instance/key combination, or {@code null} if there was none.
	 */
	public static Object setAdditionalInstanceField(Object obj, String key, Object value) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns a value which was stored with {@link #setAdditionalInstanceField}.
	 *
	 * @param obj The object instance for which the value has been stored.
	 * @param key The key in the value map for this object instance.
	 * @return The stored value for this instance/key combination, or {@code null} if there is none.
	 */
	public static Object getAdditionalInstanceField(Object obj, String key) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Removes and returns a value which was stored with {@link #setAdditionalInstanceField}.
	 *
	 * @param obj The object instance for which the value has been stored.
	 * @param key The key in the value map for this object instance.
	 * @return The previously stored value for this instance/key combination, or {@code null} if there was none.
	 */
	public static Object removeAdditionalInstanceField(Object obj, String key) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Like {@link #setAdditionalInstanceField}, but the value is stored for the class of {@code obj}. */
	public static Object setAdditionalStaticField(Object obj, String key, Object value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Like {@link #getAdditionalInstanceField}, but the value is returned for the class of {@code obj}. */
	public static Object getAdditionalStaticField(Object obj, String key) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Like {@link #removeAdditionalInstanceField}, but the value is removed and returned for the class of {@code obj}. */
	public static Object removeAdditionalStaticField(Object obj, String key) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Like {@link #setAdditionalInstanceField}, but the value is stored for {@code clazz}. */
	public static Object setAdditionalStaticField(Class<?> clazz, String key, Object value) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Like {@link #setAdditionalInstanceField}, but the value is returned for {@code clazz}. */
	public static Object getAdditionalStaticField(Class<?> clazz, String key) {
		throw new UnsupportedOperationException("STUB");
	}

	/** Like {@link #setAdditionalInstanceField}, but the value is removed and returned for {@code clazz}. */
	public static Object removeAdditionalStaticField(Class<?> clazz, String key) {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################
	/**
	 * Loads an asset from a resource object and returns the content as {@code byte} array.
	 *
	 * @param res The resources from which the asset should be loaded.
	 * @param path The path to the asset, as in {@link AssetManager#open}.
	 * @return The content of the asset.
	 */
	public static byte[] assetAsByteArray(Resources res, String path) throws IOException {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns the lowercase hex string representation of a file's MD5 hash sum.
	 */
	public static String getMD5Sum(String file) throws IOException {
		throw new UnsupportedOperationException("STUB");
	}

	//#################################################################################################
	/**
	 * Increments the depth counter for the given method.
	 *
	 * <p>The intention of the method depth counter is to keep track of the call depth for recursive
	 * methods, e.g. to override parameters only for the outer call. The Xposed framework uses this
	 * to load drawable replacements only once per call, even when multiple
	 * {@link Resources#getDrawable} variants call each other.
	 *
	 * @param method The method name. Should be prefixed with a unique, module-specific string.
	 * @return The updated depth.
	 */
	public static int incrementMethodDepth(String method) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Decrements the depth counter for the given method.
	 * See {@link #incrementMethodDepth} for details.
	 *
	 * @param method The method name. Should be prefixed with a unique, module-specific string.
	 * @return The updated depth.
	 */
	public static int decrementMethodDepth(String method) {
		throw new UnsupportedOperationException("STUB");
	}

	/**
	 * Returns the current depth counter for the given method.
	 * See {@link #incrementMethodDepth} for details.
	 *
	 * @param method The method name. Should be prefixed with a unique, module-specific string.
	 * @return The updated depth.
	 */
	public static int getMethodDepth(String method) {
		throw new UnsupportedOperationException("STUB");
	}
}
