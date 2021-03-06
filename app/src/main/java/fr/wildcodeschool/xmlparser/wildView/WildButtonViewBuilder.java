package fr.wildcodeschool.xmlparser.wildView;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

public class WildButtonViewBuilder implements WildViewBuilder {

  // Log TAG definition
  private static final String TAG = "WildButton";

  private AppCompatButton mButton;

  /**
   * Constructor
   * @param ctx Activity context
   */
  public WildButtonViewBuilder(Context ctx) {
    mButton = new AppCompatButton( ctx );
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
        mButton.setText(value);
        break;
      case "gravity":
        mButton.setGravity( Integer.parseInt( value ) );
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
    return mButton;
  }
}