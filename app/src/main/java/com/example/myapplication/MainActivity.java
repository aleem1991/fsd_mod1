package com.example.myapplication; // **** Ensure this matches YOUR project's package name ****

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Import Toast for error messages

import java.util.Locale; // Import Locale for formatting output

public class MainActivity extends AppCompatActivity {

    EditText first_no_edt, second_no_edt;
    TextView txt_result;
    // Declare the clear button variable
    Button btn_add, btn_sub, btn_multi, btn_divide, btn_clear; // Added btn_clear

    // It's good practice to define default strings, especially if you might localize
    private String defaultResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make sure your layout file is named activity_main.xml or whatever you named the XML file you provided
        setContentView(R.layout.activity_main); // Use the correct layout file name

        // --- Connect XML views to Java variables ---
        first_no_edt = findViewById(R.id.first_edt_txt);
        second_no_edt = findViewById(R.id.second_edt_txt);
        txt_result = findViewById(R.id.txt_result);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_multi = findViewById(R.id.btn_multi);
        btn_divide = findViewById(R.id.btn_divide);
        // Find the clear button by its ID
        btn_clear = findViewById(R.id.btn_clear); // Added this line

        // Store the default text from the TextView
        defaultResultText = txt_result.getText().toString(); // Store "Result"


        // --- Set click listeners for buttons ---

        // Addition
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('+');
            }
        });

        // Subtraction
        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('-');
            }
        });

        // Multiplication
        btn_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('*');
            }
        });

        // Division
        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation('/');
            }
        });

        // --- Add Click Listener for the Clear Button ---
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the input fields
                first_no_edt.setText("");
                second_no_edt.setText("");

                // Reset the result TextView to its default text
                txt_result.setText(defaultResultText); // Reset to "Result"

                // Optional: Reset text color if you change it on error
                // txt_result.setTextColor(getResources().getColor(android.R.color.holo_green_light));

                // Optional: Request focus on the first input field
                first_no_edt.requestFocus();
            }
        });
    }

    // --- Helper method to perform calculations and handle errors ---
    private void performCalculation(char operation) {
        String firstNumStr = first_no_edt.getText().toString().trim(); // Use trim()
        String secondNumStr = second_no_edt.getText().toString().trim(); // Use trim()

        // Check for empty input fields
        if (firstNumStr.isEmpty() || secondNumStr.isEmpty()) {
            // Using hardcoded string for simplicity, but string resources are better
            txt_result.setText("Error: Enter both numbers");
            // Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return; // Stop further processing
        }

        try {
            // Use double for calculations to handle decimals properly
            double firstNum = Double.parseDouble(firstNumStr);
            double secondNum = Double.parseDouble(secondNumStr);
            double result = 0.0;
            boolean calculationDone = true; // Flag to check if calculation was successful

            switch (operation) {
                case '+':
                    result = firstNum + secondNum;
                    break;
                case '-':
                    result = firstNum - secondNum;
                    break;
                case '*':
                    result = firstNum * secondNum;
                    break;
                case '/':
                    // Check for division by zero
                    if (secondNum == 0) {
                        txt_result.setText("Error: Cannot divide by zero");
                        // Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        calculationDone = false; // Mark calculation as failed
                        // return; // Don't return here if you want to potentially clear inputs on error
                    } else {
                        result = firstNum / secondNum;
                    }
                    break;
                default:
                    calculationDone = false; // Unknown operation
                    break;
            }

            // Display the result only if calculation was successful
            if (calculationDone) {
                // Display the result - Format to avoid overly long decimals
                // Using Locale.US ensures '.' is used as the decimal separator
                // Check if result is effectively an integer
                if (result == (long) result) {
                    // Display as integer if there's no fractional part
                    txt_result.setText(String.format(Locale.US, "%d", (long)result));
                } else {
                    // Display with decimals otherwise (up to 4 places)
                    txt_result.setText(String.format(Locale.US, "%.4f", result));
                }
            }

        } catch (NumberFormatException e) {
            // Handle cases where input is not a valid number (e.g., "abc")
            txt_result.setText("Error: Invalid number input");
            // Toast.makeText(MainActivity.this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }
}