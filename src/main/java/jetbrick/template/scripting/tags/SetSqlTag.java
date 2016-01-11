package jetbrick.template.scripting.tags;

import java.util.Arrays;
import java.util.List;

public class SetSqlTag extends TrimSqlTag {

	private static List<String> suffixList = Arrays.asList(",");

	public SetSqlTag(String contents) {
		super(contents, "SET", null, null, suffixList);
	}
}
