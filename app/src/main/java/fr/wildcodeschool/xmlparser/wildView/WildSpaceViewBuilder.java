package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Space;

public class WildSpaceViewBuilder implements WildViewBuilder {

    private static final String TAG = "WildSpace";

    private Space mSpace;

    public WildSpaceViewBuilder(Context context) {
        this.mSpace = new Space( context );
    }

    /**
     * Populate the view with the attribute value
     * @param key The key of xml attribute
     * @param value The value of xml attribute
     */
    @Override
    public void setAttribute(String key, String value) {
        switch (key) {
            case "id":
                /* Nothing to do */
                break;
            default:
                Log.i(TAG, "Unknown Attribute ["+key+"]");
                break;
        }
    }

    @Override
    public View getBuildView() {
        return mSpace;
    }

}
