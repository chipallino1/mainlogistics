


function makeEnabled(){
	firstNameContact.disabled="";
	lastNameContact.disabled="";
	emailContact.disabled="";
	phoneNumContact.disabled="";
	contactImage.disabled="";
	firmNameContact.disabled="";
	firmImage.disabled="";
	//myInput.disabled="";
	firmNameFirm.disabled="";
	emailFirm.disabled="";
	descriptionFirm.disabled="";
}
function makeFirmEnabled(){
    firmImage.disabled="";
    //myInput.disabled="";
    firmNameFirm.disabled="";
    emailFirm.disabled="";
    descriptionFirm.disabled="";
}
function makeDisabled() {
	firstNameContact.disabled="disabled";
	lastNameContact.disabled="disabled";
	emailContact.disabled="disabled";
	phoneNumContact.disabled="disabled";
	myInput.disabled="disabled";
	firmNameFirm.disabled="disabled";
	emailFirm.disabled="disabled";
	descriptionFirm.disabled="disabled";
}
function showContacts() {
	//getContacts.setAttribute('action','/profile/'+firmNameFirm.value+'/contactslist');
	//getContacts.submit();
	
}
function makeEnabledElemsInCont(elem) {
	console.log(elem.id+' '+elem.disabled);
	for(let i=0;i<elem.childNodes.length;i++){
		if(elem.childNodes[i].disabled){
            elem.childNodes[i].disabled='';
            console.log('hui');
		}
		else{
			makeEnabledElemsInCont(elem.childNodes[i]);
		}
	}
}
function sortBy(type,id) {
	//let separateNames = getSeparateNames(id);
	if(type=='firstName'){

		let firstNames=document.getElementsByName('firstName');
		let firstNamesArr=[];
		console.log(firstNames);
		for(let i=1;i<firstNames.length;i++){
			 firstNamesArr.push(firstNames[i].innerHTML);
		}
		firstNamesArr.sort();
		console.log(firstNamesArr);
	}
	else{
		
		let lastNames=document.getElementsByName('lastName');
		let lastNamesArr=[];
		console.log(lastNames);
		for(let i=1;i<lastNames.length;i++){
			 lastNamesArr.push(lastNames[i].innerHTML);
		}
		lastNamesArr.sort();
		console.log(lastNamesArr);
	}


}
function getSeparateNames(id) {
    let elems=document.getElementsByName(id);
    console.log(elems);
    let firstNames=[];
    let lastNames=[];
    let currValue;
    for(let i=0;i<elems.length;i++){
        currValue=elems[i].innerHTML;
        firstNames.push(currValue.substr(0,currValue.lastIndexOf(' ')));
        lastNames.push(currValue.substr(currValue.lastIndexOf(' ')+1));
    }
    console.log(firstNames);
    console.log(lastNames);
    let separateNames={firstNames:firstNames,lastNames:lastNames};
	return separateNames;
}

function get(action) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", action, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            arr=[];
            console.log(xhr.responseText);
            arr=JSON.parse(xhr.responseText);
        }
    }
    xhr.send(null);

}
function createRouteSubmit() {
	let dateTimeStart=startDateTime.value;
	let dateTimeFinish=finishDateTime.value;
	let dateStart=new Date(dateTimeStart);
    let dateFinish=new Date(dateTimeFinish);
    startDateTime.value=dateStart.toIsoString();
    finishDateTime.value=dateFinish.toIsoString();
    console.log(startDateTime.value);
    console.log(finishDateTime.value);
    let body={
        countryFrom:countryFrom.value,
        regionFrom:regionFrom.value,
        cityFrom:cityFrom.value,
        countryTo: countryTo.value,
        regionTo:regionTo.value,
        cityTo:cityTo.value,
        carName:carName.value,
        dateStart:startDateTime.value,
        dateFinish:finishDateTime.value,
        volume:volume.value,
        length:lengthRoute.value,
        duration:durationRoute.value,
        cost:cost.value
    };
    console.log(body);
    post('/routes/create',body);
}

function post(action,body) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", action, true);
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    xhr.setRequestHeader(csrfHeader,csrfToken);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            console.log(xhr.responseText);
            console.log('route created');
        }
    }
    xhr.send(JSON.stringify(body));
}

Date.prototype.toIsoString = function() {
    var tzo = -this.getTimezoneOffset(),
        dif = tzo >= 0 ? '+' : '-',
        pad = function(num) {
            var norm = Math.floor(Math.abs(num));
            return (norm < 10 ? '0' : '') + norm;
        };
    return this.getFullYear() +
        '-' + pad(this.getMonth() + 1) +
        '-' + pad(this.getDate()) +
        'T' + pad(this.getHours()) +
        ':' + pad(this.getMinutes()) +
        ':' + pad(this.getSeconds()) +
        dif + pad(tzo / 60)
         + pad(tzo % 60);
}

var dt = new Date();
console.log(dt.toIsoString());
