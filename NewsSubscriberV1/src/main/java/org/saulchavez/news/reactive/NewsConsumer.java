package org.saulchavez.news.reactive;

import org.saulchavez.news.SingleNews;
import rx.Subscriber;
import rx.Subscription;

import java.util.function.Predicate;

public class NewsConsumer {
    public static Subscriber<SingleNews> getSubscriber(String SubscriberName, Predicate<SingleNews> unsubscribeCondition){
        return new Subscriber<SingleNews>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(SingleNews singleNews) {
                if(unsubscribeCondition.test(singleNews)){
                    System.out.println("Unsubscribing: " + SubscriberName);
                    this.unsubscribe();
                } else {
                    NewsConsumer.onNext(SubscriberName,singleNews);
                }
            }
        };
    }

    public static Subscriber<SingleNews> getSubscriber(String SubscriberName){
        return new Subscriber<SingleNews>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(SingleNews singleNews) {
                NewsConsumer.onNext(SubscriberName,singleNews);
            }
        };
    }

    protected static void onNext(String suscriberName,SingleNews singleNews){
        System.out.println(suscriberName + ": "+ singleNews);
    }
}
