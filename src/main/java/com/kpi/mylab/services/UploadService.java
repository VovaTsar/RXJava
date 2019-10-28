package com.kpi.mylab.services;

import com.kpi.mylab.dao.UploadDao;
import com.kpi.mylab.model.EntryModel;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class UploadService {

    private Logger logger = Logger.getLogger(UploadService.class);
    @Autowired
    private UploadDao uploadDao;

    public void addEntries(List<EntryModel> entries) {
        Executor executor = Executors.newSingleThreadExecutor();
        Scheduler scheduler = Schedulers.from(executor);
        Observable.range(0, entries.size())
                .flatMap(i -> Observable.just(entries.get(i))
                        .map(entryModel -> {
                            logger.info("thread: " + Thread.currentThread().getName() + ", entry: " + entryModel);
                            uploadDao.addEntry(entryModel);
                            return entryModel;
                        })
                        .subscribeOn(scheduler))
                .blockingForEach(logger::debug);

    }
}
