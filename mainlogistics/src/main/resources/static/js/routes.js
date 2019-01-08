var origin1;
var destinationA ;

var originTest = 'Minsk,, Belarus';
var destinationTest = 'Munich,, Germany';

var service = new google.maps.DistanceMatrixService();


function callback(response, status) {
	console.log(response);
    let distance=response.rows[0].elements[0];
    console.log(distance);
    console.log(distance.distance.text);
    console.log(distance.duration.text);
    labelLength.innerHTML='Length '+distance.distance.text;
    labelDuration.innerHTML='Duration '+distance.duration.text;
    lengthRoute.value=distance.distance.value;
    durationRoute.value=distance.duration.value;


    // See Parsing the Results for
    // the basics of a callback function.
}
lengthRoute.onfocus=function () {
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

function deleteRoute() {
    routeForm.action="/routes/delete";
    routeForm.submit();
}

function makeOrderRoute() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/routes/order", true);
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    xhr.setRequestHeader(csrfHeader,csrfToken);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            console.log(xhr.responseText);
            if(xhr.responseText=='true')
                alert('Route ordered');
            else
                alert('Route not ordered');
        }
    }
    xhr.send(JSON.stringify({routeId:routeId.value,yourCapacity:yourCapacity.value,yourVolume:yourVolume.value}));
}