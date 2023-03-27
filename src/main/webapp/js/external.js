/**
 * 
 */
 
 const offcanva = document.getElementById("wrapper");
const toggleBtn = document.getElementById("menu-toggle");

toggleBtn.onclick = function() {
    offcanva.classList.toggle("toggled");
    console.log("ey");


}

