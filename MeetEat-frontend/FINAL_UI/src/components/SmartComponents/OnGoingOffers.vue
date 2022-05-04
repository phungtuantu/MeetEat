<template>
<div class="container" >
    <div class = "card" v-for="offer in offers" :key="offer.id">
        <template v-if= "offer.state === 'ONGOING'">
            <div class="row" >
                <div class="col-6">
                    <img v-bind:src= "offer.image" width="300px">
                </div>
                <div class="col-6">
                    <div class="column">
                        <label style="font-weight: bold; font-size: 20px">{{offer.title}}</label><br>
                        <label style="font-weight: bold">Expiration date : {{offer.expirationDate}}</label><br>
                        <label>Description : {{offer.details}}</label><br>
                        <label>Number of total servings : {{offer.totalPortion}}</label><br>
                        <label>Number of remaining servings : {{offer.remainingPortions}}</label><br>
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
                                Edit
                        </button>
                        <button class="btn btn-danger">
                            Cancel
                        </button> 
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-10">
                    <hr class="line-color">
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

.btn-edit{
    background-color: black;
    margin-left: 40%;
    margin-right: 40px;
}

.btn{
    width: 130px;
    border-radius: 20px;
    color: white;
}

.column{
    text-align: left;
}

</style>