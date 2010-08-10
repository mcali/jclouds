/**
 *
 * Copyright (C) 2009 Cloud Conscious, LLC. <info@cloudconscious.com>
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
package org.jclouds.vcloud.terremark;

import java.net.URI;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import org.jclouds.concurrent.Timeout;
import org.jclouds.rest.ResourceNotFoundException;
import org.jclouds.vcloud.terremark.domain.InternetService;
import org.jclouds.vcloud.terremark.domain.KeyPair;
import org.jclouds.vcloud.terremark.domain.Protocol;
import org.jclouds.vcloud.terremark.options.AddInternetServiceOptions;

/**
 * Provides access to VCloud resources via their REST API.
 * <p/>
 * 
 * @see <a href="https://community.vcloudexpress.terremark.com/en-us/discussion_forums/f/60.aspx"
 *      />
 * @author Adrian Cole
 */
@Timeout(duration = 300, timeUnit = TimeUnit.SECONDS)
public interface TerremarkVCloudExpressClient extends TerremarkVCloudClient {

   /**
    * @param orgName
    *           null if use the default org
    * @throws ResourceNotFoundException
    *            if the orgName is not a valid organization
    */
   Set<KeyPair> listKeyPairsInOrg(@Nullable String orgName);

   /**
    * @param orgName
    *           null if use the default org
    * @throws ResourceNotFoundException
    *            if the orgName is not a valid organization
    * @throws IllegalStateException
    *            if a key of the same name already exists
    */
   KeyPair generateKeyPairInOrg(@Nullable String orgName, String name, boolean makeDefault);

   /**
    * @param orgName
    *           null if use the default org
    * @throws ResourceNotFoundException
    *            if the orgName is not a valid organization
    */
   KeyPair getKeyPairInOrg(@Nullable String orgName, String keyPairName);

   KeyPair getKeyPair(URI keyPair);

   // TODO
   // KeyPair configureKeyPair(int keyPairId, KeyPairConfiguration
   // keyPairConfiguration);

   void deleteKeyPair(URI keyPair);

   InternetService addInternetServiceToVDC(String vDCId, String serviceName, Protocol protocol, int port,
         AddInternetServiceOptions... options);
}
