package com.ym.ms.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 
 * @ClassName: MyGenerator 
 * @Description: 代码生成
 * @author anhj 
 * @date 2017年6月8日 下午2:37:18 
 *
 */
public class MysqlGenerator {

	/**
	 * <p>
	 * MySQL 生成演示
	 * </p>
	 */
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/iot_cloud_platform?autoReconnect=true&failOverReadOnly=false&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true";
		String basePackName = "com.ym.ms"; //包名
//		String tableName = "t_token";         //表名,all-tables

		String dataUser = "root";          //用户名
		String dataPwd = "123456";  //密码
		
		String projectPath = "D:/workspace/ms/template-ms";

		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(projectPath);
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		 gc.setAuthor("mybatis-plus");

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapper");
		gc.setServiceName("%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sResource");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			// 自定义数据库表字段类型转换【可选】
			@Override
			public DbColumnType processTypeConvert(String fieldType) {
				System.out.println("转换类型：" + fieldType);
				if (fieldType.toLowerCase().contains("tinyint")) {
					return DbColumnType.INTEGER;
				}
				if (fieldType.toLowerCase().contains("varchar")) {
					return DbColumnType.STRING;
				}
				if (fieldType.toLowerCase().contains("bigint")) {
					return DbColumnType.LONG;
				}
				return super.processTypeConvert(fieldType);
			}
		});
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername(dataUser);
		dsc.setPassword(dataPwd);
		dsc.setUrl(url);
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		 strategy.setTablePrefix(new String[]{"t_"});// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		//strategy.setInclude(new String[] { tableName }); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		// strategy.setSuperEntityClass("com.fcs.demo.TestEntity");
		// 自定义实体，公共字段
		// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
		// 自定义 mapper 父类
		// strategy.setSuperMapperClass("com.fcs.demo.TestMapper");
		// 自定义 service 父类
		// strategy.setSuperServiceClass("com.fcs.demo.TestService");
		// 自定义 service 实现类父类
		// strategy.setSuperServiceImplClass("com.fcs.demo.TestServiceImpl");
		// 自定义 controller 父类
		// strategy.setSuperControllerClass("com.fcs.demo.TestController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(basePackName);
		// pc.setModuleName("user");
		// pc.setController("web");
		mpg.setPackageInfo(pc);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
//		 InjectionConfig cfg = new InjectionConfig() {
//		 @Override
//		 public void initMap() {
//		 Map<String, Object> map = new HashMap<String, Object>();
//		 map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//		 this.setMap(map);
//		 }
//		 };
//		 // 自定义 xxList.jsp 生成
//		 List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
//		 focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
//		 @Override
//		 public String outputFile(TableInfo tableInfo) {
//		 // 自定义输入文件名称
//		 return projectPath+"/nrtms-dao/src/main/resources/mapper"+ tableInfo.getEntityName() + "Mapper.xml";
//		 }
//		 });
//		 cfg.setFileOutConfigList(focList);
//		 mpg.setCfg(cfg);

		// 自定义模板，配置模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
        // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
		 TemplateConfig tc = new TemplateConfig();
//		 tc.setController("/templates/controller.java.vm");
		// tc.setEntity("...");
		// tc.setMapper("...");
//		 tc.setXml(null);
		// tc.setService("...");
		// tc.setServiceImpl("...");
		 mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();

		// 打印注入设置
		// System.err.println(mpg.getCfg().getMap().get("abc"));
	}

}