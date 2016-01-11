package jetbrick.template.scripting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jetbrick.template.runtime.InterpretContext;
import jetbrick.util.ObjectUtils;
import jetbrick.util.StringUtils;

public class JetxFunctions {

	// null 判断
	public static boolean isNull(Object val) {
		return ObjectUtils.equals(val, null);
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 */
	public static boolean isEmpty(final String s) {
		return StringUtils.isEmpty(s);
	}

	/**
	 * <p>
	 * Checks if a String is not empty ("") and not null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 */
	public static boolean isNotEmpty(final String s) {
		return StringUtils.isNotEmpty(s);
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 */
	public static boolean isBlank(final String s) {
		return StringUtils.isBlank(s);
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace
	 * only.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 */
	public static boolean isNotBlank(final String s) {
		return StringUtils.isNotBlank(s);
	}

	// Trim
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String,
	 * handling {@code null} by returning {@code null}.
	 * </p>
	 *
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.trim(null)          = null
	 * StringUtils.trim("")            = ""
	 * StringUtils.trim("     ")       = ""
	 * StringUtils.trim("abc")         = "abc"
	 * StringUtils.trim("    abc    ") = "abc"
	 * </pre>
	 */
	public static String trim(final String s) {
		return StringUtils.trim(s);
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning {@code null} if the String is empty ("") after the trim or if
	 * it is {@code null}.
	 *
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.trimToNull(null)          = null
	 * StringUtils.trimToNull("")            = null
	 * StringUtils.trimToNull("     ")       = null
	 * StringUtils.trimToNull("abc")         = "abc"
	 * StringUtils.trimToNull("    abc    ") = "abc"
	 * </pre>
	 */
	public static String trimToNull(final String s) {
		return StringUtils.trimToNull(s);
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String
	 * returning an empty String ("") if the String is empty ("") after the trim
	 * or if it is {@code null}.
	 *
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and
	 * end characters &lt;= 32.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.trimToEmpty(null)          = ""
	 * StringUtils.trimToEmpty("")            = ""
	 * StringUtils.trimToEmpty("     ")       = ""
	 * StringUtils.trimToEmpty("abc")         = "abc"
	 * StringUtils.trimToEmpty("    abc    ") = "abc"
	 * </pre>
	 */
	public static String trimToEmpty(final String s) {
		return StringUtils.trimToEmpty(s);
	}

	// Defaults
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Returns either the passed in String, or if the String is {@code null},
	 * the value of {@code defaultStr}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.defaultIfNull(null, "NULL")  = "NULL"
	 * StringUtils.defaultIfNull("", "NULL")    = ""
	 * StringUtils.defaultIfNull("bat", "NULL") = "bat"
	 * </pre>
	 */
	public static String defaultIfNull(final String s, final String defaultStr) {
		return StringUtils.defaultIfNull(s, defaultStr);
	}

	/**
	 * <p>
	 * Returns either the passed in String, or if the String is
	 * empty or {@code null}, the value of {@code defaultStr}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.defaultIfEmpty(null, "NULL")  = "NULL"
	 * StringUtils.defaultIfEmpty("", "NULL")    = "NULL"
	 * StringUtils.defaultIfEmpty(" ", "NULL")   = " "
	 * StringUtils.defaultIfEmpty("bat", "NULL") = "bat"
	 * StringUtils.defaultIfEmpty("", null)      = null
	 * </pre>
	 */
	public static String defaultIfEmpty(final String s, final String defaultStr) {
		return StringUtils.defaultIfEmpty(s, defaultStr);
	}

	/**
	 * <p>
	 * Returns either the passed in String, or if the String is
	 * whitespace, empty ("") or {@code null}, the value of {@code defaultStr}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.defaultIfBlank(null, "NULL")  = "NULL"
	 * StringUtils.defaultIfBlank("", "NULL")    = "NULL"
	 * StringUtils.defaultIfBlank(" ", "NULL")   = "NULL"
	 * StringUtils.defaultIfBlank("bat", "NULL") = "bat"
	 * StringUtils.defaultIfBlank("", null)      = null
	 * </pre>
	 */
	public static String defaultIfBlank(final String s, final String defaultStr) {
		return StringUtils.defaultIfBlank(s, defaultStr);
	}

	// Left/Right/Mid
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets the leftmost {@code len} characters of a String.
	 * </p>
	 *
	 * <p>
	 * If {@code len} characters are not available, or the String is
	 * {@code null}, the String will be returned without an exception. An empty
	 * String is returned if len is negative.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.left(null, *)    = null
	 * StringUtils.left(*, -ve)     = ""
	 * StringUtils.left("", *)      = ""
	 * StringUtils.left("abc", 0)   = ""
	 * StringUtils.left("abc", 2)   = "ab"
	 * StringUtils.left("abc", 4)   = "abc"
	 * </pre>
	 */
	public static String left(final String s, final int len) {
		return StringUtils.left(s, len);
	}

	/**
	 * <p>
	 * Gets the rightmost {@code len} characters of a String.
	 * </p>
	 *
	 * <p>
	 * If {@code len} characters are not available, or the String is
	 * {@code null}, the String will be returned without an an exception. An
	 * empty String is returned if len is negative.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.right(null, *)    = null
	 * StringUtils.right(*, -ve)     = ""
	 * StringUtils.right("", *)      = ""
	 * StringUtils.right("abc", 0)   = ""
	 * StringUtils.right("abc", 2)   = "bc"
	 * StringUtils.right("abc", 4)   = "abc"
	 * </pre>
	 */
	public static String right(final String s, final int len) {
		return StringUtils.right(s, len);
	}

	public static String leftPad(String str, int size) {
		return StringUtils.leftPad(str, size);
	}

	public static String leftPad(String str, int size, char padChar) {
		return StringUtils.leftPad(str, size, padChar);
	}

	public static String leftPad(String str, int size, String padStr) {
		return StringUtils.leftPad(str, size, padStr);
	}

	public static String rightPad(String str, int size) {
		return StringUtils.rightPad(str, size);
	}

	public static String rightPad(String str, int size, char padChar) {
		return StringUtils.rightPad(str, size, padChar);
	}

	public static String rightPad(String str, int size, String padStr) {
		return StringUtils.rightPad(str, size, padStr);
	}

	public static String prefix(String s, String prefix) {
		return StringUtils.prefix(s, prefix);
	}

	public static String suffix(String s, String suffix) {
		return StringUtils.suffix(s, suffix);
	}

	public static String[] split(String str, String delimiter) {
		return StringUtils.split(str, delimiter);
	}

	public static String[] split(String str, char delimiter) {
		return StringUtils.split(str, delimiter);
	}

	public static String[] splitChars(String str, String delimiters) {
		return StringUtils.splitChars(str, delimiters);
	}

	public static String[] splitChars(String str, char... delimiters) {
		return StringUtils.splitChars(str, delimiters);
	}

	public static String join(String... parts) {
		return StringUtils.join(parts);
	}

	public static String join(Iterable<?> elements, String separator) {
		return StringUtils.join(elements, separator);
	}

	public static String join(Iterator<?> elements, String separator) {
		return StringUtils.join(elements, separator);
	}

	public static String join(Object[] elements, String separator) {
		return StringUtils.join(elements, separator);
	}

	public static int count(String source, String substr) {
		return StringUtils.count(source, substr);
	}

	public static int count(String source, String substr, int start) {
		return StringUtils.count(source, substr, start);
	}

	public static int count(String source, char c) {
		return StringUtils.count(source, c);
	}

	public static int count(String source, char c, int start) {
		return StringUtils.count(source, c, start);
	}

	// ----------------------------------------------------------
	// sql 相关函数
	// ----------------------------------------------------------

	/**
	 * <pre>
	 * pa("abc")      = #{abc}
	 * </pre> 
	 * @param value
	 * @return String
	 */
	public static String p(String name) {
		if (name == null) { return null; }
		return String.format("#{%s}", name);
	}

	/**
	 * @Title: bind
	 * @param name
	 * @param value void
	 */
	public static void bind(String name, Object value) {
		if (name == null) { return; }
		InterpretContext interpretContext = InterpretContext.current();
		if (interpretContext != null) {
			Map<String, Object> privateContext = new HashMap<String, Object>();
			privateContext.put(name, value);
			interpretContext.getValueStack().push(null, privateContext, true);
		}
	}
}
