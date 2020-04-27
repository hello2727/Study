package org.techtown.mission15;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerInputActivity extends AppCompatActivity {
    EditText nameInput;
    EditText ageInput;

    Button birthButton;

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_input);

        nameInput = findViewById(R.id.nameInput);
        ageInput = findViewById(R.id.ageInput);

        birthButton = findViewById(R.id.birthButton);
        birthButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog();
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nameStr = nameInput.getText().toString();
                String ageStr = ageInput.getText().toString();
                String birthStr = birthButton.getText().toString();

                Toast.makeText(getApplicationContext(), "이름 : " + nameStr + ", 나이 : " + ageStr + ", 생년월일 : " + birthStr, Toast.LENGTH_SHORT).show();

                finish();
                overridePendingTransition(R.anim.entry_back, R.anim.exit_back);
            }
        });


        // set selected date using current date
        Date curDate = new Date();
        setSelectedDate(curDate);

    }

    private void showDateDialog() {
        String birthDateStr = birthButton.getText().toString();

        Calendar calendar = Calendar.getInstance();
        Date curBirthDate = new Date();
        try {
            curBirthDate = dateFormat.parse(birthDateStr);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        calendar.setTime(curBirthDate);

        int curYear = calendar.get(Calendar.YEAR);
        int curMonth = calendar.get(Calendar.MONTH);
        int curDay = calendar.get(Calendar.DAY_OF_MONTH);

        //(context:파라미터) this 또는 CustomerInputActivity.this
        //대화상자 객체의 경우 액티비티 스타일 정보 등을 추가로 참조하므로 Activity 참조 안하면 에러 발생
        DatePickerDialog dialog = new DatePickerDialog(this,  birthDateSetListener,  curYear, curMonth, curDay);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener birthDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar selectedCalendar = Calendar.getInstance();
            selectedCalendar.set(Calendar.YEAR, year);
            selectedCalendar.set(Calendar.MONTH, monthOfYear);
            selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            Date curDate = selectedCalendar.getTime();
            setSelectedDate(curDate);
        }
    };

    private void setSelectedDate(Date curDate) {
        selectedDate = curDate;

        String selectedDateStr = dateFormat.format(curDate);
        birthButton.setText(selectedDateStr);
    }

}
