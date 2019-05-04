<script>
    let artistString = 'Artists';
    let albumString = 'Albums';
    let trackString = 'Tracks';
    let trackListString ='TrackLists';
    let userString ='Users';
    let addTrackListString = 'Add TrackLists';
    let addAlbumString = 'Add Albums';
    let addUserString = 'Add Users';
    let tableCap = 'Artists';
    let serverHost = 'http://localhost:8080/';
    let artistGetAllPath = serverHost + "rest1/artists/get/all";
    let trackGetAllPath = serverHost + "rest3/tracks/get/all";
    let trackListGetAllPath = serverHost + "rest4/tracklists/get/all";
    let albumGetAllPath = serverHost + "rest2/albums/get/all";
    let userGetAllPath = serverHost + "rest5/users/get/all";
    let genreGetAllPath = serverHost + "rest9/genres/get/all";
    let artistAddPath = serverHost + "rest1/artists/add";
    let trackAddPath = serverHost + "rest3/tracks/add";
    let artistDeletePath = serverHost + "rest1/artists/del/";
    let albumDeletePath = serverHost + "rest2/albums/del/";
    let trackDeletePath = serverHost + "rest3/tracks/del/";
    let trackListsDeletePath = serverHost + "rest4/tracklists/del/";
    let usersDeletePath = serverHost + "rest5/users/del/";

    let data = getAll(artistGetAllPath);
    let selectData = null;
    let selectGenreData = null;

    function showArtistResults() {
        tableCap = artistString;
        data = getAll(artistGetAllPath);
    }
    function showAlbumResults() {
        tableCap = albumString;
        data = getAll(albumGetAllPath);
    }
    function showTrackResults() {
        tableCap = trackString;
        data = getAll(trackGetAllPath);
        selectData = getAll(artistGetAllPath);
        selectGenreData = getAll(genreGetAllPath);
    }
    function showTrackListResults(){
        tableCap = trackListString;
        data = getAll(trackListGetAllPath);
    }
    function showUserResults() {
        tableCap =userString;
        data = getAll(userGetAllPath);
    }

    function showAddTrackListPage(){
        tableCap = addTrackListString;
        data = getAll(trackGetAllPath);
    }

    function showAddAlbumPage(){
        tableCap = addAlbumString;
        data = getAll(trackGetAllPath);
        selectData = getAll(artistGetAllPath);
    }

    function showAddUserPage(){
        tableCap = addUserString;
        data = getAll(trackGetAllPath);
    }

    async function getAll(getPath){
        const response = await fetch(getPath);
        const text = await response.text();
        if (response.ok) {
            return JSON.parse(text);
        } else {
            throw new Error(text);
        }
    }

    function nameOnFocus(){
        if (this.value == 'Name')
            this.value = '';
    }

    function trackLengthOnFocus(){
            if (this.value == 'TrackLength')
                this.value = '';
        }

    function nameOnBlur(){
        if(this.value.length == 0)
            this.value = 'Name';
    }

    function trackLengthOnBlur(){
        if(this.value.length == 0)
            this.value = 'TrackLength';
    }

    async function addArtist(){
        var artistName = document.getElementById('artistNameInput');
        var body = { name : artistName.value };
        body = JSON.stringify(body);
        // alert(body);
        // const response = await
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var myInit = {
                       method : 'post',
                       headers: myHeaders,
                       mode: 'cors',
                       cache: 'default'
                        };

        fetch(artistAddPath, myInit);

    }

    async function addTrack(){
        let inputName = document.getElementById('inputName');
        let chooseArtist = document.getElementById('chooseArtist');
        let inputTrackLength = document.getElementById('inputTrackLength');
        let chooseGenre = document.getElementById('chooseGenre');
            var body = { name : inputName.value,
                            artist : {id : chooseArtist.value,
                            name : chooseArtist.options[chooseArtist.options.selectedIndex].innerText},
                            trackLength : inputTrackLength.value,
                            genre : chooseGenre.value};
            body = JSON.stringify(body);
            alert(body);
            // const response = await
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var myInit = {
                           method : 'post',
                           headers: myHeaders,
                           mode: 'cors',
                           CORS: "Access-Control-Allow-Origin",
                           cache: 'default',
                           body: body
                            };

            fetch(trackAddPath, myInit);
        }

        function deleteArtist() {
            let artistId = this.id;
            alert(artistDeletePath + artistId);
            let options = { method : 'delete',
                            CORS: "Access-Control-Allow-Origin"};
            const response = fetch(artistDeletePath + artistId, options);
                    const text = response.text;
                    if (response.ok) {
                        alert("Delete ok");
                        return JSON.parse(text);
                    } else {
                        alert("Error");
                        throw new Error(text);
                    }
        }

        function chooseDeletePath(){
            let uri = null;
            switch (tableCap) {
                            case artistString:
                                uri = artistDeletePath;
                                break;
                            case albumString:
                                uri = albumDeletePath;
                                break;
                            case trackString:
                                uri = trackDeletePath;
                                break;
                            case trackListString:
                                uri = trackListsDeletePath;
                                break;
                            case userString:
                                uri = usersDeletePath;
                                break;
                        }
                      return uri;
        }

        function deleteEntity() {
            let id = this.id;
            let uri = chooseDeletePath() + id;
            alert(uri);
            let options = { method : 'delete',
                               CORS: "Access-Control-Allow-Origin"};
            const response = fetch(uri, options);
            const text = response.text;
            if (response.ok) {
                alert("Delete ok");
                return JSON.parse(text);
            } else {
                alert("Error");
                throw new Error(text);
            }
        }

