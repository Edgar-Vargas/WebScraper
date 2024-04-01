const redirectTest = document.querySelector(".redirect");
const redirectCircles = document.querySelector(".Circles");

redirectTest.addEventListener('click', e => {
    console.log("redirect");
    const currentURL = window.location.origin;
    window.location.replace(currentURL+"/quiz2");


});

redirectCircles.addEventListener('click', e => {
    console.log("redirect");
    const currentURL = window.location.origin;
    window.location.replace(currentURL+"/quiz1");


});