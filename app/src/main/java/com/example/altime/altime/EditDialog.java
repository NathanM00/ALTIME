package com.example.altime.altime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditDialog extends AppCompatDialogFragment {

    public EditText et_tarea;
    public EditText et_actividad;

    public editDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        et_tarea = view.findViewById(R.id.et_tarea);
        et_actividad = view.findViewById(R.id.et_actividad);

        builder.setView(view)
                .setTitle("Guardar")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String materia = et_tarea.getText().toString();
                        String actividad = et_actividad.getText().toString();

                        listener.sendInput(materia, actividad);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


        return builder.create();
    }

    //@Override
    public void onAttach(Context context) {

        try {
            listener = (editDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e("onAttached: ClassCastException: ",""+e.getMessage());

        }
        super.onAttach(context);
    }

    public interface editDialogListener{
        void sendInput(String materia, String actividad);
    }
}
