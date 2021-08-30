package com.w24.API_Proj.TestUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.w24.API_Proj.setUp.APISetUp;

import io.restassured.specification.RequestSpecification;

public class TestUtil extends APISetUp{

	public static RequestSpecification setFormParam(String arguments,RequestSpecification reqSpecs) {
		String[] listOfArguments=arguments.split(",");
		for(String singleArgument:listOfArguments)
		{
			String[] keyValue=singleArgument.split(":");
			{
				String key=keyValue[0];
				String value=keyValue[1];
				reqSpecs.formParam(key, value);
			}
		}
		return reqSpecs;
	} 
	
	public static void archiveTestReport() {
		try {
		String reportName="TestReport.html";
		String lastTestReportFilePath="./src/test/resources/testReports/";
		String archiveReportPath="./src/test/resources/archivedTestReport";
		
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("dd_MM_yyyy:hh_mm_ss");
	
		String formattedDate=format.format(date);
		String archivedTestreport=formattedDate+" : "+reportName;
		
		File oldReport=new File(lastTestReportFilePath+reportName);
		File newFile=new File(archiveReportPath+archivedTestreport);
		if(oldReport.exists()) {
			oldReport.renameTo(newFile);
			oldReport.delete();
		}
		}catch(Exception e) {}
		
	}
}
