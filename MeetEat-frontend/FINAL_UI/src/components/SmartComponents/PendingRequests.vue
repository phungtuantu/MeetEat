<template>
<div class="container" >
    <div class = "card" v-for="request in requests" :key="request.id">
        <div class="row">
                <div class="col-2">
                    <a href="">
                        <img v-bind:src= "request.offerImage"  width="100px" class="">
                    </a>
                </div>
                <div class="col-3">
                    <!-- <div class="column"> -->
                        <label style="font-weight: bold; font-size: 20px">{{request.offerTitle}}</label><br>
                        <label style="font-weight: bold"> {{request.offerPortionsLeft}} portions left</label><br>
                    <!-- </div> -->
                </div>
                <div class="col-sm">
                    <!-- <div class="column"> -->
                       <a href="">
                         <img v-bind:src= "request.customer.customerImage"  width="40px" class="">
                        </a>
                    <!-- </div> -->
                     <!-- <div class="column"> -->
                        <label style="font-weight: bold; font-size: 20px">{{request.customer.customerFirstName}}&nbsp;{{request.customer.customerLastName}} </label><br>
                        <label style=""> Number of portions :{{request.nbOfPortion}} </label><br>
                        <label style="font-weight: bold"> Total Price :{{request.totalPrice}}€</label><br>
                    <!-- </div> -->
                </div>
                <div class="col-2">
                    <div class="column">
                        <button class="btn btn-success" @click="acceptButton(request.id)">
                            Accept
                        </button>
                        <button class="btn btn-danger" @click="rejectButton(request.id)">
                            Reject
                        </button> 
                    </div>
                </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from "axios";
import {urlAPI} from "@/variables";

export default {
  name: "PendingRequests",

    data(){
      return{
          requests: [],
          requestAccepted: false,
          requestRejected: false,
      }
    },

   methods : {
        acceptButton: async function(requestId){
            await axios.get(urlAPI + 'todo=acceptRequest&requestId='+requestId)
                .then(response => (this.requestAccepted = response.requestAccepted));
                console.log(this.requestAccepted );
                location.reload();
        },

        rejectButton: async function(requestId){
            await axios.get(urlAPI + 'todo=rejectRequest&requestId='+requestId)
                .then(response => (this.requestRejected = response.requestRejected));
                console.log(this.requestRejected);
                location.reload();
        },
   },

    async mounted() {

        await axios.get(urlAPI + 'todo=viewReservationsRequests')
            .then(response => (this.requests = response.data.reservations));

        console.log(this.requests);

    }
}
</script>

<style scoped>
.container {
    width: 60%;
}

.col-sm{
    padding: 0;
    float: left;
    text-align: left;
}

.row{
   background-color: #e2e2e2;
   margin: 10px;
}

.row img{
  margin: 10px;
}

.col-6{
  text-align: left;
  margin-top: 10px;
}

.btn-success{
    float: center;
    margin-top: 15px;
    margin-bottom: 15px;
    border-radius: 15px;
}

.btn-danger{
    border-radius: 15px;
}

</style>