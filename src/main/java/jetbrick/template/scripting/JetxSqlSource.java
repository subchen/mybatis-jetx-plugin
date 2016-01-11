/**
 *    Copyright 2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package jetbrick.template.scripting;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import jetbrick.template.JetTemplate;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * <p>Jetx sql 源</p>
 * @ClassName: JetxSqlSource
 * @author: haungx
 * @date: 2015年12月21日 下午2:14:29
 */
@SuppressWarnings("unchecked")
public class JetxSqlSource implements SqlSource {
	private final JetTemplate template;
	private final Configuration configuration;

	public JetxSqlSource(JetTemplate template, Configuration configuration) {
		this.template = template;
		this.configuration = configuration;
	}

	@Override
	public BoundSql getBoundSql(Object parameterObject) {
		Map<String, Object> context = (Map<String, Object>) parameterObject;
		if (parameterObject == null) {
			context = Collections.EMPTY_MAP;
		}

		StringWriter out = new StringWriter();
		template.render(context, out);
		String sql = out.toString();

		// Pass retrieved SQL into MyBatis engine, it will substitute prepared-statements parameters
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
		Class<?> parameterType1 = parameterObject == null ? Object.class : parameterObject.getClass();
		SqlSource sqlSource = sqlSourceParser.parse(sql, parameterType1, Collections.EMPTY_MAP);
		return sqlSource.getBoundSql(parameterObject);
	}
}
