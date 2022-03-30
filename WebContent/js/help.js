function buttonBasics() {
    document.getElementById("contentBasics").style.display = "block";
    document.getElementById("contentCharSkills").style.display = "none";
    document.getElementById("contentRankins").style.display = "none";
}
function buttonCharsSkills() {
    document.getElementById("contentBasics").style.display = "none";
    document.getElementById("contentCharSkills").style.display = "block";
    document.getElementById("contentRankins").style.display = "none";
}
function buttonRanking() {
    document.getElementById("contentBasics").style.display = "none";
    document.getElementById("contentCharSkills").style.display = "none";
    document.getElementById("contentRankins").style.display = "block";
}

