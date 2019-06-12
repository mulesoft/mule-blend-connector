/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.connection.provider;

import com.mulesoft.connectors.blend.internal.auth.CreateAuthentication;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.connection.provider.parametergroup.BlendlabsConnectionParameter;
import com.mulesoft.connectors.blend.internal.error.exception.BlendlabsConnectorException;
import com.mulesoft.connectors.blend.internal.util.Urls;
import org.mule.connectors.commons.template.connection.ConnectorConnectionProvider;
import org.mule.runtime.api.connection.*;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.client.auth.HttpAuthentication;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.tcp.TcpClientSocketProperties;
import static com.mulesoft.connectors.blend.internal.config.BlendlabsConfiguration.getAddressValue;
import static com.mulesoft.connectors.blend.internal.error.ErrorTypes.getError;
import static com.mulesoft.connectors.blend.internal.util.RequestService.sendAsyncRequest;
import static org.mule.runtime.extension.api.annotation.param.ParameterGroup.CONNECTION;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.util.concurrent.CompletableFuture;


/**
 * This class (as it's name implies) provides connection instances and the funcionality to disconnect and validate those
 * connection.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connection resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connection or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public class BlendlabsConnectionProvider extends ConnectorConnectionProvider<BlendlabsConnection> implements ConnectionProvider<BlendlabsConnection> {

    private static final Logger logger = LoggerFactory.getLogger(BlendlabsConnectionProvider.class);

    @ParameterGroup(name = CONNECTION)
    @Placement(order = 1)
    private BlendlabsConnectionParameter connectionParams;

    @Parameter
    private String username;
    @Parameter
    private String password;

    private HttpAuthentication authGen;

    @Inject
    private HttpService httpService;

    @Override
    public BlendlabsConnection connect() throws ConnectionException {
        authGen = CreateAuthentication.createAuth(username, password);

        HttpClient httpClient = httpService.getClientFactory().create(new HttpClientConfiguration.Builder()
                .setTlsContextFactory(connectionParams.getTlsContext())
                .setClientSocketProperties(TcpClientSocketProperties.builder()
                        .connectionTimeout(connectionParams.getConnectionTimeoutBlend())
                        .build())
                .setMaxConnections(connectionParams.getMaxConnectionsBlend())
                .setUsePersistentConnections(connectionParams.getUsePersistentConnectionsBlend())
                .setConnectionIdleTimeout(connectionParams.getConnectionIdleTimeoutBlend())
                .setStreaming(connectionParams.isStreamResponseBlend())
                .setResponseBufferSize(connectionParams.getResponseBufferSizeBlend())
                .setName("BlendlabsConfiguration")
                .build());
        httpClient.start();

        return new BlendlabsConnection(httpClient,connectionParams.getConnectionTimeoutBlend(), authGen,username, password, connectionParams.getBlendDeployment(),connectionParams.getBlendSpecialInstanceId(),connectionParams.getBlendAPIVersion(), connectionParams.getCacheControl() );
    }

    @Override
    public void disconnect(BlendlabsConnection connection) {
        try {
            connection.invalidate();
        } catch (Exception e) {
            logger.info("Error while disconnecting :", e);
        }
    }

    @Override
    public ConnectionValidationResult validate(BlendlabsConnection connection) {
        String address = getAddressValue();
        String actualUrl = new StringBuilder(address).toString();
        HttpRequest request = connection.getHttpRequestBuilder().method(HttpConstants.Method.GET).uri(actualUrl).build();
        CompletableFuture<HttpResponse> response = sendAsyncRequest(request, true, connection);
          try {
              if (response.get().getStatusCode() != 200) {
                  String str = response.get().getStatusCode() + "";
                  return ConnectionValidationResult.failure(str, new BlendlabsConnectorException(response.get().getReasonPhrase(),getError(response.get().getStatusCode()))); }
             } catch (Exception e) {
              logger.info("Error while validating the connection : " + e);
          }
          return ConnectionValidationResult.success();
         }

         public String getUsername() {
          return username;
         }

         public String getPassword() {
             return password;
        }

}
