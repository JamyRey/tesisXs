package org.tesis.xs.utils;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ProviderUtils {
  public static void loaderProvider() {
    Security.addProvider((Provider)new BouncyCastleProvider());
  }
}