package org.saulchavez.news.reactive;

import org.saulchavez.news.NewsType;
import org.saulchavez.news.SingleNews;
import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.function.Function;
import java.util.function.Predicate;

public class NewsEmitter {
    public static ConnectableObservable<SingleNews> getNewsEmitter(String sourcePath){
        return Observable.from(
                SingleNews.fromFile(sourcePath)
        ).publish();
    }
}
