/*
 * Copyright 2005 Anders Nyman.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.j2ep.responsehandlers;

import org.apache.commons.httpclient.methods.DeleteMethod;

/**
 * Handler for the DELETE method.
 *
 * @author Anders Nyman
 */
public class DeleteResponseHandler extends BasicResponseHandler {

    /**
     * Default constructor, will only call the super-constructor
     * for BasicResponseHandler. 
     * 
     * @param method The method used for this response
     */
    public DeleteResponseHandler(DeleteMethod method) {
        super(method);
    }

}
