package com.example.wheelpicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.ait.core.R;

public final class PickerActionSheetContentBinding implements ViewBinding {
    @NonNull
    private final LinearLayout rootView;
    @NonNull
    public final FrameLayout containerView;
    @NonNull
    public final TextView okButton;

    private PickerActionSheetContentBinding(@NonNull LinearLayout rootView, @NonNull FrameLayout containerView, @NonNull TextView okButton) {
        this.rootView = rootView;
        this.containerView = containerView;
        this.okButton = okButton;
    }

    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }

    @NonNull
    public static PickerActionSheetContentBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup)null, false);
    }

    @NonNull
    public static PickerActionSheetContentBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.picker_action_sheet_content, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }

        return bind(root);
    }

    @NonNull
    public static PickerActionSheetContentBinding bind(@NonNull View rootView) {
        int id = R.id.container_view;
        FrameLayout containerView = (FrameLayout)rootView.findViewById(id);
        if (containerView != null) {
            id = R.id.ok_button;
            TextView okButton = (TextView)rootView.findViewById(id);
            if (okButton != null) {
                return new PickerActionSheetContentBinding((LinearLayout)rootView, containerView, okButton);
            }
        }

        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}