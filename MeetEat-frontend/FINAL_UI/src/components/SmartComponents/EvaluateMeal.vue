<template>
<div class="container mt-5 d-flex justify-content-center" v-if="reservation !== null">
    <div class="card p-4 mt-3">
        <div class = "container-fluid">
         <h4 class="heading">Evaluate your order</h4>
         <hr class="line-color">
         <br/>
         <div class = "row">
            <div class = "col-sm-3">
                <img v-bind:src="reservation.offer.offerPhoto" width="100%">
            </div>
            <div class = "col-sm-8">
                <div class="row">
                    <div class="col-7"><h6 class="heading">{{reservation.offer.title}}</h6></div>
                    <div class="date"><img src="../../assets/calendar.png" width="20px">{{reservation.reservationDate}}</div>
                </div>
                <p class="">{{reservation.offer.details}}</p>
                <div class="row">
                    <div class="col-4"> {{reservation.nbOfPortion}} portions</div>
                    <div class="col-1"> <img src="../../assets/point.png" width="10px"> </div>
                    <div class="col-3">{{reservation.offer.price * reservation.nbOfPortion}}$</div>
                </div>
            </div>
            <div class = "col-sm-1">
                <figure>
                    <a href="">
                        <img v-bind:src="reservation.cook.cookPhoto" width="50px" class=""> <!--class="rounded-circle mt-2"-->
                    </a>
                    <figcaption>{{reservation.cook.cookFirstName}} {{reservation.cook.cookLastName}}</figcaption>
                </figure>
            </div>
         </div>
      </div>

      <div class="container-fluid">
            <br/>
            <h4 class="heading">Rate your order out of 5</h4>
            <div class="rating"> 
                <input type="radio" name="rating" value="5" id="5" class="star">
                <label for="5">☆</label> 
                <input type="radio" name="rating" value="4" id="4" class="star">
                <label for="4">☆</label> 
                <input type="radio" name="rating" value="3" id="3" class="star">
                <label for="3">☆</label> 
                <input type="radio" name="rating" value="2" id="2" class="star">
                <label for="2">☆</label> 
                <input type="radio" name="rating" value="1" id="1" class="star">
                <label for="1">☆</label> 
            </div>
            <br/> <br/>
            <br/> <br/>
            <h4 class="heading">Leave a comment</h4>
            <div class="comment-area">
                <textarea class="form-control" placeholder=
                "How did the meal or meeting go (punctuality, politeness, etc.)? Would you recommend this member to the community?" 
                rows="4" v-model="comment"></textarea>
            </div>
            <div class="pull-right">
                <button class="btn btn-danger send btn-sm" @click="history()">Cancel
                    <i class="fa fa-long-arrow-right ml-1"></i>
                </button>
                <button class="btn btn-success send btn-sm" @click="send()">Send
                    <i class="fa fa-long-arrow-right ml-1"></i>
                </button> 
            </div>
        </div>
    </div>
</div>
</template>

<script>
import axios from "axios";
import {urlAPI} from "@/variables";
import router from "@/router";

export default {
  name: "EvaluateMeal",
  data(){
    return {
      idReservation : 258,
      reservation : null,
      cook : null,
      comment : '',
      nbStars : 0,
    }
  },

  methods: {
    history : function(){
      router.replace('/history');
    },
    send : async function () {

      var max = 0;
      var arr = document.getElementsByClassName("star");
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].checked) {
          if(arr[i].value > max){
            max = arr[i].value;
          }
        }
      }
      this.nbStars = max;

      if(this.comment ===''){
        this.comment = 'No comment'
      }

      console.log(arr);

      console.log(urlAPI + "todo=evaluateMeal&userId="+this.reservation.customerId+"&nbOfStars="+this.nbStars+"&comment="+this.comment+"&reservationId="+this.reservation.id);
      await axios.get(urlAPI + "todo=evaluateMeal&userId="+this.reservation.customerId+"&nbOfStars="+this.nbStars+"&comment="+this.comment+"&reservationId="+this.reservation.id);
      /**
       * evaluateMeal
       * Long userId = (Long)session.getAttribute("userId");
       *         int nbOfStars = Integer.parseInt(request.getParameter("nbOfStars"));
       *         String comment = request.getParameter("comment");
       *         Long sourceReservation_id = Long.parseLong(request.getParameter("reservation_Id"));
       */
      router.replace('/history')
    }
  },
  async mounted() {
    //viewReservationsList
    this.idReservation =   sessionStorage.getItem("idEvaluate")
    await axios.get(urlAPI+'todo=viewReservationDetails&reservationId='+this.idReservation)
    .then(response => (this.reservation = response.data))

    console.log(this.reservation);




  }
}
</script>


<style scoped>

.card {
    background-color: #B9F6CA;
    width: 700px;
    border-radius: 20px;
    
}

figure {
    display: inline-block;
    margin-right: 5%;
}

.heading {
    font-weight: 700;
    text-align:left;
}

/*Recentre la photo de profil*/
.col-sm-1{
    padding: 0;
}

.col-sm-8{
    text-align: left;
}

.line-color {
    border-color: black;
    margin: 0;
    height: 3px;   
}

.date img{
    margin-right: 10px;
}

.comment-area textarea {
    resize: none;
    border: 1px solid #ad9f9f
}

.form-control:focus {
    color: #495057;
    background-color: #fff;
    border-color: #ffffff;
    outline: 0;
    box-shadow: 0 0 0 1px rgb(255, 0, 0)
}

.pull-right{
    margin : 10px 0px -5px;
    text-align: right;
}

.pull-right .btn{
    width: 100px;
    border-radius: 15px;
}

.rating{
    padding: 0px;
    display: flex;
    margin-top: -10px;
    margin-left: 10px;
    flex-direction: row-reverse;
    float: left;
    height: 10%;
}

.label{
    width: 150px;
}

.rating>input {
    display: none
}

.rating>label {
    position: relative;
    font-size: 60px;
    /*width: 19px;*/
    color: #ff0000;
    cursor: pointer;
}

.rating>label::before {
    content: "\2605";
    position: absolute;
    opacity: 0;
    height: 10%;
}

.rating>label:hover:before,
.rating>label:hover~label:before {
    opacity: 1 !important
}

.rating>input:checked~label:before {
    opacity: 1
}

.rating:hover>input:checked~label:before {
    opacity: 0.4
}

.btn-danger{
    margin-right: 20px;
}

</style>