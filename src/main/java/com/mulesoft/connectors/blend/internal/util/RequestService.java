/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.util;

import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.CompletableFuture;

public class RequestService {
	private static HttpResponse httpResponse;
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(RequestService.class);

	public static HttpResponse requestCall (HttpRequest request, boolean value, BlendlabsConnection connection){
		try {
			httpResponse = connection.getHttpClient().send(request,connection.getApiTimeout(),false, connection.getAuthentication());
			return httpResponse;
		} catch (IOException e) {
			logger.info("Error : ", e);
		} catch (TimeoutException e) {
			logger.info("Error : ", e);
		} catch (Exception e) {
			logger.info("Error : ", e);
		}
		return null;
	}

	public static CompletableFuture<HttpResponse> sendAsyncRequest(HttpRequest request, boolean value, BlendlabsConnection connection) {
		return connection.getHttpClient().sendAsync(request, connection.getApiTimeout(), false, connection.getAuthentication());
	}

}
