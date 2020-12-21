package com.example.myalarmmanager;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    MainActivity mListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (MainActivity) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        if (mListener != null) {
            mListener = null;
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        boolean formatHour24 = true;
        return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mListener.onDialogTimeSet(getTag(), hourOfDay, minute);
            }
        }, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public interface DialogTimeListener {
        void onDialogTimeSet(String tag, int hourOfDay, int minute);
    }
}
