//package com.aia.util;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import com.google.common.collect.Lists;
//
///**
// * 代码生成工具
// * @author yangxy
// * @version 创建时间：2023年11月3日 上午9:41:26
// */
//public class CodeGenerator {
//	public static void main(String[] args) {
//		String url = "jdbc:mysql://192.168.124.16:3306/aia_new";
//		String username = "root";
//		String password = "root123asd";
//		String moduleName = "msg";
//		String mapperLocation = "F:\\generator\\" + moduleName;
////		String tables = "aia_admin";
//		List<String> list = Lists.newArrayList("aia_note","aia_note_type","aia_message");
//		for (String tables : list) {
//			FastAutoGenerator.create(url, username, password).globalConfig(builder -> {
//				builder.author("yangxy") // 设置作者
//						.outputDir("F:\\generator\\").commentDate(LocalDate.now().toString()); // 指定输出目录
//			}).packageConfig(builder -> {
//				builder.parent("com.aia") // 设置父包名
//						.moduleName(moduleName) // 设置父包模块名
//						.pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
//			}).strategyConfig(builder -> {
//				builder.entityBuilder().enableLombok();
//				builder.addInclude(tables) // 设置需要生成的表名
//						.addTablePrefix("aia_"); // 设置过滤表前缀
//			}).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//					.execute();
//		}
//	}
//}
