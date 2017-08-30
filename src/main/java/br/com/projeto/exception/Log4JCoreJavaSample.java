package br.com.projeto.exception;

import org.apache.log4j.Logger;

public class Log4JCoreJavaSample {

	final static Logger logger = Logger.getLogger(Log4JCoreJavaSample.class);
	 public void callMeInAppInfo(String parameter) {
	  if (logger.isInfoEnabled()) {
	   logger.info("This is info : " + parameter);
	  }
	 }
	 public void callMeInAppWarn(String parameter) {
	   logger.warn("This is warn : " + parameter);
	 }
	 
	 public void callMeInAppDebug(String parameter) {
	   logger.debug("This is Debug : " + parameter);
	 }
	 
	 public void callMeInAppError(String parameter) {
	   logger.error("This is error : " + parameter);
	 }
	 
	 public void callMeInAppFatal(String parameter) {
	   logger.fatal("This is fatal : " + parameter);
	  
	 }
	 
	}
