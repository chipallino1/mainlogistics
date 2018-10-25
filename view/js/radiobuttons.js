function radioClick(curr) {
	
	let getElem=document.getElementById('searchCont');
	deleteNodes('searchCont');
	if(document.getElementById('allCont').childNodes.length-1>1)
		deleteLastNode('allCont');
	if(curr.id=='defaultInline1')
	{			
		getElem.appendChild(createCol('name'));
		getElem.appendChild(createCol('city'));
		getElem.appendChild(createButton('Search'));
		getElem.appendChild(createButton('Show all'));

	}
	if(curr.id=='defaultInline2')
	{
		
		getElem.appendChild(createCol('username'));
		getElem.appendChild(createCol('firstname'));
		getElem.appendChild(createCol('lastname'));
		getElem.appendChild(createButton('Search'));
		getElem.appendChild(createButton('Show all'));

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