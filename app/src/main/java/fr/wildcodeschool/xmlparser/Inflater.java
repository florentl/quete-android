package fr.wildcodeschool.xmlparser;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.Xml;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import fr.wildcodeschool.xmlparser.wildView.WildButtonViewBuilder;
import fr.wildcodeschool.xmlparser.wildView.WildCheckboxViewBuilder;
import fr.wildcodeschool.xmlparser.wildView.WildEditTextViewBuilder;
import fr.wildcodeschool.xmlparser.wildView.WildLinearLayoutBuilder;
import fr.wildcodeschool.xmlparser.wildView.WildSpaceViewBuilder;
import fr.wildcodeschool.xmlparser.wildView.WildTextViewBuilder;
import fr.wildcodeschool.xmlparser.wildView.WildViewBuilder;

public class Inflater {
    // Activity context
    private Context ctx;

    // Constructor should only contains initialisation
    Inflater(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * This method parse the xml layout to populate the activity screen
     * @param pParent Parent layout
     * @throws IOException
     * @throws XmlPullParserException
     */
    public void inflate(ViewGroup pParent) throws IOException, XmlPullParserException {
        // Store the parent
        ViewGroup lParentView = pParent;

        // INFO WCS - Here is how to keep a file from the assets directory
        InputStream lXmlStream = this.ctx.getAssets().open("content_assets.xml");

        // XML parser initialization
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(lXmlStream, null);

        // Loop on XML nodes
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_TAG) {
                WildViewBuilder viewBuilder=null;
                switch (parser.getName()) {
                    case "LinearLayout":
                        viewBuilder = new WildLinearLayoutBuilder( ctx );
                        break;
                    case "EditText":
                        viewBuilder = new WildEditTextViewBuilder( ctx );
                        break;
                    case "Button":
                        viewBuilder = new WildButtonViewBuilder( ctx );
                        break;
                    case "TextView":
                        viewBuilder = new WildTextViewBuilder( ctx );
                        break;
                    case "CheckBox":
                        viewBuilder = new WildCheckboxViewBuilder( ctx );
                        break;
                    case "Space":
                        viewBuilder = new WildSpaceViewBuilder( ctx );
                        break;
                    default:
                        break;
                }

                if(viewBuilder != null) {
                    viewBuilder.parseXmlNode( parser );
                    if(viewBuilder instanceof WildTextViewBuilder) {
                        ((WildTextViewBuilder)viewBuilder).setPadding();
                    }
                    lParentView.addView( viewBuilder.getBuildView() );
                    if(viewBuilder instanceof WildLinearLayoutBuilder) {
                        lParentView = ((WildLinearLayoutBuilder)viewBuilder).getLayout();
                    }
                }

            }
            else if (eventType == XmlPullParser.END_TAG) {
                switch (parser.getName()) {
                    case "LinearLayout":
                        lParentView = (ViewGroup)lParentView.getParent();
                        break;
                    default:
                        break;
                }
            }
            parser.next();
            eventType = parser.getEventType();
        }
    }
}
