package com.zxs.netclient;

import android.net.http.AndroidHttpClient;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by zxs on 14/11/5.
 */
public class NetClientHttpRequest implements Runnable {

    private AndroidHttpClient client;
    private HttpContext httpContext;
    private ResponseInterface responseInterface;
    private HttpUriRequest httpUriRequest;

    private int requestExecutionTime;

    public NetClientHttpRequest(AndroidHttpClient androidHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, ResponseInterface responseInterface) {
        this.client = androidHttpClient;
        this.httpContext = httpContext;
        this.responseInterface = responseInterface;
        this.httpUriRequest = httpUriRequest;
    }

    @Override
    public void run() {
        if (responseInterface != null) {
            responseInterface.sendStartMessage();
        }
        try {

            beginRequest();

        } catch (Exception e) {

        }


    }

    /**
     * 开始请求数据
     */
    private void beginRequest() {
        boolean requestDone = true;
        while (requestDone) {

            try {
                executionRequest();
                return;


            } catch (Exception e) {
                if (requestExecutionTime < HttpClientConstants.DEFAULT_RETRY_TIMES) {
                    requestExecutionTime++;
                    try {
                        Thread.sleep(HttpClientConstants.DEFAULT_RETRY_TIME);
                    } catch (Exception sleepError) {
                        requestDone = false;
                        if (responseInterface != null) {
                            responseInterface.sendErrorMessage();
                        }
                    }
                } else {
                    requestDone = false;
                    if (responseInterface != null) {
                        responseInterface.sendErrorMessage();
                    }
                }

            }
            if (requestDone & responseInterface != null) {
                responseInterface.setRetryMessage();
            }
        }
    }

    /**
     * 请求数据
     */
    private void executionRequest() throws IOException{
           client.execute(httpUriRequest,null,httpContext);
    }
}
