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

public class ExpenseActivity extends Activity implements DateInterface {

    private final String categories[] = { "Livsmedel", "Fritid", "Resor", "Boende", "Övrigt" };

    private EditText date_input, amount_input, title_input;
    private Spinner category_spinner;
    private Button choose_date_button;
    private DBHandler db_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        title_input = (EditText) findViewById(R.id.expense_title_input);
        amount_input = (EditText) findViewById(R.id.expense_input);
        date_input = (EditText) findViewById(R.id.expense_date_input);
        choose_date_button = (Button) findViewById(R.id.expense_choose_date_button);
        category_spinner = (Spinner) findViewById(R.id.expense_spinner);

        db_handler = new DBHandler(this);

        setListenersAndAdapters();

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

        // Set listener on add_expense_button
        Button add_expense_button = (Button) findViewById(R.id.add_expense_button);
        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date_split[] = date_input.getText().toString().split("-");
                int year = Integer.parseInt(date_split[0]);
                int month = Integer.parseInt(date_split[1]);
                int day = Integer.parseInt(date_split[2]);

                Transaction transaction = new Transaction(
                        title_input.getText().toString(),
                        (Integer.parseInt(amount_input.getText().toString()) * -1),
                        new Date(year, month, day),
                        (String) category_spinner.getSelectedItem()
                );
                db_handler.addTransaction(transaction);
                resetFields();
                Toast.makeText(ExpenseActivity.this, R.string.expense_added, Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.menu_outcome, menu);
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

    @Override
    public void setDate(int year, int month, int day) {
        this.date_input.setText(year + "-" + month + "-" + day);
    }
}
