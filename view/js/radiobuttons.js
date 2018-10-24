function radioClick(curr) {
	
	deleteNodes('searchCont');
	if(curr.id=='defaultInline1')
	{
		let input1=document.createElement('input');
		input1.className='form-control';
		input1.placeholder='name';
		let span1=document.createElement('span');
		span1.className='input-group-addon my-2 mr-1';
		span1.appendChild(input1);
		let div1=document.createElement('div');
		div1.className='col';
		div1.appendChild(span1);

		let getElem=document.getElementById('searchCont');
		getElem.appendChild(div1);

		let input2=document.createElement('input');
		input2.className='form-control';
		input2.placeholder='city';
		let span2=document.createElement('span');
		span2.className='input-group-addon my-2 mr-1';
		span2.appendChild(input2);
		let div2=document.createElement('div');
		div2.className='col';
		div2.appendChild(span2);

		
		getElem.appendChild(div2);

		let button1=document.createElement('button');
		button1.className='w-100 btn btn-outline-success my-4';
		button1.type='submit';
		button1.innerHTML='Search'
		let div3=document.createElement('div');
		div3.className='col';
		div3.appendChild(button1);

		let button2=document.createElement('button');
		button2.className='w-100 btn btn-outline-success my-4';
		button2.type='submit';
		button2.innerHTML='Show all'
		let div4=document.createElement('div');
		div4.className='col';
		div4.appendChild(button2);


		getElem.appendChild(div3);
		getElem.appendChild(div4);

	}
	if(curr.id=='defaultInline2')
	{
		let getElem=document.getElementById('searchCont');
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

}
function deleteNodes(id) {
	
	let myNode = document.getElementById(id);
	while (myNode.firstChild) {
   	 myNode.removeChild(myNode.firstChild);
	}

}