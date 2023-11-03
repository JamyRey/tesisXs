package org.tesis.xs.utils;

public enum Provider {
  BouncyCastle("BC");
  
  private String valSel;
  
  Provider(String val) {
    this.valSel = val;
  }
  
  public String value() {
    return this.valSel;
  }
}
