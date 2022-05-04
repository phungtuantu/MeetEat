<template>
<div>
  <div class="form-row justify-content-md-left">
      <div class="left">
        <h1>Diet</h1>
      </div>
  </div>

    <section class="menu">
        <div class="left">
            <figure>
                <a href="">
                    <img src="../../assets/icons8-non-lactose-food-50.png" width="70px">
                </a>
                <figcaption>Dairy free</figcaption>
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-no-gluten-50.png" alt="no-gluten" width="70px">
                </a>
                <figcaption>Gluten free</figcaption>
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-no-pork-50.png" alt="no-pork" width="70px">
                </a>  
                <figcaption>No pork</figcaption>      
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-vegan-mark-50.png" alt="vegan-mark" width="70px">
                </a>  
                <figcaption>Vegan</figcaption>      
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-carrot-50.png" alt="vegetarian-carrot" width="70px">
                </a>  
                <figcaption>Vegetarian</figcaption>      
            </figure>
            <figure>
                <a href="">
                    <img src="../../assets/icons8-whole-fish-50.png" alt="pesco-vegetarian" width="70px">
                </a>  
                <figcaption>Pesco-vegetarian</figcaption>      
            </figure>
        </div>
    </section>

    <div class="left">
        <div class="form-row justify-content-md-left">
            <h1>Preferences</h1>
        </div>
        <div class="form-row justify-content-md-left">
            <h4>Besides special diets, are there any ingredients you don't like ?</h4>
        </div>

        <div class="form-row justify-content-md-left" id="dropdown-container">
            <div class="dropdown">
                <button @click="myFunction(3)" class="dropbtn" type="button" id="dropdown_btn">Find an ingredient</button>
                <div id="myDropdown3" class="dropdown-content">
                    <input type="text" placeholder="Search.." id="myInput" @keyup="filterFunction()">
                    <p value="peanut">Peanut</p>
                    <p value="garlic">Garlic</p>
                </div>
                <button class="button_suppr" type="button">Delete</button>
            </div>
            <div class="dropdown">
                <button @click="myFunction(2)" class="dropbtn" type="button" id="dropdown_btn">Find an ingredient</button>
                <div id="myDropdown2" class="dropdown-content">
                    <input type="text" placeholder="Search.." id="myInput" @keyup="filterFunction()">
                    <p value="peanut">Peanut</p>
                    <p value="garlic">Garlic</p>
                </div>
                <button class="button_suppr" type="button">Delete</button>
            </div>
            <div class="dropdown">
                <button @click="myFunction(1)" class="dropbtn" type="button" id="dropdown_btn">Find an ingredient</button>
                <div id="myDropdown1" class="dropdown-content">
                    <input type="text" placeholder="Search.." id="myInput" @keyup="filterFunction()">
                    <p value="peanut">Peanut</p>
                    <p value="garlic">Garlic</p>
                </div>
                <button class="button_add" type="button">Add</button>
            </div>
        </div>

        <div class="form-row justify-content-md-left">
            <button class="button_validate" type="button">Validate</button>
        </div>
    </div>
    
    <div class="form-row justify-content-md-left">
        <button class="button_save" type="button">Save</button>
    </div>

</div>
</template>

<script>

