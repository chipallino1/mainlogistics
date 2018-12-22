


function makeEnabled(){
	firstNameContact.disabled="";
	lastNameContact.disabled="";
	emailContact.disabled="";
	phoneNumContact.disabled="";
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
