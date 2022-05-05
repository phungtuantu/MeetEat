<template>
  <div class="container">
    <template v-if="offer !== null">
    <div class="card mb-3">
      <br/>
      <div style="text-align: left; margin-bottom: 30px;">
        <button type="button" class="btn btn-dark" style="width: 70px;" @click="back()">Back</button>
      </div>

        <img class="card-img-top" id="mainPicture"
           v-bind:src="offer.image"
           alt="Meal image">
      <div class="card-body" style="text-align: left;">
        <h5 class="card-title">{{offer.title}}</h5>
        <div class="row">
          <div class="col-4">
            <img v-bind:src="offer.cook.image" alt="cookImage" width="120px"> <!--  height="100px" -->
          </div>
          <div class="col-8">
            <div class="row">
              {{offer.cook.firstName}}  {{offer.cook.lastName}}
            </div>
            <div class="row">
              <template v-for="img in stars" >
                <template v-if="img === 1">
                  <img src="../../assets/blackStar.png" v-bind:key="img" alt="blackStar" width="20px" height="20px">
                </template>
                <template v-if="img === 0">
                  <img src="../../assets/grayStar.png" v-bind:key="img" alt="grayStar" width="20px" height="20px">

                </template>

              </template>
              {{offer.cook.rating}}/5 ({{offer.cook.numberOfReviews}}reviews)
            </div>
          </div>
        </div>

        <br/>
        <h5>List of ingredients : </h5>
        <ul class="list-group" style="padding-left: 30px;">
          <li class="" v-for="ingredient in offer.ingredients" :key="ingredient.id">{{ingredient.name}}</li>
        </ul>
        <br/>
        <div class="row">
          <div class="col-sm">
            <h5>More specifications : </h5>
            <ul class="list-group" style="padding-left: 30px;">
              <li class="" v-for="specification in offer.specifications" :key="specification">{{specification}}</li>
            </ul>
          </div>
          <div class="col-sm">
            <h5>Type of cooking : </h5>
            <ul class="list-group" style="padding-left: 30px;">
              <li class="">Italian food</li>
            </ul>
          </div>
        </div>

        <br/>

        <p class="card-text">If you have any food allergies or special dietary requirements, please notify {{offer.cook.firstName}}
          directly before placing your order.</p>
        <p class="card-text"><small class="text-muted">{{offer.remainingPortions}} portions left</small></p>
        <div class="form-outline" id="counter">
          <label for="qty" class="form-label">Quantity : </label>
          <input class="form-control" min="1" name="quantity" step="1" type="number" id="qty"
                 v-model="qtyOrdered">
        </div>
        <br/>
        <button type="button" class="btn btn-dark" @click="addToBasket()">Add to basket </button>
      </div>


    </div>
    </template>
  </div>
</template>

<script>
import {urlAPI} from "@/variables";
import axios from "axios";
import router from "@/router";

export default {
  name: "Offer_PageMain_Content",
  data (){
    return {

      idOffer: null,
      offer : null,
      stars : [],
      qtyOrdered : 1,

    }
  },
  methods : {
    back : function ()
    {
      router.replace('/orderPage')
    },

    addToBasket : function ()
    {
      // console.log(this.offer)
      var arr = JSON.parse(sessionStorage.getItem("basket"));
      if(arr === null){
        arr = [];
      }
      var newItem = {
        order : this.offer,
        qty : this.qtyOrdered,
      };

      arr.push(newItem);

      sessionStorage.setItem("basket", JSON.stringify(arr));
      
      // console.log(arr)

      router.replace('/orderPage');

    }
  },

  async mounted() {
    this.idOffer = localStorage.getItem("itemId");
    await axios.get(urlAPI + 'todo=consultOffer&offerId=' + this.idOffer)
        .then(response => (this.offer = response.data));

    for (let i = 0; i < 5; i++) {
      this.stars[i] = 0;
    }
    for (let i = 0; i < this.offer.cook.rating; i++) {
      this.stars[i] = 1;
    }

    this.offer.specifications = this.offer.specifications.split(',');

    localStorage.setItem("offer",this.offer)
    // console.log(this.offer);
    // console.log(JSON.parse(sessionStorage.getItem("basket")));



  }
}
</script>

<style scoped>

.card {
  align-items: center;
  margin-bottom: 30px;
}

.card #mainPicture {
  width: 60%;
  border-top-right-radius: 0;
  border-bottom-left-radius: calc(0.25rem - 1px);
}

@media only screen and (max-width: 768px) {

  .card-body {
    padding: 0.5em 1.2em;
  }

  .card-body .card-text {
    margin: 0;
  }

  .card #mainPicture {
    width: 60%;
  }
}

@media only screen and (max-width: 1200px) {
  .card #mainPicture {
    width: 60%;
  }
}

button, #counter {
  width: 100%;
}
</style>