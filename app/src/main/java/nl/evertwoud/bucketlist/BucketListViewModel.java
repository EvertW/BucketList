package nl.evertwoud.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import nl.evertwoud.bucketlist.data.BucketListItem;
import nl.evertwoud.bucketlist.data.BucketListRepo;

class BucketListViewModel extends ViewModel {
    private BucketListRepo repo;
    private LiveData<List<BucketListItem>> items;

    public BucketListViewModel(Context context) {
        repo = new BucketListRepo(context);
        items = repo.getBucketListItems();
    }

    public LiveData<List<BucketListItem>> getAllBucketListItems() {
        return items;
    }

    public void insert(BucketListItem bucketListItem) {
        repo.insert(bucketListItem);
    }

    public void update(BucketListItem bucketListItem) {
        repo.update(bucketListItem);
    }

    public void delete(BucketListItem bucketListItem) {
        repo.delete(bucketListItem);
    }


}