package xeronic.org.majja.p1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;

public class IncomeActivity extends Activity implements DateInterface {

    private final String categories[] = { "Lön", "Övrigt" };

    private EditText date_input, amount_input, title_input;
    private Spinner category_spinner;
    private Button choose_date_button;
    private DBHandler db_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        title_input = (EditText) findViewById(R.id.income_title_input);
        amount_input = (EditText) findViewById(R.id.income_input);
        date_input = (EditText) findViewById(R.id.income_date_input);
        choose_date_button = (Button) findViewById(R.id.income_choose_date_button);
        category_spinner = (Spinner) findViewById(R.id.income_spinner);

        if (savedInstanceState != null) {
            title_input.setText(savedInstanceState.getString("title"));
            amount_input.setText(savedInstanceState.getString("amount"));
            date_input.setText(savedInstanceState.getString("date"));
            category_spinner.setSelection(savedInstanceState.getInt("category"));
        }

        db_handler = new DBHandler(this);

        setListenersAndAdapters();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("title", title_input.getText().toString());
        outState.putString("amount", title_input.getText().toString());
        outState.putString("date", title_input.getText().toString());
        outState.putInt("category", category_spinner.getSelectedItemPosition());

        super.onSaveInstanceState(outState);
    }

    public void setDate(int year, int month, int day) {
        this.date_input.setText("" + year + "-" + month + "-" + day);
    }

    private void setListenersAndAdapters() {
        ArrayAdapter<String> category_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(category_adapter);

        choose_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerFragment().show(getFragmentManager(), null);
            }
        });

        // Set listener on add_income_button
        Button add_income_button = (Button) findViewById(R.id.add_income_button);
        add_income_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Transaction transaction = new Transaction(
                        title_input.getText().toString(),
                        Integer.parseInt(amount_input.getText().toString()),
                        date_input.getText().toString(),
                        (String) category_spinner.getSelectedItem()
                );
                db_handler.addTransaction(transaction);
                resetFields();
                Toast.makeText(IncomeActivity.this, R.string.income_added, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void resetFields() {
        title_input.setText("");
        amount_input.setText("");
        date_input.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_income, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
