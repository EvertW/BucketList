package nl.evertwoud.bucketlist.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * The type View wrapper.
 *
 * @param <V> the type parameter
 */
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private final V view;

    /**
     * Instantiates a new View wrapper.
     *
     * @param itemView the item view
     */
    ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    /**
     * Gets view.
     *
     * @return the view
     */
    public V getView() {
        return view;
    }
}