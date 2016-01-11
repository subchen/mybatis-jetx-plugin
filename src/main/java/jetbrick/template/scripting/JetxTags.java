package jetbrick.template.scripting;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jetbrick.template.runtime.JetTagContext;
import jetbrick.template.runtime.JetWriter;
import jetbrick.template.scripting.tags.SetSqlTag;
import jetbrick.template.scripting.tags.TrimSqlTag;
import jetbrick.template.scripting.tags.WhereSqlTag;

public class JetxTags {

	public static void where(JetTagContext ctx) throws IOException {
		String contents = ctx.getBodyContent();
		WhereSqlTag sql = new WhereSqlTag(contents);
		ctx.getWriter().print(sql.apply());
	}

	public static void set(JetTagContext ctx) throws IOException {
		String contents = ctx.getBodyContent();
		SetSqlTag sql = new SetSqlTag(contents);
		ctx.getWriter().print(sql.apply());
	}

	public static void trim(JetTagContext ctx, String prefix, String prefixesToOverride, String suffix,
			String suffixesToOverride) throws IOException {
		String contents = ctx.getBodyContent();
		TrimSqlTag sql = new TrimSqlTag(contents, prefix, prefixesToOverride, suffix, suffixesToOverride);
		ctx.getWriter().print(sql.apply());
	}

	@SuppressWarnings("unchecked")
	public static void foreach(JetTagContext ctx, Object collection, String item, String open, String close, String separator)
			throws IOException {
		String contents = ctx.getBodyContent();
		JetWriter writer = ctx.getWriter();
		if (collection == null) {
			writer.print(contents);
			return;
		}

		Map<String, Object> privateContext = new HashMap<String, Object>();
		Class<? extends Object> clazz = collection.getClass();
		
		if (clazz.isArray()) {
			for (int i = 0, length = Array.getLength(collection); i < length; i++) {
				privateContext.put(item + i, Array.get(collection, i));
			}
		} else if (Iterable.class.isAssignableFrom(clazz)) {
			Iterator<Object> iterator = ((Iterable<Object>) collection).iterator();
			int i = 0;
			while (iterator.hasNext()) {
				privateContext.put(item + i, iterator.next());
				i++;
			}
		} else {

		}
		ctx.getValueStack().push(null, privateContext, true);
		ctx.getWriter().print(contents);
	}
}
