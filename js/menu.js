/**
 * Menu bar
 * GaoHR
 * 2016-03-29
 */

var active = $("#sign").val();
var menu = '<ul class="page-sidebar-menu">'
	+ 			'<li>'
	+				'<div class="sidebar-toggler hidden-phone icon-list"></div>'
	+			'</li>'
	+			'<li>'
	+				'<form class="sidebar-search">'
	+					'<div class="input-box"><a href="javascript:;" class="remove"></a>'
	+						'<input type="text" placeholder="查询..."/>'
	+						'<input type="button" class="submit" style="height:15px;width:15px; background-image: url(../img/icon/glass.png)"/>'
	+					'</div>'
	+				'</form>'
	+			'</li>'
	+			'<li class="start ' + ifactive("home") + '">'
	+				'<a href="index.html">'
	+				'<i class="icon-home"></i>'
	+				'<span class="title">首页</span>'
	+				'<span class="selected"></span>'
	+				'</a>'
	+			'</li>'
	+			'<li class="' + ifactive("dd") + '">'
	+				'<a href="DataDisplay.html">'
	+				    '<i class="icon-picture"></i>'
	+				    '<span class="title">数据显示</span>'
	+				'</a>'
	+			'</li>'
	+			'<li class="' + ifactive("bmps") + '">'
	+				'<a href="IntroBMP.html">'
	+					'<i class="icon-cogs"></i>' 
	+					'<span class="title">治理措施</span>'
	+				'</a>'
	+			'</li>'
	+			'<li class="' + ifactive("config") + '">'
	+				'<a href="javascript:;">'
	+				'<i class="icon-wrench"></i>'
	+				'<span class="title">情景配置</span>'
	+				'<span class="arrow"></span>'
	+				'</a>'
	+				'<ul class="sub-menu">'
	+					'<li><a href="#" class="active"><i class="icon- icon-user"></i>人工配置<span class="arrow"></span></a>'
	+						'<ul class="sub-menu">'
	+							'<li><a href="Single-Config.html"><i class="icon-edit"></i>空间逐地块设计情景</a></li>'
	+							'<li><a href="Rules-Config.html"><i class=" icon-th-large"></i>按属性规则设计情景</a></li>'
	+						'</ul>'
	+					'</li>'
	+					'<li><a href="CriticalZone.html"><i class="icon-screenshot"></i>按关键源区配置治理目标</a></li>'
	+					'<li><a href="Intelligent-Optimization.html"><i class="icon-sitemap"></i>智能优化</a></li>'
	+				'</ul>'
	+			'</li>'
	+			'<li class="' + ifactive("sa") + '">'
	+				'<a href="javascript:;">'
	+				'<i class="icon-legal"></i>'
	+				'<span class="title">模拟分析</span>'
	+				'<span class="arrow"></span>'
	+				'</a>'
	+				'<ul class="sub-menu">'
	+					'<li><a href="ScenarioSet.html"><i class=" icon-beaker"></i>多情景集加载、批量分析</a></li>'
	+					'<li><a href="Evaluation.html"><i class="icon-bar-chart"></i>情景分析结果对比</a></li>'
	+				'</ul>'
	+			'</li>'
	+			'<li class="' + ifactive("about") + '">'
	+				'<a href="javascript:;">'
	+				'<i class="icon-comment"></i>'
	+				'<span class="title">关于</span>'
	+				'<span class="arrow "></span>'
	+				'</a>'
	+				'<ul class="sub-menu">'
	+					'<li>'
	+					    '<a href="#"><i class="icon-group"></i>Group</a>'
	+					'</li>'
	+					'<li>'
	+					    '<a href="userManuals.html"><i class="icon-briefcase"></i>Help</a>'
	+					'</li>'
	+				'</ul>'
	+			'</li>'
    +       '</ul>'
    
    $("#menu-left").html(menu);
    
    function ifactive(n) {
    	//alert(n);
    	if (active == n) {
    		return "active";
    	} else {
    		return "";
    	}	
    }
    
    
    
    
    
	