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
		let input1=document.createElement('input');
		input1.className='form-control';
		input1.placeholder='username';
		let span1=document.createElement('span');
		span1.className='w-100 input-group-addon my-2 mr-1';
		span1.appendChild(input1);
		let div1=document.createElement('div');
		div1.className='col';
		div1.appendChild(span1);

		let input2=document.createElement('input');
		input2.className='form-control';
		input2.placeholder='firstname';
		let span2=document.createElement('span');
		span2.className='input-group-addon my-2 mr-1';
		span2.appendChild(input2);
		let div2=document.createElement('div');
		div2.className='col';
		div2.appendChild(span2);

		let input3=document.createElement('input');
		input3.className='form-control';
		input3.placeholder='lastname';
		let span3=document.createElement('span');
		span3.className='input-group-addon my-2 mr-1';
		span3.appendChild(input3);
		let div3=document.createElement('div');
		div3.className='col';
		div3.appendChild(span3);

		let button1=document.createElement('button');
		button1.className='w-100 btn btn-outline-success my-4';
		button1.type='submit';
		button1.innerHTML='Search'
		let div4=document.createElement('div');
		div4.className='col';
		div4.appendChild(button1);

		let button2=document.createElement('button');
		button2.className='w-100 btn btn-outline-success my-4';
		button2.type='submit';
		button2.innerHTML='Show all'
		let div5=document.createElement('div');
		div5.className='col';
		div5.appendChild(button2);

		getElem.appendChild(div1);
		getElem.appendChild(div2);
		getElem.appendChild(div3);
		getElem.appendChild(div4);
		getElem.appendChild(div5);


	}
	if(curr.id=='defaultInline3')
	{
		
		let input1=document.createElement('input');
		input1.className='form-control';
		input1.placeholder='username';
		let span1=document.createElement('span');
		span1.className='w-100 input-group-addon my-2 mr-1';
		span1.appendChild(input1);
		let div1=document.createElement('div');
		div1.className='col';
		div1.appendChild(span1);

		let input2=document.createElement('input');
		input2.className='form-control';
		input2.placeholder='firstname';
		let span2=document.createElement('span');
		span2.className='input-group-addon my-2 mr-1';
		span2.appendChild(input2);
		let div2=document.createElement('div');
		div2.className='col';
		div2.appendChild(span2);

		let input3=document.createElement('input');
		input3.className='form-control';
		input3.placeholder='lastname';
		let span3=document.createElement('span');
		span3.className='input-group-addon my-2 mr-1';
		span3.appendChild(input3);
		let div3=document.createElement('div');
		div3.className='col';
		div3.appendChild(span3);

		let inputMark=document.createElement('input');
		inputMark.className='form-control';
		inputMark.placeholder='mark';
		let spanMark=document.createElement('span');
		spanMark.className='input-group-addon my-2 mr-1';
		spanMark.appendChild(inputMark);
		let divMark=document.createElement('div');
		divMark.className='col';
		divMark.appendChild(spanMark);

		let inputStage=document.createElement('input');
		inputStage.className='form-control';
		inputStage.placeholder='mark';
		let spanStage=document.createElement('span');
		spanStage.className='input-group-addon my-2 mr-1';
		spanStage.appendChild(inputStage);
		let divStage=document.createElement('div');
		divStage.className='col';
		divStage.appendChild(spanStage);


		let button1=document.createElement('button');
		button1.className='w-100 btn btn-outline-success my-4';
		button1.type='submit';
		button1.innerHTML='Search'
		let div4=document.createElement('div');
		div4.className='col';
		div4.appendChild(button1);

		let button2=document.createElement('button');
		button2.className='w-100 btn btn-outline-success my-4';
		button2.type='submit';
		button2.innerHTML='Show all'
		let div5=document.createElement('div');
		div5.className='col';
		div5.appendChild(button2);

		let div6=document.createElement('div');
		div6.className='row justify-content-center';
		div6.appendChild(div4);
		div6.appendChild(div5);

		getElem.appendChild(div1);
		getElem.appendChild(div2);
		getElem.appendChild(div3);
		getElem.appendChild(divMark);
		getElem.appendChild(divStage);
		allCont.appendChild(div6);
		
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