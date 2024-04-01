const parent = document.querySelector(".parent");
const redirect = document.querySelector(".redirect");


//when page is loaded, insert question number 
addEventListener("DOMContentLoaded", (event) => {
    var questionText = "";
    var questionElement = document.createElement("questionEl");
    var parent = document.getElementById("prompt");
    var numberOfSongs = document.getElementById("MapSize");

    //if questionCount doesnt exist, always will be question 1, else use questionCount iterator
    if(localStorage["questionCount"]==null){
         questionText = document.createTextNode("Question 1" + " / " + numberOfSongs.value);
    }
    else{
         questionText = document.createTextNode("Question " + localStorage.getItem('questionCount') + " / " + numberOfSongs.value);
    }
    //add element before lyric prompt with created text
    questionElement.appendChild(questionText);
    document.body.insertBefore(questionElement, parent)

    if((localStorage.getItem('questionCount'))>=numberOfSongs){
        console.log("in m1");
        var finalQuestionButton = document.getElementById("SubmitButtonId");
        finalQuestionButton.value = "Submit Quiz";
       
    }
    numberOfSongs = Number(numberOfSongs.value);
    console.log(localStorage.getItem('questionCount'));
    console.log(numberOfSongs);
    //+1 since questioncount is started at 2 
    if((localStorage.getItem('questionCount'))>=numberOfSongs + 1 ){
        console.log("final question m2");
        console.log(numberOfSongs.value);
        var finalQuestionButton = document.getElementById("SubmitButtonId");
        finalQuestionButton.value = "Submit Quiz";
        location.href = "score";
    }
});
//clear storage after user is finished with questions 
$('.clearStorage').click(function(){
    console.log("clearing");
    localStorage.clear();
});

parent.addEventListener('click', e => {
       

});


$('.SubmitButton').click(function(){
    //get currently selected option 
    var playerGuess = $('#WhatToUpdate :selected').val();
    //get the correct answer for the question
    const answer = document.getElementById("AnswerName");
    //get the size of map of songs 
    const getCount = document.getElementById("MapSize");
    //get the question header
    const questionHeader = document.createElement;

    //pass through this if statement on 2nd question
    if(localStorage["questionCount"]==null){
        localStorage["questionCount"] = 2;
    }
    else{
        var question = (parseInt(localStorage.getItem('questionCount'))+1);
        localStorage.setItem("questionCount", question.toString());
    }

    //create local storage only on first iteration, once null, it wont reset back to 0 when page refreshes
     if(localStorage["score"]==null){
         localStorage["score"] = 0;
     }
 
    //  console.log("current question is " + localStorage.getItem("questionCount") );
     console.log(answer.value)
      if(playerGuess === answer.value){
        var attempts = (parseInt(localStorage.getItem('score'))+1);
        localStorage.setItem("score", attempts.toString());
         
      }
});

// redirectTest.addEventListener('click', e => {
//     console.log("redirect");
//     const currentURL = window.location.origin;
//     window.location.replace(currentURL+"/home");


// });




