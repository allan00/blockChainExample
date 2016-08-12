package com.webank.blockchain;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.script.ScriptException;

import com.webank.blockchain.domain.JSCommand;
import com.webank.blockchain.util.JavaScriptEngine;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JavaScriptEngineTest {

	public static void main(String[] args) {
		try {
			String jsons = (String) JavaScriptEngine.engine3("123");// 参数为jString
			ArrayList<JSCommand> list = new ArrayList<JSCommand>();
			JSONArray jsonArray = JSONArray.fromObject(jsons);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				JSCommand jsCommand = new JSCommand();
				jsCommand.setCommand((Integer) jsonObject.get("command"));
				jsCommand.setAmount((Integer) jsonObject.get("amount"));
				jsCommand.setRemark((String) jsonObject.get("remark"));
				list.add(jsCommand);
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
