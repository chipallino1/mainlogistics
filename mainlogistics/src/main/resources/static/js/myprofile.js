function makeEnabled(){
	firstNameContact.disabled="";
	lastNameContact.disabled="";
	emailContact.disabled="";
	phoneNumContact.disabled="";
	firmNameContact.disabled="";
	firmNameFirm.disabled="";
	emailFirm.disabled="";
	descriptionFirm.disabled="";
}
function makeDisabled() {
	firstNameContact.disabled="disabled";
	lastNameContact.disabled="disabled";
	emailContact.disabled="disabled";
	phoneNumContact.disabled="disabled";
	firmNameContact.disabled="disabled";
	firmNameFirm.disabled="disabled";
	emailFirm.disabled="disabled";
	descriptionFirm.disabled="disabled";
}
function sendUpdated() {
	makeDisabled();
	submitForm.submit();
}