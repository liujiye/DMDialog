package com.dm.app.helper;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

public class ViewHelper
{
    View mRoot;
    private SparseArray<View> mViews;

    public ViewHelper(View rootView)
    {
        this.mRoot = rootView;
        this.mViews = new SparseArray<>();
    }

    public View getConvertView()
    {
        return this.mRoot;
    }

    public <T extends View> T getView(int viewId)
    {
        View localView1 = (View)this.mViews.get(viewId);
        T localView2 = (T) localView1;
        if (localView1 == null)
        {
            localView2 = this.mRoot.findViewById(viewId);
            this.mViews.put(viewId, localView2);
        }
        return localView2;
    }

    public ViewHelper linkify(int viewId)
    {
        Linkify.addLinks((TextView)getView(viewId), Linkify.ALL);
        return this;
    }

    @SuppressLint({"NewApi"})
    public ViewHelper setAlpha(int viewId, float alpha)
    {
        if (Build.VERSION.SDK_INT >= 11)
        {
            getView(viewId).setAlpha(alpha);
        }
        else
        {
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(alpha, alpha);
            localAlphaAnimation.setDuration(0L);
            localAlphaAnimation.setFillAfter(true);
            getView(viewId).startAnimation(localAlphaAnimation);
        }
        return this;
    }

    public ViewHelper setBackgroundColor(int viewId, @ColorInt int color)
    {
        getView(viewId).setBackgroundColor(color);
        return this;
    }

    public ViewHelper setBackgroundRes(int viewId, int imgResId)
    {
        getView(viewId).setBackgroundResource(imgResId);
        return this;
    }

    public ViewHelper setChecked(int viewId, boolean paramBoolean)
    {
        ((Checkable)getView(viewId)).setChecked(paramBoolean);
        return this;
    }

    public ViewHelper setClickable(int viewId, boolean paramBoolean)
    {
        getView(viewId).setClickable(paramBoolean);
        return this;
    }

    public ViewHelper setEnable(int viewId, boolean paramBoolean)
    {
        getView(viewId).setEnabled(paramBoolean);
        return this;
    }

    public ViewHelper setGravity(int viewId, int gravity)
    {
        ((TextView)getView(viewId)).setGravity(gravity);
        return this;
    }

    public ViewHelper setImageBitmap(int viewId, Bitmap paramBitmap)
    {
        ((ImageView)getView(viewId)).setImageBitmap(paramBitmap);
        return this;
    }

    public ViewHelper setImageDrawable(int viewId, Drawable paramDrawable)
    {
        ((ImageView)getView(viewId)).setImageDrawable(paramDrawable);
        return this;
    }

    public ViewHelper setImageResource(int viewId, int imgResId)
    {
        ((ImageView)getView(viewId)).setImageResource(imgResId);
        return this;
    }

    public ViewHelper setMax(int viewId, int max)
    {
        ((ProgressBar)getView(viewId)).setMax(max);
        return this;
    }

    public ViewHelper setOnClickListener(int viewId, View.OnClickListener paramOnClickListener)
    {
        getView(viewId).setOnClickListener(paramOnClickListener);
        return this;
    }

    public ViewHelper setOnLongClickListener(int viewId, View.OnLongClickListener paramOnLongClickListener)
    {
        getView(viewId).setOnLongClickListener(paramOnLongClickListener);
        return this;
    }

    public ViewHelper setOnTouchListener(int viewId, View.OnTouchListener paramOnTouchListener)
    {
        getView(viewId).setOnTouchListener(paramOnTouchListener);
        return this;
    }

    public ViewHelper setProgress(int viewId, int progress)
    {
        ((ProgressBar)getView(viewId)).setProgress(progress);
        return this;
    }

    public ViewHelper setProgress(int viewId, int progress, int max)
    {
        ProgressBar localProgressBar = (ProgressBar)getView(viewId);
        localProgressBar.setMax(max);
        localProgressBar.setProgress(progress);
        return this;
    }

    public ViewHelper setRating(int viewId, float rating)
    {
        ((RatingBar)getView(viewId)).setRating(rating);
        return this;
    }

    public ViewHelper setRating(int viewId, float rating, int max)
    {
        RatingBar localRatingBar = (RatingBar)getView(viewId);
        localRatingBar.setMax(max);
        localRatingBar.setRating(rating);
        return this;
    }

    public ViewHelper setTag(int viewId, int key, Object tag)
    {
        getView(viewId).setTag(key, tag);
        return this;
    }

    public ViewHelper setTag(int viewId, Object tag)
    {
        getView(viewId).setTag(tag);
        return this;
    }

    public ViewHelper setText(int viewId, int textviewId)
    {
        ((TextView)getView(viewId)).setText(textviewId);
        return this;
    }

    public ViewHelper setText(int viewId, String text)
    {
        ((TextView)getView(viewId)).setText(text);
        return this;
    }

    public ViewHelper setTextColor(int viewId, @ColorInt int color)
    {
        ((TextView)getView(viewId)).setTextColor(color);
        return this;
    }

    public ViewHelper setTextColorRes(int viewId, int colorviewId)
    {
        ((TextView)getView(viewId)).setTextColor(this.mRoot.getContext().getResources().getColor(colorviewId));
        return this;
    }

    public ViewHelper setTypeface(Typeface typeface, int... viewIds)
    {
        int i = viewIds.length;
        for (int j = 0; j < i; j++)
        {
            TextView localTextView = (TextView)getView(viewIds[j]);
            localTextView.setTypeface(typeface);
            localTextView.setPaintFlags(localTextView.getPaintFlags() | 0x80);
        }
        return this;
    }

    public ViewHelper setVisibility(int viewId, int visibility)
    {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    public ViewHelper setVisible(int viewId, boolean visible)
    {
        View localView = getView(viewId);
        if (visible) {
            viewId = 0;
        } else {
            viewId = 8;
        }
        localView.setVisibility(viewId);
        return this;
    }

//    public ViewHelper setWheelPickerData(int viewId, Typeface paramTypeface, List paramList, int paramInt2, boolean paramBoolean, int paramInt3)
//    {
//        WheelPicker localWheelPicker = (WheelPicker)getView(viewId);
//        localWheelPicker.setData(paramList);
//        localWheelPicker.setCyclic(paramBoolean);
//        localWheelPicker.setSelectedItemPosition(paramInt2);
//        localWheelPicker.setTypeface(paramTypeface);
//        localWheelPicker.setVisibleItemCount(paramInt3);
//        return this;
//    }
}
