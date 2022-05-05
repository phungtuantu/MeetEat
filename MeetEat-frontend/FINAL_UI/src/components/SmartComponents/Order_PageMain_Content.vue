<template>
  <div class="row">

    <div class="col-3 border-right " style="text-align: left; margin-left: 25px;">

      <div class="input-group">
        <button class="btn btn-success" type="button" @click="searchOffers()">
          Search
        </button>
      </div>

      <br/>

      <h4>Price Range</h4>

      <div class="row">
        <div class="lblMaxprice">
          <label id="lblMaxprice"> Max price </label>
          <input type="number" id="maxPrice" name="maxPrice" min="1" max="20" v-model="priceLimit">
        </div>
      </div>


      <br/>

      <h4>Diets</h4>

      <div class="" v-for="diet in diets" :key="diet.id">
        <article class="col-sm dietcol">
          <input id="diet" type="checkbox" v-bind:value="diet.id"/>
          <div>
            <span>
              {{diet.name}}
            </span>
          </div>
        </article>

      </div>



      <h4>Food type</h4>


      <div class="form-check" v-for="cuisine in cuisines" :key="cuisine.id">
        <input class="form-check-input cuisinecheckbox" type="checkbox" v-bind:value="cuisine.id">
        <label class="form-check-label" for="foodType">
          {{cuisine.name}}
        </label>
      </div>




    </div>
    


    <div class="col-8">
      <div style="text-align: right; margin-bottom: 30px;">
        <button type="button" class="btn btn-dark" @click="seeBasket()">My basket</button>
      </div>

      <p class="noOffer" id="noOffer">
            No offer
      </p>

      <div class="card" v-for="offer in orders" :key="offer.id">
        <img class="card-img-top"
             v-bind:src="offer.image"/>
        <div class="card-body" style="text-align: left;">
          <h5 class="card-title">{{offer.title}}</h5>
          <p class="card-text">
            <b>At {{offer.distanceToUser}}m from your position</b><br/>
            {{offer.description }}
          </p>
          
          <p class="">
            <b>Price : {{offer.price }}$</b><br/>
          </p>
          <br/>

          <button class="btn btn-dark" type="button" @click="viewDetails(offer.id)">View details</button>

        </div>
      </div>
    </div>




  </div>

</template>

<script>
import {urlAPI} from "@/variables";
import axios from "axios";
import router from "@/router";

export default {
  name: "Order_PageMain_Content",

  data () {
    return {
      city : "",
      orders : [],
      diets : [],
      cuisines : [],
      priceLimit : 20,
    }
  },
  methods : {
    seeBasket : function (){
      router.replace('/myBasket')
    },

    viewDetails : function (id){
      localStorage.setItem("itemId", id);
      router.replace('orderPage/'+id);
    },
    searchOffers : async function (){
      this.city = sessionStorage.getItem("city");
      var searchOffersUrl = urlAPI + 'todo=searchOffers&priceLimit=' + this.priceLimit +'&address='+
                            this.city + '&requestPreferences=';
      let preferences = [];
      let cuisinecheckboxes = document.getElementsByClassName("form-check-input cuisinecheckbox");
      let dietcheckboxes = document.getElementsByClassName("col-sm dietcol")

      for (var i=0; i< dietcheckboxes.length; i++){
          if (dietcheckboxes[i].getElementsByTagName("input")[0].checked){
              preferences.push(dietcheckboxes[i].getElementsByTagName("input")[0].value);
          }
      }
      for (i=0; i< cuisinecheckboxes.length; i++){
          if (cuisinecheckboxes[i].checked){
              preferences.push(cuisinecheckboxes[i].value);
          }
      }

      // console.log(preferences);

      for (i=0; i<preferences.length-1; i++){
          searchOffersUrl+=preferences[i]+'&requestPreferences=';
      }
      if (preferences.length!==0){
          searchOffersUrl+=preferences[preferences.length-1];
      }

      // console.log(searchOffersUrl)

      await axios.get(searchOffersUrl)
        .then(response => (this.orders = response.data));

      if (!this.orders.hasResults){
          document.getElementById("noOffer").style.display="block";
      } else{
          document.getElementById("noOffer").style.display="none";
      }

      this.orders = this.orders.offers
    },

  },
  async mounted() {
    this.city = sessionStorage.getItem("city");
    var resultConsultOffer;
    await axios.get(urlAPI + 'todo=consultOffers&address=' + this.city)
        .then(response => (resultConsultOffer = response.data));
    this.orders = resultConsultOffer.offers;
    for (let i=this.orders.length-1; i>=0;i--){
      this.orders[i].distanceToUser=Math.round(this.orders[i].distanceToUser)
      if (this.orders[i].distanceToUser>2000){
        this.orders.splice(i,1);
      }
    }
    await axios.get(urlAPI + 'todo=viewDiets')
        .then(response => (this.diets = response.data.preferenceTags));
    await axios.get(urlAPI + 'todo=viewCuisines')
        .then(response => (this.cuisines = response.data.preferenceTags));
    
    if (resultConsultOffer.hasResults){
      document.getElementById("noOffer").style.display="none";
    }

    // console.log(this.orders);
    // console.log(this.city);
  }
}
</script>

<style scoped>
.card {
  flex-direction: row;
  align-items: center;
  margin-bottom: 30px;
}

.card-title {
  font-weight: bold;
}

.card img {
  width: 30%;
  border-top-right-radius: 0;
  border-bottom-left-radius: calc(0.25rem - 1px);
}

@media only screen and (max-width: 768px) {

  .card-body {
    padding: 0.5em 1.2em;
  }

  .lbl

  .card-body .card-text {
    margin: 0;
  }

  .card img {
    width: 50%;
  }
}

@media only screen and (max-width: 1200px) {
  .card img {
    width: 40%;
  }
}

.lblMaxprice{
  margin-left: 20px;
  font-style: oblique;
  font-size: 18px;
}

.lblMaxprice input[type=number]{
  margin-left: 20px;
  border-radius: 5px;
  background-color: #e0e0e0;
}



article {
  position: relative;

  margin: 5px;
  float: left;
  border: 2px solid black;
  box-sizing: border-box;
}

.priceItem {
  background-color: #191919;
  color: white;
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
  background-color: #0077B5;
}

.social i:before {
  width: 14px;
  height: 14px;
  position: fixed;
  color: #fff;
  background: #0077B5;
  padding: 10px;
  border-radius: 50%;
  top: 5px;
  right: 5px;
}


input[type="checkbox"]{
    width: 100%;
    height: 100%;
}

@keyframes slidein {
  from {
    margin-top: 100%;
    width: 300%;
  }

  to {
    margin: 0%;
    width: 100%;
  }
}

.btn{
  margin-left: 15px;
  margin-top: 10px;
  margin-bottom: 10px;
}

.btn-success{
  width: 90%;
}

.form-check-label .lbl { /* input[type="checkbox"] */
  margin-right: 10px;
}

</style>