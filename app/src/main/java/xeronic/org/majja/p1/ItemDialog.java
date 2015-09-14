package xeronic.org.majja.p1;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Xeronic on 15-09-14.
 */
public class ItemDialog extends DialogFragment {

    public ItemDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_item, container);
        getDialog().setTitle("Hello");

        return view;
    }
}