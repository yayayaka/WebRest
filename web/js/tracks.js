var tracks = null;
fillTable();
var trackTable = document.getElementById("trackTable");
trackTable.innerHTML = '';

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
    var chooseArtist = document.getElementById('chooseArtist');
    var trackLength = document.getElementById('trackLength').value;
    var chooseGenre = document.getElementById('chooseGenre');
    if (trackName === 'Name' || trackName === '') {
        alert('Input the name!');
    } else if (chooseArtist.value == '-1') {
        alert('Select Artist!');
    } else if (!isDigitsInput(trackLength)) {
        alert('TrackLength: Digits only');
    } else if (chooseGenre.value == '-1') {
        alert('Select Genre!');
    } else {
        var posthttp = new XMLHttpRequest();
        posthttp.open("POST", "http://localhost:8080/rest3/tracks/add/", true);
        posthttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        posthttp.onreadystatechange = function() {
            if (posthttp.readyState == 4 &&
                posthttp.status == 200){
                fillTable();
            }
        }
        var track = { name : trackName,
            artist : { id : chooseArtist.value,
                name : chooseArtist.options[chooseArtist.value].innerText },
            trackLength : trackLength,
            genre : chooseGenre.value };
        var jsonData = JSON.stringify(track);
        posthttp.send(jsonData);
    }
}

function isDigitsInput(input) {
    if(!input.match(/^\d+(.\d+)?$/))
        return false;
    return true;
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
        "<td class='td'><select size='1' name='chooseArtist' id='chooseArtist'>" +
        "<option value='-1' disabled selected>Choose Artist . . . </option>" +
        "</select></td>" +
        "<td class='td'><input type='text' name='trackLength' id='trackLength'" +
        "value='TrackLength' onblur='blurInputTrackLenght()'" +
        "onfocus='focusInputTrackLength()' onkeydown='inputEnterPressed()'></td>" +
        "<td class='td'><select size='1' name='chooseGenre' id='chooseGenre'>" +
        "<option value='-1' disabled selected>Choose Genre . . . </option>" +
        "</select></td>" +
        "<td class='td'><a onclick='addClick()'>Add</a></td>";
    trackTable.appendChild(trAdd);
    initArtistSelect();
    initGenreSelect();
}

function initArtistSelect() {
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
                option.setAttribute('name', artists[pointer].id);
                option.value = artists[pointer].id;
                option.innerText = artists[pointer].name;
                chooseArtist.appendChild(option);
            }
        }
    };
    xhrArtist.send(null);
}

function initGenreSelect() {
    var xhrGenre = new XMLHttpRequest();
    var genres = null;
    xhrGenre.open("GET", "http://localhost:8080/rest9/genres/get/all", true);
    xhrGenre.onreadystatechange = function () {
        if (xhrGenre.status == 200 && xhrGenre.readyState == 4) {
            genres = xhrGenre.responseText;
            genres = JSON.parse(genres);
            var chooseGenre = document.getElementsByName("chooseGenre")[0];
            for (var pointer = 0; pointer < genres.length; pointer++) {
                var option = document.createElement("option");
                option.name = genres[pointer];
                option.value = genres[pointer];
                option.innerText = genres[pointer];
                chooseGenre.appendChild(option);
            }
        }
    };
    xhrGenre.send(null);
}

function fillTrackTable(trackTable) {
    for (var pointer = 0; pointer < tracks.length; pointer++) {
        var nextTr = document.createElement("tr");
        nextTr.setAttribute('name', tracks[pointer].id)
        var nextId = document.createElement("td");
        nextId.setAttribute("class", "td");
        var nextName = document.createElement("td");
        nextName.setAttribute("class", "td");
        var nextArtist = document.createElement("td");
        nextArtist.setAttribute("class", "td");
        var nextTrackLength = document.createElement("td");
        nextTrackLength.setAttribute("class", "td");
        var nextGenre = document.createElement("td");
        nextGenre.setAttribute("class", "td");
        nextId.innerText = tracks[pointer].id;
        nextName.innerText = tracks[pointer].name;
        nextArtist.innerText = tracks[pointer].artist.name;
        nextTrackLength.innerText = tracks[pointer].trackLength;
        nextGenre.innerText = tracks[pointer].genre;
        var delArtTd = document.createElement("td");
        delArtTd.setAttribute("class", "td");
        delArtTd.innerHTML = "<a onclick='delClick(" + tracks[pointer].id + ")'>Delete</a>";
        nextTr.appendChild(nextId);
        nextTr.appendChild(nextName);
        nextTr.appendChild(nextArtist);
        nextTr.appendChild(nextTrackLength);
        nextTr.appendChild(nextGenre);
        nextTr.appendChild(delArtTd);
        trackTable.appendChild(nextTr);
    }
}

function addTableBottom(albumTable) {
    var tr = document.createElement("tr");
    tr.innerHTML = '<td colspan="6" class="td-bottom">&nbsp;</td>';
    albumTable.appendChild(tr);
}