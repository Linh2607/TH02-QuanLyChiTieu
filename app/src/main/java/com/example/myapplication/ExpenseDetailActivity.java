package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExpenseDetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvAmount, tvCategory, tvDate, tvNote;
    private Button btnBack, btnDelete;
    private int expenseIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvAmount = findViewById(R.id.tvAmount);
        tvCategory = findViewById(R.id.tvCategory);
        tvDate = findViewById(R.id.tvDate);
        tvNote = findViewById(R.id.tvNote);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);

        // Nhận vị trí item được truyền sang từ MainActivity
        expenseIndex = getIntent().getIntExtra("expense_index", -1);

        if (expenseIndex != -1 && expenseIndex < MainActivity.expenseList.size()) {
            Expense expense = MainActivity.expenseList.get(expenseIndex);
            tvTitle.setText(expense.getTitle());
            tvAmount.setText(String.format("%,.0f đ", expense.getAmount()).replace(',', '.'));
            tvCategory.setText(expense.getCategory());
            tvDate.setText(expense.getDate());
            tvNote.setText(expense.getNote().isEmpty() ? "Không có ghi chú" : expense.getNote());
        }

        // Nút Quay lại
        btnBack.setOnClickListener(v -> finish());

        // Nút Xóa khoản chi
        btnDelete.setOnClickListener(v -> {
            if (expenseIndex != -1 && expenseIndex < MainActivity.expenseList.size()) {
                MainActivity.expenseList.remove(expenseIndex);
                finish();
            }
        });
    }
}