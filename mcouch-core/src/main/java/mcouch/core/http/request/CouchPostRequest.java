package mcouch.core.http.request;

import mcouch.core.couch.database.Database;
import mcouch.core.couch.database.Databases;
import mcouch.core.http.StandardHttpResponse;
import mcouch.core.http.response.SuccessfulDocumentCreateResponse;
import mcouch.core.jackson.JSONSerializer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

public class CouchPostRequest implements CouchRequest {
    private HttpPost request;
    private CouchURI uri;

    public CouchPostRequest(HttpPost request) {
        this.request = request;
        uri = new CouchURI(request.getURI());
    }

    @Override
    public HttpResponse execute(Databases databases) {
        CouchRequestBody couchRequestBody = new CouchRequestBody(request);
        Database database = databases.getDatabase(uri.databaseName());
        String submittedJSON = couchRequestBody.submittedJSON();
        if (submittedJSON == null || database == null)
            throw new AssertionError();

        if (uri.isBulkDocsRequest()) {
            String[] documents = JSONSerializer.fromJson(submittedJSON, String[].class);
            for (String document : documents)
                database.addDocument(document);
            return StandardHttpResponse.OK;
        } else {
            SuccessfulDocumentCreateResponse response = database.addDocument(submittedJSON);
            return StandardHttpResponse.okWith(response);
        }
    }
}