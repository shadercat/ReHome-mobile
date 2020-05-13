package com.example.rehome.ui.msnackbar;

import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.rehome.R;
import com.google.android.material.snackbar.Snackbar;

public class ThematicSnackbar {
    public static void SnackbarTextShow(String mes, View element) {
        Snackbar mSnackbar = Snackbar.make(element, mes, Snackbar.LENGTH_SHORT)
                .setActionTextColor(ContextCompat.getColor(element.getContext(), R.color.colorBlue));
        View snackbarView = mSnackbar.getView();
        snackbarView.setBackgroundResource(R.color.colorPrimaryDark);
        TextView snackTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        snackTextView.setTextColor(ContextCompat.getColor(element.getContext(), R.color.colorWhite));
        mSnackbar.show();
    }

    public static void SnackbarWithActionShow(String mes, String actionMes, View.OnClickListener listener, View element) {
        Snackbar mSnackbar = Snackbar.make(element, mes, Snackbar.LENGTH_LONG)
                .setAction(actionMes, listener)
                .setActionTextColor(ContextCompat.getColor(element.getContext(), R.color.colorBlue));
        View snackbarView = mSnackbar.getView();
        snackbarView.setBackgroundResource(R.color.colorPrimaryDark);
        TextView snackTextView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        snackTextView.setTextColor(ContextCompat.getColor(element.getContext(), R.color.colorWhite));
        mSnackbar.show();
    }
}
