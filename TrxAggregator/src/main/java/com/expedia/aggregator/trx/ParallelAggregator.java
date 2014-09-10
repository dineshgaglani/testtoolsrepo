package com.expedia.aggregator.trx;

import com.expedia.aggregator.data.TestRunType;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Created by dgaglani on 8/26/14.
 */
public abstract class ParallelAggregator<T> {

    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(100));

    /*
        Merges elements and provides a single consolidated element
     */
    public T mergeElements(List<T> elements) throws Exception{
        if(elements.size() < 2) {
            return elements.get(0);
        }
        //We merge 2 elements at a time and put the merged list in another array so that we can recursively merge them later on
        List<T> mergedElements = new ArrayList<T>();
        boolean isThreaded = false;
        //For threaded
        List<ListenableFuture<T>> listenableFutureMergedElements = null;
        if(isThreaded) {
            listenableFutureMergedElements = new ArrayList<ListenableFuture<T>>();
        }
        //using the ancient for because I need to skip 2 elements at a time, please tell me there is a better idea than this!!!
        for(int i = 0; i < elements.size(); i = i+2) {
            //Checking the second element in case the list has odd number elements
            //Setting second element to null if i is at the last element
            T secondElement = i == (elements.size() - 1) ? null : elements.get(i + 1);
            if(!isThreaded) {
                mergedElements.add(mergeElements(elements.get(i), secondElement));
            }
            else {
                //Threaded
                listenableFutureMergedElements.add(executorService.submit(new RunnableMergeElements(elements.get(i), secondElement)));
            }
        }
        if(isThreaded) {
            mergedElements = Futures.allAsList(listenableFutureMergedElements).get();
        }
        return mergeElements(mergedElements);
    }


    public List<File> getFilesFromPaths(List<String> filePaths) {
        ArrayList<File> files = new ArrayList<File>();
        for (String filePath : filePaths) {
            if(filePath != null) {
                files.add(new File(filePath));
            }
        }
        return files;
    }

    public class RunnableMergeElements implements Callable<T> {

        T elementToMerge1;
        T elementToMerge2;

        public RunnableMergeElements(T elementToMerge1, T elementToMerge2) {
           this.elementToMerge1 = elementToMerge1;
           this.elementToMerge2 = elementToMerge2;
        }

        @Override
        public T call() throws Exception {
            if(elementToMerge2 == null) {
                return  elementToMerge1;
            }
            return performMerge(elementToMerge1, elementToMerge2);
        }
    }

    public T mergeElements(T elementToMerge1, T elementToMerge2) {
        if(elementToMerge2 == null) {
            return  elementToMerge1;
        }
        return performMerge(elementToMerge1, elementToMerge2);
    }

    public abstract T performMerge(T elementToMerge1, T elementToMerge2);

}
