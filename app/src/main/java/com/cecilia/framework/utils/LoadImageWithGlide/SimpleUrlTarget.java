package com.cecilia.framework.utils.LoadImageWithGlide;

import com.bumptech.glide.request.target.SimpleTarget;

/**
 * @author stone
 */

abstract class SimpleUrlTarget<Z> extends SimpleTarget<Z> {

    final String mUrl;

    SimpleUrlTarget(String url) {
        super();
        mUrl = url;
    }

    SimpleUrlTarget(String url, int width, int height) {
        super(width, height);
        mUrl = url;
    }
}
