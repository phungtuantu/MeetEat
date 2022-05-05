<template>
<div>
  <h1>Previous orders</h1>
  <br/>
  <br/>
  <div v-for="order in orders" :key="order.title" style="margin-bottom: 30px; padding-left: 30px; padding-right: 30px;" >
    <div class="row">
      <div class="col-sm">
        <img src="../../assets/lasagne.jpg" height="200px">
      </div>
      <div class="col-sm" style="text-align: left">
        <div class="row">
          <div class="col-sm-6">
            <b>{{order.title}}</b> <br/>

          </div>
          <div class="col-sm-4">
            <b>{{order.dateDelivery}}</b> <br/>
          </div>
        </div>
        <p>
          {{order.description}} <br/>
        </p>
        <p v-for="option in order.options" :key="option">
          {{option}}
        </p>
        <div class="row">
          <div class="col-sm">
            {{order.nbPortions}} portions
          </div>
          <div class="col-sm">
            {{order.nbPortions * order.price}}$
          </div>
        </div>
      </div>

      <div class="col-sm-1">
        <img src="../../assets/ithan.jpg" width="100px" height="80px"/>
        <p>{{ order.username }}</p>
      </div>


      <div class="col-sm">
        <span  v-if="order.evaluated === 0">
           <button class="btn btn-success" @click="evaluate()" style="margin-bottom: 10px;"> Evaluate </button>
          <br/>
        </span>

        <button class="btn btn-danger" @click="report()"> Report </button>
      </div>
    </div>
    <hr class="my-4"/>


  </div>

</div>
</template>

<script>
import router from "@/router";
import {urlAPI} from "@/variables";
import axios from "axios";

export default {
  name: "Historic",
  data() {
    return {
      orders : [
        {
          title : 'Delicious lasagna   !',
          nbPortions : 1,
          description : 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
          dateOrdered : '01/01/2022',
          dateDelivery : '02/04/2022',
          options : [ 'noPork'],
          price : 5,
          evaluated : 0,
          report : 0,
          username : 'Ithan',

        },
        {
          title : 'Couscous',
          nbPortions : 5,
          description : 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
          dateOrdered : '01/01/2022',
          dateDelivery : '01/04/2022',
          options : [],
          price : 7,
          evaluated : 0,
          report : 0,
          username : 'Ithan',

        },
        {
          title : 'Mexican tacos',
          nbPortions : 3,
          description : 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
          dateOrdered : '01/01/2022',
          dateDelivery : '28/03/2022',
          options : [ 'noPork'],
          price : 4,
          evaluated : 1,
          report : 0,
          username : 'Ithan',

        },
        {
          title : 'Peruvian ceviche',
          nbPortions : 1,
          description : 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
          dateOrdered : '01/01/2022',
          dateDelivery : '21/03/2022',
          options : [ 'pescoVegetarian'],
          price : 6,
          evaluated : 1,
          report : 0,
          username : 'Ithan',

        },
      ],

      user: null,

    }
  },

  methods : {
    evaluate : function ()
    {
      console.log('evaluate');
      router.replace('/evaluatemeal');
    },

    report : function ()
    {

      router.replace('/reportcook');

    },
  },

  async mounted() {
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.user = this.user.user;
    //viewOffersHistory
    await axios.get(urlAPI + "todo=viewOffersHistory&userId=" + 3)//this.user.id)
        .then(response => (this.orders = response.data));

    console.log(this.orders);
  }


}
</script>

<style scoped>

</style>