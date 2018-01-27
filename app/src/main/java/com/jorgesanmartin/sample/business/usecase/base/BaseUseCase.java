package com.jorgesanmartin.sample.business.usecase.base;

import com.jorgesanmartin.sample.data.repository.RepositoryProxy;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by jorgesanmartin on 11/2/17.
 */

public abstract class BaseUseCase<K> {

    private Scheduler subscriberScheduler;
    private Scheduler observableScheduler;
    private Subscription subscription = Subscriptions.empty();

    protected RepositoryProxy repositoryFactory;

    public BaseUseCase() {
        this.subscriberScheduler = Schedulers.io();
        this.observableScheduler = AndroidSchedulers.mainThread();
    }

    public BaseUseCase(RepositoryProxy repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
        this.subscriberScheduler = Schedulers.io();
        this.observableScheduler = AndroidSchedulers.mainThread();
    }

    public abstract Observable<K> buildUseCaseObservable();

    public void subscribe(BaseSubscriber<K> subscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(subscriberScheduler)
                .observeOn(observableScheduler)
                .subscribe(subscriber);
    }

    public void subscribe() {
        this.subscription = this.buildUseCaseObservable().subscribeOn(subscriberScheduler)
                .observeOn(observableScheduler).subscribe(new BaseSubscriber<K>(null) {
                });
    }

    public void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public boolean isUnsubscribed() {
        return subscription.isUnsubscribed();
    }
}
