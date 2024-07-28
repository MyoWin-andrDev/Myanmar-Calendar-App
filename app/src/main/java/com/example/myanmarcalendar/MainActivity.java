package com.example.myanmarcalendar;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myanmarcalendar.databinding.ActivityMainBinding;
import java.util.Locale;
import mmcalendar.MyanmarDate;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initDialog();
        initListeners();
    }

    private void initDialog(){
        datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
            binding.etDate.setText(String.format(Locale.ENGLISH, "%d/%d/%d",dayOfMonth,month +1 , year));
        });
    }

    private void initListeners(){
        binding.btnCalculate.setOnClickListener(v -> {
            try {
                //Format 01/01/1970
                String[] date = binding.etDate.getText().toString().split("/");
                if(date.length !=3) throw new Exception("Invalid date");
                //Setting Array Index
                int day = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int year = Integer.parseInt(date[2]);
                MyanmarDate mmdate = MyanmarDate.of(year, month , day);
//                String mmYear = mmdate.getYear();
//                String mmMonth = mmdate.getMonthName();
//                String mmDay = mmdate.getMoonPhase() + " " + mmdate.getFortnightDay();
//                binding.txt.setText(String.format(Locale.getDefault() ,"%s %s %s", mmYear , mmMonth , mmDay));
                  binding.txt.setText(mmdate.format("B y k, M p f r E n"));
            }
            catch(Exception e){
                Toast.makeText(MainActivity.this, "Invalid Date", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnSelect.setOnClickListener(v -> {
            datePickerDialog.show();
        });
    }
}