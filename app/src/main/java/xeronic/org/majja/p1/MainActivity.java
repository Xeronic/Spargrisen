package xeronic.org.majja.p1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkLoggedIn();
        setButtonListeners();

    }

    private void checkLoggedIn() {
        SharedPreferences sp = getSharedPreferences(getString(R.string.preference_file_name), Activity.MODE_PRIVATE);
        String firstname = sp.getString("firstname", null);
        String lastname = sp.getString("lastname", null);

        if (firstname == null && lastname == null) {
            Intent login_intent = new Intent(this, LoginActivity.class);
            startActivity(login_intent);
        }
    }

    private void setButtonListeners() {
        Button show_result_activity_button = (Button) findViewById(R.id.show_result_activity_button);
        Button add_income_button = (Button) findViewById(R.id.show_income_activity_button);
        Button add_expense_button = (Button) findViewById(R.id.show_expense_activity_button);

        show_result_activity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        add_income_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IncomeActivity.class);
                startActivity(intent);
            }
        });

        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
                startActivity(intent);
            }
        });
    }
}
