


function makeEnabled(){
	firstNameContact.disabled="";
	lastNameContact.disabled="";
	emailContact.disabled="";
	phoneNumContact.disabled="";
	contactImage.disabled="";
	firmNameContact.disabled="";
	firmImage.disabled="";
	//myInput.disabled="";
	firmNameFirm.disabled="";
	emailFirm.disabled="";
	descriptionFirm.disabled="";
}
function makeFirmEnabled(){
    firmImage.disabled="";
    //myInput.disabled="";
    firmNameFirm.disabled="";
    emailFirm.disabled="";
    descriptionFirm.disabled="";
}
function makeDisabled() {
	firstNameContact.disabled="disabled";
	lastNameContact.disabled="disabled";
	emailContact.disabled="disabled";
	phoneNumContact.disabled="disabled";
	myInput.disabled="disabled";
	firmNameFirm.disabled="disabled";
	emailFirm.disabled="disabled";
	descriptionFirm.disabled="disabled";
}
function showContacts() {
	//getContacts.setAttribute('action','/profile/'+firmNameFirm.value+'/contactslist');
	//getContacts.submit();
	
}
function makeEnabledElemsInCont(elem) {
	console.log(elem.id+' '+elem.disabled);
	for(let i=0;i<elem.childNodes.length;i++){
		if(elem.childNodes[i].disabled){
            elem.childNodes[i].disabled='';
            console.log('hui');
		}
		else{
			makeEnabledElemsInCont(elem.childNodes[i]);
		}
	}
}
function sortBy(type,id) {
	//let separateNames = getSeparateNames(id);
	if(type=='firstName'){

		let firstNames=document.getElementsByName('firstName');
		let firstNamesArr=[];
		console.log(firstNames);
		for(let i=1;i<firstNames.length;i++){
			 firstNamesArr.push(firstNames[i].innerHTML);
		}
		firstNamesArr.sort();
		console.log(firstNamesArr);
	}
	else{
		
		let lastNames=document.getElementsByName('lastName');
		let lastNamesArr=[];
		console.log(lastNames);
		for(let i=1;i<lastNames.length;i++){
			 lastNamesArr.push(lastNames[i].innerHTML);
		}
		lastNamesArr.sort();
		console.log(lastNamesArr);
	}


}
function getSeparateNames(id) {
    let elems=document.getElementsByName(id);
    console.log(elems);
    let firstNames=[];
    let lastNames=[];
    let currValue;
    for(let i=0;i<elems.length;i++){
        currValue=elems[i].innerHTML;
        firstNames.push(currValue.substr(0,currValue.lastIndexOf(' ')));
        lastNames.push(currValue.substr(currValue.lastIndexOf(' ')+1));
    }
    console.log(firstNames);
    console.log(lastNames);
    let separateNames={firstNames:firstNames,lastNames:lastNames};
	return separateNames;
}

function get(action) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", action, true);
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            arr=[];
            console.log(xhr.responseText);
            arr=JSON.parse(xhr.responseText);
        }
    }
    xhr.send(null);

}
function createRouteSubmit() {
	let dateTimeStart=startDateTime.value;
	let dateTimeFinish=finishDateTime.value;
	let dateStart=new Date(dateTimeStart);
    let dateFinish=new Date(dateTimeFinish);
    startDateTime.value=dateStart.toIsoString();
    finishDateTime.value=dateFinish.toIsoString();
    console.log(startDateTime.value);
    console.log(finishDateTime.value);
    let body={
        countryFrom:countryFrom.value,
        regionFrom:regionFrom.value,
        cityFrom:cityFrom.value,
        countryTo: countryTo.value,
        regionTo:regionTo.value,
        cityTo:cityTo.value,
        carName:carName.value,
        carrierName:carrierName.value,
        dateA:startDateTime.value,
        dateB:finishDateTime.value,
        volume:volume.value,
        capacity:capacity.value,
        length:lengthRoute.value,
        duration:durationRoute.value,
        cost:cost.value
    };
    console.log(body);
    post1('/routes/create',body);
}

function post1(action,body,cb) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", action, true);
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    xhr.setRequestHeader(csrfHeader,csrfToken);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            console.log(xhr.responseText);
            console.log('route created');
            if(cb!=null){

            }
        }
    }
    xhr.send(JSON.stringify(body));
}

