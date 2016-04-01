package practicaltest01var06.eim.systems.cs.pub.ro.practicaltest01var06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private EditText upper_edit_text = null;
    private Button details_button = null;
    private EditText web_edit_text = null;
    private Button validate_button = null;
    private Button navigate_to_secondary_activity_button = null;

    private LinearLayout web_linear_layout = null;



    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
    private Button navigateToSecondaryActivityButton = null;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var06SecondaryActivity.class);
//                    int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) +
//                            Integer.parseInt(rightEditText.getText().toString());
                    String webString = web_edit_text.getText().toString();
                    intent.putExtra("webEditText", webString);
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
            }
        }
    }

    private ShowButtonClickListener showButtonClickListener = new ShowButtonClickListener();
    private class ShowButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (web_linear_layout.getVisibility()) {
                case View.VISIBLE:
                    web_linear_layout.setVisibility(View.INVISIBLE);
                    break;
                case View.INVISIBLE:
                    web_linear_layout.setVisibility(View.VISIBLE);
//                    showHideAdditionalFieldsButton.setText(getResources().getString(R.string.hide_additional_fields));
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        upper_edit_text = (EditText)findViewById(R.id.upper_edit_text);
        web_edit_text = (EditText)findViewById(R.id.web_edit_text);
        web_edit_text.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.toString().startsWith("http")) {
                    validate_button.setBackground(getApplicationContext().getResources().getDrawable(R.color.green));
                    validate_button.setText(getApplicationContext().getResources().getText(R.string.pass));
                }
            }
        });

        web_linear_layout = (LinearLayout)findViewById(R.id.web_linear_layout);

        details_button = (Button)findViewById(R.id.details_button);
        details_button.setOnClickListener(showButtonClickListener);

        validate_button = (Button)findViewById(R.id.validate_button);
        navigate_to_secondary_activity_button = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigate_to_secondary_activity_button.setOnClickListener(buttonClickListener);
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("upperEditText", upper_edit_text.getText().toString());
        savedInstanceState.putString("webEditText", web_edit_text.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("upperEditText")) {
            upper_edit_text.setText(savedInstanceState.getString("upperEditText"));
        } else {
            upper_edit_text.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey("webEditText")) {
            web_edit_text.setText(savedInstanceState.getString("webEditText"));
        } else {
            web_edit_text.setText(String.valueOf(0));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
