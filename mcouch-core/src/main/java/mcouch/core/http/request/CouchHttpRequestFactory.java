package mcouch.core.http.request;

import mcouch.core.http.NotImplementedException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

public class CouchHttpRequestFactory {
    public static CouchRequest create(HttpRequestBase request) {
        switch (request.getMethod()) {
            case "HEAD":
                return new CouchHeadRequest(new CouchURI(request.getURI(), request.getMethod()));
            case "GET":
                return new CouchGetRequest(new CouchURI(request.getURI(), request.getMethod()));
            case "PUT":
                return new CouchPutRequest((HttpPut) request);
            case "POST":
                return new CouchPostRequest((HttpPost) request);
            default:
                throw new NotImplementedException();
        }
    }
}