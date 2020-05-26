package com.APIautomation.testRail;

import java.util.HashMap;
import java.util.Map;

import com.UIautomation.testRail.ExcelDataConfig;

public class Logic {
	public static void main(String[] args) {
		ExcelDataConfig config = new ExcelDataConfig("./testdata/testdata.xlsx");
		int rows = config.getRowCount(0);

		System.out.println(rows);

		for (int i = 1; i < rows; i++) {
			String value = config.getData(0, i, 5);
			if (value.equals("y")) {
				String id = config.getData(0, i, 0);
				System.out.println("id: " + id);
				String value_to_update = config.getData(0, i, 4);
				TestCaseManagment.updateTestCase(id, value_to_update);
			} else {

			}
		}
	}

	public static void addTCs() {
		Map<String, Object> data = new HashMap<>();

		ExcelDataConfig config = new ExcelDataConfig("./testdata/testdata.xlsx");
		int rows = config.getRowCount(0);

		System.out.println(rows);
		for (int i = 1; i < rows; i++) {
			data.put("title", config.getData(0, i, 4)); // 0-> Sheet index, i- row number, 6-> col num
			data.put("template_id", 1);
			data.put("type_id", 3);
			data.put("priority_id", 2);
			data.put("estimate", "30s");
			data.put("custom_preconds", "These are the preconditions for a test case");
			data.put("custom_steps", config.getData(0, i, 4));
			data.put("custom_expected", "Expected: Home page should display");

			TestCaseManagment.addTestCase(data);
		}
	}
}
