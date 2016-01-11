package jetbrick.template.scripting;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jetbrick.bean.Filters.MethodFilter;
import jetbrick.bean.KlassInfo;
import jetbrick.bean.MethodInfo;
import jetbrick.bean.ParameterInfo;
import jetbrick.config.Config;
import jetbrick.config.ConfigLoader;
import jetbrick.template.JetConfig;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;
import jetbrick.template.resolver.GlobalResolver;
import jetbrick.template.runtime.JetTagContext;
import jetbrick.util.Validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JetEngineHolder {
	private final static Logger log = LoggerFactory.getLogger(JetEngineHolder.class);
	static boolean isRegister = false;
	// -----------------------------------------------
	// 模板引擎
	// -----------------------------------------------
	static JetEngine jetEngine;

	public static JetEngine getJetEngine() {
		Validate.notNull(jetEngine, "JetEngine can not be null");
		if (!isRegister) {
			GlobalResolver resolver = jetEngine.getGlobalResolver();
			registerMacros(resolver);
			registerTags(resolver);
			registerFunctions(resolver);
			registerMethods(resolver);
			isRegister = true;
		}
		return jetEngine;
	}

	public static void setJetEngine(JetEngine jetEngine) {
		JetEngineHolder.jetEngine = jetEngine;
	}

	// ---------------------------------------------------------
	// 注册方法 Methods
	// ---------------------------------------------------------
	/**
	 * @Title: 注册方法 Method
	 * @param resolver
	 */
	private static void registerMethods(GlobalResolver resolver) {
		List<MethodInfo> list = PublicStaticMethodFilter.getMethods(JetxMethods.class);
		for (int i = 0, length = list.size(); i < length; i++) {
			resolver.registerMethod(list.get(i));
		}
	}

	// ---------------------------------------------------------
	// 注册函数 Function
	// ---------------------------------------------------------
	/**
	 * @Title: 注册函数 Function
	 * @param resolver
	 */
	private static void registerFunctions(GlobalResolver resolver) {
		List<MethodInfo> list = PublicStaticMethodFilter.getMethods(JetxFunctions.class);
		for (int i = 0, length = list.size(); i < length; i++) {
			resolver.registerFunction(list.get(i));
		}
	}

	// ---------------------------------------------------------
	// 注册标签 Tag
	// ---------------------------------------------------------
	/**
	 * @Title: 注册标签 Tag
	 * @param resolver
	 */
	private static void registerTags(GlobalResolver resolver) {
		List<MethodInfo> list = TagMethodFilter.getMethods(JetxTags.class);
		for (int i = 0, length = list.size(); i < length; i++) {
			resolver.registerTag(list.get(i));
		}
	}

	// ---------------------------------------------------------
	// 注册宏 Macro
	// ---------------------------------------------------------
	/**
	 * @Title: 注册宏 Macro
	 * @param resolver
	 */
	private static void registerMacros(GlobalResolver resolver) {
		List<JetTemplate> list = new ArrayList<JetTemplate>();
		for (int i = 0, length = list.size(); i < length; i++) {
			resolver.registerMacros(list.get(i));
		}
	}

	/**
	 * @Title: 加载 配置
	 * @param config
	 * @param configLocation
	 * @return
	 */
	public static Config loadConfig(Properties config, String configLocation) {
		ConfigLoader loader = new ConfigLoader();

		if (config != null) {
			loader.load(config);
		}

		if (configLocation != null) {
			try {
				log.info("Loading config file: {}", configLocation);
				loader.load(configLocation);
			} catch (IllegalStateException e) {
				// 默认配置文件允许不存在
				if (!JetConfig.DEFAULT_CONFIG_FILE.equals(configLocation)) {//
					throw e;
				}
				log.warn("no default config file found: {}", JetConfig.DEFAULT_CONFIG_FILE);
			}
		}
		return loader.asConfig();
	}

	/**
	 * @ClassName: Public and Static Method Filter
	 * @author: Administrator
	 * @date: 2015年12月23日 上午9:44:03
	 */
	public static class PublicStaticMethodFilter implements MethodFilter {
		private static PublicStaticMethodFilter instance = new PublicStaticMethodFilter();

		public static List<MethodInfo> getMethods(Class<?> clazz) {
			return KlassInfo.create(clazz).getMethods(instance);
		}

		@Override
		public boolean accept(MethodInfo method) {
			return method.isStatic() && method.isPublic();
		}
	}

	/**
	 * @ClassName: Tag Method Filter
	 * @author: Administrator
	 * @date: 2015年12月23日 上午9:55:34
	 */
	public static class TagMethodFilter extends PublicStaticMethodFilter {
		private static TagMethodFilter instance = new TagMethodFilter();

		public static List<MethodInfo> getMethods(Class<?> clazz) {
			return KlassInfo.create(clazz).getMethods(instance);
		}

		@Override
		public boolean accept(MethodInfo method) {
			List<ParameterInfo> parameters = method.getParameters();
			return parameters.size() > 0 // 参数至少1个
					&& JetTagContext.class.isAssignableFrom(parameters.get(0).getType())// 参数第一个必须是 JetTagContext
					&& method.getReturnType() == Void.TYPE// 方法返回值必须是 void
					&& super.accept(method);// 方法签名必须是 public static
		}
	}

}
