

function hide() {
	let signup=document.getElementById('signup');	
	let login=document.getElementById('login');



	if(signup.style.display==""){
		signup.style.display="none";

		login.style.display="";
	}
	else{
		signup.style.display="";
		login.style.display="none";
	}
}

function loginPage() {
	
	deleteNodes('enter');
	let buttonSub=createButton('submit','btn btn-primary btn-block','Log in');

	let divSubmit=createDiv('form-group');
	divSubmit.appendChild(buttonSub);

	let inputPass=createInput('password','form-control','password');
	let labelPass=createLabel('Password');
	let divPass=createDiv('form-group');
	divPass.appendChild(labelPass);
	divPass.appendChild(inputPass);

	let smallEmail=createSmall('form-text text-muted',"We'll never share your email with anyone else.");
	let inputEmail=createInput('text','form-control','email');
	let labelEmail=createLabel('Email address');
	let divEmail=createDiv('form-group');
	divEmail.appendChild(labelEmail);
	divEmail.appendChild(inputEmail);
	divEmail.appendChild(smallEmail);

	let form=createForm();
	form.appendChild(divEmail);
	form.appendChild(divPass);
	form.appendChild(divSubmit);
	let article=createArticle('card-body');
	article.appendChild(form);
	let h4=createH4('card-title mt-2','Log in');
	let a=createA('float-right btn btn-outline-primary mt-1','Sign Up',signupPage);
	let header=createHeader('card-header');
	header.appendChild(a);
	header.appendChild(h4);
	let divCard=createDiv('card');
	divCard.appendChild(header);
	divCard.appendChild(article);
	let divCol=createDiv('col-md-6');
	divCol.appendChild(divCard);
	let cont=document.getElementById('enter');
	cont.appendChild(divCol);

}

function signupPage() {
	deleteNodes('enter');
	let buttonSub=createButton('submit','btn btn-primary btn-block','Register');

	let divSubmit=createDiv('form-group');
	divSubmit.appendChild(buttonSub);

	let inputPass=createInput('password','form-control','password');
	let labelPass=createLabel('Password');
	let divPass=createDiv('form-group');
	divPass.appendChild(labelPass);
	divPass.appendChild(inputPass);

	let smallEmail=createSmall('form-text text-muted',"We'll never share your email with anyone else.");
	let inputEmail=createInput('text','form-control','email');
	let labelEmail=createLabel('Email address');
	let divEmail=createDiv('form-group');
	divEmail.appendChild(labelEmail);
	divEmail.appendChild(inputEmail);
	divEmail.appendChild(smallEmail);

	let inputLname=createInput('text','form-control','lastname');
	let labelLname=createLabel('First name');
	let divLname=createDiv('col form-group');
	divLname.appendChild(labelLname);
	divLname.appendChild(inputLname);

	let inputFname=createInput('text','form-control','firstname');
	let labelFname=createLabel('First name');
	let divFname=createDiv('col form-group');
	divFname.appendChild(labelFname);
	divFname.appendChild(inputFname);

	let divFLnames=createDiv('form-row');
	divFLnames.appendChild(divFname);
	divFLnames.appendChild(divLname);

	let form=createForm();
	form.appendChild(divFLnames);
	form.appendChild(divEmail);
	form.appendChild(divPass);
	form.appendChild(divSubmit);
	let article=createArticle('card-body');
	article.appendChild(form);
	let h4=createH4('card-title mt-2','Log in');
	let a=createA('float-right btn btn-outline-primary mt-1','Sign Up',loginPage);
	let header=createHeader('card-header');
	header.appendChild(a);
	header.appendChild(h4);
	let divCard=createDiv('card');
	divCard.appendChild(header);
	divCard.appendChild(article);
	let divCol=createDiv('col-md-6');
	divCol.appendChild(divCard);
	let cont=document.getElementById('enter');
	cont.appendChild(divCol);
}

function createDiv(className) {

	let div=document.createElement('div');
	div.className=className;
	return div;

}
function createHeader(className) {
	
	let header=document.createElement('header');
	header.className=className;
	return header;

}
function createA(className,innerHTML,onclick) {
	let a=document.createElement('a');
	a.className=className;
	a.innerHTML=innerHTML;
	a.style.color='#007bff';
	a.addEventListener('click',onclick);

	return a;
}
function createH4(className,innerHTML) {
	let h4=document.createElement('h4');
	h4.className=className;
	h4.innerHTML=innerHTML;

	return h4;
}
function createArticle(className) {

	let article=document.createElement('article');
	article.className=className;

	return article;

}
function createForm()
{
	let form=document.createElement('form');

	return form;
}
function createLabel(innerHTML) {
	let label=document.createElement('label');
	label.innerHTML=innerHTML;
	return label;
}
function createInput(type,className,placeholder){
	let input=document.createElement('input');
	input.type=type;
	input.className=className;
	input.placeholder=placeholder;
	return input;
}
function createSmall(className,innerHTML) {
	let small=document.createElement('small');
	small.className=className;
	small.innerHTML=innerHTML;
	return small;
}
function createButton(type,className,innerHTML) {
	let button=document.createElement('button');
	button.type=type;
	button.className=className;
	button.innerHTML=innerHTML;
	return button;
}
function deleteNodes(id) {
	
	let myNode = document.getElementById(id);
	if(myNode==null)
	{
		return;
	}
	while (myNode.firstChild) {
   	 myNode.removeChild(myNode.firstChild);
	}

}