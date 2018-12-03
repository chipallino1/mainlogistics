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