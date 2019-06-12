/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.service;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

public interface BorrowerService extends ConnectorService {
    Result<InputStream, ResponseStatus> getPostBorrowerResult(Map<String, Object> postBorrowerBody);
    Result<InputStream, ResponseStatus> getPatchBorrowerResult(String borrowerId, Map<String, Object> patchBorrowerBody);
    Result<InputStream, ResponseStatus> deleteBorrowerService(String borrowerId);

}
