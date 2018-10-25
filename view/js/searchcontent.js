
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

