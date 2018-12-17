package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.Log;
import android.view.View;

public class WildEditTextViewBuilder implements WildViewBuilder {

    // Log TAG definition
    private static final String TAG = "WildEditText";

    private AppCompatEditText mEditText;
    /**
     * Constructor
     * @param ctx Activity context
     */
    public WildEditTextViewBuilder(Context ctx) {
        mEditText = new AppCompatEditText( ctx );
    }

    /**
     * Populate the view with the attribute value
     * @param key The key of xml attribute
     * @param value The value of xml attribute
     */
    @Override
    public void setAttribute(String key, String value) {

        switch (key) {
            case "inputType":
                this.setInputType(value);
                break;
            case "ems":
                try {
                    mEditText.setEms(Integer.parseInt(value));
                } catch (NullPointerException e) {
                    Log.e(TAG, e.getMessage());
                }
                break;
            case "text":
                mEditText.setText(value);
                break;
            case "hint":
                mEditText.setHint(value);
                break;
            case "id":
                /* Nothing to do */
                break;
            default:
                Log.i(TAG, "Unknown Attribute ["+key+"]");
                break;
        }
    }

    /**
     *
     * @param pInputType
     */
    private void setInputType(String pInputType) {
        switch (pInputType) {
            case "textPersonName":
                mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                break;
            default:
                // Log it
                break;
        }
    }

    @Override
    public View getBuildView() {
        return mEditText;
    }

}
