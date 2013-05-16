/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.android.impl.pipeline;

import com.google.gson.GsonBuilder;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import org.jboss.aerogear.android.Pipeline;
import org.jboss.aerogear.android.authentication.AuthenticationModule;
import org.jboss.aerogear.android.pipeline.Pipe;
import org.jboss.aerogear.android.pipeline.PipeHandler;
import org.jboss.aerogear.android.pipeline.PipeType;
import org.jboss.aerogear.android.pipeline.paging.PageConfig;

/**
 * Specifies configurations for {@link Pipe} to be build by {@link Pipeline}
 */
public final class PipeConfig {

    private URL baseURL;
    private String name;
    private String endpoint;
    private PipeType type = PipeTypes.REST;
    private PageConfig pageConfig;
    private GsonBuilder gsonBuilder;
    private AuthenticationModule authModule;
    private PipeHandler handler;
    private Integer timeout = Integer.MAX_VALUE;
    /**
     * Where the data elements the pipe wants to extract are found in the
     * response from the server. Defaults to the root of the data structure
     * represented by an empty string
     */
    private String dataRoot = "";
    private Charset encoding = Charset.forName("UTF-8");

    public PipeConfig(URL baseURL, Class klass) {
        this.baseURL = baseURL;
        this.name = klass.getSimpleName().toLowerCase();
        this.endpoint = name;
        this.type = PipeTypes.REST;
    }

    /**
     * @return The Name that Pipeline will use to reference Pipes built by this
     * configuration.
     */
    public String getName() {
        return name;
    }

    /**
     * Modify the name field. This is the value Pipeline uses to reference a
     * Pipe built by this configuration.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the URL which Endpoints will be appended to.
     */
    public URL getBaseURL() {
        return baseURL;
    }

    /**
     * Change the URL which Endpoints are appended to.
     *
     * @param baseURL
     */
    public void setBaseURL(URL baseURL) {
        this.baseURL = baseURL;
    }

    /**
     * @return the string appended to BaseURL. It is the resource a Pipe
     * connects to.
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Change the string appended to BaseURL. It is the resource a Pipe connects
     * to.
     *
     * @param endpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return type of Pipe to configure.
     * @see PipeTypes
     */
    public PipeType getType() {
        return type;
    }

    /**
     * @param type a PipeType to use when building a Pipe with this config.
     * @see PipeTypes
     */
    public void setType(PipeType type) {
        this.type = type;
    }

    /**
     * @deprecated Pipes are moving to a more generic RequestBuilder interface.
     * {@link GsonRequestBuilder}
     * 
     * @return the current GSONBuilder which will be used to generate GSON
     * objects to be used by Pipes with this config.
     */
    public GsonBuilder getGsonBuilder() {
        return gsonBuilder;
    }

    /**
     * 
     * @deprecated Pipes are moving to a more generic RequestBuilder interface.
     * {@link GsonRequestBuilder}
     * 
     * @param gsonBuilder GSONBuilder which will be used to generate GSON
     * objects to be used by Pipes with this config.
     */
    @Deprecated
    public void setGsonBuilder(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    /**
     * @return a {@link AuthenticationModule} which will be used for
     * Authentication.
     */
    public AuthenticationModule getAuthModule() {
        return authModule;
    }

    /**
     * This value must be set to use Authentication in Pipes build from this
     * config.
     *
     * @param authModule a {@link AuthenticationModule} which works with this
     * PipeConfig
     */
    public void setAuthModule(AuthenticationModule authModule) {
        this.authModule = authModule;
    }

    /**
     * The Encoding is the String encoding to expect from the server.
     *
     * @return the current encoding, will not be null.
     */
    public Charset getEncoding() {
        return encoding;
    }

    /**
     * @param encoding a not null encoding
     * @throws IllegalArgumentException if encoding is null
     */
    public void setEncoding(Charset encoding) {
        if (encoding == null) {
            throw new IllegalArgumentException("Encoding may not be null");
        }
        this.encoding = encoding;
    }

    /**
     * @param charsetName a string for the encoding to be used
     * @throws UnsupportedCharsetException if charSet is not supported
     */
    public void setEncoding(String charsetName) {
        this.encoding = Charset.forName(charsetName);
    }

    /**
     * PageConfig is the configuration information for Paging.
     *
     * @see PageConfig
     */
    public PageConfig getPageConfig() {
        return pageConfig;
    }

    /**
     * PageConfig is the configuration information for Paging.
     *
     * @param pageConfig
     * @see PageConfig
     */
    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    /**
     * DataRoot refers to the dotted location of the result we are interested in
     * from the JSON response from the server.
     *
     * For example:
     * <pre>
     * {
     * "speakers": {
     *      "data": [
     *          {"speakerName":"John Doe", "speakerid":42},
     *          {"speakerName":"Jesse James", "speakerid":5309},
     *      ]
     *  }
     * }
     * </pre>
     *
     * A DataRoot of "speakers.data" would make the pipe pass a List using the
     * array of speakers to the onSuccess method of callback.
     *
     * @return the current dataRoot
     */
    public String getDataRoot() {
        return dataRoot;
    }

    /**
     * DataRoot refers to the dotted location of the result we are interested in
     * from the JSON response from the server.
     *
     * For example:
     * <pre>
     * {
     * "speakers": {
     *      "data": [
     *          {"speakerName":"John Doe", "speakerid":42},
     *          {"speakerName":"Jesse James", "speakerid":5309},
     *      ]
     *  }
     * }
     * </pre>
     *
     * A DataRoot of "speakers.data" would make the pipe pass a List using the
     * array of speakers to the onSuccess method of callback.
     *
     * @param dataRoot
     */
    public void setDataRoot(String dataRoot) {
        this.dataRoot = dataRoot;
    }

    /**
     *
     * @return the current {@link PipeHandler} for Pipes build using this
     * configuration
     */
    public PipeHandler getHandler() {
        return handler;
    }

    /**
     * @param handler a new {@link PipeHandler} for Pipes build using this
     * configuration
     */
    public void setHandler(PipeHandler handler) {
        this.handler = handler;
    }

    /**
     * Timeout is the length of time in milliseconds that a Pipe will wait for a
     * response from a call to read, readWithfilter, save or remove
     *
     * @return the current timeout.
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Timeout is the length of time in milliseconds that a Pipe will wait for a
     * response from a call to read, readWithfilter, save or remove
     *
     * @param timeout a new
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
