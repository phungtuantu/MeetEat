<template>
<div class="container">
    <div class="row">
        <div class="col-6">
            <h2>Share your best dishes </h2>
            <p> Want to share your dish with someone interested by your cooking ?
                Want to make some money from your dishes ?
                Your search for a solution to not wasting food ?
            </p>
        </div>
        <div class="col-5">
            <div class="column">
                <img src="../../assets/dishes.jpg" width="300px"> <br>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <h2>How to become a MeetEat Cook </h2>
            <ul> <li> Read and accept general conditions (health and sanitary conditions to respect) </li>
                <li>Take photos of your kitchen and your equipment</li>
                <li>Send us those pictures</li>
                <li>Send picture of your identity card</li>
                <li>Send your chef certification if you have one</li>
                <li>Once we verify your informations, you will become a cook !</li>
            </ul>
        </div>
        <div class="col-5">
            <div class="column">
              <br>
              <img src="../../assets/chef.jpg" width="300px"> <br>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
        </div>
        <div class="col-5">
            <div class="column">
                <button class="btn btn-success" @click="becomeCook()">
                  Become a cook
                </button>
            </div>
        </div>
    </div>

</div>
</template>

<script>
import router from "@/router";
export default {
  name: "OnGoingOffers",
  methods: {
    becomeCook : function(){
      router.replace('/verificationCook');
    }
  },
}
</script>

<style scoped>
.container {
    width: 100%;
}

.col-6, .col-5 {
    margin-bottom: 50px;
}

.col-5 img{
    margin-left: 20px;
    margin-top: 50px;
}

.col-6{
    padding: 40px;
}

.btn{
    margin-top: -70px;
    margin-left: 40px;
    width: 200px;
    border-radius: 20px;
    color: white;
}

.column{
    text-align: left;
}

.col-6 li{
  text-align: left;
}

ul {
  list-style-type: square;
}
</style>