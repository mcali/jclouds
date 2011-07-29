/**
 *
 * Copyright (C) 2011 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */
package org.jclouds.trmk.vcloud_0_8.compute.config;

import java.util.EnumSet;

import org.jclouds.trmk.vcloud_0_8.domain.Status;
import org.testng.annotations.Test;

/**
 * @author Adrian Cole
 */
@Test(groups = "unit")
public class TerremarkVCloudComputeServiceContextModuleTest {

   public void testAllStatusCovered() {

      for (Status state : EnumSet.allOf(Status.class)) {
         assert TerremarkVCloudComputeServiceContextModule.VAPPSTATUS_TO_NODESTATE.containsKey(state) : state;
      }

   }
}