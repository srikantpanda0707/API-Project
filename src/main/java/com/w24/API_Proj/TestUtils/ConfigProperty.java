package com.w24.API_Proj.TestUtils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
	//imp point->these double back slashes will not work in linux and mac in that case use single forward slash-> /
	"file:src\\test\\resources\\propertyFiles\\config.Properties"
})
public interface ConfigProperty extends Config{
	
	@Key("baseURI")
	String getBaseURI();
	
	@Key("basePath")
	String getBasePath();
	
	@Key("secretKey")
	String getSecretKey();
	
	@Key("testReportPath")
	String getTestFilepath();
	
	@Key("testReportName")
	String getTestReportName();
	
	@Key("testDataSheetName")
	String getTestDataSheetName();

}
