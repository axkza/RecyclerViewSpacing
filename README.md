# RecyclerViewSpacing
RecyclerView万能均等间距工具类（适配线性、网格、瀑布流）

# 为什么写这个类？
我发现很多大厂APP并没有一个通用的ItemDecoration的工具类，像抖音有40+个、小红书有80+个，随着需求增加会越来越不好维护。

## 用法
1.为每个item添加10dp的间距（左右下）
```
var spacing = RecyclerViewSpacing(10f)
recyclerView.addItemDecoration(spacing)
```
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv1.png)


2.为每个item添加10dp间距（左右下），并且整个recyclerview左右添加10dp间距
```
spacing = RecyclerViewSpacing(10f, 10f)
recyclerView.addItemDecoration(spacing)
```
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv2.jpeg)


3.为每个item添加10dp间距（左右下），并且整个recyclerview左右添加-10dp间距
```
spacing = RecyclerViewSpacing(10f, -10f)
recyclerView.addItemDecoration(spacing)
```
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv3.jpeg)


4.为每个item添加10dp间距（左右下），并且整个recyclerview左右添加10dp间距，并且banner（占满1行）的取消左右下间距
```
spacing = RecyclerViewSpacing(10f, 10f)
spacing.needVerticalSpacingInSingleLine = false
recyclerView.addItemDecoration(spacing)
```
![contents](https://xkz-1252121784.cos.ap-chengdu.myqcloud.com/rv4.jpeg)

## 推理过程
1.算出均等间距的公式
```
记
总item数：n
均等间距：spacing
每个item的下标：index
每个item占用的总间距：itemAllSpacing = spacing *（n + 1）/n

item 0  
    left = spacing  -> spacing
    right = itemAllSpacing - spacing

item 1
     left = spacing - (itemAllSpacing - spacing)   ->   2 * spacing - itemAllSpacing
     right = itemAllSpacing - (2 * spacing - itemAllSpacing)    ->   2 * itemAllSpacing - 2 * spacing

item 2
     left = spacing - (2*itemAllSpacing - 2 * spacing)   ->3 * spacing - 2 * itemAllSpacing
     
由上可得出结论：
每个item的left = (index + 1) * spacing - index * itemAllSpacing
每个item的right = itemAllSpacing - 每个item的left
```

2.针对整个recyclerview左右的间距可能大于或小于中间间距的特殊情况
```
增加一个recyclerview左右2个额外间距的字段：extraSpacing
则上面的itemAllSpacing = (spacing * (n + 1) + extraSpacing * 2) / n
```

3.针对占满一行的item的特殊情况（如banner、推荐行、广告等）
```
针对这种情况，可能需要左右间距，也可能不需要左右间距
新增字段是否需要左右间距：needVerticalSpacingInSingleLine

left = if(needVerticalSpacingInSingleLine) spacing + extraSpacing else 0
right = if(needVerticalSpacingInSingleLine) spacing + extraSpacing else 0

```
