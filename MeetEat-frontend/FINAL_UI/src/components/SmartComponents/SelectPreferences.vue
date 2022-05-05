<template>
<div style="padding:30px;">

  <form align="center">
    <h1>Diet</h1>
    <div class="form-group" style="text-align: center">
      <div class="row">
        <div class="col-sm-10">
          <div class="row">
            <article class=" col-sm">
              <input id="dairyFree" type="checkbox" v-bind:value="diaryFreeID"/>
              <div>
                  <span>
                    Dairy free
                  </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="glutenFree" type="checkbox" v-bind:value="glutenFreeID"/>
              <div>
                  <span>
                    Gluten free
                  </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="noPork" type="checkbox" v-bind:value="noPorkID"/>
              <div>
                  <span>
                    No pork
                  </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="vegan" type="checkbox" v-bind:value="veganID"/>
              <div>
                  <span>
                    Vegan
                  </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="Vegetarian" type="checkbox" v-bind:value="vegetarianID"/>
              <div>
                  <span>
                    Vegetarian
                  </span>
              </div>
            </article>

            <article class=" col-sm">
              <input id="Pesco-vegetarian" type="checkbox" v-bind:value="pescoID"/>
              <div>
                  <span>
                    Pesco-vegetarian
                  </span>
              </div>
            </article>

          </div>

        </div>
      </div>
    </div>

      <h1>Preferences</h1>
      <h4>Besides special diets, are there any ingredients you don't like ?</h4>
      <div class="form-group row" style="padding-left: 30%;">
        <div class="col-sm-6">
          <div class="row">
            <div class="col-sm"  id="listIngredients">
                <select name="m" id="w" class="form-control inputs">
                  <option  v-for="ing in ingredients" :key="ing.id" v-bind:value="ing.id">
                    {{ing.name}}
                  </option>
                </select>
            </div>
            <div class="col-sm-3"  id="listDeleteButton">
              <button type="button" class="btn btn-success"  @click='deleteIngredients(ingredient, numberOfIngredients)'>Delete</button>
            </div>
          </div>
          <br/>
          <button type="button" class="btn btn-success"  @click='addIngredients'>Add an ingredient</button>

        </div>
      </div>

    <button type="button" class="btn btn-success btn-block btn-lg" @click="save()">Save</button>

  </form>

</div>
</template>

<script>

import axios from "axios";
import {urlAPI} from "@/variables";

export default {
    name: "SelectPreferences",
  data(){
    return {
      ingredient : null,
      numberOfIngredients : 1,
      vegetarianID : 201,
      veganID : 202,
      pescoID : 203,
      diaryFreeID : 204,
      glutenFreeID : 205,
      noPorkID : 206,
      user : null,
      ingredients : [],
    }
  },
    methods: {
      save : function (){

        var ingredients = document.getElementsByClassName("inputs");
        var arr = [];
        for(var i =0; i < ingredients.length; i++){
          if(ingredients[i].value !== '' && ingredients[i].value !== null){
            arr.push(ingredients[i].value);
          }
        }
        console.log('ARR');

        console.log(arr);
        var diet = ["dairyFree",
          "glutenFree",
          "noPork",
          "vegan",
          "Vegetarian",
          "Pesco-vegetarian"];

        var dietChecked = [];
        for(var j=0; j<diet.length; j++){
          if(document.getElementById(diet[j]).checked){
            dietChecked.push(document.getElementById(diet[j]).value);
            console.log(document.getElementById(diet[j]))
          }
        }


        var atLeastOne = 0;
        console.log(dietChecked);
        var request = urlAPI+'todo=specifiyPreferences&userId='+this.user.id+'&requestPreferences=';
        for(let k = 0; k<dietChecked.length; k++){
          if(atLeastOne === 1){
            request += ','+dietChecked[k];
          }else{
            request += dietChecked[k];
            atLeastOne = 1;
          }
        }

        var atLeastOne2 = 0;
        for(let k = 0; k<arr.length; k++){
          if(atLeastOne2 === 1 || (atLeastOne2===0 && atLeastOne===1)){
            request += ','+arr[k];
          }else{
            request += arr[k];
            atLeastOne2 = 1;
          }
        }

        console.log(this.user);
        console.log(request);
        var resp;
        axios.get(request).then(response => (resp = response.data));
        console.log(resp);


        //specifiyPreferences
        //userId
      },
      addIngredients : function () {

        console.log('add');
        this.numberOfIngredients ++;
        var newInput = document.createElement("select");
        newInput.setAttribute("class", "form-control inputs");
        newInput.setAttribute("id", "ingredients"+this.numberOfIngredients);

        for(let i=0; i<this.ingredients.length; i++){
          var option = document.createElement("option");
          option.setAttribute("value", this.ingredients[i].id);
          option.innerText = this.ingredients[i].name;
          newInput.appendChild(option);
        }

        document.getElementById("listIngredients").appendChild(newInput);

        var br = document.createElement("br");

        var newButton = document.createElement("button");
        newButton.setAttribute("type", "button");
        newButton.setAttribute("class", "btn btn-success");
        newButton.setAttribute("id", "button"+this.numberOfIngredients);
        newButton.setAttribute("@click", this.deleteIngredients("ingredients"+this.numberOfIngredients, this.numberOfIngredients));

        newButton.innerText = "Delete";
        document.getElementById("listDeleteButton").appendChild(br);
        document.getElementById("listDeleteButton").appendChild(newButton);

      },

      deleteIngredients : function (ingredient, index)
      {
        console.log(ingredient + ' ' + index);

        //document.getElementById("button"+index).remove();
        //document.getElementById("ingredients"+index).remove();


        return 0;
      },

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

    },

    async mounted() {
      this.user = JSON.parse(sessionStorage.getItem("user"))
      this.user = this.user.user;

      await axios.get(urlAPI + 'todo=viewIngredients')
          .then(response => (this.ingredients = response.data));

      this.ingredients = this.ingredients.preferenceTags;

      console.log(this.ingredients.preferenceTags);
    }
}

</script>

<style scoped>



article {
  position: relative;
  margin: 5px;
  float: left;
  border: 2px solid black;
  box-sizing: border-box;
}

article div {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  line-height: 25px;
  transition: .5s ease;
}

article input {
  position: absolute;
  top: 0;
  left: 0;
  width: 140px;
  height: 100px;
  opacity: 0;
  cursor: pointer;
}

input[type=checkbox]:checked ~ div {
  background-color: green;
}

.btn-dark, .btn-success2{
  width: 50%;
  height: 50px;
  float: center;
  font-size: 20px;
}

.btn-success2{
  background-color: green;
  color: white;
}

.btn-dark{
  margin-left: 25%;
}

.btn-info{
  background-color: grey;
  margin-left: 20px;
}

.successPublish{
  color: gray;
  float: center;
  margin-left: 100px;
  margin-top: 30px;
  font-size: 20px;
}

input[type="checkbox"]{
  width: 100%;
  height: 100%;
}


/**
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
}**/

</style>