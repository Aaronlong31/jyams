
$(document).ready(function() { 
	var projectListGrid = $("#projectList").jqGrid({
		url : "../project/basic",
		datatype : "json",
		colNames : ["客户", "项目编号", "项目名称"],
		colModel : [
			{name : "clientName", index : "clientName", width : 100, search: true,searchoptions :{sopt:["eq", "cn"]}},
			{name : "projectId", key : true, index : "projectId", width : 90, search: true,searchoptions :{sopt:["eq"]}, searchrules : {integer: true}},
			{name : "projectName", index : "projectName", width : 322, search: true,searchoptions :{sopt:["eq", "cn"]}},
		],
		rowNum : 20,
		width: "auto",
		height: 300,
		rowList : [10, 20, 30],
		pager : "#projectPager",
		viewRecords : true,
		sortorder: "desc",
		jsonReader: {
			repeatitems : false
		},
		ondblClickRow : function(rowid, iRow, iCol, e){
			var $tr = $($("#projectList").find("tr").get(iRow));
			selectProject($tr);
		},
		onSelectRow : function(rowid, status){
			$("#selectedRow").val(rowid);
		}
	}).jqGrid('navGrid','#projectPager',{edit:false,add:false,del:false,search:false})
	.jqGrid('filterToolbar',{stringResult: true,searchOnEnter : false})
	.navButtonAdd('#projectPager', {
		caption : "${searchYear - 1}",
		id : "preYear",
		buttonicon : "ui-icon-triangle-1-w",
		onClickButton : function (){
			updateSearchYear(-1);
		}
	})
	.navButtonAdd('#projectPager', {
		caption : "${searchYear}",
		id : "curYear"
	})
	.navButtonAdd('#projectPager', {
		caption : "${searchYear + 1}",
		id : "nextYear",
		buttonicon : "ui-icon-triangle-1-e",
		onClickButton : function (){
			updateSearchYear(1);
		}
	});
	function updateSearchYear(incre){
		$.post("../project/updateSearchYear", {"increYear" : incre, "_method" : "PUT"}, function (data){
			$("#preYear, #curYear, #nextYear").each(function(){
				$(this).find(".ui-pg-div").html(Number($(this).text()) + incre);
			});
			projectListGrid.trigger("reloadGrid");
		});
	}
		/*隐藏头部信息*/
		$("#menuFold").click(function(){
			if($(this).hasClass("menu_open")){
					$(".header").slideUp("fast"); 
	         		 parent.document.getElementById("frame_header").setAttribute("rows", "9px,*,28px");   
					$(this).attr("className","menu_close"); 				
				}
			else if($(this).hasClass("menu_close")){
					$(".header").slideDown("fast"); 
					parent.document.getElementById("frame_header").setAttribute("rows", "45px,*,28px");  
					$(this).attr("className","menu_open"); 
				}
			});
		/*面板可见性切换*/
		$(" .sidebar .main_panel h3").next().hide();
		
		$(".main_panel h3").click(function(){
			if($("div", this).hasClass("open_panel")){
					$(".main_panel h3").next().hide();
					$(".main_panel h3 div").attr("className","open_panel"); 
					$(this).next().show();
					$("div", this).attr("className","close_panel"); 
				}
			else if($("div", this).hasClass("close_panel")){
					$(".main_panel h3").next().hide();
					$(".main_panel h3 div").attr("className","open_panel"); 

				}
			});
		$(".sub_panel h4").click(function(){
			if($("div", this).hasClass("close_panel")){
					$(this).next().hide();
					$("div.close_panel", this).attr("className","open_panel"); 
				}
			else if($("div", this).hasClass("open_panel")){
					$(this).next().show();
					$("div.open_panel", this).attr("className","close_panel"); 
				}
			});
		/*导航菜单切换*/
		$(".open_menu ul").hide();
		$(".sidebar_menu li").click(function(event){
			if($(this).hasClass("open_menu")){
					$(this).siblings().each(function(){
						if($(this).children().is('ul')){
							$(this).find(">ul").hide();
							$(this).attr("className","open_menu"); 
						}
					});
					$(this).children().show();
					$(this).attr("className","close_menu"); 
				}
			else if($(this).hasClass("close_menu")){
					$(">ul",this).hide();
					$(this).attr("className","open_menu"); 
				}
			else{
				$(this).siblings().each(function(){
					if($(this).children().is('ul')){
						$(this).find(">ul").hide();
						$(this).attr("className","open_menu"); 
					}
				});
			}
			event.stopPropagation();
			});
			
		    $(".sidebar_sub_menu li").click(function(event){
			if($(this).has('ul').length > 0){
				event.stopPropagation();
			}
			else{
				$(".sidebar_sub_menu li").removeClass("current");
				$(this).addClass("current");
				event.stopPropagation();
			}
			});
		/*表格间隔行换色，随鼠标变色*/
		$(".table_normal tr").bind("mouseover mouseout",function(e){$(this).toggleClass("highlight");});
		//$(".table_normal tr:even").addClass("even"); 
		/*ie6下去横向滚动条*/	
		$(function(){
	   		if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height())
	       	$("html").css("overflowY","scroll");
		});
		$("#cancel").click(function(){
			history.back();
		});
		$(".tableBox").scroll(function(){
			$(".table_head table").css('margin-left',(-this.scrollLeft)+'px');
			this.scrollLeft;
		});
		$(":button, :submit, :reset").removeClass("btn btn1 btn2 btn3 btn4").button();
		
});

/*转义字符函数   & < >  / */
function escapeHTML(html)
{	
	html = html.replace(/&/g,'&amp;').replace(/>/g,'&gt;').replace(/</g,'&lt;').replace(/"/g,'&quot;');
	return html ;
}

/*反转义字符函数   & < >  / */
function unescapeHTML(html)
{	
	html = html.replace(/&amp;/g,'&').replace(/&gt;/g,'>').replace(/&lt;/g,'<').replace(/&quot;/g,'"');
	return html ;
}

function validateNotBlank(value){
	return !/^\s*$/.test(value);
}

function validateNumber(value){
	return /^-?\d+\.?\d*$/.test(value);
}

function validatePositiveNumber(value){
	return /^\d+\.?\d*$/.test(value);
}
