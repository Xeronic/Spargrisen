package xeronic.org.majja.p1;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends Fragment {

    TextView username_text, logout_button, page_text;


    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);

        username_text = (TextView) view.findViewById(R.id.username_text);
        logout_button = (TextView) view.findViewById(R.id.logout_button);

        logout_button.setOnClickListener(new ButtonListener());

        page_text = (TextView) view.findViewById(R.id.page_text);
        String page = getActivity().getLocalClassName();
        setPageText(page);
        setUsername();

        return view;
    }

    public void setPageText(String page) {

        if (page.equals("MainActivity")) {
            page = getString(R.string.title_activity_main);
        } else if (page.equals("ResultActivity")) {
            page = getString(R.string.title_activity_result);
        } else if (page.equals("ExpenseActivity")) {
            page = getString(R.string.title_activity_expense);
        } else if (page.equals("ExpenseListActivity")) {
            page = getString(R.string.title_activity_expense_list);
        } else if (page.equals("IncomeActivity")) {
            page = getString(R.string.title_activity_income);
        } else if (page.equals("IncomeListActivity")) {
            page = getString(R.string.title_activity_income_list);
        }

        page_text.setText(page);
    }

    private void setUsername() {
        SharedPreferences sp = getActivity().getSharedPreferences(getString(R.string.preference_file_name), Activity.MODE_PRIVATE);
        String username = sp.getString("firstname", "") + " " + sp.getString("lastname", "");
        username_text.setText(username);
    }

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            SharedPreferences sp = getActivity().getSharedPreferences(getString(R.string.preference_file_name), Activity.MODE_PRIVATE);
            SharedPreferences.Editor spe = sp.edit();
            spe.clear();
            spe.apply();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().startActivity(intent);
        }
    }

}
