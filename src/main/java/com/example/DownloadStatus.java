package com.example;

import java.io.Serializable;
import java.util.concurrent.Future;

/**
 * @author daicham
 */
public class DownloadStatus implements Serializable {
    private String id;
    private String status;
    private int delay;
    private String url;
    private Future<String> result = null;

    public DownloadStatus() {
        super();
    }
    private DownloadStatus(String id, String status, int delay, Future<String> result) {
        this.id = id;
        this.status = status;
        this.delay = delay;
        this.result = result;
    }

    public static DownloadStatus create(Future<String> result) {
        String id = "4"; //TODO Get id from result
        return new DownloadStatus(id, "working", 3, result);
    }
    public DownloadStatus done() {
        this.status = "done";
        this.delay = -1;
        this.url = "/test.csv"; //TODO Get url from result
        return this;
    }
    public boolean isDone() {
        return result.isDone();
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getDelay() {
        return delay;
    }

    public String getUrl() {
        return url;
    }

    public Future<String> getResult() {
        return result;
    }
}
