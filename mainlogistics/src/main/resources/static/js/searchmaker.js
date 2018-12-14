var isDesc=false;
var orderBy="ModifiedTime";
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


function createSearchSorting(){
	let divCont=document.createElement('div');
	divCont.className='container';

	let divForm1=document.createElement('div');
	divForm1.className='form-check';

	let inp1=document.createElement('input');
	inp1.type='checkbox';
	inp1.className='form-check-input';
	inp1.id='checkFirstQueue';
	inp1.setAttribute('onclick',"sortBy('firstName','/contacts/sort/')");

	let label1=document.createElement('label');
	label1.className='form-check-label';
	label1.for='checkFirstQueue';

	divForm1.appendChild(inp1);
	divForm1.appendChild(label1);

	let inp2=document.createElement('input');
	inp2.type='checkbox';
	inp2.className='form-check-input';
	inp2.id='checkLastQueue';
	inp2.setAttribute('onclick',"sortBy('lastName','/contacts/sort/')");

	let label2=document.createElement('label');
	label2.className='form-check-label';
	label2.for='checkLastQueue';

	let divForm2=document.createElement('div');
	divForm2.className='form-check';

	divForm2.appendChild(inp2);
	divForm2.appendChild(label2);

	divCont.appendChild(divForm1);
	divCont.appendChild(divForm2);
	return divCont;
}

function getResults(arr,params) {
	let pageNumber=arr.pageNumber;
	let pageCount=arr.pageCount;
	arr=arr.listEntitiesDTO;
	let result=document.getElementById(params.id);
	if(document.getElementById(params.resultId)!=null)
		deleteNodes(params.resultId);
	else{
		result.appendChild(createResultsDiv(params.resultId));
		if(pageCount>0){
			result.appendChild(createDivPad());
			result.appendChild(createPageNums(pageCount,params.state,pageNumber));
		}
	}
	for(let i=0;i<arr.length;i++){
		addResult(null,arr[i].firstName,arr[i].lastName,arr[i].email,'',params.resultId,params.state,pageNumber,i);
	}

}


function createButtonBlue(id,text,state) {
	let butt=document.createElement('button');
	butt.className='btn btn-primary btn-block';
	butt.type='button';
	butt.id=id+state;
	butt.setAttribute('state',state);
	butt.addEventListener('click',getPage);
	butt.appendChild(document.createTextNode(text));
	return butt;
}

function getPage(e) {
	let innerText = e.target.innerHTML;
	let body;
	let cbParams;
	state=e.target.getAttribute('state');
	body={firmName:firmNameFirm.value,state:e.target.getAttribute('state'),orderBy:orderBy,desc:isDesc};
	body=JSON.stringify(body);
	let currP=document.getElementById('currPage'+state);
	let firstP=document.getElementById('firstPage'+state);
	let lastP=document.getElementById('lastPage'+state);
	let prevP=document.getElementById('prevPage'+state);
	let nextP=document.getElementById('nextPage'+state);
	if(e.target.getAttribute('state')=='WAIT')
		cbParams={id:'resultColQueue',resultId:'resultContQueue',state:e.target.getAttribute('state')};
	else
		cbParams={id:'resultCol',resultId:'resultCont',state:e.target.getAttribute('state')};

	if(e.target.id=='prevPage'+state && (Number(currP.innerHTML)*1-1)>0){	
		console.log('prevPage'+state);
		currP.innerHTML=Number(currP.innerHTML)-1;
		let prev=Number(currP.innerHTML)-1;
		post(body,'/contacts/readall?page='+prev,getResults,cbParams);
		return;
	}
	if(e.target.id=='nextPage'+state && (Number(currP.innerHTML)*1+1)<=Number(lastP.innerHTML)){
		console.log('nextPage'+state);
		currP.innerHTML=Number(currP.innerHTML)+1;
		let next=Number(currP.innerHTML)-1;
		post(body,'/contacts/readall?page='+next,getResults,cbParams);
		return;
	}
	if(e.target.id=='firstPage'+state){
		console.log('firstPage'+state);
		if(currP!=null)
			currP.innerHTML=firstP.innerHTML;
		post(body,'/contacts/readall?page='+0,getResults,cbParams);
		return;
	}
	if(e.target.id=='lastPage'+state){
		console.log('lastPage'+state);
		let last;
		if(currP!=null){
			currP.innerHTML=lastP.innerHTML;
			last=Number(currP.innerHTML)-1;
			
		}
		else{
			last=Number(lastP.innerHTML)-1;
		}
		post(body,'/contacts/readall?page='+last,getResults,cbParams);
		return;
	}

}

function createPageNums(pageCount,state,pageNumber) {
	console.log('Page count: '+pageCount);
	let divCont=document.createElement('div');
	divCont.className='form-row';
	if(pageCount>2)
		divCont.appendChild(createDivClassName('col form-group',createButtonBlue('prevPage','<<',state)));
	divCont.appendChild(createDivClassName('col form-group',createButtonBlue('firstPage','1',state)));
	if(pageCount>2){
		
		divCont.appendChild(createDivClassName('col form-group',createButtonBlue('currPage',pageNumber+1,state)));
		
	}
	if(pageCount>1)
		divCont.appendChild(createDivClassName('col form-group',createButtonBlue('lastPage',pageCount,state)));
	if(pageCount>2)
		divCont.appendChild(createDivClassName('col form-group',createButtonBlue('nextPage','>>',state)));
	
	return divCont;
}

