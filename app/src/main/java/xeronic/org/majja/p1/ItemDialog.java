package xeronic.org.majja.p1;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Xeronic on 15-09-14.
 */
public class ItemDialog extends DialogFragment {

    Transaction transaction;

    public ItemDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_item, container, false);

        Bundle bundle = getArguments();
        Transaction transaction = (Transaction) bundle.getSerializable("transaction");

        TextView item_amount = (TextView) getDialog().findViewById(R.id.item_amount);
        TextView item_date = (TextView) getDialog().findViewById(R.id.item_date);
        TextView item_category = (TextView) getDialog().findViewById(R.id.item_category);
        Button close_button = (Button) view.findViewById(R.id.item_dialog_close_button);


        //item_amount.setText("Belopp: " + transaction.getAmount());
        //item_date.setText("Datum: " + transaction.getDate());
        item_category.setText("test");
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        getDialog().setTitle(transaction.getTitle());
        return view;
    }
}