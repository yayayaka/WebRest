var trackLists = null;
fillTable();
var trackListTable = document.getElementById("trackListTable");
trackListTable.innerHTML = '';

function fillTable() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:8080/rest4/tracklists/get/all", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 &&
            xmlhttp.status == 200){
            trackLists = xmlhttp.responseText;
            trackLists = JSON.parse(trackLists);
            var trackListTable = document.getElementById("trackListTable");
            trackListTable.innerHTML = '';
            makeTableHeader(trackListTable);
            // makeFormCreationNewTrack(trackListTable);
            // fillTrackTable(trackListTable);
            // addTableBottom(trackListTable);
        }
    }
    xmlhttp.send(null);
}

function addClick() {
    var trackListName = document.getElementById('name').value;
    var chooseArtist = document.getElementById('chooseArtist');
    var trackLength = document.getElementById('trackLength').value;
    var chooseGenre = document.getElementById('chooseGenre');
    if (trackListName === 'Name' || trackName === '') {
        alert('Input the name!');
    } else if (chooseArtist.value == '-1') {
        alert('Select Artist!');
    } else if (!isDigitsInput(trackLength)) {
        alert('TrackLength: Digits only');
    } else if (chooseGenre.value == '-1') {
        alert('Select Genre!');
    } else {
        var posthttp = new XMLHttpRequest();
        posthttp.open("POST", "http://localhost:8080/rest4/tracklists/add/", true);
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
    delhttp.open("DELETE", "http://localhost:8080/rest4/tracklists/del/" + id, true);
    delhttp.onreadystatechange = function () {
        fillTable();
    };
    delhttp.send(null);
}

function makeTableHeader(trackListTable) {
    var tableHeader = document.createElement("th");
    tableHeader.setAttribute("class", "th");
    tableHeader.setAttribute("colspan", 6);
    tableHeader.innerHTML = "<h2>TrackLists</h2>";
    trackListTable.appendChild(tableHeader);
    var tr = document.createElement("tr");
    tr.innerHTML = "<td class='th'>Id</td>" +
        "<td class='th'>Name</td>" +
        "<td class='th'>Album</td>" +
        "<td class='th'>Show tracks</td>" +
        "<td class='th'>&nbsp;</td>";
    var addTr = document.createElement("tr");
    addTr.innerHTML = "<td class='add' colspan='5'><a onclick='addNew()'>+ Add new</a></td>";
    trackListTable.appendChild(addTr);
    trackListTable.appendChild(tr);
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

// function inputEnterPressed(e) {
//     if (e.keyCode == 13) {
//         // addClick();
//     }
// }

// function makeFormCreationNewTrack(trackTable) {
//     var trAdd = document.createElement("tr");
//     trAdd.innerHTML = "<td class='td'>&nbsp;</td> " +
//         "<td class='td'><input type='text' name='name' id='name' " +
//         "value='Name' onblur='blurInputName()' onfocus='focusInputName()' onkeydown='inputEnterPressed()'></td>" +
//         "<td class='td'><select size='1' name='chooseArtist' id='chooseArtist'>" +
//         "<option value='-1' disabled selected>Choose Artist . . . </option>" +
//         "</select></td>" +
//         "<td class='td'><input type='text' name='trackLength' id='trackLength'" +
//         "value='TrackLength' onblur='blurInputTrackLenght()'" +
//         "onfocus='focusInputTrackLength()' onkeydown='inputEnterPressed()'></td>" +
//         "<td class='td'><select size='1' name='chooseGenre' id='chooseGenre'>" +
//         "<option value='-1' disabled selected>Choose Genre . . . </option>" +
//         "</select></td>" +
//         "<td class='td'><a onclick='addClick()'>Add</a></td>";
//     trackTable.appendChild(trAdd);
//     initArtistSelect();
//     initGenreSelect();
// }

function initAlbumSelect() {
    var xhrAlbum = new XMLHttpRequest();
    var albums = null;
    xhrAlbum.open("GET", "http://localhost:8080/rest2/albums/get/all", true);
    xhrAlbum.onreadystatechange = function () {
        if (xhrAlbum.status == 200 && xhrAlbum.readyState == 4) {
            albums = xhrAlbum.responseText;
            albums = JSON.parse(albums);
            // alert(albums[0].id + ", " + albums[0].name);
            var chooseAlbum = document.getElementById('selectAlbum');
            for (var pointer = 0; pointer < albums.length; pointer++) {
                var option = document.createElement("option");
                // option.name = albums[pointer].id;
                // alert(albums[pointer].id);
                option.setAttribute("name", albums[pointer].id);
                option.value = albums[pointer].id;
                option.innerText = albums[pointer].name;
                chooseAlbum.appendChild(option);
            }
        }
    };
    xhrAlbum.send(null);
}

// function fillTrackTable(trackListTable) {
//     for (var pointer = 0; pointer < tracks.length; pointer++) {
//         var nextTr = document.createElement("tr");
//         nextTr.setAttribute('name', tracks[pointer].id)
//         var nextId = document.createElement("td");
//         nextId.setAttribute("class", "td");
//         var nextName = document.createElement("td");
//         nextName.setAttribute("class", "td");
//         var nextArtist = document.createElement("td");
//         nextArtist.setAttribute("class", "td");
//         var nextTrackLength = document.createElement("td");
//         nextTrackLength.setAttribute("class", "td");
//         var nextGenre = document.createElement("td");
//         nextGenre.setAttribute("class", "td");
//         nextId.innerText = tracks[pointer].id;
//         nextName.innerText = tracks[pointer].name;
//         nextArtist.innerText = tracks[pointer].artist.name;
//         nextTrackLength.innerText = tracks[pointer].trackLength;
//         nextGenre.innerText = tracks[pointer].genre;
//         var delArtTd = document.createElement("td");
//         delArtTd.setAttribute("class", "td");
//         delArtTd.innerHTML = "<a onclick='delClick(" + tracks[pointer].id + ")'>Delete</a>";
//         nextTr.appendChild(nextId);
//         nextTr.appendChild(nextName);
//         nextTr.appendChild(nextArtist);
//         nextTr.appendChild(nextTrackLength);
//         nextTr.appendChild(nextGenre);
//         nextTr.appendChild(delArtTd);
//         trackListTable.appendChild(nextTr);
//     }
// }

function addTableBottom(trackListTable) {
    var tr = document.createElement("tr");
    tr.innerHTML = '<td colspan="6" class="td-bottom">&nbsp;</td>';
    trackListTable.appendChild(tr);
}

function addNew() {
    var trackListTable = document.getElementById('trackListTable');
    trackListTable.innerHTML = '';
    var trAdd = document.createElement('tr');
    trAdd.innerHTML ="<td class='td' colspan='5'><select size='1' id='selectAlbum'>" +
        "<option value='-1' disabled selected>Choose Album . . . </option>" +
        "</select></td>" +
        "<td class='td'><a onclick='createNew()'>Add</a></td>";
    trackListTable.appendChild(trAdd);
    initAlbumSelect();
    getAllTracks();
}

function createNew() {
    var selectAlbum = document.getElementById("selectAlbum");
    var checkBoxes = document.getElementsByTagName("input");
    var ids = [];
    for (pointer = checkBoxes.length - 1; pointer >= 0; pointer--) {
        if (checkBoxes[pointer].checked && checkBoxes[pointer].id.match("[0-9]"))
            ids.push({ id : checkBoxes[pointer].id });
    }
    if (selectAlbum.value == -1) {
        // go to begin
        return;
    } else if (ids.length < 1) {
        // go to begin
        return;
    }
    var tracks = null;
    var trackResponse = new XMLHttpRequest();
    trackResponse.open("POST", "http://localhost:8080/rest3/tracks/getbyids", true);
    trackResponse.onreadystatechange = function () {
        if (trackResponse.status == 200 && trackResponse.readyState == 4) {
            tracks = trackResponse.responseText;
            tracks = JSON.parse(tracks);
            var trackListResponse = new XMLHttpRequest();
            trackListResponse.open("POST", "http://localhost:8080/rest4/tracklists/add", true);
            trackListResponse.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            trackListResponse.onreadystatechange = function () {
                if (trackListResponse.status == 200 && trackListResponse.readyState == 4) {
                }
            };
            var trackList = { album : { id : selectAlbum.value,
                    name : selectAlbum.options[selectAlbum.options.selectedIndex].innerText }, tracks : tracks };
            trackList = JSON.stringify(trackList);
            alert(trackList);
            trackListResponse.send(trackList);
        }
    };
    alert(JSON.stringify(ids));
    trackResponse.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    trackResponse.send(JSON.stringify(ids));
}

function getAllTracks() {
    var trackListTable = document.getElementById('trackListTable');
    tracksHTTP = new XMLHttpRequest();
    tracksHTTP.open("GET", "http://localhost:8080/rest3/tracks/get/all", true);
    var tracks = null;
    tracksHTTP.onreadystatechange = function () {
        if (tracksHTTP.readyState == 4 &&
            tracksHTTP.status == 200) {
            tracks = tracksHTTP.responseText;
            tracks = JSON.parse(tracks);
            fillTrackTable(tracks);
        }
    };
    tracksHTTP.send(null);

}

function fillTrackTable(tracks) {
    var trackListTable = document.getElementById('trackListTable');
    for (var pointer = 0; pointer < tracks.length; pointer++) {
        var tdCheck = document.createElement("td");
        tdCheck.setAttribute("class", "td");
        var nextCheckBox = document.createElement("input");
        nextCheckBox.setAttribute("type", "checkbox");
        nextCheckBox.setAttribute("id", tracks[pointer].id);
        tdCheck.appendChild(nextCheckBox);
        var nextTr = document.createElement("tr");
        nextTr.setAttribute('name', tracks[pointer].id);
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
        nextTr.appendChild(tdCheck);
        nextTr.appendChild(nextId);
        nextTr.appendChild(nextName);
        nextTr.appendChild(nextArtist);
        nextTr.appendChild(nextTrackLength);
        nextTr.appendChild(nextGenre);
        var trackListTable = document.getElementById('trackListTable');
        trackListTable.appendChild(nextTr);
    }
}