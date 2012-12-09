
$(document).ready(function() { 
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
