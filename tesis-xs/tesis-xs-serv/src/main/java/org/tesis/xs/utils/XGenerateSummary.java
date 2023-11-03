package org.tesis.xs.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XGenerateSummary {
  private static Logger log = LoggerFactory.getLogger(XGenerateSummary.class);
  
  protected static String generateSummary(FileInputStream fis, Algorithm algorithm, Provider provider) throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
    String resumen = null;
    try {
      MessageDigest md = MessageDigest.getInstance(algorithm.name(), provider.value());
      byte[] dataBytes = new byte[1024];
      int nread = 0;
      while ((nread = fis.read(dataBytes)) != -1)
        md.update(dataBytes, 0, nread); 
      byte[] mdbytes = md.digest();
      StringBuffer sb = new StringBuffer("");
      for (int i = 0; i < mdbytes.length; i++)
        sb.append(Integer.toString((mdbytes[i] & 0xFF) + 256, 16).substring(1)); 
      resumen = sb.toString();
    } finally {
      fis.close();
      fis = null;
    } 
    return resumen;
  }
  
  protected static String generateSummary(String valor, Algorithm algorithm, Provider provider) throws NoSuchAlgorithmException, NoSuchProviderException {
    String resumen = null;
    MessageDigest md = MessageDigest.getInstance(algorithm.name(), provider.value());
    md.update(valor.getBytes());
    byte[] mdbytes = md.digest();
    StringBuffer sb = new StringBuffer("");
    for (int i = 0; i < mdbytes.length; i++)
      sb.append(Integer.toString((mdbytes[i] & 0xFF) + 256, 16).substring(1)); 
    resumen = sb.toString();
    return resumen;
  }
}
