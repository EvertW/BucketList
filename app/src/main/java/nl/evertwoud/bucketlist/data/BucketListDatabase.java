package nl.evertwoud.bucketlist.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {BucketListItem.class}, version = 1)
public abstract class BucketListDatabase extends RoomDatabase {
    private final static String NAME_DATABASE = "bucketlist_db";
    private static BucketListDatabase sInstance;

    public static BucketListDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, BucketListDatabase.class, NAME_DATABASE).allowMainThreadQueries().build();
        }
        return sInstance;
    }

    public abstract BucketListDao bucketListDao();
}