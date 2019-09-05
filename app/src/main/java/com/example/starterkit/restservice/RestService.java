package com.example.starterkit.restservice;

import android.content.Context;
import android.util.SparseArray;

import com.example.starterkit.BuildConfig;
import com.example.starterkit.eventbus.RxBus;
import com.example.starterkit.eventbus.SchedulerProvider;
import com.example.starterkit.eventbus.SuccessEvent;
import com.example.starterkit.model.NasaModelClass;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class RestService {

    private RestInterface restInterface;
    private RxBus eventBus;
    private SparseArray<Subscription> subscriptions;
    private Context context;

    public RestService(Context context, RxBus eventBus) {
        this.eventBus = eventBus;
        this.context = context;
        this.subscriptions = new SparseArray<>();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        clientBuilder.readTimeout(60, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(60, TimeUnit.SECONDS);
        clientBuilder.addInterceptor(new NetworkInterceptor(context));
        clientBuilder.addInterceptor(new TokenInterceptor(context));

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);


        restInterface = new Retrofit.Builder()
                .baseUrl(RestInterface.BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build()
                .create(RestInterface.class);

    }


    public RestService(Context context) {
        this(context, null);
    }

    public void cancelAll() {
        for (int i = 0; i < subscriptions.size(); i++) {
            subscriptions.valueAt(i).unsubscribe();
        }
    }


    private <T> Subscriber<T> createSubscriber(final int requestCode) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                subscriptions.remove(requestCode);
                if(eventBus!=null) {
                    eventBus.postCompletion(requestCode);
                }
            }

            @Override
            public void onError(Throwable e) {
                subscriptions.remove(requestCode);
                if(eventBus!= null) {
                    eventBus.postError(e, requestCode);
                }
            }

            @Override
            public void onNext(T t) {

                if(eventBus !=null) {
                    eventBus.postNext(new SuccessEvent<>(t, requestCode));
                }
            }
        };
    }

    public class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0) return null;
                    return delegate.convert(body);
                }
            };
        }
    }

    //example api

    public void getStarterData(String apiKey, int requestcode) {
        restInterface.getPlanetaryData(apiKey)
                .compose(SchedulerProvider.DEFAULT.<Response<NasaModelClass>>applySchedulers())
                .subscribe(this.<Response<NasaModelClass>> createSubscriber(requestcode));
    }



}
