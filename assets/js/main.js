var menuItemElements = document.querySelectorAll(".navbar__menu-item")


for(let i = 0; i < menuItemElements.length; i++){
    menuItemElements[i].addEventListener("click", function(){
        for(let j = 0; j < menuItemElements.length; j++){
            menuItemElements[j].classList.remove("navbar__menu-item-actived");
        }
        menuItemElements[i].classList.add("navbar__menu-item-actived")
    })
}

// click Menu Btn on Mobile or Tablet

var menuElement = document.querySelector(".menu_MBTL i")
var menuContentElement = document.querySelector(".menu_MBTL-content")

menuElement.addEventListener("click", function(){
    if(menuContentElement.style.display == "none"){
        menuContentElement.style.display = "block";
    }else{
        menuContentElement.style.display = "none";
    }
})