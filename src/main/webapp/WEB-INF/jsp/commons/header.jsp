<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="header">
  <div class="header_inner">
    <div class="logo">
        
        <div class="index_topad" id="playLogo" style="">
            <a href="/html/activity/1472179566.html" target="_blank">
              <img src="/images/html/20160829181637762.gif">
            </a>
        </div>
		<a name="sfbest_hp_hp_head_logo" href="#" <%--class="trackref logoleft"--%>><img src="images/logo.jpg">
		</a>
		<%--<div class="logo-text">--%>
			<%--<img src="/images/html/logo_word.jpg">--%>
		<%--</div>--%>
		</div>
    <div class="index_promo"></div>
    <div class="search">
      <form action="http://localhost:8080/search" id="searchForm" name="query" method="GET">
        <input type="text" class="text keyword ac_input" name="keyword" id="keyword" value="" style="color: rgb(153, 153, 153);" onkeydown="javascript:if(event.keyCode==13) search_keys('searchForm');" autocomplete="off">
        <input type="button" value="" class="submit" onclick="search_keys('searchForm')">
      </form>
      <div class="search_hot"><a target="_blank" href="#">笔记本电脑</a><a target="_blank" href="#">单反相机</a><a target="_blank" href="#">自行车</a><a target="_blank" href="#">健身卡</a></div>
    </div>
    <div class="shopingcar" id="topCart">
      <s class="setCart"></s><a href="http://localhost:8080/cart/cart" class="t" rel="nofollow">我的购物车</a><b id="cartNum">0</b>
      <span class="outline"></span>
      <span class="blank"></span>
      <div id="cart_lists">
        <!--cartContent-->   
        <div class="clear"></div>
      </div>
    </div>
  </div>
</div>