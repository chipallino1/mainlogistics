lengthRoute.oninput=function () {
	//if(countryFrom.value!="" && (regionFrom.value!="" || cityFrom!="") && countryTo!="" && (regionTo.value!="" || cityTo!="")){
		getCoordinates('https://maps.googleapis.com/maps/api/geocode/json?address='+cityFrom.value+','+regionFrom.value+','+countryFrom.value+'&key=AIzaSyBZdKOB5yggOzmMtpExsaUdXR1xYXDn__c');
	//}
}
function getCoordinates(action) {
	let xhr = new XMLHttpRequest();
    xhr.open("GET", action, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
        	console.log(xhr.responseText);
        }
    }
    xhr.send(null);

}