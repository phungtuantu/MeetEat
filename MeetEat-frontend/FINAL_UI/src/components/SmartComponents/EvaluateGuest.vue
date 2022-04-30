<template>
<div class="container mt-5 d-flex justify-content-center">
    <div class="card p-4 mt-3">
        <div class = "container-fluid">
        <h4 class="heading">Evaluate Ithan</h4>
         <hr class="line-color">
         <div class = "row">
            <div class = "col-sm-8">
                <div class="row">
                    <div class="col-7"><h6 class="heading">Peruvian Ceviche</h6></div>
                    <div class="date"><img src="../../assets/calendar.png" width="20px">23 September</div>
                </div>
                <div class="row">
                    <div class="col-4">1 portion</div>
                    <div class="col-1"> <img src="../../assets/point.png" width="10px"> </div>
                    <div class="col-3">6€</div>
                </div>
            </div>
            <div class = "col-sm-3">
                <figure>
                    <a href="">
                        <img src="../../assets/ithan.jpg" width="60px" class="rounded-circle mt-2"> <!--class="rounded-circle mt-2"-->
                    </a>
                    <figcaption>Ithan</figcaption>
                </figure>
            </div>
         </div>
      </div>

      <div class="container-fluid">
            <h4 class="heading">Rate your guest out of 5</h4>
            <div class="rating"> 
                <input type="radio" name="rating" value="5" id="5">
                <label for="5">☆</label> 
                <input type="radio" name="rating" value="4" id="4">
                <label for="4">☆</label> 
                <input type="radio" name="rating" value="3" id="3">
                <label for="3">☆</label> 
                <input type="radio" name="rating" value="2" id="2">
                <label for="2">☆</label> 
                <input type="radio" name="rating" value="1" id="1">
                <label for="1">☆</label> 
            </div>
            <br/> <br/>
            <br/> <br/>
            <h4 class="heading">Leave a comment</h4>
            <div class="comment-area">
                <textarea class="form-control" placeholder=
                "How did the meal or meeting go (punctuality, politeness, etc.)? Would you recommend this member to the community?" 
                rows="4"></textarea> 
            </div>
            <div class="pull-right"> 
                    <button class="btn btn-success send btn-sm">Send 
                        <i class="fa fa-long-arrow-right ml-1"></i>
                    </button> 
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
  name: "EvaluateGuest"
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
    background-color: black;
    margin: 0;
    /*height: 1px;*/   
}

.date{
    margin-left:20px;
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

</style>