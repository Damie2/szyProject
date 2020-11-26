package com.smh.szyproject.test.okhttp;

import android.os.Bundle;
import android.view.View;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.ExecutorsPool.CachedExecutorsUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import  okhttp3.Response;

/**
 * author : smh
 * date   : 2020/11/20 13:22
 * desc   :
 */
public class okhttpActivity extends BaseActivity {
    /**
     * OK里面主要有dispitch分发器
     * 分发器主要是调度同步和异步请求
     * 同步请求放到阻塞队列
     * 异步请求放到队列，一个是正在执行(同时60个)，一个是等待执行
     *
     *
     *接着是拦截器链
     *1、先创建一系列拦截器，把它们放到一个拦截器的list里 ,包含了应用程序拦截器(包括重定向拦截器、缓存拦截器等等)和网络拦截器,我们只分析okhttp提供给我们的5个拦截器
     *2、创建一个拦截器链的对象 RealInterceptorChain，并执行proceed方法，proceed方法核心是创建下一个 拦截器链
     *  可以把okhttp当做一个一个执行interceptor的过程
     *
     * okHttp拦截器总结
     * 1、在发起请求前对request进行处理
     * 2、调用下一个拦截器，获取response
     * 3、对response进行处理，再返回给上一个拦截器
     *
     *
     * 重定向拦截器
     * 1、创建StreamAllocation
     * 2、调用RealInterceptorChain.proceed进行请求
     * 3、根据异常结果或者想赢结果判断是否要进行重新请求 如果超过了20次请求，就释放   retryAndFollowUpInterceptor
     * 4、调用下一个拦截器,对response进行处理，并将结果返回给上一ig拦截器
     *
     * 桥接拦截器 BridgeIntercept
     * 1、是负责将用户构建的一个Request请求转化为能够进行网络访问的请求
     * 2、将这个符合网络请求的Request和服务器进行网络请求
     * 3、将网络请求回来的Response转化为用户可见的Response
     *
     * 连接池
     *  ConnectPool  作用是时间范围内复用Connection
     *
     *
     *
     *
     * ------------------------okHttp  中一次网络请求的大致过程
     * 1、Call对象对请求的封装
     * 2、dispatcher对请求的分发     分发同步或者异步
     * 3、调用getResponseWithInterceptors方法 ，这个方法就是拦截器链
     *     RealInterceptorInterceptor 主要是重试和重定向我们的请求
     *     CacheInterceptor           处理缓存的拦截器
     *     BridgeInterceptor          okHttp协议和实际的http协议进行转换的一个拦截器，转换的同时可以进行cook的一些内容
     *     ConnectionInterceptor      负责建立连接和流对象的
     *     CallServerInterceptor      负责完成最终的网络请求的
     *
     *
     *
     *
     *  =====================总结=================
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */



    OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        findViewById(R.id.tv_next).setOnClickListener((View v)->{
            L.e("点?");

            CachedExecutorsUtil.getInstance().init();

            CachedExecutorsUtil.getInstance().executors(()->{
                Request request = new Request.Builder().url("http://www.baidu.com").get().build();
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();//这是同步的，还有异步的
                    L.e("结果是:"+response.body().string());
                    //上面是同步请求
                    //下面是异步请求,异步会开启一个工作线程
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {

                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            L.e("结果是123:"+response.body().string());
                        }
                    });








                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        });
    }
}
