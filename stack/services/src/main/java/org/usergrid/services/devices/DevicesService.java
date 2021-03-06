/*******************************************************************************
 * Copyright 2012 Apigee Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.usergrid.services.devices;


import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usergrid.services.AbstractCollectionService;
import org.usergrid.services.ServiceContext;
import org.usergrid.services.ServiceResults;


public class DevicesService extends AbstractCollectionService {

    private static final Logger logger = LoggerFactory.getLogger( DevicesService.class );


    public DevicesService() {
        super();
        logger.info( "/devices" );
    }


    @Override
    public ServiceResults putItemById( ServiceContext context, UUID id ) throws Exception {
        logger.info( "Registering device {}", id );
        return super.putItemById( context, id );
    }


    @Override
    public ServiceResults postItemById( ServiceContext context, UUID id ) throws Exception {
        logger.info( "Attempting to connect an entity to device {}", id );
        return super.postItemById( context, id );
    }
}