</script>
<style>
td.td {
	text-align: center;
	border-style: solid;
	border-width: 0 1px 1px 0;
	border-color: white;
	background: #BCEBDD;
	color: #52a5ff;
	text-shadow: 0 1px 1px #2D2020;
	padding: 5px;
}

td.a {
    text-align: center;
    border-style: solid;
    border-width: 0 1px 1px 0;
    border-color: white;
    background: #F8E391;
    color: #F56433;
    text-shadow: 0 1px 1px #2D2020;
    padding: 5px;
	border-top-right-radius: 10px;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
}

td.t {
	text-align: center;
	background: #BCEBDD;
	color: cyan;
	text-shadow: 0 1px 1px #2D2020;
	padding: 5px;
}

table {
	font-size: 18px;
	border-radius: 10px;
	border-spacing: 0;
	width: 80%;
}

th {
	color: #F56433;
	text-shadow: 0 1px 1px #2D2020;
	border-top-right-radius: 10px;
	border-top-left-radius: 10px;
	background: #F8E391;
}

td.th {
	color: #F56433;
	text-shadow: 0 1px 1px #2D2020;
	padding: 5px;
	background: #F8E391;
	border-style: solid;
	border-width: 0 1px 1px 0;
	border-color: white;
	text-align: center;
}

td.add {
	color: #F56433;
	text-shadow: 0 1px 1px #2D2020;
	padding: 5px;
	background: #a1f850;
	border-style: solid;
	border-width: 0 1px 1px 0;
	border-color: white;
	text-align: center;
}

td.td-bottom {
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
	padding: 10px;
	background: #F8E391;
	//border-style: solid;
	//border-width: 0 1px 1px 0;
	//border-color: white;
	text-align: center;
}

a:hover {
    color: #52a5ff;
    cursor: pointer;
}

a {
    color: #F56433;
}

input, select {
	color: #F56433;
	background: white;
	padding: 5px;
	border-color: black;
}

input.button:hover {
	background: #F8E391;
}

select:hover, input:hover {
	border-color: #52a5ff;
}
</style>

<table align="center">
    <tr>
        <td class="a" width="50%"><a on:click={showArtistResults}>Artists</a></td>
        <td class="a"><a on:click={showAlbumResults}>Albums</a></td>
    </tr>
    <tr>
        <td class="a"><a on:click={showTrackResults}>Tracks</a></td>
        <td class="a"><a on:click={showTrackListResults}>TrackLists</a></td>
    </tr>
    <tr>
        <td colspan="2" class="a"><a on:click={showUserResults}>Users</a></td>
    </tr>
