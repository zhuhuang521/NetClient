package com.zxs.netclient;

/**
 * Created by zxs on 14/11/5.
 */
public interface ResponseInterface {

    void sendStartMessage();

    void sendErrorMessage();

    void sendFinishMessage();

    void setRetryMessage();

}
