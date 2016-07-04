/***
 Copyright (c) 2016 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.
 */

package com.commonsware.cwac.netseccfg.test.priv;

import com.commonsware.cwac.netseccfg.test.AbstractHURLTest;
import com.commonsware.cwac.netseccfg.BuildConfig;
import com.commonsware.cwac.netseccfg.TrustManagerBuilder;

public class SimpleHTTPTest extends AbstractHURLTest {
  @Override
  protected String getUrl() {
    return(BuildConfig.TEST_PRIVATE_HTTP_URL);
  }

  @Override
  protected TrustManagerBuilder getBuilder() {
    return(null);
  }
}
