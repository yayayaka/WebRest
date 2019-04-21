var tracks = null;
// fillTable();
var trackTable = document.getElementById("trackTable");
trackTable.innerHTML = '';
makeTableHeader(trackTable);
makeFormCreationNewTrack(trackTable);

function fillTable() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:8080/rest3/tracks/get/all", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 &&
            xmlhttp.status == 200){
            tracks = xmlhttp.responseText;
            tracks = JSON.parse(tracks);
            var trackTable = document.getElementById("trackTable");
            trackTable.innerHTML = '';
            makeTableHeader(trackTable);
            makeFormCreationNewTrack(trackTable);
            fillTrackTable(trackTable);
            addTableBottom(trackTable);
        }
    }
    xmlhttp.send(null);
}

function addClick() {
    var trackName = document.getElementById('name').value;
    if (trackName === 'Name' || trackName === '') {
        alert('Input the name');
    } else {
        var posthttp = new XMLHttpRequest();
        posthttp.open("POST", "http://localhost:8080/rest3/tracks/add/", true);
        posthttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
        posthttp.onreadystatechange = function() {
            if (posthttp.readyState == 4 &&
                posthttp.status == 200){
                fillTable();
            }
        }
        var album = { name : trackName };
        var jsonData = JSON.stringify(album);
        posthttp.send(jsonData);
    }
}

function delClick(id) {
    delhttp = new XMLHttpRequest();
    delhttp.open("DELETE", "http://localhost:8080/rest3/tracks/del/" + id, true);
    delhttp.onreadystatechange = function () {
        fillTable();
    };
    delhttp.send(null);
}

function makeTableHeader(trackTable) {
    var tableHeader = document.createElement("th");
    tableHeader.setAttribute("class", "th");
    tableHeader.setAttribute("colspan", 6);
    tableHeader.innerHTML = "<h2>Tracks</h2>";
    trackTable.appendChild(tableHeader);
    var tr = document.createElement("tr");
    tr.innerHTML = "<td class='th'>Id</td>" +
        "<td class='th'>Name</td>" +
        "<td class='th'>Artist</td>" +
        "<td class='th'>TrackLenght</td>" +
        "<td class='th'>Genre</td>" +
        "<td class='th'>&nbsp;</td>";
    trackTable.appendChild(tr);
}

function blurInputName() {
    if(document.getElementById('name').value == '')
        document.getElementById('name').value = 'Name';
}

function blurInputTrackLenght() {
    if(document.getElementById('trackLength').value == '')
        document.getElementById('trackLength').value = 'TrackLength';
}

function focusInputName() {
    if (document.getElementById('name').value == 'Name')
        document.getElementById('name').value = '';
}

function focusInputTrackLength() {
    if (document.getElementById('trackLength').value == 'TrackLength')
        document.getElementById('trackLength').value = '';
}

function inputEnterPressed(e) {
    if (e.keyCode == 13) {
        addClick();
    }
}

function makeFormCreationNewTrack(trackTable) {
    var trAdd = document.createElement("tr");
    trAdd.innerHTML = "<td class='td'>&nbsp;</td> " +
        "<td class='td'><input type='text' name='name' id='name' " +
        "value='Name' onblur='blurInputName()' onfocus='focusInputName()' onkeydown='inputEnterPressed()'></td>" +
        "<td class='td'><select size='1'>" +
        "<option name='chooseArtist' disabled selected>Choose Artist</option>" +
        "</select></td>" +
        "<td class='td'><input type='text' name='trackLength' id='trackLength'" +
        "value='TrackLength' onblur='blurInputTrackLenght()'" +
        "onfocus='focusInputTrackLength()' onkeydown='inputEnterPressed()'></td>" +
        "<td class='td'><select>" +
        "<option name='chooseGenre' disabled selected>Choose Genre</option>" +
        "</select></td>" +
        "<td class='td'><a onclick='addClick()'>Add</a></td>";
    trackTable.appendChild(trAdd);
    var xhrArtist = new XMLHttpRequest();
    var artists = null;
    xhrArtist.open("GET", "http://localhost:8080/rest1/artists/get/all", true);
    xhrArtist.onreadystatechange = function () {
        if (xhrArtist.status == 200 && xhrArtist.readyState == 4) {
            artists = xhrArtist.responseText;
            artists = JSON.parse(artists);
            var chooseArtist = document.getElementsByName("chooseArtist")[0];
            for (var pointer = 0; pointer < artists.length; pointer++) {
                var option = document.createElement("option");
                // option.setAttribute("name", artists[pointer].id);
                option.textContent = artists[pointer].name;
                option.value = artists[pointer].name;
                chooseArtist.appendChild(option);
                // chooseArtist.innerHTML += "<option name='" + artists[pointer].id + "' value='" + artists[pointer].name + "'>" + artists[pointer].name + "</option>"
            }
        }
    };
    xhrArtist.send(null);


}

function fillTrackTable(trackTable) {
    for (var pointer = 0; pointer < tracks.length; pointer++) {
        var nextTr = document.createElement("tr");
        nextTr.setAttribute('name', tracks[pointer].id)
        var nextId = document.createElement("td");
        nextId.setAttribute("class", "td");
        var nextName = document.createElement("td");
        nextName.setAttribute("class", "td");
        nextId.innerText = tracks[pointer].id;
        nextName.innerText = tracks[pointer].name;
        var delArtTd = document.createElement("td");
        delArtTd.setAttribute("class", "td");
        delArtTd.innerHTML = "<a onclick='delClick(" + tracks[pointer].id + ")'>Delete</a>";
        nextTr.appendChild(nextId);
        nextTr.appendChild(nextName);
        nextTr.appendChild(delArtTd);
        trackTable.appendChild(nextTr);
    }
}

function addTableBottom(albumTable) {
    var tr = document.createElement("tr");
    tr.innerHTML = '<td colspan="3" class="td-bottom">&nbsp;</td>';
    albumTable.appendChild(tr);
}