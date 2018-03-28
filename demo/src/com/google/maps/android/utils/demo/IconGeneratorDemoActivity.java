/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.maps.android.utils.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import static android.graphics.Typeface.BOLD;
import static android.graphics.Typeface.ITALIC;
import static android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;

public class IconGeneratorDemoActivity extends BaseDemoActivity {

    private ViewGroup mContainer;
    private TextView mTextView;

    @Override
    protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-33.8696, 151.2094), 10));

        mContainer = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.amu_text_bubble, null);
        mTextView = (TextView) mContainer.findViewById(com.google.maps.android.R.id.amu_text);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(-33.8696, 151.2094));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(makeIcon("test")));

        getMap().addMarker(markerOptions);

    }



    public Bitmap makeIcon(CharSequence text) {
        if (mTextView != null) {
            mTextView.setText(text);
        }

        return makeIcon();
    }

    public Bitmap makeIcon() {

        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mContainer.measure(30, 39);

        int measuredWidth = mContainer.getMeasuredWidth();
        int measuredHeight = mContainer.getMeasuredHeight();

        mContainer.layout(0, 0, measuredWidth, measuredHeight);


        measuredHeight = mContainer.getMeasuredWidth();
        measuredWidth = mContainer.getMeasuredHeight();


        Bitmap r = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        r.eraseColor(Color.TRANSPARENT);

        Canvas canvas = new Canvas(r);

        mContainer.draw(canvas);
        return r;
    }

    private CharSequence makeCharSequence() {
        String prefix = "Mixing ";
        String suffix = "different fonts";
        String sequence = prefix + suffix;
        SpannableStringBuilder ssb = new SpannableStringBuilder(sequence);
        ssb.setSpan(new StyleSpan(ITALIC), 0, prefix.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new StyleSpan(BOLD), prefix.length(), sequence.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}