function createDivClassName(className,elem) {
	let div = document.createElement('div');
	div.className=className;
	if(elem!=undefined)
		div.appendChild(elem);
	return div;
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

function createResultsDiv(resultId) {
	
	

	let tbody=document.createElement('tbody');
	tbody.id=resultId;
	let table=document.createElement('table');
	table.className='table table-hover';
	table.appendChild(tbody);
	let div=document.createElement('div');
	div.className='table-responsive';
	div.appendChild(table);

	return div;


}

function addResult(imgSrc,firstName,lastName,email,price,resultId,isWait,pageNumber,currNum) {
	


	let td1=document.createElement('td');
	td1.className='number text-center';
	//console.log(document.getElementById(resultId).childNodes.length);
	//console.log('page num: '+pageNumber);
	td1.appendChild(document.createTextNode(/*document.getElementById(resultId).childNodes.length*/5*pageNumber+currNum+1));
	//let td2=document.createElement('td');
	//td2.className='image';
	//let img=document.createElement('img');
	//img.src='https://lorempixel.com/400/300/nature/1';
	//td2.appendChild(img);

	let td3=document.createElement('td');
	td3.className='product';
	let strong=document.createElement('strong');
	strong.setAttribute('name','fullName');
	let spanFirst=document.createElement('span');
	spanFirst.setAttribute('name','firstName');
	spanFirst.appendChild(document.createTextNode(firstName));
	let spanSpace=document.createElement('span');
	spanSpace.appendChild(document.createTextNode(' '));
	let spanLast=document.createElement('span');
	spanLast.setAttribute('name','lastName');
	spanLast.appendChild(document.createTextNode(lastName));
	strong.appendChild(spanFirst);
	strong.appendChild(spanSpace);
	strong.appendChild(spanLast);
	let br=document.createElement('br');
	td3.appendChild(strong);
	td3.appendChild(br);
	td3.setAttribute('name','email');
	td3.appendChild(document.createTextNode(email));
	let form=document.createElement('form');
	td3.appendChild(form);

	let td5=document.createElement('td');
	td5.className='price text-right';
	let path = window.location.pathname;
	let profile = path.substr(path.lastIndexOf('/')+1)
	console.log(profile);
	if(profile =='me')
	{
		if(isWait=='ADDED')
		{
			let butt=document.createElement('button');
			butt.className='btn btn-primary btn-block';
			butt.type='button';
			butt.id='deleteButton';
			butt.addEventListener('click',deleteContact);
			butt.appendChild(document.createTextNode("--"));
			td5.appendChild(butt);
		}
		else
		{
			let butt=document.createElement('button');
			butt.className='btn btn-primary btn-block';
			butt.type='button';
			butt.id='addButton';
			butt.addEventListener('click',addContact);
			butt.appendChild(document.createTextNode("++"));
			td5.appendChild(butt);
		}
	}
	
	


 	let tr=document.createElement('tr');
 	tr.appendChild(td1);
 	//tr.appendChild(td2);
 	tr.appendChild(td3);
 	tr.appendChild(td5);
 	tr.setAttribute('email',email);
 	tr.addEventListener('click',getCurrenctContactPage);

 	let resultsCont=document.getElementById(resultId);
 	resultsCont.appendChild(tr);

}

function getContactsGet(firmName,status,id,resultId,page,value) {
	let xhr = new XMLHttpRequest();
    xhr.open("GET", '/contacts/readall/'+firmName+'/'+status+'?page='+page+'&value='+value, true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
          arr=[];
          console.log(xhr.responseText);
          arr=JSON.parse(xhr.responseText);
          if(arr.length==0)
          	return;
          if(arr.listEntitiesDTO[0].contactState=='WAIT')
          	getResults(arr.listEntitiesDTO,id,resultId,'Contacts wait list','They wait for your decision');
          else
          	getResults(arr.listEntitiesDTO,id,resultId,'Contacts list','Here you can see all contacts');
        }
      }
      xhr.send(null); 
}
function getContacts(curr,firmName,state,id,resultId,page,value) {

	let body;
	let cbParams={id:id,resultId:resultId,state:state};
	if(curr==null){
		body={firmName:firmName,state:state,page:page,orderBy:null,desc:null};
		body=JSON.stringify(body);
		post(body,'/contacts/readall',getResults,cbParams);
		console.log(body);
	}
	else{
		body={firmName:firmName,state:state,page:page,orderBy:curr.orderBy,desc:curr.desc};
		body=JSON.stringify(body);
		post(body,'/contacts/readall',getResults,cbParams);
		console.log(body);
	}
	
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
		if(e.target.id=='deleteButton' || e.target.id=='addButton'){
			return;
		}
		elem=elem.parentNode;
		console.log(elem.childNodes[1]);
		console.log(elem.childNodes[1].childNodes[3]);
		elem.childNodes[1].childNodes[3].action='/profile/contact/'+elem.getAttribute('email');
		elem.childNodes[1].childNodes[3].submit();

	}
	
}
function deleteContact(e) {
	let body = JSON.stringify({firmName:firmNameFirm.value,email:e.target.parentNode.parentNode.getAttribute('email')});
	console.log(body);
	post(body,'/firms/contacts/delete');
}
function addContact(e) {
	let body = JSON.stringify({firmName:firmNameFirm.value,email:e.target.parentNode.parentNode.getAttribute('email')});
	console.log(body);
	post(body,'/firms/contacts/add');
}
function post(body,action,cb,cbParams) {
	console.log(body);
	let xhr = new XMLHttpRequest();
    xhr.open("POST", action, true);
    let csrfToken = $("meta[name='_csrf']").attr("content");
	let csrfHeader = $("meta[name='_csrf_header']").attr("content");
	xhr.setRequestHeader(csrfHeader,csrfToken);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
          arr=[];
          console.log(xhr.responseText);
          arr=JSON.parse(xhr.responseText);
          if(cb!=undefined){

          	if(arr.length==0)
          		return;          	
          	cb(arr,cbParams);          	
          }
        }
      }
      xhr.send(body); 
     
}