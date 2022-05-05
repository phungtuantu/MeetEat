<template>
  <div class="container-fluid px-1 py-5 mx-auto">
    <div class="row justify-content-center">
        <div class="col-xl-7 col-lg-8 col-md-10 col-12 text-center mb-5">
            <div class="card">
              <h2 class="left">Rosine's reviews</h2>
              <hr class="line-color">
                <div class="row justify-content-left d-flex">
                    <div class="col-md-4 d-flex flex-column">
                        <div class="rating-box">
                            <h1 class="pt-4">4.0</h1>
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
                                            <div class="bar-5"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">15</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">Very Good</td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-4"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">23</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">Good</td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-3"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">10</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">Correct</td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-2"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">3</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">Incorrect</td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-1"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">0</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="">
              <CardReview/>
            </div>
            <div class="">
              <CardReview/>
            </div>
            <div class="">
              <CardReview/>
            </div>
      </div>
  </div>
 </div>
</template>

<script>
import CardReview from '@/components/SmartComponents/CardReview.vue'
import {urlAPI} from "@/variables";
import axios from "axios";
export default {
  name: "ViewReviews",
  data(){
    return {
      user : null,
    }
  },
  components: {
    CardReview,
  },
  async mounted() {
    //userId
    //consultCooksReviews
    this.user = JSON.parse(sessionStorage.getItem("user"));
    this.user = this.user.user;
    await axios.get(urlAPI+'todo=consultCooksReviews&userId='+this.user.id);

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

.bar-5 {
    width: 30%;
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

.bar-4 {
    width: 50%;
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

.bar-3 {
    width: 20%;
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

.bar-2 {
    width: 10%;
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

.bar-1 {
    width: 0%;
    height: 13px;
    background-color: #FBC02D;
    border-radius: 20px
}

td {
    padding-bottom: 10px
}

</style>