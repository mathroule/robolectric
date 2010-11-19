package com.xtremelabs.robolectric.shadows;

import android.database.DataSetObserver;
import android.widget.BaseAdapter;
import com.xtremelabs.robolectric.util.Implementation;
import com.xtremelabs.robolectric.util.Implements;

import java.util.ArrayList;
import java.util.List;

/**
 * Shadows the {@code android.widget.BaseAdapter} class.
 */
@SuppressWarnings({"UnusedDeclaration"})
@Implements(BaseAdapter.class)
public class ShadowBaseAdapter {
    private final List<DataSetObserver> dataSetObservers = new ArrayList<DataSetObserver>();

    /**
     * Just returns true
     * @return true
     */
    @Implementation
    public boolean areAllItemsEnabled() {
        return true;
    }

    /**
     * Registers the observer
     * @param observer
     */
    @Implementation
    public void registerDataSetObserver(DataSetObserver observer) {
        dataSetObservers.add(observer);
    }

    /**
     * Unregisters the observer if it can be found. Nothing otherwise.
     * @param observer
     */
    @Implementation
    public void unregisterDataSetObserver(DataSetObserver observer) {
        dataSetObservers.remove(observer);
    }

    /**
     * Notifies the registered observers
     */
    @Implementation
    public void notifyDataSetChanged() {
        for (DataSetObserver dataSetObserver : dataSetObservers) {
            dataSetObserver.onChanged();
        }
    }

    /**
     * Notifies the registered observers
     */
    @Implementation
    public void notifyDataSetInvalidated() {
        for (DataSetObserver dataSetObserver : dataSetObservers) {
            dataSetObserver.onInvalidated();
        }
    }

}
