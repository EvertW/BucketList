package nl.evertwoud.bucketlist.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BucketListRepo {
    private Executor mExecutor = Executors.newSingleThreadExecutor();
    private LiveData<List<BucketListItem>> buckelistItems;
    private BucketListDao bucketListDao;

    public BucketListRepo(Context context) {
        BucketListDatabase mAppDatabase = BucketListDatabase.getInstance(context);
        bucketListDao = mAppDatabase.bucketListDao();
        buckelistItems = bucketListDao.getBucketListItems();
    }

    public LiveData<List<BucketListItem>> getBucketListItems() {
        return buckelistItems;
    }

    public void insert(final BucketListItem bucketListItem) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.addBucketListItem(bucketListItem);
            }
        });
    }

    public void update(final BucketListItem bucketListItem) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.updateBucketListItem(bucketListItem);
            }
        });

    }

    public void delete(final BucketListItem bucketListItem) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.deleteBucketListItem(bucketListItem);
            }
        });
    }
}
