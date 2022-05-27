const url = "http://localhost:4000/";
// Your projects will replace localhost with your AWS endpoint

document.getElementById("getReimbursement").addEventListener("click",getReimbursements);


async function getReimbursements(){
    let response = await fetch(url + "Reimbursement", {credentials: "include"});

    console.log(response);

    if(response.status === 200){
        let data = await response.json(); //Our response is NOT JSON... Its something else. So .json() Parses our response into a JSON object

        console.log(data);

        //For every Employee object we get, put it in the table please!
        for(let Reimbursement of data){
            //This specifically creates a variable that is a row
            let row = document.createElement("tr");
            //creates a data cell for each employee field
            let cell = document.createElement("td");
            //fill the cell with our employee data
            cell.innerHTML = Reimbursement.id;
            //add the td element (our data cell) to the tr element (our tables row)
            row.appendChild(cell); // This specifically adds this cell to the above roll


            //creates a data cell for each employee field
            let cell2 = document.createElement("td");
            //fill the cell with our employee data
            cell2.innerHTML = Reimbursement.type;
            //add the td element (our data cell) to the tr element (our tables row)
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            //fill the cell with our employee data
            cell3.innerHTML = Reimbursement.Resolver;
            //add the td element (our data cell) to the tr element (our tables row)
            row.appendChild(cell3);


            //creates a data cell for each employee field
            let cell4 = document.createElement("td");
            //fill the cell with our employee data
            cell4.innerHTML = Reimbursement.Description;
            //add the td element (our data cell) to the tr element (our tables row)
            row.appendChild(cell4);


            //creates a data cell for each employee field
            let cell5 = document.createElement("td");
            //fill the cell with our employee data
            cell5.innerHTML = Reimbursement.Amount;
            //add the td element (our data cell) to the tr element (our tables row)
            row.appendChild(cell5);

            let cell6 = document.createElement("td");
            //fill the cell with our employee data
            cell6.innerHTML = Reimbursement.status;
            //add the td element (our data cell) to the tr element (our tables row)
            row.appendChild(cell6);

            //We want to loop this, so we need to attach this row
            document.getElementById("reimBody").appendChild(row); //This right here, actually add our row to our HTML
        }
    }
}