export default {
    name: "SelectPreferences",
    methods: {
        myFunction: function(id)
        {
          document.getElementById("myDropdown"+id.toString()).classList.toggle("show");
        },
        filterFunction: function()
        {
            var input, filter, div, txtValue, p, i;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            div = document.getElementById("myDropdown3");
            p = div.getElementsByTagName("p");
            for (i = 0; i < p.length; i++) {
                txtValue = p[i].textContent || p[i].innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    p[i].style.display = "";
                } else {
                    p[i].style.display = "none";
                }
            }
        },
        /**addIngredients : function () {
            var newDropDown = document.createElement("div");
            newDropDown.setAttribute("class", "dropdown");
            
            // Button
            var button_drop = document.createElement("button");
            button_drop.setAttribute("class", "dropbtn");
            button_drop.setAttribute("type", "button");
            button_drop.innerHTML = "Find an ingredient";
            button_drop.style.position = "relative";
            button_drop.style.display = "inline-block";
            button_drop.style.marginTop = "10px";
            button_drop.style.width = "200px";
            button_drop.style.background = "white";
            button_drop.style.textAlign = "center";
            button_drop.style.border = "2px solid black";
            button_drop.style.color = "black";
            button_drop.style.textTransform = "uppercase";
            button_drop.style.borderRadius = "5px";

            // dropdown-content = input_text + p
            var dropdown_content = document.createElement("div");
            dropdown_content.setAttribute("id", "myDropdown2");
            var input_text = document.createElement("input");
            input_text.setAttribute("type", "text");
            input_text.setAttribute("placeholder", "Search..");
            var p1 = document.createElement("p");
            p1.setAttribute("value", "peanut");
            p1.innerHTML = "Peanut";
            var p2 = document.createElement("p");
            p2.setAttribute("value", "garlic");
            p2.innerHTML = "Garlic";
            dropdown_content.appendChild(input_text);
            dropdown_content.appendChild(p1);
            dropdown_content.appendChild(p2);

            dropdown_content.style.display = "None";
            dropdown_content.style.position = "absolute";
            dropdown_content.style.backgroundColor = "#f6f6f6";
            dropdown_content.style.minWidth = "230px";
            dropdown_content.style.overflow = "auto";
            dropdown_content.style.border = "1px solid #ddd";
            dropdown_content.style.zIndex = "1";
            dropdown_content.style.textAlign = "center";
            p1.style.color = "black";
            p1.style.padding = "12px 16px";
            p1.style.display = "block";
            p2.style.color = "black";
            p2.style.padding = "12px 16px";
            p2.style.display = "block";

            newDropDown.appendChild(button_drop);
            newDropDown.appendChild(dropdown_content);
            document.getElementById("dropdown-container").appendChild(newDropDown);
            button_drop.addEventListener("click", this.myFunction(2));
        },**/
    }
}

</script>

<style scoped>

#myInput {
  box-sizing: border-box;
  background-position: 14px 12px;
  background-repeat: no-repeat;
  font-size: 16px;
  padding: 14px 20px 12px 45px;
  border: none;
  border-bottom: 1px solid #ddd;
}

#myInput:focus {outline: 3px solid #ddd;}

.dropbtn {
  position: relative;
  display: inline-block;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: #DCDCDC;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f6f6f6;
  min-width: 230px;
  overflow: auto;
  border: 1px solid #ddd;
  z-index: 1;
  text-align: center;
}

.dropdown-content p {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown p:hover {background-color: #ddd;}

.show {display: block;}

.left {
    text-align: left;
    margin-left: 20%;
    margin-top: 50px;
}

.menu figure {
    display: inline-block;
    margin-right: 5%;
}

.figcaption{
    text-align: center;
}

.dropbtn {
    margin-top: 10px;
    width: 200px;
    background: white;
    text-align: center;
    border: 2px solid black;
    color: black;
    text-transform: uppercase;
    border-radius: 5px;
}

.button_suppr, .button_add{
    margin-left: 20px;
    margin-top: 10px;
    width: 100px;
    background-color: green;
    text-transform: none;
    border: 0;
    background: green;
    text-align: center;
    border: 2px solid black;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
}

.button_suppr:hover, .button_suppr:focus{
    background-color: #66CC00
}

.button_add:hover, .button_add:focus{
    background-color: #66CC00
}

.button_validate{
    margin-top: 10px;
    width: 200px;
    background-color: green;
    background: green;
    text-align: center;
    border: 2px solid black;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
}

.button_validate:hover, .button_validate:focus{
    background-color: #66CC00
}

.button_save{
    margin-left: 17%;
    margin-top: 30px;
    width: 800px;
    Height: 50px;
    background-color: green;
    background: green;
    text-align: center;
    border: 2px solid black;
    color: white;
    text-transform: uppercase;
    border-radius: 5px;
}

.button_save:hover, .button_save:focus{
    background-color: #66CC00
}

</style>