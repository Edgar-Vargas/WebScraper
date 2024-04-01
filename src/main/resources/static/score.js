addEventListener("DOMContentLoaded", (event) => {

    //subtract 1 from questioncount here since localstorage(questionCount) is started at 2 instead of 1
    var scoreText = document.createTextNode("Final Score is " + localStorage.getItem('score')+ 
    "/" + (Number(localStorage.getItem('questionCount')) -1));
   
    var scoreResult = document.getElementById("ScoreResult");

    scoreResult.appendChild(scoreText);

    localStorage.clear();

});

const redirectTest = document.querySelector(".redirect");
 
redirectTest.addEventListener('click', e => {
    console.log("redirect");
    const currentURL = window.location.origin;
    localStorage.clear();
    window.location.replace(currentURL+"/home");


});