package com.automation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.config.*;
import com.automation.constants.*;

import cucumber.api.Scenario;

/**
 * The Class TestDataUtils.
 */
public class TestDataUtils extends SeleniumNGSuite {

	/** The base project path. */
	public static String baseProjectPath = System.getProperty(Constants.USER_DIR);

	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(TestDataUtils.class);

	/** The repoistory. */
	public static PropertyUtils repoistory = new PropertyUtils(baseProjectPath.concat(Constants.CONFIG_PROPERTY));

		
	public static ArrayList<String> list = new ArrayList<String>();
	


	/**
	 * Read test data.
	 *
	 * @param scenario
	 *            the scenario
	 * @throws Throwable
	 *             the throwable
	 */
	public void readTestData(Scenario scenario) throws Throwable {
		LOG.info("===== Reading Feature Details====");
		LOG.info("Scenario::{}", scenario);
		LOG.info("Scenario name::{}", scenario.getName());
		String scenarioName = scenario.getName();
		LocalTestDataManager.setScenarioname(scenario.getName());
		LOG.info("****************");
		LOG.info("Scenario Name:" + scenarioName);
		String featureName = scenario.getId().split(";")[0];
		LOG.info("feature file Name:" + featureName);
		readFromFile(featureName, scenarioName);
		LocalTestDataManager.setFeaturename(featureName);
	}

	/**
	 * Read from file.
	 *
	 * @param featureName
	 *            the feature name
	 * @param scenarioName
	 *            the scenario name
	 * @throws Throwable
	 *             the throwable
	 */
	public void readFromFile(String featureName, String scenarioName) throws Throwable {

		HashMap<String, String> hmap = new HashMap<String, String>();

		LocalTestDataManager.setScenarioID(scenarioName.split(Constants.COLON)[0]);
		LOG.info("------------");
		LOG.info("Scenario_id:" + LocalTestDataManager.getScenarioID());
		int rowNum = 0;
		int colNum = 0;
		int numberofcells = 0;
		int numberofrows = 0;
		boolean scenariofound = false;
		DataFormatter formatter = new DataFormatter();
		ArrayList<String> header = new ArrayList<String>();
		String filepath = baseProjectPath.concat(Constants.TEST_DATA_PATH);
		FileInputStream inputstream = new FileInputStream(new File(filepath));
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(inputstream);
		Sheet firstsheet = workbook.getSheet(featureName);
		Iterator<Row> iterator = firstsheet.iterator();
		LOG.info("SheetName is: " + firstsheet.getSheetName());
		while (iterator.hasNext()) {
			Row nextrow = iterator.next();
			Iterator<Cell> celliterator = nextrow.cellIterator();
			while (celliterator.hasNext()) {
				Cell nextcell = celliterator.next();
				String value = formatter.formatCellValue(nextcell);

				if (value.equalsIgnoreCase(LocalTestDataManager.getScenarioID())) {
					rowNum = nextcell.getRowIndex();
					colNum = nextcell.getColumnIndex();
					LOG.info("Row is: " + rowNum + "Column is: " + colNum);
					rowNum++;
					Row headerrow = iterator.next();
					numberofcells = headerrow.getPhysicalNumberOfCells();
					LOG.info("no of cells 1::" + numberofcells);
					scenariofound = true;
					break;
				}
			}
		}

		if (scenariofound == true) {
			Row row = firstsheet.getRow(rowNum);
			for (int p = 0; p < numberofcells; p++) {
				Cell cell = row.getCell(p);
				header.add(formatter.formatCellValue(cell));
			}
			LOG.info("header::" + header);
			rowNum++;
			for (int j = rowNum; j <= firstsheet.getLastRowNum(); j++) {
				if (firstsheet.getRow(j) == null) {
					break;
				} else {
					numberofrows++;
				}
			}
			LOG.info("no of rows::" + numberofrows);
		} else {
			LOG.info("Scenario not found: " + LocalTestDataManager.getScenarioID());
		}
		String key;
		String value;
		int count = 1;
		for (int b = 1; b <= numberofrows; b++)

		{
			for (int c = 0; c < numberofcells; c++) {
				key = header.get(c).concat(Constants.UNDERSCORE + count);
				//LOG.info("key is ::" + key);
				value = formatter.formatCellValue(firstsheet.getRow(rowNum).getCell(c));
//				LOG.info("value is ::" + value);
				hmap.put(key, value);
			}
			count++;
			rowNum++;
		}
		LocalTestDataManager.setTestDataHashMap(hmap);
	}



		/**
	 * Gets the current scenario ID.
	 *
	 * @return the current scenario ID
	 */
	public static String getCurrentScenarioID() {
		return LocalTestDataManager.getScenarioID();
	}

		
}