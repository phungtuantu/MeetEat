<template>

  <div class="container">
    <div style="text-align: center">
      <h1>Set my Offer</h1>
    </div>
    <form>
      <div class="form-group">
        <img class="" id="mainPicture" src="../../assets/lasagne.jpg" height="400px" width="60%" >
      </div>

      <div class="form-group">
        <input type="file" id="csv" name="profile_pic"
               accept=".jpg">
      </div>

      <div class="form-group row">
        <label for="title" class="col-sm-2 col-form-label">Titre</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="title" v-model="title">
        </div>
      </div>
      <!--
      <div class="form-group row">
        <label for="ingredients" class="col-sm-2 col-form-label">Ingredients</label>
        <div class="col-sm">
            <div class="row">
              <div class="col-sm"  id="listIngredients">
                <input type="text" class="form-control" id="ingredients" v-model="ingredient">
              </div>
              <div class="col-sm"  id="listDeleteButton">
                <button type="button" class="btn btn-success" @click='deleteIngredients(ingredient, numberOfIngredients)'>Delete</button>
              </div>
            </div>
            <br/>
              <button type="button" class="btn btn-success" @click='addIngredients'>Add an ingredient</button>

        </div>
       </div>
        -->


      <div class="form-group row">
        <label for="description" class="col-sm-2 col-form-label">Description</label>
        <div class="col-sm">
          <textarea id="description" class="form-control" rows="3" v-model="description"></textarea>
        </div>
      </div>

      <div class="form-group row">
        <label for="specification" class="col-sm-2 col-form-label">More specification</label>
        <div class="col-sm-10">
          <textarea id="specification" class="form-control" rows="3" v-model="specification"></textarea>
        </div>
      </div>

      <div class="form-group row">
        <label for="typeOfCuisine" class="col-sm-2 col-form-label">Type of cuisine</label>
        <div class="col-sm">
          <select name="line" id="typeOfCuisine" class="form-select form-select-sm" aria-label=".form-select-sm" v-model="typeOfCuisine">
            <option v-for="cuisine in typeOfCuisineList.preferenceTags" :key="cuisine.id" v-bind:value="cuisine.id">
              {{ cuisine.name }}</option>
          </select>
        </div>
      </div>


      <div class="form-group row">
        <label for="specification" class="col-sm-2 col-form-label">More specification</label>
        <div class="col-sm-10">
          <div class="row">
            <article class=" col-sm" v-for="diet in ig.preferenceTags" :key="diet.id">
              <template v-if="diets.includes(diet.name)">
                <input v-bind:id="diet.id" type="checkbox" class="check" v-bind:value="diet.id" checked/>
                <div>
                <span>
                  {{ diet.name }}
                </span>
                </div>
              </template>
              <template v-if="!diets.includes(diet.name)">
                <input  v-bind:id="diet.id"  type="checkbox" class="check" v-bind:value="diet.id"  />
                <div>
                <span>
                     {{ diet.name }}
                </span>
                </div>
              </template>
            </article>
          </div>
        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Portions available</label>
        <div class="col-sm-10">
          <input type="number" class="form-control-plaintext" id="portions" v-model="portions">
        </div>
      </div>

      <div class="form-group row">
        <label for="suggestedPrice" class="col-sm-2 col-form-label">Price</label>
        <div class="col-sm-10">
          <input type="number" readonly class="form-control-plaintext" id="suggestedPrice" v-bind:value="price" disabled>
        </div>
      </div>




    </form>

    <br/>
    <br/>


    <div class="row">
      <div class="col-sm">
        <button type="button" class="btn btn-dark  btn-lg btn-block" @click='backHome()'>Cancel</button>

      </div>

      <div class="col-sm">
        <button type="button" class="btn btn-danger  btn-lg btn-block" @click='deleteOffer()'>Delete</button>

      </div>
    </div>


    <br/>
    <br/>

    <br/>
    <br/>


  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import {urlAPI} from "@/variables";

export default {
  name: "ModificationOffer",
  data() {
    return {

      title : '',
      description : '',
      specification : '',
      typeOfCuisine : '',
      portions : 0,
      ingredients : [],
      show : 0,
      date : null,
      numberOfIngredients : 1,
      idOffer : 236,
      offer : null,
      totalPortions : 1,
      diets : [],
      tagId : [],
      ig : [],
      typeOfCuisineList : [],
      price : '',

    }
  },
  methods: {
    addIngredients : function () {
      console.log('add');
      this.numberOfIngredients ++;
      var newInput = document.createElement("input");
      newInput.setAttribute("class", "form-control");
      newInput.setAttribute("id", "ingredients"+this.numberOfIngredients);
      newInput.setAttribute("type", "text");

      document.getElementById("listIngredients").appendChild(newInput);

      var br = document.createElement("br");

      var newButton = document.createElement("button");
      newButton.setAttribute("type", "button");
      newButton.setAttribute("class", "btn btn-success");
      newButton.innerText = "Delete";
      newButton.onclick = this.deleteIngredients("ingredients"+this.numberOfIngredients, this.numberOfIngredients);
      document.getElementById("listDeleteButton").appendChild(br);
      document.getElementById("listDeleteButton").appendChild(newButton);

    },

    deleteIngredients : function (ingredient, index)
    {
      console.log(ingredient + ' ' + index);
      return 0;
    },

    validateInformation : function ()
    {
      this.show = 1;
    },

    backHome : function () {
      router.replace('/');

    },

    deleteOffer : function () {
      console.log('delete');
    },

    save : function () {
      console.log('save');
      for(let i =0; i<document.getElementsByClassName('check').length; i++){
        if(document.getElementsByClassName('check')[i].checked){
          console.log(document.getElementsByClassName('check')[i].value);
        }
      }

    },
  },

  async mounted() {
    //this.idOffer = localStorage.getItem("itemId");
    await axios.get(urlAPI + 'todo=consultOffer&offerId=' + this.idOffer)
        .then(response => (this.offer = response.data));

    console.log(this.offer);

    this.title = this.offer.title;
    this.ingredients = this.offer.ingredients;
    this.description = this.offer.details;
    this.specification = this.offer.specifications;
    this.typeOfCuisine = this.offer.cuisines[0].name;
    this.totalPortions = this.offer.remainingPortions;
    this.numberOfIngredients = this.offer.ingredients.length;
    this.price = this.offer.price;

    for(var i=0; i<this.offer.diets.length; i++){
      this.diets.push(this.offer.diets[i].name);
      this.tagId[this.offer.diets[i].name] = this.offer.diets[i].id;
    }
    console.log(this.tagId['Vegan'])

    //viewIngredients
    await axios.get(urlAPI + 'todo=viewDiets')
        .then(response => (this.ig = response.data));

    console.log(this.ig)

    //viewCuisines
    await axios.get(urlAPI + 'todo=viewCuisines')
        .then(response => (this.typeOfCuisineList = response.data))

    console.log(this.typeOfCuisineList);

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

input[type="checkbox"]{
    width: 100%;
    height: 100%;
}

</style>