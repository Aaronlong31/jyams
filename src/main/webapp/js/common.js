function getContextPath(){
	return "/jyams";
}

function getSearchYear(){
	var searchYear;
	$.ajax({
		url : contextPath + "/project/searchYear",
		success : function(data){
			searchYear = data;
		},
		dataType : "json"
	});
	return searchYear;
}