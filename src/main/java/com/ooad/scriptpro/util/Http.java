package com.ooad.scriptpro.util;

import okhttp3.*;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

public class Http {
    private final int TIMEOUT = 10;

    private static OkHttpClient client = null;

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public Http() {
        client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    public byte[] get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().bytes();
    }

    public String post(String url, String json) throws SocketTimeoutException {
        RequestBody body;
        try {
            body = RequestBody.create(JSON, json);
        } catch (NullPointerException e) {
            body = RequestBody.create(null, "");
        }
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (SocketTimeoutException se) {
            throw se;
        } catch (IOException e) {
            // ignore
        }
        return null;
    }
}
