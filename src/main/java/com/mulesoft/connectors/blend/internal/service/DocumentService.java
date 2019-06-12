/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.service;

import com.mulesoft.connectors.blend.api.ResponseStatus;
import org.mule.connectors.commons.template.service.ConnectorService;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

public interface DocumentService extends ConnectorService {
    Result<InputStream, ResponseStatus> getDocumentsResult(String limit, String cursor, String exported, String includeAllExports, String uploadedAfter, String uploadedBefore, String statusIncludes);
    Result<InputStream, ResponseStatus> getDocumentDataResult(String documentID);
    Result<InputStream, ResponseStatus> getPostDocumentResult(InputStream documentsBody);

}
