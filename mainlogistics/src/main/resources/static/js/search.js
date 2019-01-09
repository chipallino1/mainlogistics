function getSearchResults() {
    if(defaultInline3.checked){
        getSearchRoutes("/search/routes?countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
    }
    if(defaultInline1.checked){
        getSearchFirms("/search/firms?firmName="+firmName.value,getSearchFirmsResult);
    }
}

function getSearchRoutes(action,cb) {
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
function getSearchRoutesResult(routes) {
    let listRoutes = routes.listEntitiesDTO;
    deleteNodes("searchResultCont");
    deleteLastNode("resultCol");
    for(let i=0;i<listRoutes.length;i++){
        addSearchRouteToResultCont(listRoutes[i].routeId, listRoutes[i].countryFrom+" "+listRoutes[i].regionFrom+" "+listRoutes[i].cityFrom,
            listRoutes[i].countryTo+" "+listRoutes[i].regionTo+" "+listRoutes[i].cityTo,
            listRoutes[i].dateA,listRoutes[i].dateB,listRoutes[i].cost,routes.pageNumber,i);
    }
    document.getElementById("resultCol").appendChild(createSearchRoutesPagination(routes.pageCount,routes.pageNumber,'search'));
}
function addSearchRouteToResultCont(routeId,pointFrom,pointTo,dateStart,dateFinish,cost,pageNumber,currNum) {
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
    tr.setAttribute("routeId",routeId);
    tr.addEventListener('click',openRoutePage);
    tr.appendChild(td1);
    tr.appendChild(td3);
    tr.appendChild(td5);
    let resultsCont=document.getElementById('searchResultCont');
    resultsCont.appendChild(tr);
}

function createSearchRoutesPagination(pageCount,pageNumber,state) {
    let divCont=document.createElement('div');
    divCont.className='form-row';
    divCont.id='pageNums'+state;
    if(pageCount>2)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('prevPage','<<',state,getSearchRoutePage)));
    divCont.appendChild(createDivClassName('col form-group',createButtonBlue('firstPage','1',state,getSearchRoutePage)));
    if(pageCount>2){

        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('currPage',pageNumber+1,state,getSearchRoutePage)));

    }
    if(pageCount>1)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('lastPage',pageCount,state,getSearchRoutePage)));
    if(pageCount>2)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('nextPage','>>',state,getSearchRoutePage)));

    return divCont;
}
function getSearchRoutePage(e) {
    state=e.target.getAttribute('state');
    let currP=document.getElementById('currPage'+state);
    let firstP=document.getElementById('firstPage'+state);
    let lastP=document.getElementById('lastPage'+state);
    let prevP=document.getElementById('prevPage'+state);
    let nextP=document.getElementById('nextPage'+state);

    if(e.target.id=='prevPage'+state && (Number(currP.innerHTML)*1-1)>0){
        console.log('prevPage'+state);
        currP.innerHTML=Number(currP.innerHTML)-1;
        let prev=Number(currP.innerHTML)-1;
        if(window.location.pathname=="me")
            getSearchRoutes("/search/routes?page="+prev+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
        else
            getSearchRoutes("/search/routes?page="+prev+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
        return;
    }
    if(e.target.id=='nextPage'+state && (Number(currP.innerHTML)*1+1)<=Number(lastP.innerHTML)){
        console.log('nextPage'+state);
        currP.innerHTML=Number(currP.innerHTML)+1;
        let next=Number(currP.innerHTML)-1;
        if(window.location.pathname=="me")
            getSearchRoutes("/search/routes?page="+next+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
        else
            getSearchRoutes("/search/routes?page="+next+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
        return;
    }
    if(e.target.id=='firstPage'+state){
        console.log('firstPage'+state);
        if(currP!=null)
            currP.innerHTML=firstP.innerHTML;
        if(window.location.pathname=="me")
            getSearchRoutes("/search/routes?page="+0+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
        else
            getSearchRoutes("/search/routes?page="+0+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
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
        if(window.location.pathname=="me")
            getSearchRoutes("/search/routes?page="+last+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
        else
            getSearchRoutes("/search/routes?page="+last+"&countryFrom="+countryFrom.value+"&cityFrom="+cityFrom.value+"&countryTo="+countryTo.value+"&cityTo="+cityTo.value,getSearchRoutesResult);
        return;
    }

}




function getSearchFirms(action,cb) {
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
function getSearchFirmsResult(routes) {
    let listRoutes = routes.listEntitiesDTO;
    deleteNodes("searchResultCont");
    deleteLastNode("resultCol");
    for(let i=0;i<listRoutes.length;i++){
        addSearchFirmToResultCont(listRoutes[i].email, listRoutes[i].firmName,listRoutes[i].email,
            listRoutes[i].description,routes.pageNumber,i);
    }
    document.getElementById("resultCol").appendChild(createSearchFirmsPagination(routes.pageCount,routes.pageNumber,'search'));
}
function addSearchFirmToResultCont(email,firmName,email,description,pageNumber,currNum) {
    let td1=document.createElement('td');
    td1.className='number text-center';
    td1.appendChild(document.createTextNode(5*pageNumber+currNum+1));
    let td3=document.createElement('td');
    td3.className='product';
    let strong=document.createElement('strong');
    let spanFrom=document.createElement('span');
    spanFrom.appendChild(document.createTextNode(firmName));
    let spanTo=document.createElement('span');
    spanTo.appendChild(document.createTextNode(description));
    strong.appendChild(spanFrom);
    strong.appendChild(document.createTextNode(' '));
    strong.appendChild(spanTo);
    let br=document.createElement('br');
    td3.appendChild(strong);
    td3.appendChild(br);
    td3.appendChild(document.createTextNode(description));

    let tr=document.createElement('tr');
    tr.setAttribute("firmMail",email);
    tr.addEventListener('click',openRoutePage);
    tr.appendChild(td1);
    tr.appendChild(td3);
    //tr.appendChild(td5);
    let resultsCont=document.getElementById('searchResultCont');
    resultsCont.appendChild(tr);
}

function createSearchFirmsPagination(pageCount,pageNumber,state) {
    let divCont=document.createElement('div');
    divCont.className='form-row';
    divCont.id='pageNums'+state;
    if(pageCount>2)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('prevPage','<<',state,getSearchFirmPage)));
    divCont.appendChild(createDivClassName('col form-group',createButtonBlue('firstPage','1',state,getSearchFirmPage)));
    if(pageCount>2){

        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('currPage',pageNumber+1,state,getSearchFirmPage)));

    }
    if(pageCount>1)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('lastPage',pageCount,state,getSearchFirmPage)));
    if(pageCount>2)
        divCont.appendChild(createDivClassName('col form-group',createButtonBlue('nextPage','>>',state,getSearchFirmPage)));

    return divCont;
}
function getSearchFirmPage(e) {
    state=e.target.getAttribute('state');
    let currP=document.getElementById('currPage'+state);
    let firstP=document.getElementById('firstPage'+state);
    let lastP=document.getElementById('lastPage'+state);
    let prevP=document.getElementById('prevPage'+state);
    let nextP=document.getElementById('nextPage'+state);

    if(e.target.id=='prevPage'+state && (Number(currP.innerHTML)*1-1)>0){
        console.log('prevPage'+state);
        currP.innerHTML=Number(currP.innerHTML)-1;
        let prev=Number(currP.innerHTML)-1;
        if(window.location.pathname=="me")
            getSearchFirms("/search/firms?page="+prev+"&firmName="+firmName.value,getSearchFirmsResult);
        else
            getSearchFirms("/search/firms?page="+prev+"&firmName="+firmName.value,getSearchFirmsResult);
        return;
    }
    if(e.target.id=='nextPage'+state && (Number(currP.innerHTML)*1+1)<=Number(lastP.innerHTML)){
        console.log('nextPage'+state);
        currP.innerHTML=Number(currP.innerHTML)+1;
        let next=Number(currP.innerHTML)-1;
        if(window.location.pathname=="me")
            getSearchFirms("/search/firms?page="+next+"&firmName="+firmName.value,getSearchFirmsResult);
        else
            getSearchFirms("/search/firms?page="+next+"&firmName="+firmName.value,getSearchFirmsResult);
        return;
    }
    if(e.target.id=='firstPage'+state){
        console.log('firstPage'+state);
        if(currP!=null)
            currP.innerHTML=firstP.innerHTML;
        if(window.location.pathname=="me")
            getSearchFirms("/search/firms?page="+0+"&firmName="+firmName.value,getSearchFirmsResult);
        else
            getSearchFirms("/search/firms?page="+0+"&firmName="+firmName.value,getSearchFirmsResult);
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
        if(window.location.pathname=="me")
            getFirmsRoutes("/search/firms?page="+last+"&firmName="+firmName.value,getSearchFirmsResult);
        else
            getFirmsRoutes("/search/firms?page="+last+"&firmName="+firmName.value,getSearchFirmsResult);
        return;
    }

}