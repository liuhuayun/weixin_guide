
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="">
<title>自动轮播jQuery焦点图插件SliderJS - xw素材网</title>
<link rel="stylesheet" href="/static/slider/css/style.css">
</head>
<body>
<br><br><br>
<div id="jquery-script-menu"> </div>
<div class="slider">
  <div class="slider-container">
    <div class="slider-wrapper">
      <div class="slide"> <img src="/static/slider/img/img1.jpg" alt="jQuery Slider with CSS Transitions"> </div>
      <div class="slide"> <img src="/static/slider/img/img2.jpg" alt="jQuery Slider with CSS Transitions"> </div>
      <div class="slide"> <img src="/static/slider/img/img3.jpg" alt="jQuery Slider with CSS Transitions"> </div>
      <div class="slide"> <img src="/static/slider/img/img4.jpg" alt="jQuery Slider with CSS Transitions"> </div>
    </div>
  </div>
</div>
<script src="/static/slider/js/jquery-1.8.3.min.js"></script> 
<script src="/static/slider/js/slider.js"></script> 
<script type="text/javascript">
	(function() {
		Slider.init({
			target: $('.slider'),
			time: 2000
		});
	})();
</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>
<p>来源：<a href="http://www.xwcms.net/" target="_blank">xw素材网</a></p>
</div>
</body>
</html>
