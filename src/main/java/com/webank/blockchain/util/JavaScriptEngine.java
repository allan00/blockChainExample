package com.webank.blockchain.util;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.ibatis.javassist.compiler.JvstTypeChecker;

public class JavaScriptEngine {
	//使用脚本引擎运行脚本程序  
	public static void engine1(String jString) throws ScriptException{  
	    ScriptEngineManager manager = new ScriptEngineManager();  
	    ScriptEngine engine = manager.getEngineByName("js");//ScriptEngine用来处理脚本解释和求值  
	    engine.eval(jString);//解析 JavaScript 脚本,对脚本表达式进行求值  
	}  
	
	//使用脚本引擎执行外部资源中的脚本  
	public static void engine2() throws FileNotFoundException, ScriptException{  
	    ScriptEngineManager manager = new ScriptEngineManager();  
	    ScriptEngine engine = manager.getEngineByName("js");  
	    engine.put("age", 26);  
	    engine.eval(new FileReader("E:/ScriptEngine/test01.js"));//对文件或其它外部资源中得脚本表达式进行求值  
	}
	
	//使用脚本引擎检索结果  
	public static Object engine3(String jString) throws FileNotFoundException, ScriptException{  
	    ScriptEngineManager manager = new ScriptEngineManager();  
	    ScriptEngine engine = manager.getEngineByName("js");  
	    
	   /* String jString="function customOpt(){"
			    +"return '[{"
			        +"command:1,"
			        +"amount:10,"
			        +"remark:'asas'"
			    +"},{"
			        +"command:1,"
			        +"amount:10,"
			        +"remark:'asas'"
			    +"},{"
			        +"command:2,"
			        +"amount:10,"
			        +"remark:'asas'"
			    +"},{"
			        +"command:2,"
			        +"amount:100,"
			        +"remark:'asas'"
			    +"}]';"
			+"}";*/

	    /*
	    String jString="function customOpt(){"
			    +"return '[{\"command\":1,\"amount\":10,\"remark\":\"asas\"},{\"command\":1,\"amount\":10,\"remark\":\"asas\"}]';}";
	    */
	    engine.eval(jString);
		Invocable invocableEngine = (Invocable) engine;
		String callbackvalue = null;
		try {
			callbackvalue = invocableEngine.invokeFunction("customOpt").toString();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} 
		System.out.println(callbackvalue); 
	    return callbackvalue;
	} 

}
