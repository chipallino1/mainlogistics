function radioClick(curr) {
	
	let getElem=document.getElementById('searchCont');
	deleteNodes('searchCont');
	deleteNodes('resultCol');
	if(document.getElementById('allCont').childNodes.length-1>1)
		deleteLastNode('allCont');
	if(curr.id=='defaultInline1')
	{			
		getElem.appendChild(createCol('name'));
		getElem.appendChild(createCol('city'));
		getElem.appendChild(createButton('Search'));
		getElem.appendChild(createButton('Show all'));
		getHelp('Firms');

	}
	if(curr.id=='defaultInline2')
	{
		
		getElem.appendChild(createCol('username'));
		getElem.appendChild(createCol('firstname'));
		getElem.appendChild(createCol('lastname'));
		getElem.appendChild(createButton('Search'));
		getElem.appendChild(createButton('Show all'));
		getHelp('Users');

	}
	if(curr.id=='defaultInline3')
	{
		getElem.appendChild(createCol('username'));
		getElem.appendChild(createCol('firstname'));
		getElem.appendChild(createCol('lastname'));
		getElem.appendChild(createCol('mark'));
		getElem.appendChild(createCol('stage'));

		let div=document.createElement('div');
		div.className='row justify-content-center';
		div.appendChild(createButton('Search'));
		div.appendChild(createButton('Search'));		
		allCont.appendChild(div);
		getHelp('Carriers');
		
	}

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
function deleteLastNode(id) {
	
	let myNode = document.getElementById(id);
	if(myNode==null)
	{
		return;
	}
	
   	 myNode.removeChild(myNode.lastChild);
	

}

function createCol(placeholder) {

	let input=document.createElement('input');
	input.className='form-control';
	input.placeholder=placeholder;
	let span=document.createElement('span');
	span.className='input-group-addon my-2 mr-1';
	span.appendChild(input);
	let div=document.createElement('div');
	div.className='col';
	div.appendChild(span);

	return div;
}
function createButton(text) {
	
	let button=document.createElement('button');
	button.className='w-100 btn btn-outline-success my-4';
	button.type='submit';
	button.innerHTML=text;
	let div=document.createElement('div');
	div.className='col';
	div.appendChild(button);

	return div;

}

function getHelp(type)
{
	let resultCol=document.getElementById('resultCol');
	
	if(type=='Firms')
	{
		resultCol.appendChild(createHead('You will get results about firms which registred on our resource.'));
		resultCol.appendChild(createParag('If you want to find info about users or carriers just choose it.'));
		resultCol.appendChild(createDivPad());
	}
	if(type=='Users')
	{
		resultCol.appendChild(createHead('You will get results about users which registred on our resource.'));
		resultCol.appendChild(createParag('If you want to find info about firms or carriers just choose it.'));
		resultCol.appendChild(createDivPad());
	}
	if(type=='Carriers')
	{
		resultCol.appendChild(createHead('You will get results about carriers which registred on our resource.'));
		resultCol.appendChild(createParag('If you want to find info about users or firms just choose it.'));
		resultCol.appendChild(createDivPad());
	}
}


function getResults() {
	let result=document.getElementById('resultCol');
	result.appendChild(createHead('Result'));
	result.appendChild(document.createElement('hr'));
	result.appendChild(createParag('Showing all results matching'));
	result.appendChild(createDivPad());
	result.appendChild(createResultsDiv());
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
	addResult(null,'Product 1','Its new product taht help me to test this page.',300);
}





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

function createResultsDiv() {
	
	

	let tbody=document.createElement('tbody');
	tbody.id='resultsCont';
	let table=document.createElement('table');
	table.className='table table-hover';
	table.appendChild(tbody);
	let div=document.createElement('div');
	div.className='table-responsive';
	div.appendChild(table);

	return div;


}

function addResult(imgSrc,product,description,price) {
	


	let td1=document.createElement('td');
	td1.className='number text-center';
	td1.appendChild(document.createTextNode(document.getElementById('resultsCont').childNodes.length+1));

	let td2=document.createElement('td');
	td2.className='image';
	let img=document.createElement('img');
	img.src='https://lorempixel.com/400/300/nature/1';
	td2.appendChild(img);

	let td3=document.createElement('td');
	td3.className='product';
	let strong=document.createElement('strong');
	strong.appendChild(document.createTextNode(product));
	let br=document.createElement('br');
	td3.appendChild(strong);
	td3.appendChild(br);
	td3.appendChild(document.createTextNode(description));

	let td5=document.createElement('td');
	td5.className='price text-right';
	td5.appendChild(document.createTextNode('$'+price));


 	let tr=document.createElement('tr');
 	tr.appendChild(td1);
 	tr.appendChild(td2);
 	tr.appendChild(td3);
 	tr.appendChild(td5);

 	let resultsCont=document.getElementById('resultsCont');
 	resultsCont.appendChild(tr);

}

