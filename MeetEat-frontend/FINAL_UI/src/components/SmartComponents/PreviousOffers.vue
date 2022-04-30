<template>
<div class="container">
    <div class="row">
        <div class="col-6">
            <img src="../../assets/lasagne.jpg" width="300px">
        </div>
        <div class="col-6">
            <div class="column">
                <label style="font-weight: bold; font-size: 20px">My mother's delicious lasagna !</label>
                <label class="rating" style="font-weight: bold">Rating : 4.2/5</label> <br>
                <label style="font-weight: bold">Expiration date : 04/04/2022</label><br>
                <label>Description : Lorem ipsum dolor sit amet, consectetur adpiscing elit.</label><br>
                <label>Number of sold servings : 5</label><br>
                <label style="font-weight: bold; font-size: 18px">Price : 5€</label> <br>
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

</div>
</template>

<script>
export default {
  name: "OnGoingOffers"
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