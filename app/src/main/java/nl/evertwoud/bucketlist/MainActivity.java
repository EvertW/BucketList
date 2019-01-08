package nl.evertwoud.bucketlist;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import nl.evertwoud.bucketlist.data.BucketListItem;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.recycler_bucket_list)
    RecyclerView recycler;
    BucketListAdapter bucketlistAdapter;

    BucketListViewModel viewModel;
    //Swipe listener
    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder,
                             int swipeDir) {
            //Get the swiped item and remove it from the list
            viewModel.delete(bucketlistAdapter.getItem(viewHolder.getAdapterPosition()));
        }
    };

    @AfterViews
    void init() {
        bucketlistAdapter = new BucketListAdapter(this, new BucketListRow.BucketListUpdateListener() {
            @Override
            public void bucketListItemUpdated(BucketListItem item) {
                viewModel.update(item);
            }
        });

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(bucketlistAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recycler);

        viewModel = new BucketListViewModel(this);
        viewModel.getAllBucketListItems().observe(this, new Observer<List<BucketListItem>>() {
            @Override
            public void onChanged(@Nullable List<BucketListItem> bucketListItems) {
                bucketlistAdapter.setItems(bucketListItems);
            }
        });
    }

    @Click(R.id.fab_add_item)
    void addBucketListRow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.add_item));
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.view_dialog_input, (ViewGroup) getWindow().getDecorView(), false);
        final EditText title = viewInflated.findViewById(R.id.title_input);
        final EditText desc = viewInflated.findViewById(R.id.desc_input);
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String titleInput = title.getText().toString();
                String descInput = desc.getText().toString();

                viewModel.insert(new BucketListItem(titleInput, descInput));
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
