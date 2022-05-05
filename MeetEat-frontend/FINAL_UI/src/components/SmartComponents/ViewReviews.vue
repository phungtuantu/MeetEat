<template>


  <div class="container-fluid px-1 py-5 mx-auto">

    <template v-if="reviews.hasReviews">
      <div class="row justify-content-center">
        <div class="col-xl-7 col-lg-8 col-md-10 col-12 text-center mb-5">
          <div class="card">
            <h2 class="left">{{user.firstName}}'s reviews</h2>
            <hr class="line-color">
            <div class="row justify-content-left d-flex">
              <div class="col-md-4 d-flex flex-column">
                <div class="rating-box">
                  <h1 class="pt-4">{{average.toFixed(1)}}</h1>
                  <p class="">out of 5</p>
                </div>
                <div>
                  <span class="fa fa-star star-active mx-1"></span>
                </div>
              </div>
              <div class="col-md-8">
                <div class="rating-bar0 justify-content-center">
                  <table class="text-left mx-auto">
                    <tr>
                      <td class="rating-label">Excellent</td>
                      <td class="rating-bar">
                        <div class="bar-container">
                          <div id="bar-5"></div>
                        </div>
                      </td>
                      <td class="text-right">{{ nbReviewsPerRank[4] }}</td>
                    </tr>
                    <tr>
                      <td class="rating-label">Very Good</td>
                      <td class="rating-bar">
                        <div class="bar-container">
                          <div id="bar-4"></div>
                        </div>
                      </td>
                      <td class="text-right">{{ nbReviewsPerRank[3] }}</td>
                    </tr>
                    <tr>
                      <td class="rating-label">Good</td>
                      <td class="rating-bar">
                        <div class="bar-container">
                          <div id="bar-3"></div>
                        </div>
                      </td>
                      <td class="text-right">{{ nbReviewsPerRank[2] }}</td>
                    </tr>
                    <tr>
                      <td class="rating-label">Correct</td>
                      <td class="rating-bar">
                        <div class="bar-container">
                          <div id="bar-2"></div>
                        </div>
                      </td>
                      <td class="text-right">{{ nbReviewsPerRank[1] }}</td>
                    </tr>
                    <tr>
                      <td class="rating-label">Incorrect</td>
                      <td class="rating-bar">
                        <div class="bar-container">
                          <div id="bar-1"></div>
                        </div>
                      </td>
                      <td class="text-right">{{ nbReviewsPerRank[0] }}</td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div class="" v-for="review in reviews.reviews" :key="review.id">
            <div class="card">
              <div class="row d-flex">
                <div class=""> <img class="profile-pic" v-bind:src="review.reviewingUser_photo"> </div>
                <div class="">
                  <h3 class="mt-2 mb-0">{{ review.reviewingUser_firstName }} {{ review.reviewingUser_lastName }}</h3>
                  <br/>
                  <div class="row">
                    <div class="col-md-4 d-flex flex-column">
                      <div class="rating-box2">
                        <h1 class="pt-4">{{review.nbOfStars.toFixed(1)}}</h1>
                        <p class="">out of 5</p>
                      </div>
                    </div>
                  </div>
                </div>

              </div>
              <div class="row text-left">
                <h4 class="blue-text mt-3">{{ remarks[review.nbOfStars-1] }}</h4>

              </div>
              <div class="row text-left">
                <p class="content">{{review.reversationDate}} <br/>{{review.comment}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </template>

 </div>
</template>

<script>
import {urlAPI} from "@/variables";
import axios from "axios";
export default {
  name: "ViewReviews",
  data(){
    return {
      user : null,
      reviews : null,
      average : 0,
      nbReviewsPerRank : [ 0, 0, 0, 0, 0],
      remarks : ["Incorrect", "Correct", "Good", "Very Good", "Excellent"],
    }
  },

  async mounted() {
    //userId
    //consultCooksReviews
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.user = this.user.user;
    console.log(this.user);
    await axios.get(urlAPI+'todo=consultCooksReviews&userId='+this.user.id)
    .then(response =>(this.reviews = response.data))
    console.log(this.reviews)

    var arr = this.reviews.reviews;
    for(let i=0; i<arr.length; i++){
      this.average += arr[i].nbOfStars;
      switch (arr[i].nbOfStars) {
        case 5 :
          this.nbReviewsPerRank[4]++;
          break;
        case 4 :
          this.nbReviewsPerRank[3]++;
          break;
        case 3 :
          this.nbReviewsPerRank[2]++;
          break;
        case 2 :
          this.nbReviewsPerRank[1]++;
          break;
        case 1 :
          this.nbReviewsPerRank[0]++;
          break;
      }
    }
    document.getElementById('bar-5').style.width = this.nbReviewsPerRank[4]/this.average * 100;
    document.getElementById('bar-4').style.width = this.nbReviewsPerRank[3]/this.average * 100;
    document.getElementById('bar-3').style.width = this.nbReviewsPerRank[2]/this.average * 100;
    document.getElementById('bar-2').style.width = this.nbReviewsPerRank[1]/this.average * 100;
    document.getElementById('bar-1').style.width = this.nbReviewsPerRank[0]/this.average * 100;

    this.average = this.average / arr.length;
  }
}
</script>

<style scoped>

.left {
    text-align: left;
}

.card {
    border-radius: 5px;
    background-color: #fff;
    padding-left: 60px;
    padding-right: 60px;
    margin-top: 30px;
    padding-top: 30px;
    padding-bottom: 30px
}

.rating-box {
    width: 130px;
    height: 130px;
    margin-right: auto;
    margin-left: auto;
    background-color: #FBC02D;
    color: #fff;
}

.rating-box2 {
  width: 400px;
  height: 130px;
  margin-right: auto;
  margin-left: auto;
  background-color: #FBC02D;
  color: #fff;
}

.rating-label {
    font-weight: bold
}

.rating-bar {
    width: 300px;
    padding: 8px;
    border-radius: 5px;
}

.bar-container {
    width: 100%;
    background-color: #f1f1f1;
    text-align: center;
    color: white;
    border-radius: 20px;
    margin-bottom: 5px
}

#bar-5 {
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

#bar-4 {
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

#bar-3 {
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

#bar-2 {
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

#bar-1 {
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

td {
    padding-bottom: 10px
}

.star-active {
  color: #FBC02D;
  font-size: 40px;
  margin: 0;
  padding: 0;
}

.star-inactive {
  color: #CFD8DC;
  margin: 0;
  padding: 0;
  font-size: 40px;
}

.blue-text {
  color: #0091EA;
  /*padding: 0;*/
}

.content {
  font-size: 18px
}

.profile-pic {
  width: 90px;
  height: 90px;
  border-radius: 100%;
  margin-right: 30px
}

div.col-10, div.col-2{
  padding: 0;
}

h4.text-note{
  margin-top: 15px;
}

</style>