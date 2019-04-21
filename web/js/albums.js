var albums = null;
fillTable();

function fillTable() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:8080/rest2/albums/get/all", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 &&
            xmlhttp.status == 200){
            albums = xmlhttp.responseText;
            albums = JSON.parse(albums);
            var albumTable = document.getElementById("albumTable");
            albumTable.innerHTML = '';
            makeTableHeader(albumTable);
            makeFormCreationNewAlbum(albumTable);
            fillAlbumTable(albumTable);
            addTableBottom(albumTable);
        }
    }
    xmlhttp.send(null);
}

function addClick() {
    var albumName = document.getElementById('name').value;
    if (albumName === 'Name' || albumName === '') {
        alert('Input the name');
    } else {
        var posthttp = new XMLHttpRequest();
        posthttp.open("POST", "http://localhost:8080/rest2/albums/add/", true);
        posthttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
        posthttp.onreadystatechange = function() {
            if (posthttp.readyState == 4 &&
                posthttp.status == 200){
                fillTable();
            }
        }
        var album = { name : albumName };
        var jsonData = JSON.stringify(album);
        posthttp.send(jsonData);
    }
}

function delClick(id) {
    delhttp = new XMLHttpRequest();
    delhttp.open("DELETE", "http://localhost:8080/rest2/albums/del/" + id, true);
    delhttp.onreadystatechange = function () {
        fillTable();
    };
    delhttp.send(null);
}

function makeTableHeader(albumTable) {
    var tableHeader = document.createElement("th");
    tableHeader.setAttribute("class", "th");
    tableHeader.setAttribute("colspan", 3);
    tableHeader.innerHTML = "<h2>Albums</h2>";
    albumTable.appendChild(tableHeader);
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
    albumTable.appendChild(tr);
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

function makeFormCreationNewAlbum(albumTable) {
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
    tdAddName.appendChild(nameInput);
    tdAddInput.innerHTML = "<a onclick='addClick()'>Add</a>";
    trAdd.appendChild(tdAddEmpty);
    trAdd.appendChild(tdAddName);
    trAdd.appendChild(tdAddInput);
    trAdd.appendChild(addForm);
    albumTable.appendChild(trAdd);
}

function fillAlbumTable(albumTable) {
    for (var pointer = 0; pointer < albums.length; pointer++) {
        var nextTr = document.createElement("tr");
        nextTr.setAttribute('name', albums[pointer].id)
        var nextId = document.createElement("td");
        nextId.setAttribute("class", "td");
        var nextName = document.createElement("td");
        nextName.setAttribute("class", "td");
        nextId.innerText = albums[pointer].id;
        nextName.innerText = albums[pointer].name;
        var delArtTd = document.createElement("td");
        delArtTd.setAttribute("class", "td");
        delArtTd.innerHTML = "<a onclick='delClick(" + albums[pointer].id + ")'>Delete</a>";
        nextTr.appendChild(nextId);
        nextTr.appendChild(nextName);
        nextTr.appendChild(delArtTd);
        albumTable.appendChild(nextTr);
    }
}

function addTableBottom(albumTable) {
    var tr = document.createElement("tr");
    tr.innerHTML = '<td colspan="3" class="td-bottom">&nbsp;</td>';
    albumTable.appendChild(tr);
}