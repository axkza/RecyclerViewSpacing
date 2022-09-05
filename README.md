# RecyclerViewSpacing
RecyclerView万能均等间距工具类（适配线性、网格、瀑布流）

## 用法
1.为每个item添加10dp的间距（左右下）
```
var spacing = RecyclerViewSpacing(10f)
recyclerView.addItemDecoration(spacing)
```
<!-- 
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv1.jpeg) -->


2.为每个item添加10dp间距（左右下），并且整个recyclerview左右添加10dp间距
```
spacing = RecyclerViewSpacing(10f, 10f)
recyclerView.addItemDecoration(spacing)
```
<!-- 
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv1.jpeg) -->


3.为每个item添加10dp间距（左右下），并且整个recyclerview左右添加-10dp间距
```
spacing = RecyclerViewSpacing(10f, -10f)
recyclerView.addItemDecoration(spacing)
```
<!-- 
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv1.jpeg) -->


3.为每个item添加10dp间距（左右下），并且整个recyclerview左右添加10dp间距，并且banner（占满1行）的取消左右下间距
```
spacing = RecyclerViewSpacing(10f, 10f)
spacing.needVerticalSpacingInSingleLine = false
recyclerView.addItemDecoration(spacing)
```
<!-- 
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv1.jpeg) -->
