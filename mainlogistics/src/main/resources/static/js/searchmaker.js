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
		getElem.appendChild(createButton('Search',getResults));
		getElem.appendChild(createButton('Show all',getResults));
		getHelp('Firms');

	}
	if(curr.id=='defaultInline2')
	{
		
		getElem.appendChild(createCol('username'));
		getElem.appendChild(createCol('firstname'));
		getElem.appendChild(createCol('lastname'));
		getElem.appendChild(createButton('Search',getResults));
		getElem.appendChild(createButton('Show all',getResults));
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
		div.appendChild(createButton('Search',getResults));
		div.appendChild(createButton('Show all',getResults));		
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
function createButton(text,handler) {
	
	let button=document.createElement('button');
	button.className='w-100 btn btn-outline-success my-4';
	button.type='submit';
	button.innerHTML=text;
	let div=document.createElement('div');
	div.className='col';
	div.appendChild(button);

	div.addEventListener('click',handler);

	return div;

}

function getHelp(type)
{
	let resultCol=document.getElementById('resultCol');
	
	if(type=='Firms')
	{
		resultCol.appendChild(createHead('You will get results about firms which registred on our resource.'));
		resultCol.appendChild(createParag('If you want to find info about users or carriers just choose it.'));
		resultCol.appendChild(document.createElement('hr'));
		resultCol.appendChild(createDivPad());
	}
	if(type=='Users')
	{
		resultCol.appendChild(createHead('You will get results about users which registred on our resource.'));
		resultCol.appendChild(createParag('If you want to find info about firms or carriers just choose it.'));
		resultCol.appendChild(document.createElement('hr'));
		resultCol.appendChild(createDivPad());
	}
	if(type=='Carriers')
	{
		resultCol.appendChild(createHead('You will get results about carriers which registred on our resource.'));
		resultCol.appendChild(createParag('If you want to find info about users or firms just choose it.'));
		resultCol.appendChild(document.createElement('hr'));
		resultCol.appendChild(createDivPad());
	}
}


function getResults(arr) {
	deleteNodes('resultCol');
	let result=document.getElementById('resultCol');
	result.appendChild(createHead('Contacts list'));
	result.appendChild(document.createElement('hr'));
	result.appendChild(createParag('You can see each person who works here.'));
	result.appendChild(createDivPad());
	result.appendChild(createResultsDiv());
	for(let i=0;i<arr.length;i++){
		addResult(null,arr[i].firstName+' '+arr[i].lastName,arr[i].email,'');
	}

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

	//let td2=document.createElement('td');
	//td2.className='image';
	//let img=document.createElement('img');
	//img.src='https://lorempixel.com/400/300/nature/1';
	//td2.appendChild(img);

	let td3=document.createElement('td');
	td3.className='product';
	let strong=document.createElement('strong');
	strong.appendChild(document.createTextNode(product));
	let br=document.createElement('br');
	td3.appendChild(strong);
	td3.appendChild(br);
	td3.name='email';
	td3.appendChild(document.createTextNode(description));
	let form=document.createElement('form');
	td3.appendChild(form);

	let td5=document.createElement('td');
	td5.className='price text-right';
	let butt=document.createElement('button');
	butt.className='btn btn-primary btn-block';
	butt.type='button';
	butt.id='deleteButton';
	butt.addEventListener('click',deleteContact);
	butt.appendChild(document.createTextNode("--"));
	td5.appendChild(butt);


 	let tr=document.createElement('tr');
 	tr.appendChild(td1);
 	//tr.appendChild(td2);
 	tr.appendChild(td3);
 	tr.appendChild(td5);
 	tr.setAttribute('email',description);
 	tr.addEventListener('click',getCurrenctContactPage);

 	let resultsCont=document.getElementById('resultsCont');
 	resultsCont.appendChild(tr);

}

function getContacts(firmName) {
	let xhr = new XMLHttpRequest();
    xhr.open("GET", '/firm/contacts/readall/'+firmName, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
          arr=[];
          arr=JSON.parse(xhr.responseText);
          getResults(arr);
        }
      }
      xhr.send(null); 
}
function getCurrenctContactPage(e) {
	console.log(e.target.parentNode);
	let elem=e.target.parentNode;
	if(elem.getAttribute('email')!=null){
		console.log(elem.childNodes[1].childNodes[3]);
		elem.childNodes[1].childNodes[3].action='/profile/contact/'+elem.getAttribute('email');
		elem.childNodes[1].childNodes[3].submit();
	}
	else{
		if(e.target.id=='deleteButton'){
			return;
		}
		elem=elem.parentNode;
		console.log(elem.childNodes[1].childNodes[3]);
		elem.childNodes[1].childNodes[3].action='/profile/contact/'+elem.getAttribute('email');
		elem.childNodes[1].childNodes[3].submit();

	}
	
}
function deleteContact(e) {
	console.log(e.target);
	let xhr = new XMLHttpRequest();
    xhr.open("POST", '/firm/contacts/delete', true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
          arr=[];
          arr=JSON.parse(xhr.responseText);
          console.log(xhr.responseText);
          alert(arr[0].message);
        }
      }
     console.log( e.target.parentNode.parentNode.childNodes[1].childNodes[2]);
     let body = JSON.stringify({firmName:firmNameFirm.value,email:e.target.parentNode.parentNode.getAttribute('email')});
     console.log(body);
      xhr.send(body); 
}