function getRoutePage(e) {
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
        cbParams={id:'resultColQueue',resultId:'resultContQueue',pagesId:'pageNumsWAIT',state:e.target.getAttribute('state')};
    else
        cbParams={id:'resultCol',resultId:'resultCont',pagesId:'pageNumsADDED',state:e.target.getAttribute('state')};

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
function createRoutesPagination(pageCount,pageNumber,state) {
    let divCont=document.createElement('div');
    divCont.className='form-row';
    divCont.id='pageNums'+state;
    if(pageCount>2)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('prevPage','<<',state,getRoutePage)));
    divCont.appendChild(createDivClassName('col form-group',createButtonBlue('firstPage','1',state,getRoutePage)));
    if(pageCount>2){

        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('currPage',pageNumber+1,state,getRoutePage)));

    }
    if(pageCount>1)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('lastPage',pageCount,state,getRoutePage)));
    if(pageCount>2)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('nextPage','>>',state,getRoutePage)));

    return divCont;
}
function getRoutesResult(routes) {
    let listRoutes = routes.listEntitiesDTO;
    deleteNodes("routesResultCont");
    deleteLastNode("resultRouteCol");
    for(let i=0;i<listRoutes.length;i++){
        addRouteToResultCont(listRoutes[i].countryFrom+" "+listRoutes[i].regionFrom+" "+listRoutes[i].cityFrom,
            listRoutes[i].countryTo+" "+listRoutes[i].regionTo+" "+listRoutes[i].cityTo,
            listRoutes[i].dateA,listRoutes[i].dateB,listRoutes[i].cost,routes.pageNumber,i);
    }
    document.getElementById("resultRouteCol").appendChild(createRoutesPagination(routes.pageCount,routes.pageNumber,'routes'));
}
function addRouteToResultCont(pointFrom,pointTo,dateStart,dateFinish,cost,pageNumber,currNum) {
    let td1=document.createElement('td');
    td1.className='number text-center';
    td1.appendChild(document.createTextNode(5*pageNumber+currNum+1));
    let td3=document.createElement('td');
    td3.className='product';
    let strong=document.createElement('strong');
    let spanFrom=document.createElement('span');
    spanFrom.appendChild(document.createTextNode(pointFrom));
    let spanSpace=document.createElement('span');
    spanSpace.appendChild(document.createTextNode(' -> '));
    let spanTo=document.createElement('span');
    spanTo.appendChild(document.createTextNode(pointTo));
    strong.appendChild(spanFrom);
    strong.appendChild(spanSpace);
    strong.appendChild(spanTo);
    let br=document.createElement('br');
    td3.appendChild(strong);
    td3.appendChild(br);
    td3.appendChild(document.createTextNode(dateStart+' -> '+dateFinish));

    let td5=document.createElement('td');
    td5.className='price text-right';
    td5.appendChild(document.createTextNode(cost+'$'));

    let tr=document.createElement('tr');
    tr.appendChild(td1);
    tr.appendChild(td3);
    tr.appendChild(td5);

    let resultsCont=document.getElementById('routesResultCont');
    resultsCont.appendChild(tr);
}
function getUserRoutes(action,cb) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", action, true);
    let csrfToken = $("meta[name='_csrf']").attr("content");
    let csrfHeader = $("meta[name='_csrf_header']").attr("content");
    xhr.setRequestHeader(csrfHeader,csrfToken);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function() {
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            console.log(xhr.responseText);
            let routes=JSON.parse(xhr.responseText);
            if(cb!=null){
                cb(routes);
            }
        }
    }
    xhr.send(null);
}

Date.prototype.toIsoString = function() {
    var tzo = -this.getTimezoneOffset(),
        dif = tzo >= 0 ? '+' : '-',
        pad = function(num) {
            var norm = Math.floor(Math.abs(num));
            return (norm < 10 ? '0' : '') + norm;
        };
    return this.getFullYear() +
        '-' + pad(this.getMonth() + 1) +
        '-' + pad(this.getDate()) +
        'T' + pad(this.getHours()) +
        ':' + pad(this.getMinutes()) +
        ':' + pad(this.getSeconds()) +
        dif + pad(tzo / 60)
         + pad(tzo % 60);
}

var dt = new Date();
console.log(dt.toIsoString());
