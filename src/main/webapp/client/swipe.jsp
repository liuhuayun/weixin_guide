<%@ page language="java" 
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML> 
<html>
<head>
<title>Swipe 2</title>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0'/> 
<link href='/static/swipe/style.css' rel='stylesheet'/>
<style>

/* Swipe 2 required styles */

.swipe {
  overflow: hidden;
  visibility: hidden;
  position: relative;
}
.swipe-wrap {
  overflow: hidden;
  position: relative;
}
.swipe-wrap > div {
  float:left;
  width:100%;
  position: relative;
}

/* END required styles */

</style>

</head>
<body>

<h1>Swipe 2</h1>



<div id='mySwipe' style='max-width:500px;margin:0 auto' class='swipe'>
  <div class='swipe-wrap'>
    <div> <a href="http://www.baidu.com"><img src="/static/slider/img/img1.jpg" alt="jQuery Slider with CSS Transitions"> </a></div>
    <div> <a href="http://www.baidu.com"><img src="/static/slider/img/img2.jpg" alt="jQuery Slider with CSS Transitions"> </a></div>
    <div> <a href="http://www.baidu.com"><img src="/static/slider/img/img3.jpg" alt="jQuery Slider with CSS Transitions"> </a></div>
    <div> <a href="http://www.baidu.com"><img src="/static/slider/img/img4.jpg" alt="jQuery Slider with CSS Transitions"> </a></div>
  </div>
</div>


 <div style='text-align:center;padding-top:20px;'>
    <div id="pager"> <em class="on">1</em> <em>2</em>
      <em>3</em>
    </div>
    <button onclick='mySwipe.prev();return false;'>prev</button>
    <button onclick='mySwipe.next();return false;'>next</button>

  </div>



<script src="/static/slider/js/jquery-1.8.3.min.js"></script>
<script src='/static/swipe/swipe.js'></script>
<script>
/* startSlide Integer (default:0) - 开始滚动的位置
speed Integer (default:300) - 动画滚动的间隔（秒数）
auto Integer - 开始自动幻灯片（以毫秒为单位幻灯片之间的时间）
continuous Boolean (default:true) - 创建一个无限的循环（当滑动到所有动画结束时是否循环滑动）
disableScroll Boolean (default:false) - 当滚动滚动条时是否停止幻灯片滚动
stopPropagation Boolean (default:false) - 是否停止事件冒泡
callback Function - 幻灯片运行中的回调函数
transitionEnd Function - 动画运行结束的回调函数
*/
// pure JS
var elem = document.getElementById('mySwipe');
window.mySwipe = Swipe(elem, {
   startSlide: 1,
   auto: 2000,
   continuous: true,
   disableScroll: true,
   stopPropagation: true,
   callback: function(index, element) {console.log(element);},
   transitionEnd: function(index, element) {console.log(element);}
});

// with jQuery
 //window.mySwipe = $('#mySwipe').Swipe().data('Swipe');

</script>