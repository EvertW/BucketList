package nl.evertwoud.bucketlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import nl.evertwoud.bucketlist.base.BaseRecyclerAdapter;
import nl.evertwoud.bucketlist.base.ViewWrapper;
import nl.evertwoud.bucketlist.data.BucketListItem;

public class BucketListAdapter extends BaseRecyclerAdapter<BucketListItem, BucketListRow> {

    private Context context;
    private BucketListRow.BucketListUpdateListener listener;

    public BucketListAdapter(Context context, BucketListRow.BucketListUpdateListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected BucketListRow onCreateItemView(ViewGroup parent, int viewType) {
        BucketListRow row = BucketListRow_.build(context);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        return row;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewWrapper<BucketListRow> holder, int position) {
        holder.getView().bind(getData().get(position), listener);
    }
}