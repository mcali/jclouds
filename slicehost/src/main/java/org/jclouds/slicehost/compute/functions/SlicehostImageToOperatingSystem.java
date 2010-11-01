/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
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

package org.jclouds.slicehost.compute.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.inject.Singleton;

import org.jclouds.compute.domain.OperatingSystem;
import org.jclouds.compute.domain.OsFamily;
import org.jclouds.compute.reference.ComputeServiceConstants;
import org.jclouds.logging.Logger;

import com.google.common.base.Function;

/**
 * 
 * @author Adrian Cole
 */
@Singleton
public class SlicehostImageToOperatingSystem implements
      Function<org.jclouds.slicehost.domain.Image, OperatingSystem> {
   public static final Pattern SLICEHOST_PATTERN = Pattern.compile("(([^ ]*) ([0-9.]+) ?.*)");

   @Resource
   @Named(ComputeServiceConstants.COMPUTE_LOGGER)
   protected Logger logger = Logger.NULL;

   public OperatingSystem apply(final org.jclouds.slicehost.domain.Image from) {
      OsFamily osFamily = null;
      String osName = null;
      String osArch = null;
      String osVersion = null;
      String osDescription = from.getName();
      boolean is64Bit = !from.getName().endsWith("32-bit");
      if (from.getName().indexOf("Red Hat EL") != -1) {
         osFamily = OsFamily.RHEL;
      } else if (from.getName().indexOf("Oracle EL") != -1) {
         osFamily = OsFamily.OEL;
      }
      Matcher matcher = SLICEHOST_PATTERN.matcher(from.getName());
      if (matcher.find()) {
         try {
            osFamily = OsFamily.fromValue(matcher.group(2).toLowerCase());
         } catch (IllegalArgumentException e) {
            logger.debug("<< didn't match os(%s)", matcher.group(2));
         }
         osVersion = matcher.group(3);
      }
      OperatingSystem os = new OperatingSystem(osFamily, osName, osVersion, osArch, osDescription, is64Bit);
      return os;
   }
}