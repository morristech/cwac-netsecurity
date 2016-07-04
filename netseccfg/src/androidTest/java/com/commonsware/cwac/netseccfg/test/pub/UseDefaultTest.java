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

package com.commonsware.cwac.netseccfg.test.pub;

import android.os.Build;
import com.commonsware.cwac.netseccfg.TrustManagerBuilder;

public class UseDefaultTest extends SimpleHTTPSTest {
  @Override
  protected TrustManagerBuilder getBuilder() throws Exception {
    return(new TrustManagerBuilder().useDefault());
  }

  // on N+, fail because of manifest config
  @Override
  protected boolean isPositiveTest() {
    return(Build.VERSION.SDK_INT<Build.VERSION_CODES.N);
  }
}
