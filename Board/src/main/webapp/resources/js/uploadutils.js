/**
 * 
 */
function getOriginalLink(data) {
	if(checkImageType(data)){
		var prefix = data.substring(0, 12);
		var suffix = data.substring(14);
		return prefix + suffix;
	}else{
		return data;
	}
	
}

function getOriginalName(data) {
	var idx = data.lastIndexOf("_") + 1;
	return data.substring(idx);
}

function checkImageType(data) {
	var pattern = /jpg|png|jpeg|gif/i;
	return data.match(pattern);
}