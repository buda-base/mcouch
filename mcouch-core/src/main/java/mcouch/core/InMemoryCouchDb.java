package mcouch.core;

import mcouch.core.couch.database.Databases;
import mcouch.core.http.ClientConnectionManagerStub;
import mcouch.core.http.HttpParamsStub;
import mcouch.core.http.NotImplementedException;
import mcouch.core.http.request.CouchHttpRequestFactory;
import mcouch.core.http.request.CouchRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;

import java.io.IOException;

public class InMemoryCouchDb implements HttpClient {
    private static Logger logger = Logger.getLogger(InMemoryCouchDb.class);
    private Databases databases;

    public InMemoryCouchDb(Databases databases) {
        this.databases = databases;
    }

    public InMemoryCouchDb() {
        this(new Databases());
    }

    @Override
    public HttpParams getParams() {
        return new HttpParamsStub();
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        return new ClientConnectionManagerStub();
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest) throws IOException {
        return execute((HttpRequestBase) httpUriRequest);
    }

    private HttpResponse execute(HttpRequestBase httpRequestBase) {
        logger.debug(String.format("%s---%s", httpRequestBase.getURI().toString(), httpRequestBase.getMethod()));
        CouchRequest couchRequest = CouchHttpRequestFactory.create(httpRequestBase);
        return couchRequest.execute(databases);
    }

    @Override
    public HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return execute((HttpRequestBase)httpRequest);
    }

    @Override
    public HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws IOException {
        throw new NotImplementedException();
    }
}