({
	appDir : "../",
	baseUrl : "js/",
	dir : "../../webapp-build",
	// Comment out the optimize line if you want
	// the code minified by UglifyJS
	// optimize: "none",

	paths : {
		"jquery" : "empty:"
	},

	modules : [
		{name : "dispatch/list"},
		{name : "dispatch/edit"} 
	]
})
