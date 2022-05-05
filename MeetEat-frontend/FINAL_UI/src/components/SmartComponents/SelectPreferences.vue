<template>
<div style="padding:30px;">

  <form align="center">
    <h1>Diet</h1>
    <div class="form-group" style="text-align: center">
      <div class="row dietButton">
        <div class="col-sm-10">
          <div class="row dietButton2">

            <article class=" col-sm" v-for="diet in diets" :key="diet.id">
              <template v-if="myDiets.includes(diet.id)">
                <input class="check" v-bind:id="diet.id" type="checkbox" v-bind:value="diet.id" checked/>
                <div>
                  <span>
                    {{diet.name}}
                  </span>
                </div>
              </template>
              <template v-if="!myDiets.includes(diet.id)">
                <input class="check" v-bind:id="diet.id" type="checkbox" v-bind:value="diet.id"/>
                <div>
                  <span>
                    {{diet.name}}
                  </span>
                </div>
              </template>

            </article>

          </div>

        </div>
      </div>
    </div>

      <h1>Preferences</h1>
      <h4>Besides special diets, are there any ingredients you don't like ?</h4>
      <template v-if="myIngr.length >= 0">
        <div class="form-group row" style="padding-left: 30%;">
          <div class="col-sm-6">
            <div class="row"  v-for="ingr in myIngr" :key="ingr.id">
              <div class="col-sm"  id="listIngredients" >
                <select name="m" id="w" class="form-control inputs">
                  <option selected v-bind:value="ingr.id">{{ingr.name}}</option>
                  <option  v-for="ing in ingredients" :key="ing.id" v-bind:value="ing.id">
                    {{ing.name}}
                  </option>
                </select>
              </div>
              <div class="col-sm-3"  id="listDeleteButton">
              </div>
            </div>
            <template v-if="myIngr.length === 0">
              <div class="row" >
                <select class="form-control inputs">
                  <option  v-for="ing in ingredients" :key="ing.id" v-bind:value="ing.id">
                    {{ing.name}}
                  </option>
                </select>
              </div>
            </template>
            <br/>
            <button type="button" class="btn btn-success"  @click='addIngredients'>Add an ingredient</button>
            <button type="button" class="btn btn-danger"  @click='deleteIngredients()'>Delete last ingredient</button>

          </div>
        </div>
      </template>

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
      lastIngredient : [],
      diets :[],
      myDiets : [],
      myIngr : [],
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

        var arr2 = [];
        var diets = document.getElementsByClassName("check");
        for(var j=0; j<diets.length; j++) {
          if(diets[j].checked) {
            arr2.push((diets[j].value));
          }
        }


        console.log(arr2);
        var pref = this.user.preferences;
        console.log(pref)
        var request = urlAPI+'todo=specifiyPreferences&userId='+this.user.id;
        for(let k =0; k<arr.length; k++) {
          request += '&requestPreferences=' + arr[k];
          for(let m=0; m<this.ingredients; m++){
            if(this.ingredients[m].id === arr[k]){
              let obj = {
                id : this.ingredients[m].id,
                name : this.ingredients[m].name
              }
              pref.push(obj);
            }
          }
          //this.user.preferences.push(arr[k]);

        }
          for(let k =0; k<arr2.length; k++){
            request+= '&requestPreferences='+arr2[k];
            //this.user.preferences.push(arr2[k]);
            for(let m=0; m<this.diets; m++){
              if(this.diets[m].id === arr2[k]){
                let obj = {
                  id : this.diets[m].id,
                  name : this.diets[m].name
                }
                pref.push(obj);
              }
            }

          }

          console.log(pref);






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
        this.lastIngredient[this.numberOfIngredients-1] = newInput.getAttribute("id");
        
        for(let i=0; i<this.ingredients.length; i++){
          var option = document.createElement("option");
          option.setAttribute("value", this.ingredients[i].id);
          option.innerText = this.ingredients[i].name;
          newInput.appendChild(option);
        }

        document.getElementById("listIngredients").appendChild(newInput);

        //var br = document.createElement("br");
        /*
        var newButton = document.createElement("button");
        newButton.setAttribute("type", "button");
        newButton.setAttribute("class", "btn btn-success");
        newButton.setAttribute("id", "button"+this.numberOfIngredients);
        //newButton.setAttribute("@click", this.deleteIngredients("ingredients"+this.numberOfIngredients, this.numberOfIngredients));
        newButton.innerText = "Delete";*/
        //document.getElementById("listDeleteButton").appendChild(br);
        //document.getElementById("listDeleteButton").appendChild(newButton);

      },

      deleteIngredients : function ()
      {

        if (this.numberOfIngredients>1) {
          this.numberOfIngredients--;
          //document.getElementById(this.lastIngredient[this.numberOfIngredients]).remove();
          console.log(this.numberOfIngredients);
          console.log(document.getElementsByClassName("inputs")[this.numberOfIngredients]);
          document.getElementsByClassName("inputs")[this.numberOfIngredients].remove();
          //this.lastIngredient.pop();
        }
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
      this.user = JSON.parse(sessionStorage.getItem("user"));
      this.user = this.user.user;
      console.log(this.user);

      await axios.get(urlAPI + 'todo=viewIngredients')
          .then(response => (this.ingredients = response.data));

      this.ingredients = this.ingredients.preferenceTags;

      await axios.get(urlAPI + 'todo=viewDiets')
          .then(response => (this.diets = response.data));
      this.diets = this.diets.preferenceTags;

      var allDiet = [];
      for(let i=0; i<this.diets.length; i++){
        allDiet.push(this.diets[i].id);
      }

      var allIng = [];

      for(let i=0; i<this.ingredients.length; i++){
        allIng.push(this.ingredients[i].id);
      }



      for(let i=0; i<this.user.preferences.length; i++){
        if(allDiet.includes(this.user.preferences[i].id)){
          this.myDiets.push(this.user.preferences[i].id);
        }
        if(allIng.includes(this.user.preferences[i].id)){
          this.myIngr.push(this.user.preferences[i]);
        }
      }

      this.numberOfIngredients = this.myIngr.length;


      console.log(this.ingredients);
      console.log(this.user);
      console.log(this.myDiets);
      console.log(this.myIngr);

    }
}

</script>

<style scoped>

.dietButton{
  margin-left: 10%;
}

.dietButton2{
  margin-bottom: 30px;
}

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

.btn-danger{
  margin-left: 15px;
  background-color: brown;
  font-size: 15px;
}

/*
.btn-block{
  margin-left: 25%;
  width: 50%;
}
*/
</style>