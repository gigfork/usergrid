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
package org.usergrid.persistence.query.ir.result;


import java.nio.ByteBuffer;
import java.util.UUID;

import org.usergrid.persistence.Schema;

import me.prettyprint.hector.api.beans.DynamicComposite;


/**
 * Parser for reading uuid connections from ENTITY_COMPOSITE_DICTIONARIES and DICTIONARY_CONNECTED_ENTITIES type
 *
 * @author tnine
 */
public class ConnectionIndexSliceParser implements SliceParser {

    private final String connectedEntityType;


    /** @param connectedEntityType Could be null if we want to return all types */
    public ConnectionIndexSliceParser( String connectedEntityType ) {
        this.connectedEntityType = connectedEntityType;
    }


    /* (non-Javadoc)
     * @see org.usergrid.persistence.query.ir.result.SliceParser#parse(java.nio.ByteBuffer)
     */
    @Override
    public ScanColumn parse( ByteBuffer buff ) {
        DynamicComposite composite = DynamicComposite.fromByteBuffer( buff.duplicate() );

        String connectedType = ( String ) composite.get( 1 );


        //connection type has been defined and it doesn't match, skip it
        if ( connectedEntityType != null && !connectedEntityType.equals( connectedType ) ) {
            return null;
        }

        //we're checking a loopback and it wasn't specified, skip it
        if ( ( connectedEntityType != null && !connectedEntityType.equalsIgnoreCase( connectedType ) ) || Schema
                .TYPE_CONNECTION.equalsIgnoreCase( connectedType ) ) {
            return null;
        }

        return new ConnectionColumn( ( UUID ) composite.get( 0 ), connectedType, buff );
        //    return composite;
        //    return null;
    }


    public static class ConnectionColumn extends AbstractScanColumn {

        private final String connectedType;


        public ConnectionColumn( UUID uuid, String connectedType, ByteBuffer column ) {
            super( uuid, column );
            this.connectedType = connectedType;
        }


        /** Get the target type from teh column */
        public String getTargetType() {
            return connectedType;
        }
    }
}
