<template>
<div class="container">
    <div class="row">
        <div class="col-6">
            <img src="../../assets/couscous.jpg" width="300px">
        </div>
        <div class="col-6">
            <div class="column">
                <label style="font-weight: bold; font-size: 20px">Couscous !</label>
                <img src="../../assets/icons8-no-pork-50.png" width="30px"> <br>
                <label style="font-weight: bold">Expiration date : 16/04/2022</label><br>
                <label>Description : Lorem ipsum dolor sit amet, consectetur adpiscing elit.</label><br>
                <label>Number of offered serving : 3</label><br>
                <label>Number of sold serving : 2</label><br>
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
                        Edit
                </button>
                <button class="btn btn-danger">
                    Cancel
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