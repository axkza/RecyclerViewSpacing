package com.example.recyclerviewspacing

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.library.RecyclerViewSpacing

class MainActivity : AppCompatActivity() {
    private var mode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val gridLayoutManager = GridLayoutManager(this, 4)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0 || position == 9) 4 else 1
            }
        }
        recyclerView.run {
            layoutManager = gridLayoutManager
            adapter = NewAdapter()
            addItemDecoration(RecyclerViewSpacing(10f))
        }

        findViewById<View>(R.id.change).setOnClickListener {
            mode++
            var spacing = recyclerView.getItemDecorationAt(0)
            recyclerView.removeItemDecorationAt(0)
            when (mode % 4) {
                // todo 为每个item添加10dp的间距（左右下）
                0 -> spacing = RecyclerViewSpacing(10f)
                // todo 为每个item添加10dp间距（左右下），并且整个recyclerview左右添加10dp间距
                1 -> spacing = RecyclerViewSpacing(10f, 10f)
                // todo 为每个item添加10dp间距（左右下），并且整个recyclerview左右添加-10dp间距
                2 -> spacing = RecyclerViewSpacing(10f, -10f)
                // todo 为每个item添加10dp间距（左右下），并且整个recyclerview左右添加10dp间距，并且banner（占满1行）的取消左右下间距
                3 -> {
                    spacing = RecyclerViewSpacing(10f, 10f)
                    spacing.needVerticalSpacingInSingleLine = false
                }
            }
            recyclerView.addItemDecoration(spacing)
        }
    }
}