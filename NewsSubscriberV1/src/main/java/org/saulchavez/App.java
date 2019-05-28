package org.saulchavez;

import org.saulchavez.news.NewsType;
import org.saulchavez.news.SingleNews;
import org.saulchavez.news.reactive.NewsConsumer;
import org.saulchavez.news.reactive.NewsEmitter;
import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConnectableObservable<SingleNews> emitter = NewsEmitter.getNewsEmitter("/src/resources/sourceNews");
        emitter.filter(
                news -> NewsType.DEPORTES.equals(news.getType())
            ).subscribe(
                NewsConsumer.getSubscriber("Subscriber 1")
        );

        emitter.filter(
                news -> NewsType.ESPECTACULOS.equals(news.getType())
        ).subscribe(
                NewsConsumer.getSubscriber("Subscriber 2")
        );

        emitter.filter(
                news -> {
                    return NewsType.POLITICA.equals(news.getType()) ||
                            //FOR TESTING - THIS SUBSCRIBER IS GOING TO UNSUBSCRIBE AS SOON AS IT GETS AN "ESCPECTACULOS" NEWS
                            NewsType.ESPECTACULOS.equals(news.getType());
                }
        ).subscribe(
                NewsConsumer.getSubscriber(
                        "Subscriber 3",
                        news -> NewsType.ESPECTACULOS.equals(news.getType())
                )
        );

        emitter.filter(
                news -> {
                    return NewsType.ESPECTACULOS.equals(news.getType()) ||
                            NewsType.DEPORTES.equals(news.getType());
                }
        ).subscribe(
                NewsConsumer.getSubscriber("Subscriber 4")
        );

        emitter.filter(
                news -> {
                    return NewsType.ESPECTACULOS.equals(news.getType()) ||
                            NewsType.DEPORTES.equals(news.getType()) ||
                            NewsType.POLITICA.equals(news.getType());
                }
        ).subscribe(
                NewsConsumer.getSubscriber("Subscriber 5")
        );

        emitter.connect();
    }
}
