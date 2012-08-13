package mcouch.core.couch.indexing.query;

import mcouch.core.couch.indexing.IndexEntry;
import mcouch.core.couch.indexing.IndexKey;
import mcouch.core.couch.indexing.View;

import java.util.NavigableMap;

public class SimpleQuery implements IndexQuery {
    private final IndexKey indexKey;

    public SimpleQuery(String key) {
        this.indexKey = new IndexKey(key);
    }

    @Override
    public NavigableMap<IndexKey, IndexEntry> execute(View view) {
        return view.get(indexKey);
    }
}