function loginPage() {
	
	let header=createHeader():
	let divCard=createDiv('card');
	divCard.appendChild();
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
function createA(className,innerHTML,href) {
	let a=document.createElement('a');
	a.className=className;
	a.innerHTML=innerHTML;
	a.href=href;

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
	article.innerHTML=innerHTML;

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