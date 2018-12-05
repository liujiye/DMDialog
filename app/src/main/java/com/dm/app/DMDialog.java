package com.dm.app;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.dm.app.helper.ViewHelper;
import com.dm.app.listener.OnDialogDestoryListener;
import com.dm.app.listener.OnDialogInitListener;

import java.io.Serializable;

public class DMDialog extends DialogFragment
{
    DialogParams dialogParams;
    View mRoot;
    OnDialogDestoryListener onDialogDestoryListener;
    OnDialogInitListener onDialogInitListener;

    public static Builder builder(FragmentActivity activity, int layoutId)
    {
        return new Builder(layoutId, activity.getSupportFragmentManager());
    }

    public void dismiss()
    {
        super.dismiss();
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
    {
        getDialog().requestWindowFeature(1);
        this.dialogParams = ((DialogParams) getArguments().getSerializable("params"));
        this.mRoot = paramLayoutInflater.inflate(this.dialogParams.layoutId, paramViewGroup);
        setCancelable(this.dialogParams.cancelable);
        if (this.onDialogInitListener != null)
        {
            this.onDialogInitListener.convert(new ViewHelper(this.mRoot), this);
        }
        return this.mRoot;
    }

    public void onDestroy()
    {
        super.onDestroy();
        if (this.onDialogDestoryListener != null)
        {
            this.onDialogDestoryListener.onDestory(new ViewHelper(this.mRoot), this);
        }
        this.dialogParams = null;
        this.onDialogInitListener = null;
        this.mRoot = null;
    }

    public void onStart()
    {
        super.onStart();
        Window localWindow = getDialog().getWindow();
        if (localWindow != null)
        {
            localWindow.setBackgroundDrawable(new ColorDrawable(0));
            WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
            localLayoutParams.dimAmount = this.dialogParams.dimAmount;
            localLayoutParams.gravity = this.dialogParams.gravity;
            localLayoutParams.width = -1;
            localLayoutParams.height = -2;
            localWindow.setAttributes(localLayoutParams);
        }
    }

    public void setOnDialogDestoryListener(OnDialogDestoryListener destoryListener)
    {
        this.onDialogDestoryListener = destoryListener;
    }

    public void setOnDialogInitListener(OnDialogInitListener initListener)
    {
        this.onDialogInitListener = initListener;
    }

    public static class Builder
    {
        OnDialogDestoryListener destoryListener;
        FragmentManager fragmentManager;
        OnDialogInitListener initCompleteListener;
        DMDialog.DialogParams params = new DMDialog.DialogParams();

        public Builder(int layoutId, FragmentManager fragmentManager)
        {
            this.params.layoutId = layoutId;
            this.fragmentManager = fragmentManager;
        }

        public Builder onDialogDestoryListener(OnDialogDestoryListener listener)
        {
            this.destoryListener = listener;
            return this;
        }

        public Builder onDialogInitListener(OnDialogInitListener listener)
        {
            this.initCompleteListener = listener;
            return this;
        }

        public Builder setCancelable(boolean cancelable)
        {
            this.params.cancelable = cancelable;
            return this;
        }

        public Builder setDimAmount(float dimAmount)
        {
            this.params.dimAmount = dimAmount;
            return this;
        }

        public Builder setGravity(int gravity)
        {
            this.params.gravity = gravity;
            return this;
        }

        public void show()
        {
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("params", this.params);
            DMDialog localSGLDialog = new DMDialog();
            localSGLDialog.setArguments(localBundle);
            localSGLDialog.setOnDialogInitListener(this.initCompleteListener);
            localSGLDialog.setOnDialogDestoryListener(this.destoryListener);
            localSGLDialog.show(this.fragmentManager, "t_dialog");
        }
    }

    public static class DialogParams implements Serializable
    {
        boolean cancelable = true;
        float dimAmount = 0.4F;
        int gravity = Gravity.BOTTOM;
        int layoutId;
    }
}
