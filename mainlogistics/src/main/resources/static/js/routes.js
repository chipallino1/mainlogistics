var origin1;
var destinationA ;

var originTest = 'Minsk,, Belarus';
var destinationTest = 'Munich,, Germany';

var service = new google.maps.DistanceMatrixService();


function callback(response, status) {
	console.log(response);
    // See Parsing the Results for
    // the basics of a callback function.
}
lengthRoute.oninput=function () {
	if(countryFrom.value!="" &&  cityFrom.value!="" && countryTo.value!="" &&  cityTo.value!=""){
        origin1 = countryFrom.value+','+regionFrom.value+','+cityFrom.value;
        destinationA = countryTo.value+','+regionTo.value+','+cityTo.value;
        service.getDistanceMatrix(
            {
                origins: [origin1],
                destinations: [destinationA],
                travelMode: 'DRIVING'
            }, callback);
		//getCoordinates('https://maps.googleapis.com/maps/api/geocode/json?address='+cityFrom.value+','+regionFrom.value+','+countryFrom.value+'&key=AIzaSyBZdKOB5yggOzmMtpExsaUdXR1xYXDn__c');
        //getCoordinates('https://maps.googleapis.com/maps/api/geocode/json?address='+cityTo.value+','+regionTo.value+','+countryTo.value+'&key=AIzaSyBZdKOB5yggOzmMtpExsaUdXR1xYXDn__c');
	}
}
let from;
let to;
function getCoordinates(action) {
	let xhr = new XMLHttpRequest();
    xhr.open("GET", action, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
        	console.log(xhr.responseText);
        	let response = JSON.parse(xhr.responseText);
        	console.log(response.results);
            console.log(response.results[0].geometry);
            console.log(response.results[0].geometry.location);
            if(from==null)
             	from = response.results[0].geometry.location.lat+','+response.results[0].geometry.location.lng;
            else{
                to = response.results[0].geometry.location.lat+','+response.results[0].geometry.location.lng;
                getDistance(from,to);
			}
        }
    }
    xhr.send(null);

}

function getDistance(from,to) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+from+"&destinations="+to+"&key=AIzaSyBZdKOB5yggOzmMtpExsaUdXR1xYXDn__c", true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            console.log(xhr.responseText);
            let distance=JSON.parse(xhr.responseText).distance;
            console.log(distance);
        }
    }
    xhr.send(null);
}