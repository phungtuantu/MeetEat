<template>
<div class="container" >
    <div class = "card" v-for="offer in offers" :key="offer.id">
        <template v-if= "offer.state === 'SOLDOUT' || offer.state ==='UNAVAILABLE'">
            <div class="row" >
                <div class="col-6">
                    <img v-bind:src= "offer.image" width="300px">
                </div>
                <div class="col-6">
                    <div class="column">
                        <label style="font-weight: bold; font-size: 20px">{{offer.title}}</label><br>
                        <label class="rating" style="font-weight: bold">Rating : {{offer.cook.rating}}/5</label> <br>
                        <label style="font-weight: bold">Expiration date : {{offer.expirationDate}}</label><br>
                        <label>Description : {{offer.details}}</label><br>
                        <label>Number of sold servings : {{offer.soldPortions}}</label><br>
                        <label style="font-weight: bold; font-size: 18px">Price : {{offer.price}}€</label> <br>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <!-- espace sous image-->
                </div>
                <div class="col-6">
                    <div class="column">
                        <button class="btn btn-edit">
                            Details
                        </button>
                    </div>
                </div>
            </div>
        </template>
    </div>
</div>
</template>

<script>
import axios from "axios";
import {urlAPI} from "@/variables";
export default {
  name: "OnGoingOffers",

  data(){
      return{
          offers:[],
      }
  },

  async mounted() {

    await axios.get(urlAPI + 'todo=viewOffersHistory')
        .then(response => (this.offers = response.data.offers));

    console.log(this.offers);

  }
}
</script>

<style scoped>
.container {
    width: 100%;
}

.col-6 {
    margin-top: 10px;
}

.col-6  img{
    filter: opacity(50%); /*griser l'image*/
}

.rating{
    margin-left: 10px;
    font-style: italic;
    color: green;
}

.btn-edit{
    width: 130px;
    border-radius: 20px;
    color: white;
    background-color: black;
    margin-left: 40%;
    margin-right: 40px;
    margin-bottom: 80px;
}

.column{
    text-align: left;
}

</style>