/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */
package com.mulesoft.connectors.blend.internal.connection.provider;

import com.mulesoft.connectors.blend.internal.auth.CreateAuthentication;
import com.mulesoft.connectors.blend.internal.connection.BlendlabsConnection;
import com.mulesoft.connectors.blend.internal.connection.provider.parametergroup.BlendlabsConnectionParameter;
import org.mule.connectors.commons.template.connection.ConnectorConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.client.auth.HttpAuthentication;
import org.mule.runtime.http.api.tcp.TcpClientSocketProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.extension.api.annotation.param.ParameterGroup.CONNECTION;

/**
 * This class (as it's name implies) provides connection instances and the funcionality to disconnect and validate those
 * connections.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connections resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connections or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public class BlendlabsConnectionProvider extends ConnectorConnectionProvider<BlendlabsConnection> implements ConnectionProvider<BlendlabsConnection> {

    private final Logger logger = LoggerFactory.getLogger(BlendlabsConnectionProvider.class);

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
                .setTlsContextFactory(connectionParams.getTlsContextFactory())
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
            logger.info("Error while disconnecting : ", e);
        }
    }

    @Override
    public ConnectionValidationResult validate(BlendlabsConnection connection) {
        return ConnectionValidationResult.success();
    }

}
