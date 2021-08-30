package com.w24.API_Proj.TestUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String reportName=configProperty.getTestReportName();
		String lastTestReportFilePath=System.getProperty("user.dir")+"/src/test/resources/testReports/";
		String archiveReportPath=System.getProperty("user.dir")+"/src/test/resources/archivedTestReport/";
		
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	
		String formattedDate=format.format(date);
		String archivedTestreport=formattedDate+" _ "+reportName;
		
		File oldReport=new File(lastTestReportFilePath+reportName);
		File newFile=new File(archiveReportPath + archivedTestreport);
		
		if(oldReport.exists()) {
			oldReport.renameTo(newFile);
			oldReport.delete();
		}
		}catch(Exception e) {}
		
	}
}
