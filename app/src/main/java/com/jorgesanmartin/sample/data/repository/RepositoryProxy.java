package com.jorgesanmartin.sample.data.repository;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public class RepositoryProxy {

    public enum RepositoryType {
        MOCK,
        REST,
        LOCAL
    }

    private Application context;
    private RestRepository restRepository;
    private LocalRepository localRepository;
    private MockRepository mockRepository;

    @Inject
    public RepositoryProxy(Application context, RestRepository restRepository, LocalRepository
            localRepository, MockRepository mockRepository) {
        this.context = context;
        this.restRepository = restRepository;
        this.localRepository = localRepository;
        this.mockRepository = mockRepository;
    }

    public TinkerLinkRepository getRepository() {
        return isNetworkAvailable() ? restRepository : localRepository;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public TinkerLinkRepository getRepository(RepositoryType repositoryType) {
        switch (repositoryType) {
            case LOCAL:
                return localRepository;
            case REST:
                return restRepository;
            case MOCK:
                return mockRepository;
            default:
                return restRepository;
        }
    }
}