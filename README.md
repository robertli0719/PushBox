# 推箱子求解程序

自带了一个地图编辑UI，支持自定义地图大小。

##界面表示：
*圆圈表示人
*黑色表示墙壁
*绿色的方块是箱子
*蓝色格子表示箱子要推到的位置

##支持两种算法：
*算法1：返回最优按键顺序，例如“上下下下右右上左。。。”
*算法2：返回例如哪个箱子移动到哪个位置，然后再移动哪个。

目前的问题：
由于算法使用的是对状态空间做穷举，所以如果地图大了后会造成内存不足。