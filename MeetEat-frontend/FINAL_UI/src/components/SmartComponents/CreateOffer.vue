<template>

  <div class="container">
    <div style="text-align: center">
      <h1>Create an Offer</h1>
    </div>
    <form>
      <div class="form-group">
        <img class="" id="mainPicture" src="../../assets/grey-square.png" height="400px" width="60%" >
      </div>

      <div class="form-group">
        <input type="file" id="csv" name="profile_pic"
               accept=".jpg">
      </div>

      <div class="form-group row">
        <label for="title" class="col-sm-2 col-form-label">Title</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" id="title" v-model="title">
        </div>
      </div>

      <div class="form-group row">
        <label for="ingredients" class="col-sm-2 col-form-label">Ingredients</label>
        <div class="col-sm">
            <div class="row">
              <div class="col-sm"  id="listIngredients">
                <select name="m" id="w" class="form-control inputs">
                    <option  v-for="ing in ingredients" :key="ing.id" v-bind:value="ing.id">
                      {{ing.name}}
                    </option>
                  </select>
              </div>
              <div class="col-sm"  id="listDeleteButton">
              </div>
            </div>
            <br/>
              <button type="button" class="btn btn-success" @click='addIngredients()'>Add an ingredient</button>
              <button type="button" class="btn btn-danger" @click='deleteIngredients(ingredient, numberOfIngredients)'>Delete last ingredient</button>
        </div>
        <div class="col-sm">
        </div>
      </div>

      <div class="form-group row">
        <label for="description" class="col-sm-2 col-form-label">Description</label>
        <div class="col-sm">
          <textarea id="description" class="form-control" rows="3" v-model="details"></textarea>
        </div>
      </div>

      <div class="form-group row">
        <label for="specification" class="col-sm-2 col-form-label">More specifications :</label>
        <div class="col-sm-10">
          <textarea id="specification" class="form-control" rows="3" v-model="specifications"></textarea>
        </div>
      </div>

      <div class="form-group row">
        <label for="typeOfCuisine" class="col-sm-2 col-form-label">Type of cuisine</label>
        <div class="col-sm">
          <select name="line" id="typeOfCuisine" class="form-select form-select-sm" aria-label=".form-select-sm" v-model="typeOfCuisine">
            <option  v-for="cui in cuisines" :key="cui.id" v-bind:value="cui.id">
                    {{cui.name}}
            </option>
          </select>
        </div>
      </div>


      <div class="form-group row">
        <label for="specification" class="col-sm-2 col-form-label">Diet </label>
        <div class="col-sm-10">
          <div class="row">
            
            <article class=" col-sm" v-for="die in diets" :key="die.id">
              <input id="die.name" class="col-sm-diet" type="checkbox" v-bind:value="die.id"/>
              <div>
                <span>
                  {{die.name}}
                </span>
              </div>
            </article>

          </div>

        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Number of servings</label>
        <div class="col-sm-10">
          <input type="number" min="1" name="quantity" step="1" class="form-control-plaintext" id="portions" v-model="portions">
        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Sale date</label>
        <div class="col-sm-10">
          <input type="date" class="form-control-plaintext" id="portion" v-model="saleDate">
        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Expiration date</label>
        <div class="col-sm-10">
          <input type="date" class="form-control-plaintext" id="portionBis" v-model="expirationDate">
        </div>
      </div>

      <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Sales address</label>
        <input type="text" name="" placeholder="Address" v-model="address">
        </div>

        <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">Zip code </label>
        <input type="text" name="" placeholder="Zip code" v-model="zipCode">
        </div>

        <div class="form-group row">
        <label for="portions" class="col-sm-2 col-form-label">City </label>
            <input type="text" name="" placeholder="City" v-model="city">
        </div>

        <div class="row">
        <label for="portions" class="col-sm-2 col-form-label">Check one or both </label>
            <article class="col-4">
              <input type="checkbox"/>
              <div>
                <span>
                  To take away
                </span>
              </div>
            </article>
            <article class="col-4">
              <input type="checkbox"/>
              <div>
                <span>
                  On site
                </span>
              </div>
            </article>
        </div>
        <br>

    </form>

    <div class="row">
        <div class="col-sm" id="validate">
            <button type="button" class="btn btn-success2" @click='validateInformation()'>Next step</button>
        </div>
    </div>


    <template v-if="show === 1">
      <form>
        <div class="form-group row">
          <label for="suggestedPriceMin" class="col-sm-2 col-form-label">Suggested price range</label>
          <input type="number" disabled readonly class="form-control-plaintext col-sm-1 price" id="suggestedPriceMin" v-model="suggestedPriceMin"> 
          <div class="form-control-plaintext col-1 price">
          -
          </div>
          <input type="number" disabled readonly class="form-control-plaintext col-sm-1 price" id="suggestedPriceMax" v-model="suggestedPriceMax">
        </div>

        <div class="form-group row">
          <label for="sellingPrice" class="col-sm-2 col-form-label">Sale price</label>
          <div class="col-sm-10">
            <input type="number" step="0.01" class="form-control-plaintext" id="sellingPrice" v-model="sellingPrice">
          </div>
        </div>

      </form>

      <button type="button" class="btn btn-success2" id="saveBtn" @click='save()'>Publish</button>
    </template>

    <template v-if="show === 2">
        <div class="row">
            <label class="successPublish">Your application has been published successfully</label>
            <button type="button" class="btn btn-info" id="okHome" @click='backHome()'> OK </button>
        </div>
    </template>

    <br/>
    <br/>


    <div class="row">
      <div class="col-sm">
        <br>
        <button type="button" class="btn btn-dark  btn-lg btn-block" id="cancelBtn" @click='backHome()'>Cancel</button>
      </div>
    <!--
      <div class="col-sm">
        <button type="button" class="btn btn-danger  btn-lg btn-block" @click='deleteOffer()'>Delete</button>
      </div>-->
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
      cuisines : [],
      ingredients : [],
      recipeIngredients : [],
      diets : [],
      title : '',
      description : '',
      specifications : '',
      typeOfCuisine : '',
      portions : 0,
      suggestedPriceMin : null,
      suggestedPriceMax : null,
      sellingPrice : 5.99,
      guessedTitle : '',
      ingredient : null,
      show : 0,
      date : null,
      numberOfIngredients : 1,
      city : '',
      zipCode : '',
      address : '',
      saleDate : null,
      expirationDate : null,
      strRequestEstimatePrice : '',
      offer: null,
      lastIngredient : [],

    }
  },
  methods: {

    addIngredients : function () {

        // console.log('add');
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
        // this.recipeIngredients = document.getElementsByClassName("form-control inputs");
        // for (var i=0;i<this.recipeIngredients.length;i++){
        //   console.log(this.recipeIngredients[i].value);
        // }
        /*
        var br = document.createElement("br");
        var newButton = document.createElement("button");
        newButton.setAttribute("type", "button");
        newButton.setAttribute("class", "btn btn-success");
        newButton.setAttribute("id", "button"+this.numberOfIngredients);
        //newButton.setAttribute("@click", this.deleteIngredients("ingredients"+this.numberOfIngredients, this.numberOfIngredients));
        newButton.innerText = "Delete";
        document.getElementById("listDeleteButton").appendChild(br);
        document.getElementById("listDeleteButton").appendChild(newButton);*/

      },

    deleteIngredients : function (ingredient, index)
      {
        console.log(ingredient + ' ' + index);
        if (this.numberOfIngredients>1) {
          this.numberOfIngredients--;
          document.getElementById(this.lastIngredient[this.numberOfIngredients]).remove();
          this.lastIngredient.pop();
        }
        
        // this.recipeIngredients.splice((this.recipeIngredients.length)-1);
        // this.recipeIngredients = document.getElementsByClassName("form-control inputs");
        // for (var i=0;i<this.recipeIngredients.length;i++){
        //   console.log(this.recipeIngredients[i].value);
        // }
        return 0;
      },

    validateInformation : async function ()
    {
      this.recipeIngredients = document.getElementsByClassName("form-control inputs");
      if(this.recipeIngredients.length==0){
        this.strRequestEstimatePrice='&ingredients=';
      }else{
        for (let i = 0; i < this.recipeIngredients.length; i++) {
          this.strRequestEstimatePrice += '&ingredients='+ this.recipeIngredients[i].options[this.recipeIngredients[i].selectedIndex].text;
        }
      }
     
      // for (let i=0; i< this.recipeIngredients.length;i++){
      //   // console.log(this.recipeIngredients[i].options[this.recipeIngredients[i].selectedIndex].text)
      //   // console.log(this.recipeIngredients[i].options[this.recipeIngredients[i].selectedIndex].value)
      // }
      
      var resultEstimate

      await axios.get(urlAPI + 'todo=estimatePrice' + this.strRequestEstimatePrice)
        .then(response => (resultEstimate = response.data));
      
      this.suggestedPriceMin=resultEstimate.min;
      this.suggestedPriceMax=resultEstimate.max;
      document.getElementById("validate").style.display = "none";
      // var url = "";
      // var list = document.getElementsByClassName("ingredient");
      // for (let item of list) {
      //     console.log(item.value);
      //     url+="&ingredients="+item.value;
      // }
      // await axios.get(urlAPI+'todo=estimatePrice&'+url).then(response => {
      //   this.suggestedPriceMin = response.data.min;
      //   this.suggestedPriceMax = response.data.max;
      //   this.guessedTitle = response.data.title;
      //   this.sellingPrice = (this.suggestedPriceMax+this.suggestedPriceMin)/2;
      // });
      this.show = 1;

    },

    backHome : function () {
      router.replace('/');

    },

    deleteOffer : function () {
      console.log('delete');
    },

    save : async function () {
     
      console.log('save');
      var makeOfferUrlIngredients='';
      this.recipeIngredients = document.getElementsByClassName("form-control inputs");
      if(this.recipeIngredients.length==0){
        makeOfferUrlIngredients='&ingredients=';
      }else{
        for (let i = 0; i < this.recipeIngredients.length; i++) {
          makeOfferUrlIngredients += '&ingredients='+ this.recipeIngredients[i].options[this.recipeIngredients[i].selectedIndex].value;
        }
      }
      console.log(makeOfferUrlIngredients)
      

      var makeOfferUrlDiets='';
      var selectedDiets=document.getElementsByClassName("col-sm-diet");
      for(let i =0;i<selectedDiets.length;i++){
        if (selectedDiets[i].checked){
            makeOfferUrlDiets += '&preferences='+ selectedDiets[i].value;
            console.log(selectedDiets[i].value);
        }
      }
      console.log(makeOfferUrlDiets)
      
      var expirationDateParsed = this.expirationDate.split('-');
      var fromDateDateParsed = this.saleDate.split('-');
      console.log(this.specifications);
      await axios.get(urlAPI + 'todo=makeOffer&fromDate='+fromDateDateParsed[2]+'-'+fromDateDateParsed[1]+'-'+fromDateDateParsed[0]+
                      '&title='+this.title+'&totalPortions='+this.portions+
                      '&details='+this.details+'&address='+this.address+
                      '&city='+this.city+'&zipcode='+this.zipCode+
                      '&expDate='+expirationDateParsed[2]+'-'+expirationDateParsed[1]+
                      '-'+expirationDateParsed[0]+'&photoPath='+makeOfferUrlIngredients+
                      '&preferences='+this.typeOfCuisine+makeOfferUrlDiets+
                      '&specifications='+this.specifications)
        .then(response => (this.offer = response.data));
      
      
      await axios.get(urlAPI + 'todo=setPrice&offerId='+this.offer.id+
                      '&price='+this.sellingPrice)
        .then(response => (this.offer = response.data));
      await axios.get(urlAPI + 'todo=publishOffer&offerId='+this.offer.id)
        .then(response => (this.offer = response.data));
      
      
      document.getElementById("saveBtn").style.display = "none";
      document.getElementById("cancelBtn").style.display = "none";
      this.show = 2;
    },

  },
  async mounted() {
    this.user = JSON.parse(sessionStorage.getItem("user"))
      this.user = this.user.user;
    await axios.get(urlAPI + 'todo=viewCuisines')
        .then(response => (this.cuisines = response.data.preferenceTags));
    await axios.get(urlAPI + 'todo=viewIngredients')
        .then(response => (this.ingredients = response.data.preferenceTags));
    await axios.get(urlAPI + 'todo=viewDiets')
        .then(response => (this.diets = response.data.preferenceTags));
    console.log(this.cuisines);
    console.log(this.diets);
    console.log(this.ingredients);

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

.price{
  text-align: center;
  display: flex;
  justify-content: center;
  align-content: center;
  flex-direction: column;
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


</style>