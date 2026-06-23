package a5_JQuery;

/**
 * ClassName: J1
 * Package: a5_JQuery
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/18 - 16:50
 * @Version: v1.0
 */
public class J1 {

    /*
      一.JQuery基础
          1.概念：一个JS的框架，减少代码量。提供简便的JS设计模式，优化HTML文档，事件处理，动画设计，AJAX交互，选择器
              JavaScript框架：封装js原生代码的js文件
          2.使用(见J1.html)
              (1)下载JQuery
              (2)导入JQuery文件
              (3)使用(新建js文件存放jquery)
                  [1]目前jQuery有三个大版本
                      {1}1.x：兼容ie678，使用最为广泛的，官方只做BUG维护，功能不再新增。因此一般项目来说，使用1.x版本就可以了，
                         最终版本：1.12.4 (2016年5月20日）
                      {2}2.x:不兼容ie678,很少有人使用,官方只做BUG维护,功能不再新增。如果不考虑兼容低版本的浏览器可以使用2.x,
                         最终版本：2.2.4 （2016年5月20日）
                      {3}3.x：不兼容ie678，只支持最新的浏览器。除非特殊要求，一般不会使用3.x版本的，很多老的jQuery插件不支持这个版本。
                         目前该版本是官方主要更新维护的版本。(使用这个版本学习3.6.4)
                  [2]jquery-xxx.js 与 jquery-xxx.min.js区别 ：
                      {1}jquery-xxx.js：开发版本。给程序员看的，有良好的缩进和注释。体积大一些
                      {2}jquery-xxx.min.js：生产版本。程序中使用，没有缩进。体积小一些。程序加载更快
          3.JQuery对象和JS对象区别及转换(见J1.html)
              jq -> js：jq对象[索引] 或 jq对象.get(索引) 注：加索引代表转换单个，不加索引代表全部转换！
              js -> jq：$(js对象)
          4.选择器：筛选具有相似特性的元素(标签)，具体操作查看API(见J2.html)
              (1)基本语法
                  [1]事件绑定
                  [2]入口函数
                  [3]样式控制
              (2)分类
                  [1]基本
                      #id
                      element
                      .class
                      *
                      selector1,selector2,selectorN
                  [2]层级
                      ancestor descendant
                      parent > child
                      prev + next
                      prev ~ siblings
                  [3]基本筛选器
                      :first
                      :not(selector)
                      :even
                      :odd
                      :eq(index)
                      :gt(index)
                      :lang 1.9+
                      :last
                      :lt(index)
                      :header
                      :animated
                      :focus
                      :root 1.9+
                      :target 1.9+
                  [4]内容
                      :contains(text)
                      :empty
                      :has(selector)
                      :parent
                  [5]可见性
                      :hidden
                      :visible
                  [6]属性
                      [attribute]
                      [attribute=value]
                      [attribute!=value]
                      [attribute^=value]
                      [attribute$=value]
                      [attribute*=value]
                      [attrSel1][attrSel2][attrSelN]
                  [7]子元素
                      :first-child
                      :first-of-type1.9+
                      :last-child
                      :last-of-type1.9+
                      :nth-child
                      :nth-last-child()1.9+
                      :nth-last-of-type()1.9+
                      :nth-of-type()1.9+
                      :only-child
                      :only-of-type1.9+
                  [8]表单
                      :input
                      :text
                      :password
                      :radio
                      :checkbox
                      :submit
                      :image
                      :reset
                      :button
                      :file
                  [9]表单对象属性
                      :enabled
                      :disabled
                      :checked
                      :selected
                  [10]混淆选择器
                      $.escapeSelector(selector)3.0+
          5.DOM操作
              (1)内容操作(见J3.html)
                  [1]html()：获取/设置元素的标签体内容(可以获取标签下的标签和纯文本)
                  [2]text()：获取/设置元素的标签体纯文本内容(仅可以获取标签下的纯文本)
                  [3]val()：获取/设置元素的value属性值
              (2)属性操作(见J3.html)
                  [1]通用属性操作
                      {1}attr()：获取/设置元素的属性
                      {2}removeAttr()：删除元素的属性
                      {3}prop()：获取/设置元素的属性
                      {4}removeProp():删除元素的属性
                          注：attr和prop的区别
                              操作固有属性(标签自带的属性)：使用prop(用attr不行)
                              操作自定义属性(自己定义属性名和属性值)：使用attr(用prop也可以)
                  [2]对class属性操作
                      {1}addClass()：添加class属性值
                      {2}remove()：删除class属性值
                      {3}toggleClass()：切换class属性
                          例：toggleClass("abc")：若标签上有class="abc"，则删除属性值abc，若不存在则添加
              (3)CRUD操作(见J4.html)
                  [1]append()：父元素将子元素追加到末尾。  对象1.append(对象2)：将对象2添加到对象1元素内部，并且在末尾
                  [2]prepend()：父元素将子元素追加到开头。  对象1.prepend(对象2)：将对象2添加到对象1元素内部，并且在开头
                  [3]appendTo()：  对象1.appendTo(对象2)：将对象1添加到对象2内部，并且在末尾
                  [4]prependTo()：  对象1.prependTo(对象2)：将对象1添加到对象2内部,并且在开头

                  [5]after()：添加元素到元素后边。  对象1.after(对象2)：将对象2添加到对象1后边。对象1和对象2是兄弟关系
                  [6]before()：添加元素到元素前边。  对象1.before(对象2)：将对象2添加到对象1前边。对象1和对象2是兄弟关系
                  [7]insertAfter()：  对象1.insertAfter(对象2)：将对象1添加到对象2后边。对象1和对象2是兄弟关系
                  [8]insertBefore()：  对象1.insertBefore(对象2)：将对象1添加到对象2前边。对象1和对象2是兄弟关系

                  [9]remove()：移除元素  对象.remove()：将对象删除掉
                  [10]empty()：清空元素的所有后代元素及文本，但是保留当前对象以及其属性节点

      二.JQuery高级
          1.动画(见J5.html)
              (1)三种方式显示和隐藏元素
                  [1]默认显示和隐藏方式
                      {1}show([speed, [easing],[fn]])：显示
                      {2}hide([speed, [easing],[fn]])：隐藏
                      {3}toggle([speed], [easing], [fn])：切换
                      参数：
                          speed：动画的速度。三个预定义的值("slow","normal","fast")和自定义动画时长毫秒数(1000)
                          easing：用来指定切换效果，默认"swing"
                              swing：动画先慢，中间快，后慢
                              linear：动画匀速
                          fn：在动画执行完成后执行的函数，每个元素执行一次
                  [2]滑动显示和隐藏方式
                      {1}slideDown([speed], [easing],[fn])：滑动显示
                      {2}slideUp([speed, [easing], [fn]])：滑动隐藏
                      {3}slideToggle([speed], [easing],[fn])：滑动切换
                  [3]淡入淡出显示和隐藏方式
                      {1}fadeIn([speed], [easing],[fn])：淡化显示
                      {2}fadeout([speed], [easing], [fn])：淡化隐藏
                      {3}fadeToggle([speed, [easing], [fn]])：淡化切换
          2.遍历(见J6.html)
              (1)js的遍历方式
                  for(初始化值;循环结束条件;步长)
              (2)iq遍历方式
                  [1]jq对象.each(callback)
                  [2]$.each(object,[callback])
                  [3]for..of:(jq3.0+)
          3.事件绑定(见J7.html)
              (1)jguery标准的绑定方式
                  jq对象.事件方法(回调函数）
              (2)on绑定事件/off解除绑定
                  jq对象.on("事件名称"，回调函数)
                  jq对象.off("事件名称")
              (3)事件切换toggle
                  jq对象.toggle(fn1,fn2...)
                  1.9版本 .toggle(function, function, … ) 方法删除,jQuery Migrate插件(1.9以下)可以恢复此功能。
          4.案例
              (1)图片显示(见J8.html)
              (2)抽奖(见J9.html)
          5.插件(见J10.html)
              (1)实现方式
                  [1]$.fn.extend(object)：增强jq获取的对象的功能
                  [2]$.extend(object)：增强jq对象自身功能
     */

}
