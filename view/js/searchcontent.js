let result=document.getElementById('resultCol');
result.appendChild(createHead('Result'));
result.appendChild(document.createElement('hr'));
result.appendChild(createParag('Showing all results matching'));
result.appendChild(createDivPad());


function createHead(text) {
	
	let i = document.createElement('i');
	i.className='fa fa-file-o';
	let h2 = document.createElement('h2');
	h2.appendChild(i);
	h2.appendChild(document.createTextNode(text));
	return h2;


}

function createParag(text) {

	let p=document.createElement('p');
	p.appendChild(document.createTextNode(text));
	return p;
}

function createDivPad() {

	let divPad=document.createElement('div');
	divPad.className='padding';
	return divPad;

}