</table>
<table align="center">
    <tr>
        <th class="th"><h2>{tableCap}</h2></th>
    </tr>
</table>
<table align="center" id="addDataTable">
    <tr id="addDataCell">
    {#if tableCap == artistString}
        <td class="td"><input id="artistNameInput" value="Name" on:focus={nameOnFocus} on:blur={nameOnBlur}></td>
        <td class="td"><a on:click={addArtist}>Add</a></td>
    {:else if tableCap == trackString}
        <td class="td"><input id="inputName" value="Name" on:focus={nameOnFocus} on:blur={nameOnBlur}></td>
        <td class="td">
            <select value="-1" id="chooseArtist">
                <option value="-1" selected disabled>Choose artist . . . </option>
                {#await selectData}
                {:then questions}
                {#each questions as question}
                			<option value={question.id}>
                				{question.name}
                			</option>
                		{/each}
                {/await}
            </select></td>
        <td class="td"><input id="inputTrackLength" value="TrackLength" on:focus={trackLengthOnFocus} on:blur={trackLengthOnBlur}></td>
        <td class="td">
            <select value="-1" id="chooseGenre">
                 <option selected disabled>Choose genre . . . </option>
                 {#await selectGenreData}
                 {:then questions}
                 {#each questions as question}
                            <option value={question}>
                                {question}
                            </option>
                 {/each}
                 {/await}
            </select></td>
        <td class="td"><a on:click={addTrack}>Add</a></td>
    {:else if tableCap == trackListString}
        <td class="add"><a on:click={showAddTrackListPage}>+++ Add TrackList +++</a></td>
    {:else if tableCap == albumString}
            <td class="add"><a on:click={showAddAlbumPage}>+++ Add Album +++</a></td>
    {:else if tableCap == userString}
            <td class="add"><a on:click={showAddUserPage}>+++ Add User +++</a></td>
    {:else if tableCap == addTrackListString}
        <td class="td"><input value="Name" on:focus={nameOnFocus} on:blur={nameOnBlur}></td>
        <td class="td"><a>Add</a></td>
    {:else if tableCap == addAlbumString}
        <td class="td"><input id="inputName" value="Name" on:focus={nameOnFocus} on:blur={nameOnBlur}></td>
                <td class="td">
                    <select value="-1" id="chooseArtist">
                        <option value="-1" selected disabled>Choose artist . . . </option>
                        {#await selectData}
                        {:then questions}
                        {#each questions as question}
                        			<option value={question.id}>
                        				{question.name}
                        			</option>
                        		{/each}
                        {/await}
                    </select></td>
                <td class="td"><a>Add</a></td>
    {:else if tableCap == addUserString}
        <td class="td"><input value="Name" on:focus={nameOnFocus} on:blur={nameOnBlur}></td>
        <td class="td"><a>Add</a></td>
    {/if}
    </tr>
</table>
<table align="center" id="dataTable">
{#await data}
	<tr><td class="td"><p>wait...data loading...</p></td></tr>
{:then value}
{#if tableCap == artistString}
    <tr>
        <td class="th">Id</td>
        <td class="th">Name</td>
        <td class="th"></td>
    </tr>
{#each value as item}
	<tr>
	    <td class="td">{item.id}</td>
	    <td class="td">{item.name}</td>
	    <td class="td"><a id={item.id} on:click={deleteEntity}>Delete</a></td>
	</tr>
{/each}
{:else if tableCap == trackString}
        <tr>
            <td class="th">Id</td>
            <td class="th">Name</td>
            <td class="th">Artist</td>
            <td class="th">TrackLength</td>
            <td class="th">Genre</td>
            <td class="th"></td>
        </tr>
{#each value as item}
    	<tr>
    	    <td class="td">{item.id}</td>
    	    <td class="td">{item.name}</td>
    	    <td class="td">{item.artist.name}</td>
    	    <td class="td">{item.trackLength}</td>
    	    <td class="td">{item.genre}</td>
    	    <td class="td"><a id={item.id} on:click={deleteEntity}>Delete</a></td>
    	</tr>
{/each}
{:else if tableCap == trackListString}
        <tr>
            <td class="th">Id</td>
            <td class="th">Name</td>
            <td class="th"></td>
            <td class="th"></td>
        </tr>
{#each value as item}
    	<tr>
    	    <td class="td">{item.id}</td>
    	    <td class="td">{item.name}</td>
    	    <td class="td"><a>Show tracks</a></td>
    	    <td class="td"><a id={item.id} on:click={deleteEntity}>Delete</a></td>
    	</tr>
{/each}
{:else if tableCap == albumString}
        <tr>
            <td class="th">Id</td>
            <td class="th">Name</td>
            <td class="th">Artist</td>
            <td class="th"></td>
            <td class="th"></td>
        </tr>
{#each value as item}
    	<tr>
    	    <td class="td">{item.id}</td>
    	    <td class="td">{item.name}</td>
    	    <td class="td">{item.artist.name}</td>
    	    <td class="td"><a>Show tracks</a></td>
    	    <td class="td"><a id={item.id} on:click={deleteEntity}>Delete</a></td>
    	</tr>
{/each}
{:else if tableCap == userString}
        <tr>
            <td class="th">Id</td>
            <td class="th">Name</td>
            <td class="th"></td>
            <td class="th"></td>
        </tr>
{#each value as item}
    	<tr>
    	    <td class="td">{item.id}</td>
    	    <td class="td">{item.name}</td>
    	    <td class="td"><a>Show tracks</a></td>
    	    <td class="td"><a id={item.id} on:click={deleteEntity}>Delete</a></td>
    	</tr>
{/each}
{:else if tableCap == addTrackListString}
        <tr>
            <td class="th"></td>
            <td class="th">Id</td>
            <td class="th">Name</td>
            <td class="th">Artist</td>
            <td class="th">TrackLength</td>
            <td class="th">Genre</td>
        </tr>
{#each value as item}
    	<tr>
    	    <td class="td"><input type="checkbox"></td>
    	    <td class="td">{item.id}</td>
    	    <td class="td">{item.name}</td>
    	    <td class="td">{item.artist.name}</td>
    	    <td class="td">{item.trackLength}</td>
    	    <td class="td">{item.genre}</td>
    	</tr>
{/each}
{:else if tableCap == addAlbumString}
        <tr>
            <td class="th"></td>
            <td class="th">Id</td>
            <td class="th">Name</td>
            <td class="th">Artist</td>
            <td class="th">TrackLength</td>
            <td class="th">Genre</td>
        </tr>
{#each value as item}
    	<tr>
    	    <td class="td"><input type="checkbox"></td>
    	    <td class="td">{item.id}</td>
    	    <td class="td">{item.name}</td>
    	    <td class="td">{item.artist.name}</td>
    	    <td class="td">{item.trackLength}</td>
    	    <td class="td">{item.genre}</td>
    	</tr>
{/each}
{:else if tableCap == addUserString}
        <tr>
            <td class="th"></td>
            <td class="th">Id</td>
            <td class="th">Name</td>
            <td class="th">Artist</td>
            <td class="th">TrackLength</td>
            <td class="th">Genre</td>
        </tr>
{#each value as item}
    	<tr>
    	    <td class="td"><input type="checkbox"></td>
    	    <td class="td">{item.id}</td>
    	    <td class="td">{item.name}</td>
    	    <td class="td">{item.artist.name}</td>
    	    <td class="td">{item.trackLength}</td>
    	    <td class="td">{item.genre}</td>
    	</tr>
{/each}
{/if}
{:catch error}
	<tr><td class="td"><p style="color: red">{error.message}</p></td></tr>
{/await}
</table>
<table align="center">
    <tr>
        <td class="td-bottom">&nbsp;</td>
    </tr>
</table>