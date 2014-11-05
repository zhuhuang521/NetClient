package com.zxs.netclient;

import android.net.http.AndroidHttpClient;

import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * Created by zxs on 14/11/5.
 */
public class TaskHttpClient {



    private AndroidHttpClient httpClient;
    /**
     * 默认构造方法
     * */
    public  TaskHttpClient(){
        this(false,HttpClientConstants.DEFAULT_CONNECT_PORT,443);
    }

    /**
     * 构造方法
     *
     * @param  readCache 是否需要读取缓存
     * */
    public  TaskHttpClient(boolean readCache){
            this(readCache,HttpClientConstants.DEFAULT_CONNECT_PORT,443);
    }

    /**
     * 构造方法
     *
     * @param httpPort  http连接的端口号
     * */
    public  TaskHttpClient(int httpPort){
            this(false,httpPort,443);
    }

    /**
     * 构造方法
     *
     * @param httpPort http连接端口
     * @param httpsPort https连接端口
     * */
    public  TaskHttpClient(int httpPort , int httpsPort){
        this(false,httpPort,httpsPort);
    }
    /**
     * 构造方法
     *
     * @param readCache 是否读取缓存
     * */
    public  TaskHttpClient(boolean readCache , int httpPort ,int httpsPort){
            this(readCache,getDefaultSchemeRegistry(httpPort , httpsPort));
    }

    /**
     * 构造方法
     *
     * @param schemeRegistry 连接配置
     * */
    public TaskHttpClient(SchemeRegistry schemeRegistry){
         this(false,schemeRegistry);
    }

    /**
     * 构造方法
     *
     * @param readCache 是否读取缓存
     * @param schemeRegistry 连接配置
     * */
    public TaskHttpClient(boolean readCache , SchemeRegistry schemeRegistry){

    }


    /**
     * 获取请求的默认配置
     *
     * @param httpPort http端口
     * @param httpsPort https端口
     *
     * @return SchemeRegistry
     * */
    private static SchemeRegistry getDefaultSchemeRegistry(int httpPort , int httpsPort){

        if(httpPort < 1)
        {
            httpPort = 80;
        }

        if(httpsPort < 1){
            httpsPort = 443;
        }
        SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();

        SchemeRegistry schemeRegistry=new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(),httpPort));
        schemeRegistry.register(new Scheme("https",sslSocketFactory,httpsPort));

        return schemeRegistry;
    }

    /**
     * get请求Http
     *
     * @param url  请求的地址
     * @param responseInterface 请求的结果反馈
     * */
    public void get(String url , ResponseInterface responseInterface){

    }

    /**
     * post请求Http
     *
     * @param url   请求地址
     * @param responseInterface 请求结果的反馈
     * */
    public void post(String url , ResponseInterface responseInterface){

    }
 }
