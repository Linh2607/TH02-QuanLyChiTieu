package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvTotalAmount;
    private ListView lvExpenses;
    private Button btnAddExpense;

    // Danh sách toàn cục để lưu trữ các khoản chi tiêu
    public static ArrayList<Expense> expenseList = new ArrayList<>();
    private ExpenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        lvExpenses = findViewById(R.id.lvExpenses);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        // Khởi tạo dữ liệu mẫu ban đầu nếu danh sách đang trống[cite: 1]
        if (expenseList.isEmpty()) {
            expenseList.add(new Expense("Ăn sáng", 30000, "Ăn uống", "12/10/2026", "Phở bò"));
            expenseList.add(new Expense("Đổ xăng", 50000, "Đi lại", "12/10/2026", "Xe máy"));
            expenseList.add(new Expense("Mua sách", 120000, "Học tập", "13/10/2026", "Sách Lập trình Android"));
        }

        // Gắn Adapter vào ListView
        adapter = new ExpenseAdapter(this, expenseList);
        lvExpenses.setAdapter(adapter);
        updateTotalAmount();

        // Xử lý khi nhấn nút "Thêm khoản chi"[cite: 1]
        btnAddExpense.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            startActivity(intent);
        });

        // Xử lý khi nhấn vào một khoản chi trong danh sách để xem chi tiết[cite: 1]
        lvExpenses.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, ExpenseDetailActivity.class);
            intent.putExtra("expense_index", position); // Truyền vị trí item được chọn sang màn hình chi tiết
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật lại giao diện danh sách và tổng tiền mỗi khi quay lại màn hình này
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            updateTotalAmount();
        }
    }

    // Hàm tính toán và cập nhật tổng số tiền đã chi[cite: 1]
    private void updateTotalAmount() {
        double total = 0;
        for (Expense e : expenseList) {
            total += e.getAmount();
        }
        tvTotalAmount.setText(String.format("%,.0f đ", total).replace(',', '.'));
    }
}