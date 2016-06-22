package com.commonsware.cwac.netseccfg;

import android.support.test.runner.AndroidJUnit4;
import com.commonsware.cwac.netseccfg.TrustManagerBuilder;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;

@RunWith(AndroidJUnit4.class)
abstract public class AbstractHURLTest {
  abstract protected String getUrl();
  abstract protected TrustManagerBuilder getBuilder()
    throws Exception;

  @Test
  public void testRequest() throws Exception {
    HttpURLConnection c=
      (HttpURLConnection)new URL(getUrl()).openConnection();

    if (c instanceof HttpsURLConnection) {
      TrustManagerBuilder builder=getBuilder();

      if (builder!=null) {
        SSLContext ssl=SSLContext.getInstance("TLS");
        TrustManager[] trustManagers=builder.buildArray();

        ssl.init(null, trustManagers, null);
        ((HttpsURLConnection)c).setSSLSocketFactory(ssl.getSocketFactory());
      }
    }

    try {
      InputStream in=c.getInputStream();

      try {
        if (!isPositiveTest()) {
          throw new AssertionFailedError("Expected SSLHandshakeException, did not get!");
        }

        Assert.assertEquals(getExpectedResponse(), slurp(in));
      }
      finally {
        in.close();
      }
    }
    catch (SSLHandshakeException e) {
      if (isPositiveTest()) {
        throw e;
      }
    }
  }

  protected String getExpectedResponse() {
    return("{\"Hello\": \"world\"}");
  }

  protected boolean isPositiveTest() {
    return(true);
  }

  // based on http://stackoverflow.com/a/309718/115145

  public static String slurp(final InputStream is)
    throws IOException {
    final char[] buffer = new char[128];
    final StringBuilder out = new StringBuilder();
    final Reader in = new InputStreamReader(is, "UTF-8");

    for (;;) {
      int rsz = in.read(buffer, 0, buffer.length);
      if (rsz < 0)
        break;
      out.append(buffer, 0, rsz);
    }

    return out.toString();
  }
}