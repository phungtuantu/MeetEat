<template>
  <div>
    <div style="text-align: center">
      <h1>Basket</h1>
    </div>

    <div class="row">
      <div class="col-sm-7">
        <h2>My shopping list ( {{totalQty}} product)</h2>
      </div>
      <div class="col-sm">
        <button type="button" class="btn btn-dark" @click="backHome()">Continue my shopping </button>
      </div>
    </div>

    <br/>
    <br/>
    <br/>


    <div class="row">
      <div class="col-sm">
        <div class="card border border-secondary" v-for="order in orders" :key="order.order.id" style="background-color: lightgray">
          <img class="card-img-top"
               v-bind:src="order.order.image" style="padding-left: 5px;"/>
          <div class="card-body" style="text-align: left;">
            <h5 class="card-title">{{order.order.title}}</h5>
            <p class="card-text">
              <b>Delivery : {{order.order.availableFrom}}-{{order.order.expirationDate}} </b>
            </p>
            <!--
            <p class="card-text" v-for="option in order.order.classifications" :key="option">
              {{option.name}}
            </p>
            -->
            <p class="card-text">
              {{order.order.details}}
            </p>
            <div class="row">
              <div class="col-sm">
                <p class="card-text">
                  <b>{{order.order.price * order.qty}}$</b>
                </p>
              </div>
              <div class="col-sm">
                <p class="card-text">
                  <b>Number of portions : {{order.qty}}/{{order.order.remainingPortions}}</b>
                </p>
              </div>
            </div>

            <br/>
            <div class="input-group w-auto">
              <button class="btn btn-dark" type="button" @click="viewDetails(order.order.id)">View details</button>
              <button class="btn btn-dark" type="button" @click="deleteItem(order)">Delete item</button>
            </div>
          </div>
        </div>

        <br/>
      </div>
      <div class="col-sm border border-secondary rounded" style="background-color: lightgray; padding: 15px;">
          <h3 >Summary</h3>
          <hr class="my-4"/>
          <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between align-items-center" v-for="order in orders" :key="order.order.id">
              {{ order.order.title }} <br/>
              <span class="badge badge-primary badge-pill">Number of portions {{ order.qty }}</span>
            </li>
          </ul>
        <hr class="my-4"/>

        <div class="row">
          <div class="col-sm">
            <h4>Total</h4>
          </div>
          <div class="col-sm">
              {{totalPrice}}$
          </div>
        </div>
      </div>

      <br/>
      <br/>

    </div>

    <div class="container p-0" >
      <p class="h8 py-3">Payment Details</p>

      <div class="card px-4">
        <div class="row gx-3">
          <div class="col-12">
            <div class="d-flex flex-column">
              <p class="text mb-1">Person Name</p> <input class="form-control mb-3" type="text" placeholder="Name" v-model="name">
            </div>
          </div>
          <div class="col-12">
            <div class="d-flex flex-column">
              <p class="text mb-1">Card Number</p> <input class="form-control mb-3" type="text" placeholder="1234 5678 435678" v-model="cardNumber">
            </div>
          </div>
          <div class="col-6">
            <div class="d-flex flex-column">
              <p class="text mb-1">Expiry</p> <input class="form-control mb-3" type="text" placeholder="MM/YYYY" v-model="expiry">
            </div>
          </div>
          <div class="col-6">
            <div class="d-flex flex-column">
              <p class="text mb-1">CVV/CVC</p> <input class="form-control mb-3 pt-2 " type="password" placeholder="***" v-model="cvv">
            </div>
          </div>
          <div class="col-12">
            <button class="btn btn-primary mb-3" @click="pay()">
              <span class="ps-3">Pay {{totalPrice}}$</span> <span class="fas fa-arrow-right"></span> </button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";
import {urlAPI} from "@/variables";

export default {
  name: "ResumeOrder",
  data() { return {
    orders : [],
    totalQty : 0,
    totalPrice : 0,
    name : '',
    cardNumber : '',
    expiry : '',
    cvv : '',
    user : '',

  }
  },
  methods : {
    viewDetails : function (id)
    {
      router.replace('/orderPage/'+id)
    },

    deleteItem : function (order)
    {
      this.totalQty -= parseInt(order.qty)
      this.totalPrice -= parseFloat(order.qty*order.order.price)
      console.log(order.qty)
      console.log(order.price)
      this.orders.splice(this.orders.indexOf(order),1);
      sessionStorage.setItem("basket", JSON.stringify(this.orders));
    },

    pay : async function () {
      //createReservation
      console.log('pay');
      for(let i=0; i<this.orders.length; i++){
        let order = this.orders[i].order;
        let qty = this.orders[i].qty;
        await axios.get(urlAPI + 'todo=createReservation&offerId='+order.id+'&nbOfPortions='+qty)
            .then(response => (response.data));
      }
      sessionStorage.setItem("basket", JSON.stringify([]));
      router.replace('/orderPage');

    },

    backHome : function ()
    {
      router.replace('/orderPage')

    },
  },
  async mounted() {
    var arr = JSON.parse(sessionStorage.getItem("basket"));
    if (arr !== null) {
      for (let i = 0; i < arr.length; i++) {
        // console.log(arr[i]);
        var element = null;
        await axios.get(urlAPI + 'todo=consultOffer&offerId=' + arr[i].order.id)
            .then(response => (element = response.data));

        this.orders.push({
          order : element,
          qty : arr[i].qty
        });

        this.totalPrice += (arr[i].qty * element.price);
        this.totalQty += parseInt(arr[i].qty);
      }

      // console.log(this.orders);
      this.user = JSON.parse(sessionStorage.getItem("user"));
      // console.log(JSON.parse(sessionStorage.getItem("user")));
    }
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


@import url('https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Montserrat', sans-serif
}

body {
  display: flex;
  min-height: 100vh;
  background-color: #0C4160;
  padding: 30px 10px
}

.card {
  max-width: 500px;
  margin: auto;
  color: black;
  border-radius: 20px;
}

p {
  margin: 0px
}

.container .h8 {
  font-size: 30px;
  font-weight: 800;
  text-align: center
}

.btn.btn-primary {
  width: 100%;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 15px;
  background-image: linear-gradient(to right, #77A1D3 0%, #79CBCA 51%, #77A1D3 100%);
  border: none;
  transition: 0.5s;
  background-size: 200% auto
}

.btn.btn.btn-primary:hover {
  background-position: right center;
  color: #fff;
  text-decoration: none
}

.btn.btn-primary:hover .fas.fa-arrow-right {
  transform: translate(15px);
  transition: transform 0.2s ease-in
}

.form-control {
  color: white;
  background-color: #223C60;
  border: 2px solid transparent;
  height: 60px;
  padding-left: 20px;
  vertical-align: middle
}

.form-control:focus {
  color: white;
  background-color: #0C4160;
  border: 2px solid #2d4dda;
  box-shadow: none
}

.text {
  font-size: 14px;
  font-weight: 600
}

::placeholder {
  font-size: 14px;
  font-weight: 600
}
</style>