package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.wildcodeschool.xmlparser.LayoutManager;

public class WildTextViewBuilder implements WildViewBuilder{

    private static final String TAG = "WildTextView";

    private AppCompatTextView mTextView;

    private int paddingV=0;

    private int paddingH=0;

    public WildTextViewBuilder(Context context) {
        mTextView = new AppCompatTextView( context );
    }

    /**
     * Populate the view with the attribute value
     * @param key The key of xml attribute
     * @param value The value of xml attribute
     */
    @Override
    public void setAttribute(String key, String value) {

        Pattern pattern = Pattern.compile("([0-9]*)([a-z]*)");
        Matcher matcher;

        switch (key) {
            case "background" :
                mTextView.setBackgroundColor( Color.parseColor( value ) );
                break;
            case "textColor":
                mTextView.setTextColor( Color.parseColor( value ) );
                break;
            case "textSize":
                mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,
                        Float.parseFloat( value.replace( "sp", "" ) ) );
                break;
            case "text":
                mTextView.setText(value);
                break;
            case "hint":
                mTextView.setHint(value);
                break;
            case "id":
                /* Nothing to do */
                break;

            case "paddingHorizontal" :
                matcher = pattern.matcher(value);
                if (matcher.find()) {
                    this.setPaddingV(LayoutManager.convertToPixel(matcher.group(1), matcher.group(2)) );
                }
                break;
            case "paddingVertical" :
                matcher = pattern.matcher(value);
                if (matcher.find()) {
                    this.setPaddingH(LayoutManager.convertToPixel(matcher.group(1), matcher.group(2)) );
                }
                break;
            default:
                Log.i(TAG, "Unknown Attribute ["+key+"]");
                break;
        }
    }

    @Override
    public View getBuildView() {
        return mTextView;
    }

    public void setPaddingV(int paddingV) {
        this.paddingV = paddingV;
    }

    public void setPaddingH(int paddingH) {
        this.paddingH = paddingH;
    }

    public void setPadding() {
        mTextView.setPadding( paddingH, paddingV, paddingH, paddingV );
    }
}
