package org.tesis.xs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tesis.xs.exception.BasicException;

public class XSHA1Util extends XGenerateSummary {
  private static Logger log = LoggerFactory.getLogger(XSHA1Util.class);
  
  public static void main(String[] args) throws BasicException {
    System.out.println(toSHA1(new File("/Users/jperaza/Desktop/sokai/logo_sokai.png")));
  }
  
  public static String toSHA1(File file) throws BasicException {
    ProviderUtils.loaderProvider();
    String summary = null;
    FileInputStream fs = null;
    try {
      fs = new FileInputStream(file);
      summary = generateSummary(fs, Algorithm.SHA1, Provider.BouncyCastle);
    } catch (NoSuchAlgorithmException|java.security.NoSuchProviderException|IOException e) {
      log.error("Error: ", e);
      throw new BasicException(e);
    } finally {
      try {
        if (fs != null) {
          fs.close();
          fs = null;
        } 
      } catch (IOException e) {
        log.debug("Error: ", e);
      } 
      file = null;
    } 
    return summary;
  }
  
  public static String toSHA1(String valor) throws BasicException {
    ProviderUtils.loaderProvider();
    String summary = null;
    try {
      summary = generateSummary(valor, Algorithm.SHA1, Provider.BouncyCastle);
    } catch (NoSuchAlgorithmException|java.security.NoSuchProviderException e) {
      log.error("Error: ", e);
      throw new BasicException(e);
    } 
    return summary;
  }
}
