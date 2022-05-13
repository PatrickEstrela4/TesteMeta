function requestFiles(data){
    let httpRequest = new XMLHttpRequest();
    httpRequest.open("POST","http://localhost:8080/api/link",false);
    httpRequest.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    httpRequest.send(JSON.stringify(data));
}


function makeRow(file){
    var row = document.createElement("tr");

    var TDextension = document.createElement("td");
    var TDcount = document.createElement("td");
    var TDlines = document.createElement("td");   

    

    TDextension.innerHTML = file.extension;
    TDcount.innerHTML = file.count; 
    TDlines.innerHTML = file.lines;


    row.appendChild(TDextension);
    row.appendChild(TDcount);
    row.appendChild(TDlines);

    
    return row

}


function makeTable() {
    let UserTableBudy = document.getElementById('FileTableBudy');
    UserTableBudy.innerHTML="";

    var data ={
        "link" : document.getElementById("link_git").value,
    }

    var list = JSON.parse(requestFiles(data));

    list.forEach(element => {
        let row = makeRow(element);
        UserTableBudy.appendChild(row);
    });
}