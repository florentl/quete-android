package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;

public class WildCheckboxViewBuilder implements WildViewBuilder {

    private static final String TAG = "WildCheckbox";

    private AppCompatCheckBox mCheckBox;

    public WildCheckboxViewBuilder(Context context) {
        mCheckBox = new AppCompatCheckBox( context );
    }

    /**
     * Populate the view with the attribute value
     * @param key The key of xml attribute
     * @param value The value of xml attribute
     */
    @Override
    public void setAttribute(String key, String value) {
        switch (key) {
            case "text":
                mCheckBox.setText(value);
                break;
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
        return mCheckBox;
    }

}
