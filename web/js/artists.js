var artists = null;
fillTable();

function fillTable() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:8080/rest1/artists/get/all", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 &&
            xmlhttp.status == 200){
            artists = xmlhttp.responseText;
            artists = JSON.parse(artists);
            var artistTable = document.getElementById("artistTable");
            artistTable.innerHTML = '';
            makeTableHeader(artistTable);
            makeFormCreationNewArtist(artistTable);
            fillArtistTable(artistTable);
            addTableBottom(artistTable);
        }
    }
    xmlhttp.send(null);
}

function addClick() {
    var artistName = document.getElementById('name').value;
    if (artistName === 'Name' || artistName === '') {
        alert('Input the name');
    } else {
        var posthttp = new XMLHttpRequest();
        posthttp.open("POST", "http://localhost:8080/rest1/artists/add/", true);
        posthttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
        posthttp.onreadystatechange = function() {
            if (posthttp.readyState == 4 &&
                posthttp.status == 200){
                // alert('ready state = ' + posthttp.readyState + ', status = ' + posthttp.status);
                fillTable();
            }
        }
        var artist = { name : artistName };
        var jsonData = JSON.stringify(artist);
        posthttp.send(jsonData);
    }
}

function delClick(id) {
    delhttp = new XMLHttpRequest();
    delhttp.open("DELETE", "http://localhost:8080/rest1/artists/del/" + id, true);
    delhttp.onreadystatechange = function () {
        fillTable();
    };
    delhttp.send(null);
}

function makeTableHeader(artistTable) {
    var tableHeader = document.createElement("th");
    tableHeader.setAttribute("class", "th");
    tableHeader.setAttribute("colspan", 3);
    tableHeader.innerHTML = "<h2>Artists</h2>";
    artistTable.appendChild(tableHeader);
    var tr = document.createElement("tr");
    var idTd = document.createElement("td");
    var nameTd = document.createElement("td");
    var emptyTd = document.createElement("td");
    idTd.innerText = "Id";
    idTd.setAttribute("class", "th");
    nameTd.innerText = "Name";
    nameTd.setAttribute("class", "th");
    emptyTd.setAttribute("class", "th");
    tr.appendChild(idTd);
    tr.appendChild(nameTd);
    tr.appendChild(emptyTd);
    artistTable.appendChild(tr);
}

function blurInputName() {
    if(document.getElementById('name').value == '')
        document.getElementById('name').value = 'Name';
}

function focusInputName() {
    if (document.getElementById('name').value == 'Name')
        document.getElementById('name').value = '';
}

function inputEnterPressed(e) {
    if (e.keyCode == 13) {
        addClick();
    }
}

function makeFormCreationNewArtist(artistTable) {
    var addForm = document.createElement("form");
    addForm.setAttribute("name", "addForm");
    addForm.setAttribute("action", "****");
    addForm.setAttribute("method", "post");
    addForm.setAttribute("accept-charset", "utf-8");
    var trAdd = document.createElement("tr");
    var tdAddEmpty = document.createElement("td");
    var tdAddName = document.createElement("td");
    var tdAddInput = document.createElement("td");
    tdAddEmpty.setAttribute("class", "td");
    tdAddName.setAttribute("class", "td");
    tdAddInput.setAttribute("class", "td");
    var nameInput = document.createElement("input");
    nameInput.setAttribute("type", "text");
    nameInput.setAttribute("name", "name");
    nameInput.setAttribute("id", "name");
    nameInput.setAttribute("value", "Name");
    nameInput.addEventListener("blur", blurInputName);
    nameInput.addEventListener("focus", focusInputName);
    nameInput.addEventListener("keydown", inputEnterPressed);
    var addButton = document.createElement("input");
    // addButton.setAttribute("class", "button");
    // addButton.setAttribute("type", "submit");
    // addButton.setAttribute("value", "Add");
    // addButton.addEventListener("click", addClick);
    tdAddName.appendChild(nameInput);
    // tdAddInput.appendChild(addButton);
    tdAddInput.innerHTML = "<a onclick='addClick()'>Add</a>";
    trAdd.appendChild(tdAddEmpty);
    trAdd.appendChild(tdAddName);
    trAdd.appendChild(tdAddInput);
    trAdd.appendChild(addForm);
    artistTable.appendChild(trAdd);
}

function fillArtistTable(artistTable) {
    for (var pointer = 0; pointer < artists.length; pointer++) {
        var nextTr = document.createElement("tr");
        nextTr.setAttribute('name', artists[pointer].id)
        var nextId = document.createElement("td");
        nextId.setAttribute("class", "td");
        var nextName = document.createElement("td");
        nextName.setAttribute("class", "td");
        nextId.innerText = artists[pointer].id;
        nextName.innerText = artists[pointer].name;
        var delArtTd = document.createElement("td");
        delArtTd.setAttribute("class", "td");
        delArtTd.innerHTML = "<a onclick='delClick(" + artists[pointer].id + ")'>Delete</a>";
        nextTr.appendChild(nextId);
        nextTr.appendChild(nextName);
        nextTr.appendChild(delArtTd);
        artistTable.appendChild(nextTr);
    }
}

function addTableBottom(artistTable) {
    var tr = document.createElement("tr");
    tr.innerHTML = '<td colspan="3" class="td-bottom">&nbsp;</td>';
    artistTable.appendChild(tr);
}