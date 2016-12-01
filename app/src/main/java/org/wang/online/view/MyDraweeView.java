package org.wang.online.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者：wang on 2016/8/12 16:42
 */
public class MyDraweeView extends SimpleDraweeView {
    public MyDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
    }

    public MyDraweeView(Context context) {
        super(context);
    }

    public MyDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Displays an image given by the uri.
     *
     * @param uri uri of the image
     * @param callerContext caller context
     */
    public void setImageURI(Uri uri, Object callerContext) {
        DraweeController controller =  Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setCallerContext(callerContext)
                .setUri(uri)
                .setOldController(getController())
                .build();
        setController(controller);
    }
}
