

const url = "http://localhost:4000/";
// Your projects will replace localhost with your AWS endpoint


document.getElementById("loginButton").addEventListener("click",loginFunction);


async function loginFunction(){
    let usern = document.getElementById("userName").value;
    let userp = document.getElementById("password").value;
    console.log(usern);
    console.log(userp);
    //This is us making our user JSON object, so that we can send it to our database
    let user = {
        userName: usern,
        password: userp
    }

    console.log(user);

    let response = await fetch(url + "login",{
        method: "POST", //This is what we are doing, we are created an employee, so it is a post request
        body: JSON.stringify(user), //This will turn our user object into JSON we can send
        credentials: "include"
    });

    console.log(response.status);

    if(response.status === 201){
        document.getElementById("loginRow").innerText = "Welcome to the Manager Menu!";
    }
    else if(response.status === 202){
        document.getElementById("loginRow").innerText = "Welcome to the Employee Menu!";
        window.location.href="Employee.html";
    }
    else{
        document.getElementById("loginRow").innerText = "Login Failed, please refresh the page!";
    }

}