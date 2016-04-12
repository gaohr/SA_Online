/**
 * BMPs display
 * Write by GaoHR
 * 2016-01-05
 */
var param_BMPs = eval('BMPsparamObj'); 
//alert(param_BMPs.length);

/*Single and Rules*/
for(i = 0; i < param_BMPs.length; i++) {
	$("#BMPsparam").append(
//		"<div class='tabbable' style='display:none' id='" + param_BMPs[i].id + param_BMPs[i].id + "info'>" + param_BMPs[i].name + "</div>"
		"<div class='tabbable' style='display:none' id='" + param_BMPs[i].id + param_BMPs[i].id + "info'>" +
			"<ul class='nav nav-tabs'>" +
				"<li class='active'><a data-toggle='tab' href='#panel-" + param_BMPs[i].id + "1'>措施介绍</a></li>" +
				"<li><a data-toggle='tab' href='#panel-" + param_BMPs[i].id + "2'>BMP参数</a></li>" +
				"<li><a data-toggle='tab' href='#panel-" + param_BMPs[i].id + "3'>实施结果</a></li>" +
			"</ul>" +
			"<div class='tab-content' style='height:105px;overflow:scroll'>" +
				"<div class='tab-pane active' id='panel-" + param_BMPs[i].id + "1'>" +
					"<h5><b>" + param_BMPs[i].name + "</b><br><small>&nbsp;&nbsp;&nbsp;&nbsp;" + param_BMPs[i].instruction + "</small></h5>" +
				"</div>" +
				"<div class='tab-pane' id='panel-" + param_BMPs[i].id + "2'>" +
					"<div class='portlet-body' >" +
						"<table class='table table-condensed table-hover'>" +
							"<thead><tr><th>NO.</th><th>参数</th><th>描述</th><th>过程</th><th>单位</th></tr></thead>" +
							"<tbody>" + param_table_1(i) + "</tbody>" +
						"</table>" +
					"</div>" +
				"</div>" +
				"<div class='tab-pane' id='panel-" + param_BMPs[i].id + "3'>" +
					"<h5><b>实施该措施将会导致:</b><br><small>" + param_BMPs[i].result + "</small></h5>" +
				"</div>" +
			"</div>" +
		"</div>"
	)
	
}

/*BMP instruction*/
for(k = 0; k < param_BMPs.length; k++) {
	$("#infodiv").append(
//		"<div class='tabbable' style='display:none' id='" + param_BMPs[k].id + param_BMPs[k].id + "info'>" + param_BMPs[k].name + "</div>"
		"<div id='" + param_BMPs[k].en + "' style='display:none'>" +
            "<div class='infodiv2'>" +
                 "<h5><b>" + param_BMPs[k].name + "</b><br><small>&nbsp;&nbsp;&nbsp;&nbsp;" + param_BMPs[k].instruction + "</h5>" +
                 "<h5><b>&nbsp;&nbsp;&nbsp;&nbsp;实施该措施将会导致:</b><br><small>" + param_BMPs[k].result+ "</small></h5>" +
            "</div>" +
            "<div class='portlet-body infodiv2'>" +
                "<h5><b>" + param_BMPs[k].name + "措施参数修改表</b></h5>" +
				"<table class='table table-condensed table-hover'>" +
					"<thead>" +
						"<tr><th>NO.</th><th>参数</th><th>描述</th><th>过程</th><th>单位</th><th>改参</th><th>取值</th></tr>" +
					"</thead>" +
					"<tbody>" + param_table_2(k) + "</tbody>" +
				"</table>" +
		    "</div>" +
       "</div>"
	)
	
}



/*获得BMPs参数列表*/
function param_table_1(n) {
	var param_tr = "";
	var bg;
	var unitbg;
	for(j = 0; j < param_BMPs[n].parameter.length; j++) {
		bg = Math.round(Math.random() * 3);
		switch(bg) {
			case 0: unitbg = "success"; break;
			case 1: unitbg = "info"; break;
			case 2: unitbg = "warning"; break;
			case 3: unitbg = "danger"; break;
			default: unitbg = "success";
		}
		param_tr += "<tr><td>" + param_BMPs[n].parameter[j].number + "</td><td>" + param_BMPs[n].parameter[j].param + "</td><td>" + param_BMPs[n].parameter[j].info + "</td><td>" + param_BMPs[n].parameter[j].process + "</td><td><span class='label label-" + unitbg + "'>" + param_BMPs[n].parameter[j].unit + "</span></td></tr>"
	}
//	alert(param_tr);
	return param_tr;
}

function param_table_2(n) {
	var param_tr = "";
	var bg;
	var unitbg;
	for(l = 0; l < param_BMPs[n].parameter.length; l++) {
		bg = Math.round(Math.random() * 3);
		switch(bg) {
			case 0: unitbg = "success"; break;
			case 1: unitbg = "info"; break;
			case 2: unitbg = "warning"; break;
			case 3: unitbg = "danger"; break;
			default: unitbg = "success";
		}
		param_tr += "<tr><td>" + param_BMPs[n].parameter[l].number + "</td><td>" + param_BMPs[n].parameter[l].param + "</td><td>" + param_BMPs[n].parameter[l].info + "</td><td>" + param_BMPs[n].parameter[l].process + "</td><td><span class='label label-" + unitbg + "'>" + param_BMPs[n].parameter[l].unit + "</span></td><td>" + param_BMPs[n].parameter[l].type + "</td><td>" + param_BMPs[n].parameter[l].value + "</td></tr>"
	}
//	alert(param_tr);
	return param_tr;
}












