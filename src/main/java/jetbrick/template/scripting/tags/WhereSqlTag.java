package jetbrick.template.scripting.tags;

import java.util.Arrays;
import java.util.List;

public class WhereSqlTag extends TrimSqlTag {

	private static List<String> prefixList = Arrays.asList("AND ", "OR ", "AND\n", "OR\n", "AND\r", "OR\r", "AND\t", "OR\t");

	public WhereSqlTag(String contents) {
		super(contents, "WHERE", prefixList, null, null);
	}
}
